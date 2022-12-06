package lpka.prj.crystallis.view.symbol.pages.impl;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import lpka.prj.crystallis.domain.commons.session.input.SessionApplyInput;
import lpka.prj.crystallis.domain.symbol.components.SymbolViewComponents;
import lpka.prj.crystallis.domain.symbol.session.SymbolSession;
import lpka.prj.crystallis.view.symbol.pages.TrainingView;
import org.apache.commons.lang3.StringUtils;

public class TrainingViewPageImpl extends TrainingView {
    private final SymbolSession session;
    private final SessionApplyInput<SymbolViewComponents> textFieldApplyInput;

    public TrainingViewPageImpl(SymbolSession session) {
        this.session = session;

        textFieldApplyInput = SessionApplyInput.of(
                SymbolViewComponents.TRAINING_TEXT_FIELD,
                (Object data) -> {
                    String question = (String) data;
                    label.setText(question);
                },
                null
        );

        session.setStartTrainingCallback((Boolean bol) -> {
            setVisible(bol);
            if (bol)
                session.apply(textFieldApplyInput);
        });
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
        label.getStyle().set("font-size", "7rem");
    }

    @Override
    protected void initListeners() {
        textField.addKeyDownListener(Key.ENTER, event -> {
            String value = textField.getValue();
            if (StringUtils.isNotBlank(value)) {
                textFieldApplyInput.setData(value);
                session.apply(textFieldApplyInput);
                textField.clear();
            }
        });
    }

    @Override
    protected void initData() {
        setVisible(false);
    }
}
