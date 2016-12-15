
package cz.fel.sep.view.pages;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import cz.fel.sep.persistence.DAO.UserDao;
import cz.fel.sep.persistence.classes.CustomerType;
import cz.fel.sep.utils.Utils;
import cz.fel.sep.wsdl.APIaccess;
import org.vaadin.cdiviewmenu.ViewMenuItem;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.math.BigInteger;

@CDIView
@ViewMenuItem(icon = FontAwesome.CIRCLE, order = 2, title = "Customers")
public class CustomerListView extends MVerticalLayout implements View {

    @Inject
    private APIaccess api;

    @Inject
    private UserDao userDao;

    private MTable<CustomerType> table;

    @PostConstruct
    void init() {
        this.setResponsive(true);

        add(Utils.buildHeader("Clients"));


        table = new MTable<>(api.readAllCustomers(BigInteger.ZERO, BigInteger.TEN))
                .withProperties("firstName", "surname", "status")
                .withColumnHeaders("First Name", "Last Name", "Status");
        table.setPageLength(10);
        table.setWidth("100%");


        add(table);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
