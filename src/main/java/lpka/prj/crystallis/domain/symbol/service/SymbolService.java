package lpka.prj.crystallis.domain.symbol.service;

import lpka.prj.crystallis.domain.symbol.classification.SymbolType;
import lpka.prj.crystallis.domain.symbol.models.SymbolModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SymbolService {
    Set<SymbolType> getAllSymbolTypes();
    Map<SymbolType, List<SymbolModel>> findSymbolModelsByTypes(Set<SymbolType> types);
    List<SymbolModel> findSymbolModelsBySymbols(List<String> symbols);
}
