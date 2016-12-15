package cz.fel.sep.view.window;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import cz.fel.sep.persistence.classes.CustomerType;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class CustomerWindow extends AbstractWindow {
    public CustomerWindow(String title, CustomerType customer, MTable<CustomerType> table) {
        super(title);
        ClientForm clientForm = new ClientForm(customer);
        clientForm.setSavedHandler(new AbstractForm.SavedHandler<CustomerType>() {
            private static final long serialVersionUID = 1008970415395369248L;

            @Override
            public void onSave(CustomerType entity) {
                close();
                Notification.show(entity.toString());
            }
        });

        clientForm.setDeleteHandler(new AbstractForm.DeleteHandler<CustomerType>() {
            @Override
            public void onDelete(CustomerType entity) {
                close();
                Notification.show(entity.toString());
            }
        });


        setContent(clientForm);
    }


    public class ClientForm extends AbstractForm<CustomerType> {
        TextField firstName = new MTextField("First Name").withFullWidth();
        TextField surname = new MTextField("Surname").withFullWidth();

        public ClientForm(CustomerType customer) {
            super();
            setSaveCaption("Uložit");
            setDeleteCaption("Smazat");
            setEntity(customer);
        }

        @Override
        protected Component createContent() {
            return new MVerticalLayout(firstName, surname, getToolbar());
        }
    }
}
