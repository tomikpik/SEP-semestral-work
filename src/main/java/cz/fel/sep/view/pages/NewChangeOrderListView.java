
package cz.fel.sep.view.pages;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import cz.fel.sep.persistence.DAO.CustomerChangeOrderDao;
import cz.fel.sep.persistence.DAO.CustomerDetailTypeDao;
import cz.fel.sep.persistence.classes.CustomerChangeOrder;
import cz.fel.sep.utils.Utils;
import cz.fel.sep.view.components.CustomerPicker;
import cz.fel.sep.view.window.NewCustomerRequestWindow;
import cz.fel.sep.view.window.OtherRequestWindow;
import cz.fel.sep.wsdl.APIaccess;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@CDIView("")
@ViewMenuItem(icon = FontAwesome.CIRCLE, order = 0, title = "New Change Request")
public class NewChangeOrderListView extends MVerticalLayout implements View {

    @Inject
    private APIaccess api;

    @Inject
    private CustomerChangeOrderDao customerChangeOrderDao;

    @Inject
    private CustomerDetailTypeDao customerDetailTypeDao;

    @PostConstruct
    void init() {
        this.setResponsive(true);

        add(Utils.buildHeader("New Change Request"));


        MHorizontalLayout horizontalLayout = new MHorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.setSizeFull();

        CustomerPicker customerPicker = new CustomerPicker(api);
        customerPicker.setCaption(null);

        horizontalLayout.addComponents(new MButton("New Customer", clickEvent -> {
            NewCustomerRequestWindow window = new NewCustomerRequestWindow(api, customerChangeOrderDao, this, null);
            UI.getCurrent().addWindow(window);
        }).withFullWidth(), customerPicker, new MButton("Edit Customer", clickEvent -> {
            if (customerPicker.getCustomer() != null) {
                NewCustomerRequestWindow window = new NewCustomerRequestWindow(api, customerChangeOrderDao, this, api.readCustomerDetails(customerPicker.getCustomer()));
                UI.getCurrent().addWindow(window);
            } else {
                new Notification("You have to select the customer first.").show(Page.getCurrent());
            }
        }).withFullWidth(), new MButton("Other Requests", clickEvent -> {
            if (customerPicker.getCustomer() != null) {
                OtherRequestWindow window = new OtherRequestWindow(api, customerChangeOrderDao, this, customerPicker);
                UI.getCurrent().addWindow(window);
            } else {
                new Notification("You have to select the customer first.").show(Page.getCurrent());
            }
        }).withFullWidth());

        add(horizontalLayout);


    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private void sendChangeOrder(CustomerChangeOrder changeOrder) {
        api.createCustomerChangeOrder(changeOrder, customerChangeOrderDao);
    }
}
