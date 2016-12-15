
package cz.fel.sep.persistence.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for CustomerDetailType complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="CustomerDetailType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="5"/&gt;
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="5"/&gt;
 *         &lt;element name="address" type="{http://www.cvut.cz/FEL/}AddressType" maxOccurs="3"/&gt;
 *         &lt;element name="phoneNum" type="{http://www.cvut.cz/FEL/}PhoneType" maxOccurs="3"/&gt;
 *         &lt;element name="birthNum" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="countryOfOrigin" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerDetailType", propOrder = {
        "firstName",
        "surname",
        "address",
        "phoneNum",
        "birthNum",
        "countryOfOrigin"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerDetailType extends AbstractEntity {

    @ElementCollection
    @OrderColumn(name = "firstnames")
    @XmlElement(required = true)
    protected List<String> firstName;

    @ElementCollection
    @OrderColumn(name = "surnames")
    @XmlElement(required = true)
    protected List<String> surname;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @XmlElement(required = true)
    protected List<AddressType> address;

    @OneToMany
    @Cascade(CascadeType.ALL)
    @XmlElement(required = true)
    protected List<PhoneType> phoneNum;

    @Basic
    @XmlElement(required = true)
    protected String birthNum;

    @Basic
    @XmlElement(required = true)
    protected String countryOfOrigin;

    public List<String> getFirstName() {
        if (firstName == null) {
            firstName = new ArrayList<String>();
        }
        return this.firstName;
    }

    public List<String> getSurname() {
        if (surname == null) {
            surname = new ArrayList<String>();
        }
        return this.surname;
    }

    public List<AddressType> getAddress() {
        if (address == null) {
            address = new ArrayList<AddressType>();
        }
        return this.address;
    }

    public List<PhoneType> getPhoneNum() {
        if (phoneNum == null) {
            phoneNum = new ArrayList<PhoneType>();
        }
        return this.phoneNum;
    }

    public String getBirthNum() {
        return birthNum;
    }

    public void setBirthNum(String value) {
        this.birthNum = value;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String value) {
        this.countryOfOrigin = value;
    }

    @Override
    public String toString() {
        return firstName.get(firstName.size() - 1) + " " + surname.get(surname.size() - 1);
    }
}
