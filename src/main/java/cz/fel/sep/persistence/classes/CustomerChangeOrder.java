package cz.fel.sep.persistence.classes;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerChangeOrder extends AbstractEntity {

    @Basic
    private String requestType;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private CustomerDetailType customer;

    public CustomerChangeOrder(CustomerDetailType customerDetailType, CustomerType.Status status) {
        super();
        setCustomer(customerDetailType);
        setRequestType(status.name());
        setId(customerDetailType.getId());
    }
}
