package cz.fel.sep.view;

import com.vaadin.server.Page;
import com.vaadin.ui.*;
import cz.fel.sep.utils.UserSession;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MPasswordField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.io.Serializable;

public class LoginWindow extends GridLayout implements Serializable {


    public LoginWindow() {
        super(3, 4);
        setSizeFull();


        TextField username = new MTextField("Username");
        username.setSizeFull();

        PasswordField password = new MPasswordField("Password");
        password.setSizeFull();

        Button login = new MButton("Login", clickEvent -> {
            if (UserSession.login(username.getValue(), password.getValue())) {
                Page.getCurrent().reload();
            } else {
                new Notification("Invalid username or password!").show(Page.getCurrent());
            }

        });
        login.setSizeFull();

        VerticalLayout verticalLayout = new MVerticalLayout(username, password, login);
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
        verticalLayout.setSizeFull();

        addComponent(verticalLayout, 1, 1, 1, 2);
    }

}
