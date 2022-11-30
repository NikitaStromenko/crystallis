package lpka.prj.crystallis.domain.symbol.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lpka.prj.crystallis.domain.symbol.data.type.SymbolType;

@AllArgsConstructor(staticName = "of")
@Getter
public class SymbolModel {
    private String symbol;
    private String romaji;
    private Integer str;
    private SymbolType type;
}
