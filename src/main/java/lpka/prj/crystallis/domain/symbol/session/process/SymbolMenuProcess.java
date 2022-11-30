package lpka.prj.crystallis.domain.symbol.session.process;

import lpka.prj.crystallis.domain.symbol.data.SymbolData;
import lpka.prj.crystallis.domain.symbol.data.model.SymbolModel;
import lpka.prj.crystallis.domain.symbol.data.type.SymbolType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SymbolMenuProcess {
    private final SymbolData data;
    private Set<SymbolType> selectedTypes;
    private List<String> selectedSymbols;

    public SymbolMenuProcess(SymbolData data) {
        this.data = data;
    }

    public List<String> selectTypes() {
        return data.getAllSymbolTypes().stream().map(Enum::name).collect(Collectors.toList());
    }

    public List<String> selectSymbolJoinedByStr(Set<SymbolType> types) {
        selectedTypes = Set.copyOf(types);
        Map<SymbolType, List<SymbolModel>> models = data.getSymbolModelsByTypes(types);

        return models.keySet()
                .stream()
                .flatMap(key -> models.get(key)
                        .stream()
                        .map(SymbolModel::getStr)
                        .distinct()
                        .map(str -> joinSymbolsByString(models.get(key), str)))
                .collect(Collectors.toList());
    }

    public void saveSelectedSymbols(List<String> symbols) {
        this.selectedSymbols = List.copyOf(symbols);
    }

    public Set<String> getSelectedTypes() {
        if (selectedTypes != null && !selectedTypes.isEmpty()) {
            return selectedTypes.stream().map(Enum::name).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    public List<String> getSelectedSymbols() {
        if (selectedSymbols != null && !selectedSymbols.isEmpty()) {
            return selectedSymbols;
        }
        return Collections.emptyList();
    }

    private String joinSymbolsByString(List<SymbolModel> models, Integer str) {
        return models.stream()
                .filter(symbolModel -> symbolModel.getStr().equals(str))
                .map(SymbolModel::getSymbol)
                .collect(Collectors.joining(","));
    }
}
