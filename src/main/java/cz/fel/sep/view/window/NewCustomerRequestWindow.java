package cz.fel.sep.view.window;

import com.vaadin.ui.ComboBox;
import cz.fel.sep.persistence.DAO.CustomerChangeOrderDao;
import cz.fel.sep.persistence.classes.*;
import cz.fel.sep.view.pages.NewChangeOrderListView;
import cz.fel.sep.wsdl.APIaccess;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MGridLayout;

import java.math.BigInteger;


public class NewCustomerRequestWindow extends AbstractWindow {

    private MGridLayout verticalLayout;
    private CustomerDetailType customerDetailType = new CustomerDetailType();

    private MTextField firstName;
    private MTextField lastName;
    private MTextField streetName;
    private MTextField streetNum;
    private MTextField postalCode;
    private MTextField cityPart;
    private MTextField city;
    private MTextField county;
    private MTextField country;
    private ComboBox phoneNumberType;
    private MTextField phoneNum;
    private MTextField cityCode;
    private MTextField countryCode;
    private MTextField birthNum;
    private MTextField countryOrigin;


    public NewCustomerRequestWindow(APIaccess api, CustomerChangeOrderDao customerChangeOrderDao, NewChangeOrderListView table, CustomerDetailType customerDetailType) {
        super("New Customer");
        setSizeFull();

        verticalLayout = new MGridLayout(3, 1);
        verticalLayout.setSizeFull();

        final CustomerType.Status status;

        if (customerDetailType != null) {
            status = CustomerType.Status.Active;
        } else {
            status = CustomerType.Status.InChange;
        }
        generateLayout(customerDetailType);

        MButton btnSuspend = new MButton("Save", clickEvent -> {

            customerDetailType.getFirstName().add(firstName.getValue());
            customerDetailType.getSurname().add(lastName.getValue());

            customerDetailType.getAddress().add(new AddressType(
                    streetName.getValue(),
                    streetNum.getValue(),
                    postalCode.getValue(),
                    cityPart.getValue(),
                    city.getValue(),
                    county.getValue(),
                    country.getValue()));

            customerDetailType.getPhoneNum().add(new PhoneType(
                    BigInteger.valueOf(((PhoneType.PhoneNumberType) phoneNumberType.getValue()).ordinal()),
                    phoneNum.getValue(),
                    cityCode.getValue(),
                    countryCode.getValue()));

            customerDetailType.setBirthNum(birthNum.getValue());
            customerDetailType.setCountryOfOrigin(countryOrigin.getValue());

            CustomerChangeOrder customerChangeOrder = new CustomerChangeOrder(customerDetailType, status);
            customerChangeOrderDao.create(customerChangeOrder);

            close();
        });
        btnSuspend.setSizeFull();


        verticalLayout.add(btnSuspend);

        setContent(verticalLayout);
    }

    private void generateLayout(CustomerDetailType customerDetailType) {

        AddressType addressType = null;
        PhoneType phoneType = null;

        firstName = new MTextField("First Name");
        lastName = new MTextField("Surname");

        if (customerDetailType == null) {
            customerDetailType = new CustomerDetailType();
        } else {
            if (customerDetailType.getAddress().size() > 0) {
                addressType = customerDetailType.getAddress().get(customerDetailType.getAddress().size() - 1);
            }

            if (customerDetailType.getPhoneNum().size() > 0) {
                phoneType = customerDetailType.getPhoneNum().get(customerDetailType.getPhoneNum().size() - 1);
            }

            firstName.setValue(customerDetailType.getFirstName().get(customerDetailType.getFirstName().size() - 1));
            lastName.setValue(customerDetailType.getSurname().get(customerDetailType.getSurname().size() - 1));

        }

        streetName = new MTextField("Street Name");
        streetNum = new MTextField("Street Number");
        postalCode = new MTextField("Postal Code");
        cityPart = new MTextField("City Part");
        city = new MTextField("City");
        county = new MTextField("County");
        country = new MTextField("Country");

        if (addressType != null) {
            streetName.setValue(addressType.getStreetName());
            streetNum.setValue(addressType.getStreetNum());
            postalCode.setValue(addressType.getPostalCode());
            cityPart.setValue(addressType.getCityPart());
            city.setValue(addressType.getCity());
            county.setValue(addressType.getCounty());
            country.setValue(addressType.getCountry());
        }

        phoneNumberType = new ComboBox();

        phoneNum = new MTextField("Phone Number");
        cityCode = new MTextField("City Code");
        countryCode = new MTextField("Country Code");

        if (phoneType != null) {

            phoneNumberType.addItem(PhoneType.PhoneNumberType.Fax);
            phoneNumberType.addItem(PhoneType.PhoneNumberType.Mobile);
            //DO NOT USE
            if (phoneType.getPhoneNumberType() != null)
                phoneNumberType.select(PhoneType.PhoneNumberType.values()[phoneType.getPhoneNumberType().intValue()]);

            phoneNum.setValue(phoneType.getPhoneNum());
            cityCode.setValue(phoneType.getCityCode());
            countryCode.setValue(phoneType.getCountryCode());
        }

        birthNum = new MTextField("Birth Number", customerDetailType.getBirthNum());
        countryOrigin = new MTextField("Country Of Origin", customerDetailType.getCountryOfOrigin());

        firstName.setSizeFull();
        lastName.setSizeFull();

        streetName.setSizeFull();
        streetNum.setSizeFull();
        postalCode.setSizeFull();
        cityPart.setSizeFull();
        city.setSizeFull();
        county.setSizeFull();
        country.setSizeFull();

        phoneNumberType.setSizeFull();
        phoneNum.setSizeFull();
        cityCode.setSizeFull();
        countryCode.setSizeFull();

        birthNum.setSizeFull();
        countryOrigin.setSizeFull();


        verticalLayout.add(
                firstName,
                lastName,
                streetName,
                streetNum,
                postalCode,
                cityPart,
                city,
                county,
                country,
                phoneNumberType,
                phoneNum,
                cityCode,
                countryCode,
                birthNum,
                countryOrigin);
    }

}
