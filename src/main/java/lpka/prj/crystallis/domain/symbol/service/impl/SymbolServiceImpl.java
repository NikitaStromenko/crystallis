package lpka.prj.crystallis.domain.symbol.service.impl;

import lpka.prj.crystallis.domain.symbol.classification.SymbolType;
import lpka.prj.crystallis.domain.symbol.data.SymbolData;
import lpka.prj.crystallis.domain.symbol.models.SymbolModel;
import lpka.prj.crystallis.domain.symbol.service.SymbolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SymbolServiceImpl implements SymbolService {
    private final SymbolData data;

    public SymbolServiceImpl() {
        this.data = new SymbolData();
    }

    @Override
    public Set<SymbolType> getAllSymbolTypes() {
        return data.getAllSymbolTypes();
    }

    @Override
    public Map<SymbolType, List<SymbolModel>> findSymbolModelsByTypes(Set<SymbolType> types) {
        return data.getSymbolModelsByTypes(types);
    }

    @Override
    public List<SymbolModel> findSymbolModelsBySymbols(List<String> strings) {
        return null;
    }
}
