package lpka.prj.crystallis.view.symbol.pages.impl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lpka.prj.crystallis.domain.commons.session.input.SessionApplyInput;
import lpka.prj.crystallis.domain.symbol.components.SymbolViewComponents;
import lpka.prj.crystallis.domain.symbol.session.SymbolSession;
import lpka.prj.crystallis.view.symbol.pages.MenuView;

import java.util.List;
import java.util.Set;

public class MenuViewPageImpl extends MenuView {
    private final SymbolSession session;
    private final SessionApplyInput<SymbolViewComponents> labelApplyInput;
    private final SessionApplyInput<SymbolViewComponents> selectBoxApplyInput;
    private final SessionApplyInput<SymbolViewComponents> backButtonApplyInput;

    public MenuViewPageImpl(SymbolSession session) {
        this.session = session;

        labelApplyInput = SessionApplyInput.of(
                SymbolViewComponents.MENU_LABEL,
                (Object data) -> {
                    String strData = ((String) data);
                    menuLabel.setText(strData);
                },
                null);
        selectBoxApplyInput = SessionApplyInput.of(
                SymbolViewComponents.MENU_SELECT_BOX,
                (Object data) -> {
                    List<String> listData = (List<String>) data;
                    menuSelectBox.setItems(listData);
                },
                null
        );
        backButtonApplyInput = SessionApplyInput.of(
                SymbolViewComponents.MENU_BACK_BUTTON,
                (Object data) -> {
                    Boolean bol = (Boolean) data;
                    menuBackButton.setVisible(bol);
                },
                null
        );

        session.setCloseMenuCallback((Boolean bol) -> {
            menuLabel.setVisible(!bol);
            menuSelectBox.setVisible(!bol);
            menuSelectBox.clear();
        });
        init();
    }

    @Override
    protected void init() {
        buttonContainer = new HorizontalLayout();
        menuLabel = new Label();
        menuSelectBox = new MultiSelectListBox<>();
        menuNextButton = new Button();
        menuBackButton = new Button();

        buttonContainer.add(
                menuBackButton,
                menuNextButton
        );

        add(
                menuLabel,
                menuSelectBox,
                buttonContainer
        );

        initStyles();
        initData();
        initListeners();
    }

    @Override
    protected void initStyles() {
        setAlignItems(FlexComponent.Alignment.CENTER);
        menuSelectBox.getStyle().set("border-top", "1px dotted #A9A9A9");
        menuSelectBox.getStyle().set("border-bottom", "1px dotted #A9A9A9");
        buttonContainer.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }

    @Override
    protected void initData() {
        menuBackButton.setVisible(false);
        menuNextButton.setVisible(false);
        menuBackButton.setText("Back");
        menuNextButton.setText("Next");

        List.of(selectBoxApplyInput, backButtonApplyInput, labelApplyInput)
                        .forEach(session::apply);
    }

    @Override
    protected void initListeners() {
        menuSelectBox.addSelectionListener(event -> {
            menuNextButton.setVisible(!event.getAllSelectedItems().isEmpty());
        });

        menuNextButton.addClickListener(event -> {
            Set<String> data = menuSelectBox.getSelectedItems();
            selectBoxApplyInput.setData(data);
            session.nextStage(List.of(selectBoxApplyInput, backButtonApplyInput, labelApplyInput));
        });

        menuBackButton.addClickListener(event -> {
            session.backStage();
            List.of(selectBoxApplyInput, backButtonApplyInput, labelApplyInput).forEach(session::apply);
        });
    }
}
