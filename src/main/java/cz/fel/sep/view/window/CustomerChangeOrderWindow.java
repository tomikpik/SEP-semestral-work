package cz.fel.sep.view.window;

import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import cz.fel.sep.persistence.classes.CustomerChangeOrder;
import org.vaadin.viritin.fields.MDateField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

public class CustomerChangeOrderWindow extends AbstractWindow {
    public CustomerChangeOrderWindow(String title, CustomerChangeOrder customerChangeOrder) {
        super(title);
        RequestForm requestForm = new RequestForm(customerChangeOrder);
        requestForm.setSavedHandler(new AbstractForm.SavedHandler<CustomerChangeOrder>() {
            private static final long serialVersionUID = 1008970415395369248L;

            @Override
            public void onSave(CustomerChangeOrder entity) {
                Notification.show(entity.toString());
            }
        });

        requestForm.setDeleteHandler(new AbstractForm.DeleteHandler<CustomerChangeOrder>() {
            @Override
            public void onDelete(CustomerChangeOrder entity) {
                Notification.show(entity.toString());
            }
        });


        setContent(requestForm);
    }


    public class RequestForm extends AbstractForm<CustomerChangeOrder> {
        TextField requestType = new MTextField("Typ").withFullWidth();
        DateField created = new MDateField("Vytvořeno").withFullWidth();

        public RequestForm(CustomerChangeOrder customerChangeOrder) {
            super();
            setSaveCaption("Uložit");
            setDeleteCaption("Smazat");
            setEntity(customerChangeOrder);
        }

        @Override
        protected Component createContent() {
            return new MVerticalLayout(requestType, created, getToolbar());
        }
    }
}
