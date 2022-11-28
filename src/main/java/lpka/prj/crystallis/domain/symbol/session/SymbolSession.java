package lpka.prj.crystallis.domain.symbol.session;

import lombok.RequiredArgsConstructor;
import lpka.prj.crystallis.domain.symbol.classification.SymbolType;
import lpka.prj.crystallis.domain.symbol.models.SymbolModel;
import lpka.prj.crystallis.domain.symbol.service.SymbolService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SymbolSession {
    private final SymbolService symbolService;
    private Stages stage = Stages.SELECT_TYPES;

    public Set<SymbolType> getAllSymbolTypes() {
        return symbolService.getAllSymbolTypes();
    }

    public Collection<?> nextStage(Collection<?> data) {
        switch (stage) {
            case SELECT_TYPES:
                if (data != null && !data.isEmpty()) {
                    stage = Stages.SELECT_STRINGS;
                    return findSymbolStringsByTypes(data.stream()
                            .map(type -> SymbolType.valueOf((String) type))
                            .collect(Collectors.toSet()));
                }
        }
        return null;
    }

    public String getTextForMenuLabel() {
        switch (stage) {
            case SELECT_TYPES:
                return "Choose types";
            case SELECT_STRINGS:
                return "Choose symbols";
        }
        return null;
    }

    private List<String> findSymbolStringsByTypes(Set<SymbolType> types) {
        Map<SymbolType, List<SymbolModel>> modelsByTypes = symbolService.findSymbolModelsByTypes(types);

        return modelsByTypes.keySet()
                .stream()
                .flatMap(key -> modelsByTypes.get(key)
                        .stream()
                        .map(SymbolModel::getStr)
                        .distinct()
                        .map(str -> joinSymbolsByString(modelsByTypes.get(key), str)))
                .collect(Collectors.toList());
    }

    private String joinSymbolsByString(List<SymbolModel> models, Integer str) {
        return models.stream()
                .filter(symbolModel -> symbolModel.getStr().equals(str))
                .map(SymbolModel::getSymbol)
                .collect(Collectors.joining(","));
    }
}
