
package cz.fel.sep.wsdl;

import cz.fel.sep.persistence.classes.CustomerDetailType;
import lombok.Getter;
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="customer" type="{http://www.cvut.cz/FEL/}CustomerDetailType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "status",
        "customer"
})
@Getter
@Setter
@XmlRootElement(name = "ReadCustomerDetailsResponse1")
public class ReadCustomerDetailsResponse1 {

    @XmlElement(required = true)
    protected BigInteger id;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected CustomerDetailType customer;

}
