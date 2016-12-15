
package cz.fel.sep.persistence.classes;

import lombok.*;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;


/**
 * <p>Java class for PhoneType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="PhoneType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="phoneNumberType" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="phoneNum" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneType", propOrder = {
        "phoneNumberType",
        "phoneNum",
        "cityCode",
        "countryCode"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class PhoneType extends AbstractEntity {

    @XmlElement(required = true)
    protected BigInteger phoneNumberType;
    @XmlElement(required = true)
    protected String phoneNum;
    protected String cityCode;
    protected String countryCode;

    public enum PhoneNumberType {
        Mobile, Fax;
    }

}
