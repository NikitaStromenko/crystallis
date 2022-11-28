package lpka.prj.crystallis.view.symbol;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lpka.prj.crystallis.domain.symbol.session.SymbolSession;
import lpka.prj.crystallis.view.symbol.components.SymbolView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Route("")
public class SymbolViewImpl extends SymbolView {
    private final SymbolSession session;

    public SymbolViewImpl(SymbolSession session) {
        this.session = session;
        init();
    }

    private void init() {
        initMenu();
        initMenuStyles();
        initMenuData();
        initListeners();

        add(menuVertical);
    }

    private void initMenu() {
        menuVertical = new VerticalLayout();
        menuButtonCont = new HorizontalLayout();
        menuLabel = new Label();
        menuSelectBox = new MultiSelectListBox<>();
        menuNextButton = new Button();
        menuBackButton = new Button();

        menuVertical.add(
                menuLabel,
                menuSelectBox,
                menuButtonCont
        );

        menuButtonCont.add(
                menuBackButton,
                menuNextButton
        );
    }

    private void initMenuStyles() {
        menuVertical.setAlignItems(FlexComponent.Alignment.CENTER);
        menuSelectBox.getStyle().set("border-top", "1px dotted #A9A9A9");
        menuSelectBox.getStyle().set("border-bottom", "1px dotted #A9A9A9");
        menuButtonCont.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }

    private void initMenuData() {
        menuBackButton.setText("Back");
        menuNextButton.setText("Next");
        menuLabel.setText("Choose types");

        menuSelectBox.setItems(session.getAllSymbolTypes().stream()
                .map(Enum::name)
                .collect(Collectors.toList()));
    }

    private void initListeners() {
        menuNextButton.addClickListener(event -> {
            Set<String> types = menuSelectBox.getSelectedItems();
            List<String> newItems = session.nextStage(types)
                    .stream()
                    .map(obj -> (String) obj)
                    .collect(Collectors.toList());
            menuSelectBox.setItems(newItems);
            menuLabel.setText(session.getTextForMenuLabel());
        });
    }
}
