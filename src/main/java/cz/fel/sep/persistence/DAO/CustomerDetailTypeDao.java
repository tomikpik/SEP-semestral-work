package cz.fel.sep.persistence.DAO;

import cz.fel.sep.persistence.classes.CustomerDetailType;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerDetailTypeDao extends GenericDao<CustomerDetailType> {

    public CustomerDetailTypeDao() {
        super(CustomerDetailType.class);
    }
}
