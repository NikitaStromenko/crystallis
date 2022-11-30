package lpka.prj.crystallis.view.symbol;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lpka.prj.crystallis.domain.symbol.session.SymbolSession;
import lpka.prj.crystallis.view.symbol.pages.impl.MenuViewPageImpl;
import lpka.prj.crystallis.view.symbol.pages.impl.TrainingViewPageImpl;

@Route("")
public class SymbolViewPage extends VerticalLayout {
    private final SymbolSession session;
    private final MenuViewPageImpl menuPage;
    private final TrainingViewPageImpl trainingPage;

    public SymbolViewPage(SymbolSession session) {
        this.session = session;
        this.menuPage = new MenuViewPageImpl(session);
        this.trainingPage = new TrainingViewPageImpl(session);

        init();
    }

    private void init() {
        add(
                trainingPage,
                menuPage
        );
        initStyles();
    }

    private void initStyles() {
        setAlignItems(Alignment.CENTER);
    }
}
