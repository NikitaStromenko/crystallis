package lpka.prj.crystallis.view.symbol.pages;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lpka.prj.crystallis.view.commons.BaseView;

public abstract class MenuView extends BaseView {
    protected HorizontalLayout buttonContainer;
    protected Label menuLabel;
    protected MultiSelectListBox<String> menuSelectBox;
    protected Button menuNextButton;
    protected Button menuBackButton;
}
