package lpka.prj.crystallis.domain.symbol.session.process;

import lpka.prj.crystallis.domain.symbol.data.SymbolData;
import lpka.prj.crystallis.domain.symbol.session.process.models.TrainingProcessSymbolModel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SymbolTrainingProcess {
    private final SymbolData data;

    private final List<String> selectedSymbols;
    private final Map<String, TrainingProcessSymbolModel> symbolModelMap;
    private final LinkedList<Integer> callLog;
    private String currentSymbol;
    private boolean allSymbolsUsed = false;

    public SymbolTrainingProcess(SymbolData data) {
        this.data = data;
        this.symbolModelMap = new HashMap<>();
        this.selectedSymbols = new ArrayList<>();
        this.callLog = new LinkedList<>();
    }

    public void setSelectedSymbolsStrings(Set<String> symbolsStrings) {
        selectedSymbols.clear();
        symbolModelMap.clear();
        callLog.clear();
        currentSymbol = null;
        allSymbolsUsed = false;

        selectedSymbols.addAll(symbolsStrings.stream()
                .flatMap(str -> Stream.of(str.split(",")))
                .collect(Collectors.toList()));

        data.getSymbolModelsBySymbols(selectedSymbols).forEach(symbolModel -> {
            symbolModelMap.put(symbolModel.getSymbol(),
                    TrainingProcessSymbolModel.of(symbolModel.getSymbol(), symbolModel.getRomaji(), symbolModel.getType()));
        });
    }

    public boolean checkAnswer(String answer) {
        return symbolModelMap.get(currentSymbol).checkAnswer(answer);
    }

    public String findRandomSymbol() {
        if (callLog.size() <= symbolModelMap.size() / 2) {
            while (true) {
                String randomSymbol = selectedSymbols.get(generateRandomNumber(selectedSymbols.size(), 0));
                if (currentSymbol == null || !currentSymbol.equals(randomSymbol)) {
                    currentSymbol = randomSymbol;
                    break;
                }
            }
        } else if (!allSymbolsUsed) {
            List<String> notUsedSymbols = findNotUsedSymbols();
            for (int counter = 0; counter < 100; counter++) {
                String randomSymbol = notUsedSymbols.get(generateRandomNumber(notUsedSymbols.size(), 0));
                if (!currentSymbol.equals(randomSymbol)) {
                    currentSymbol = randomSymbol;
                    break;
                }
            }
        } else {
            List<String> rareSymbols = findRareSymbols();
            for (int counter = 0; counter < 100; counter++) {
                String randomSymbol = rareSymbols.get(generateRandomNumber(rareSymbols.size(), 0));
                if (!currentSymbol.equals(randomSymbol)) {
                    currentSymbol = randomSymbol;
                    break;
                }
            }
        }

        if (!callLog.isEmpty() && selectedSymbols.get(callLog.getLast()).equals(currentSymbol)) {
            currentSymbol = selectedSymbols.get(generateRandomNumber(selectedSymbols.size(), 0));
        }
        callLog.add(selectedSymbols.indexOf(currentSymbol));
        symbolModelMap.get(currentSymbol).incrementCalled();
        return currentSymbol;
    }

    private List<String> findRareSymbols() {
        int min = symbolModelMap.values()
                .stream()
                .mapToInt(TrainingProcessSymbolModel::getCalled)
                .min()
                .orElse(0);

        return symbolModelMap.values()
                .stream()
                .filter(model -> model.getCalled() <= min + 1)
                .map(TrainingProcessSymbolModel::getSymbol)
                .collect(Collectors.toList());
    }

    private List<String> findNotUsedSymbols() {
        List<String> result = symbolModelMap.values()
                .stream()
                .filter(model -> model.getCalled() == 0)
                .map(TrainingProcessSymbolModel::getSymbol)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            allSymbolsUsed = true;
            return findRareSymbols();
        }

        return result;
    }

    private int generateRandomNumber(int max, int min) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
