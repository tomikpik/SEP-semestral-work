package cz.fel.sep.persistence.DAO;

import cz.fel.sep.persistence.classes.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserDao extends GenericDao<User> {

    public UserDao() {
        super(User.class);
    }

}

