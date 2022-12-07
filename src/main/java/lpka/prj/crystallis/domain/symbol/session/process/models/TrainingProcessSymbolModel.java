package lpka.prj.crystallis.domain.symbol.session.process.models;

import lombok.Getter;
import lpka.prj.crystallis.domain.symbol.data.type.SymbolType;
@Getter
public class TrainingProcessSymbolModel {
    private final String symbol;
    private final String romaji;
    private final SymbolType type;
    private int called = 0;
    private int rightAnswers = 0;
    private int wrongAnswers = 0;

    public static TrainingProcessSymbolModel of(String symbol, String romaji, SymbolType type) {
        return new TrainingProcessSymbolModel(symbol, romaji, type);
    }

    public TrainingProcessSymbolModel(String symbol, String romaji, SymbolType type) {
        this.symbol = symbol;
        this.romaji = romaji;
        this.type = type;
    }

    public void incrementCalled() {
        called++;
    }

    public boolean checkAnswer(String answer) {
        if (romaji.equals(answer)) {
            rightAnswers++;
            return true;
        } else {
            wrongAnswers++;
            return false;
        }
    }
}
