package cz.fel.sep.persistence.DAO;

import cz.fel.sep.persistence.classes.CustomerChangeOrder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerChangeOrderDao extends GenericDao<CustomerChangeOrder> {
    public CustomerChangeOrderDao() {
        super(CustomerChangeOrder.class);
    }
}
