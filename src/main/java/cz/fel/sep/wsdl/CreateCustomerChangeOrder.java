
package cz.fel.sep.wsdl;

import cz.fel.sep.persistence.classes.CustomerChangeOrder;
import cz.fel.sep.persistence.classes.CustomerDetailType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="requestType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="customer" type="{http://www.cvut.cz/FEL/}CustomerDetailType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "requestType",
        "id",
        "customer"
})
@XmlRootElement(name = "CreateCustomerChangeOrder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerChangeOrder {

    @XmlElement(required = true)
    protected String requestType;
    @XmlElement(required = true)
    protected BigInteger id;
    protected CustomerDetailType customer;

    public CreateCustomerChangeOrder(CustomerChangeOrder customerChangeOrder) {
        setRequestType(customerChangeOrder.getRequestType());
        setId(customerChangeOrder.getId());
        setCustomer(customerChangeOrder.getCustomer());
    }

    public enum RequestType {
        CREATE_USER, DELETE_USER, REFUND, SUSPEND;
    }
}
