package cz.fel.sep.wsdl;

import cz.fel.sep.persistence.DAO.CustomerChangeOrderDao;
import cz.fel.sep.persistence.classes.CustomerChangeOrder;
import cz.fel.sep.persistence.classes.CustomerDetailType;
import cz.fel.sep.persistence.classes.CustomerType;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.xml.ws.Holder;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Transactional
@ApplicationScoped
public class APIaccess {

    private final String apiUrl = "http://localhost:8088/mockCustomerDatabaseSOAP?WSDL";

    private CustomerDatabaseWSDL customerDatabaseWSDL;

    public APIaccess() {
        try {
            customerDatabaseWSDL = new CustomerDatabase(new URL(apiUrl)).getCustomerDatabaseSOAP();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Object createCustomerChangeOrder(CustomerChangeOrder parameter, CustomerChangeOrderDao customerChangeOrderDao) {
        CreateCustomerChangeOrder createCustomerChangeOrder = new CreateCustomerChangeOrder(parameter);
        Object response = customerDatabaseWSDL.createCustomerChangeOrder(createCustomerChangeOrder);
        if (response != null) {
            customerChangeOrderDao.delete(parameter);
        }
        return response;
    }

    public List<CustomerType> readAllCustomers(BigInteger from, BigInteger to) {
        return customerDatabaseWSDL.readAllCustomers(from, to);
    }

    public void readCustomerDetails(Holder<BigInteger> id, Holder<String> status, Holder<CustomerDetailType> customer) {
        customerDatabaseWSDL.readCustomerDetails(id, status, customer);
    }


    public CustomerDetailType readCustomerDetails(CustomerType customer) {

        Holder<String> status = new Holder<>();
        Holder<CustomerDetailType> customerDetail = new Holder<>();

        customerDatabaseWSDL.readCustomerDetails(new Holder<>(customer.getId()), status, customerDetail);

        return customerDetail.value;
    }
}
