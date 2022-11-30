package lpka.prj.crystallis.view.commons;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class BaseView extends VerticalLayout {
    abstract protected void init();
    abstract protected void initStyles();
    abstract protected void initListeners();
    abstract protected void initData();
}
