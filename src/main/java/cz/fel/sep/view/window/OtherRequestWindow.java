package cz.fel.sep.view.window;

import cz.fel.sep.persistence.DAO.CustomerChangeOrderDao;
import cz.fel.sep.persistence.classes.CustomerChangeOrder;
import cz.fel.sep.persistence.classes.CustomerType;
import cz.fel.sep.view.pages.NewChangeOrderListView;
import cz.fel.sep.view.components.CustomerPicker;
import cz.fel.sep.wsdl.APIaccess;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;


public class OtherRequestWindow extends AbstractWindow {

    public OtherRequestWindow(APIaccess api, CustomerChangeOrderDao customerChangeOrderDao, NewChangeOrderListView components, CustomerPicker customerPicker) {
        super("Other Requests");
        setWidth("30%");
        setHeight("50%");

        MVerticalLayout verticalLayout = new MVerticalLayout();
        verticalLayout.setSizeFull();

        MButton btnSuspend = new MButton("Suspend", clickEvent -> {
            CustomerChangeOrder customerChangeOrder = new CustomerChangeOrder(api.readCustomerDetails(customerPicker.getCustomer()), CustomerType.Status.Suspended);
            customerChangeOrderDao.create(customerChangeOrder);
            close();
        });
        btnSuspend.setSizeFull();

        MButton btnRefund = new MButton("Refund", clickEvent -> {
            CustomerChangeOrder customerChangeOrder = new CustomerChangeOrder(api.readCustomerDetails(customerPicker.getCustomer()), CustomerType.Status.Redunded);
            customerChangeOrderDao.create(customerChangeOrder);
            close();
        });
        btnRefund.setSizeFull();

        MButton btnDeactivate = new MButton("Deactivate", clickEvent -> {
            CustomerChangeOrder customerChangeOrder = new CustomerChangeOrder(api.readCustomerDetails(customerPicker.getCustomer()), CustomerType.Status.Deactivated);
            customerChangeOrderDao.create(customerChangeOrder);
            close();
        });
        btnDeactivate.setSizeFull();


        verticalLayout.add(btnSuspend, btnRefund, btnDeactivate);

        setContent(verticalLayout);
    }
}
