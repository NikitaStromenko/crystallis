package lpka.prj.crystallis.domain.symbol.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lpka.prj.crystallis.domain.symbol.classification.SymbolType;

@AllArgsConstructor(staticName = "of")
@Data
public class SymbolModel {
    private String symbol;
    private String romaji;
    private Integer str;
    private SymbolType type;
}
