package lpka.prj.crystallis.domain.symbol.session;

import lpka.prj.crystallis.domain.symbol.classification.SymbolType;
import lpka.prj.crystallis.domain.symbol.models.SymbolModel;
import lpka.prj.crystallis.domain.symbol.service.SymbolService;
import lpka.prj.crystallis.domain.symbol.session.models.ComponentKeys;
import lpka.prj.crystallis.domain.symbol.session.models.SessionDataByStage;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SymbolSession {
    private final SymbolService symbolService;
    private Stages stage = Stages.SELECT_TYPES;
    private final SessionDataByStage dataByStage;

    public SymbolSession(SymbolService symbolService) {
        this.symbolService = symbolService;
        dataByStage = SessionDataByStage.of(
                Map.of(
                        ComponentKeys.MENU_LABEL, Collections.singletonList("Choose types"),
                        ComponentKeys.MENU_SELECT_BOX, getAllSymbolTypes(),
                        ComponentKeys.MENU_BACK_BUTTON, Collections.singletonList(false)
                ));
    }

    public <T> T getStageDataByKey(ComponentKeys key, T dataContainer) {
        return dataByStage.getData(key, dataContainer);
    }

    public void nextStage(Collection<?> data) {
        switch (stage) {
            case SELECT_TYPES:
                if (data != null && !data.isEmpty()) {
                    stage = Stages.SELECT_STRINGS;
                    List<String> symbolStrings = findSymbolStringsByTypes(data.stream()
                            .map(type -> SymbolType.valueOf((String) type))
                            .collect(Collectors.toSet()));

                    setDataByStage(symbolStrings);
                }
        }
    }

    public void backStage() {
        switch (stage) {
            case SELECT_STRINGS:
                stage = Stages.SELECT_TYPES;

                setDataByStage(Collections.emptyList());
        }
    }

    private void setDataByStage(List<String> data) {
        switch (stage) {
            case SELECT_TYPES:
                dataByStage.clearAndPutNew(
                        Map.of(
                                ComponentKeys.MENU_SELECT_BOX, getAllSymbolTypes(),
                                ComponentKeys.MENU_LABEL, Collections.singletonList("Choose types"),
                                ComponentKeys.MENU_BACK_BUTTON, Collections.singletonList(false)
                        )
                );
                break;
            case SELECT_STRINGS:
                dataByStage.clearAndPutNew(
                        Map.of(
                                ComponentKeys.MENU_SELECT_BOX, data,
                                ComponentKeys.MENU_LABEL, Collections.singletonList("Choose symbols"),
                                ComponentKeys.MENU_BACK_BUTTON, Collections.singletonList(true)
                        ));
        }
    }

    private Set<String> getAllSymbolTypes() {
        return symbolService.getAllSymbolTypes()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toSet());
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
