package cz.fel.sep.view.components;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import cz.fel.sep.persistence.classes.CustomerType;
import cz.fel.sep.wsdl.APIaccess;

import java.math.BigInteger;
import java.util.Collection;

public class CustomerPicker extends ComboBox {

    public CustomerPicker(APIaccess api) {
        super();
        this.setCaption("Customer");

        BeanItemContainer beanItemContainer = new BeanItemContainer<CustomerType>(CustomerType.class);
        beanItemContainer.addAll(api.readAllCustomers(BigInteger.ZERO, BigInteger.valueOf(1000000)));
        this.setContainerDataSource(beanItemContainer);
        this.setSizeFull();
        this.setImmediate(true);

        Collection<Object> items = (Collection<Object>) this.getItemIds();

        for (Object o : items) {
            CustomerType customer = (CustomerType) o;
            String caption = customer.getFirstName() + " " + customer.getSurname();
            this.setItemCaption(o, caption);

        }

    }

    public CustomerType getCustomer() {
        return (CustomerType) this.getValue();
    }

}
