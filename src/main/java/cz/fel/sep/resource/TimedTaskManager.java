package cz.fel.sep.resource;

import cz.fel.sep.persistence.DAO.CustomerChangeOrderDao;
import cz.fel.sep.wsdl.APIaccess;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class TimedTaskManager {

    @Inject
    private APIaccess api;

    @Inject
    private CustomerChangeOrderDao customerChangeOrderDao;

    //every 10 second
    //@Schedule(second = "*/10", minute = "*", hour = "*")

    @Schedule(second = "*", minute = "*", hour = "0")
    public void sendPendingRequests() {
        customerChangeOrderDao.getAll().forEach(changeOrder -> {
            api.createCustomerChangeOrder(changeOrder, customerChangeOrderDao);
        });
    }
}