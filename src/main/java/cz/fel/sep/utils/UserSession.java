package cz.fel.sep.utils;

import com.vaadin.server.VaadinSession;
import cz.fel.sep.persistence.classes.User;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Getter
@Setter
public class UserSession implements Serializable {

    private User user;

    @PostConstruct
    public void init() {
    }

    public static boolean login(String username, String password) {
        if (username.equals("username") && password.equals("password")) {
            setCurrentUser(new User());
            return true;
        }
        return false;
    }

    public static void logout() {
        setCurrentUser(null);
    }

    public static User getCurrentUser() {
        return (User) VaadinSession.getCurrent().getAttribute(
                User.class.getName());
    }

    public static void setCurrentUser(User user) {
        VaadinSession.getCurrent().setAttribute(User.class.getName(), user);
    }

}
