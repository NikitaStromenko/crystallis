package lpka.prj.crystallis.view.symbol.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class SymbolView extends HorizontalLayout {
    protected VerticalLayout menuVertical;
    protected HorizontalLayout menuButtonCont;
    protected Label menuLabel;
    protected MultiSelectListBox<String> menuSelectBox;
    protected Button menuNextButton;
    protected Button menuBackButton;
}
