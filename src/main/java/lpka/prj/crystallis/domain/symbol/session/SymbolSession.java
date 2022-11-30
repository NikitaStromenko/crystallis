package lpka.prj.crystallis.domain.symbol.session;

import lpka.prj.crystallis.domain.commons.session.Session;
import lpka.prj.crystallis.domain.commons.session.input.SessionApplyInput;
import lpka.prj.crystallis.domain.symbol.components.SymbolViewComponents;
import lpka.prj.crystallis.domain.symbol.data.SymbolData;
import lpka.prj.crystallis.domain.symbol.data.type.SymbolType;
import lpka.prj.crystallis.domain.symbol.session.process.SymbolMenuProcess;
import lpka.prj.crystallis.domain.symbol.stage.SymbolViewStages;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class SymbolSession implements Session<SymbolViewComponents> {
    private final SymbolData data;
    private SymbolViewStages stage;
    private final SymbolMenuProcess menuProcess;

    public SymbolSession() {
        this.data = new SymbolData();
        this.stage = SymbolViewStages.SELECT_TYPES;
        this.menuProcess = new SymbolMenuProcess(data);
    }

    @Override
    public void nextStage(List<SessionApplyInput<SymbolViewComponents>> inputs) {
        switch (stage) {
            case SELECT_TYPES:
                stage = SymbolViewStages.SELECT_STRINGS;
                inputs.forEach(this::apply);
                break;
        }
    }

    @Override
    public void backStage() {
        switch (stage) {
            case SELECT_STRINGS:
                stage = SymbolViewStages.SELECT_TYPES;
                break;
        }
    }

    @Override
    public void apply(SessionApplyInput<SymbolViewComponents> input) {
        Consumer<Object> cons = input.getCons();
        switch (input.getEnumeration()) {
            case MENU_LABEL:
                if (stage.equals(SymbolViewStages.SELECT_TYPES)) {
                    cons.accept("Choose types");
                } else if (stage.equals(SymbolViewStages.SELECT_STRINGS)) {
                    cons.accept("Choose symbols");
                } else {
                    cons.accept("");
                }
                break;
            case MENU_SELECT_BOX:
                if (stage.equals(SymbolViewStages.SELECT_TYPES)) {
                    cons.accept(menuProcess.selectTypes());
                } else if (stage.equals(SymbolViewStages.SELECT_STRINGS)) {
                    Set<String> types = (Set<String>) input.getData();
                    cons.accept(menuProcess.selectSymbolJoinedByStr(types.stream().map(SymbolType::valueOf).collect(Collectors.toSet())));
                } else {
                    cons.accept(null);
                }
                break;
            case MENU_BACK_BUTTON:
                cons.accept(!stage.equals(SymbolViewStages.SELECT_TYPES));
        }
    }
}
