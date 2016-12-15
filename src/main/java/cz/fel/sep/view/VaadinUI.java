package cz.fel.sep.view;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import cz.fel.sep.utils.UserSession;
import org.vaadin.cdiviewmenu.ViewMenuUI;

@CDIUI("")
@Theme("valo")
@Title("SEP")
@StyleSheet({"http://fonts.googleapis.com/css?family=Roboto"})
public class VaadinUI extends ViewMenuUI {

    @Override
    protected void init(VaadinRequest request) {
        super.init(request);
        if (UserSession.getCurrentUser() == null) {
            setContent(new LoginWindow());
        }


    }

    @Override
    protected void refresh(VaadinRequest request) {
        super.refresh(request);
        if (UserSession.getCurrentUser() == null) {
            setContent(new LoginWindow());
        }
    }

}
