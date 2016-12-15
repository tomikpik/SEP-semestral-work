
package cz.fel.sep.view.pages;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.UI;
import cz.fel.sep.persistence.DAO.CustomerChangeOrderDao;
import cz.fel.sep.persistence.DAO.CustomerDetailTypeDao;
import cz.fel.sep.persistence.classes.CustomerChangeOrder;
import cz.fel.sep.utils.Utils;
import cz.fel.sep.view.components.CustomerPicker;
import cz.fel.sep.view.window.ProgressBarWindow;
import cz.fel.sep.wsdl.APIaccess;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@CDIView("change-requests")
@ViewMenuItem(icon = FontAwesome.CIRCLE, order = 1, title = "Change requests")
public class CustomerChangeOrderListView extends MVerticalLayout implements View {

    @Inject
    private APIaccess api;

    @Inject
    private CustomerChangeOrderDao customerChangeOrderDao;

    @Inject
    private CustomerDetailTypeDao customerDetailTypeDao;

    private MTable<CustomerChangeOrder> table;

    @PostConstruct
    void init() {
        this.setResponsive(true);

        add(Utils.buildHeader("Change requests"));

        table = new MTable<>(customerChangeOrderDao.getAll())
                .withProperties("requestType", "customer")
                .withColumnHeaders("Request Type", "Customer");


        table.setPageLength(10);
        table.setWidth("100%");


        MHorizontalLayout horizontalLayout = new MHorizontalLayout();
        horizontalLayout.setSpacing(true);

        CustomerPicker customerPicker = new CustomerPicker(api);
        customerPicker.setCaption(null);

        horizontalLayout.addComponents(new MButton("Send Requests", clickEvent -> {
            ConfirmDialog.show(UI.getCurrent(), "", "Send pending requests?", "Yes", "No", (ConfirmDialog.Listener) dialog -> {
                if (dialog.isConfirmed()) {

                    ProgressBarWindow progressBarWindow = new ProgressBarWindow();
                    UI.getCurrent().addWindow(progressBarWindow);

                    customerChangeOrderDao.getAll().forEach(changeOrder -> sendChangeOrder(changeOrder));

                    progressBarWindow.close();
                    table.setRows(customerChangeOrderDao.getAll());
                }
            });
        }), new MButton("Delete Requests", clickEvent -> {
            ConfirmDialog.show(UI.getCurrent(), "", "Delete pending requests?", "Yes", "No", (ConfirmDialog.Listener) dialog -> {
                if (dialog.isConfirmed()) {
                    customerChangeOrderDao.getAll().forEach(changeOrder -> customerChangeOrderDao.delete(changeOrder));
                    table.setRows(customerChangeOrderDao.getAll());
                }
            });
        }));

        add(horizontalLayout);


        add(table);

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private void sendChangeOrder(CustomerChangeOrder changeOrder) {
        api.createCustomerChangeOrder(changeOrder, customerChangeOrderDao);
    }
}
