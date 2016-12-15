package cz.fel.sep.view.window;

import com.vaadin.ui.Window;


public abstract class AbstractWindow extends Window {

    public AbstractWindow(String title) {
        super(title); // Set window caption
        center();
        setClosable(true);
        setResizable(false);
        setModal(true);
        setDraggable(false);
    }

}