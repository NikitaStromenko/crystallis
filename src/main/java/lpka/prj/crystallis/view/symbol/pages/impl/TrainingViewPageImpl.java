package lpka.prj.crystallis.view.symbol.pages.impl;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import lpka.prj.crystallis.domain.symbol.session.SymbolSession;
import lpka.prj.crystallis.view.symbol.pages.TrainingView;

public class TrainingViewPageImpl extends TrainingView {
    private final SymbolSession session;

    public TrainingViewPageImpl(SymbolSession session) {
        this.session = session;
        init();
    }

    @Override
    protected void init() {
        label = new Label();
        textField = new TextField();

        add(
                label,
                textField
        );

        initStyles();
        initListeners();
        initData();
    }

    @Override
    protected void initStyles() {
        setAlignItems(Alignment.CENTER);
    }

    @Override
    protected void initListeners() {
        textField.addKeyDownListener(Key.ENTER, event -> {
            String value = textField.getValue();

        });
    }

    @Override
    protected void initData() {
        setVisible(false);
    }
}
