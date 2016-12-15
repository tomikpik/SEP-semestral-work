import cz.fel.sep.persistence.classes.AddressType;
import cz.fel.sep.persistence.classes.CustomerType;
import cz.fel.sep.wsdl.CustomerDatabase;
import cz.fel.sep.wsdl.CustomerDatabaseWSDL;
import org.junit.Test;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class APITest {

    @Test
    public void test1() {

        try {
            CustomerDatabaseWSDL customerDatabaseWSDL = new CustomerDatabase(new URL("http://localhost:8088/mockCustomerDatabaseSOAP?WSDL")).getCustomerDatabaseSOAP();

            ArrayList<CustomerType> b = (ArrayList<CustomerType>) customerDatabaseWSDL.readAllCustomers(BigInteger.ZERO, BigInteger.TEN);

            //customerDatabaseWSDL.createCustomerChangeOrder(new CreateCustomerChangeOrder());

            b.forEach(customerType -> {
                System.out.println(customerType);
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {


    }


}
