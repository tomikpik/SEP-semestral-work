
package cz.fel.sep.view.pages;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import cz.fel.sep.utils.UserSession;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

@CDIView
@ViewMenuItem(icon = FontAwesome.CIRCLE, order = 3, title = "Logout")
public class LogoutView extends MVerticalLayout implements View {


    @PostConstruct
    void init() {
        ConfirmDialog.show(UI.getCurrent(), "Logout", "Do you really want to logout?", "Yes", "No", (ConfirmDialog.Listener) dialog -> {
            UI.getCurrent().getNavigator().navigateTo("");
            if (dialog.isConfirmed()) {
                UserSession.logout();
            }
            Page.getCurrent().reload();

        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
