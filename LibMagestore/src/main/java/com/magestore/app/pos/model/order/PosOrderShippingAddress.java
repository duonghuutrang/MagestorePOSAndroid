package com.magestore.app.pos.model.order;

import com.magestore.app.lib.model.order.OrderShippingAddress;
import com.magestore.app.pos.model.customer.PosCustomerAddress;
import com.magestore.app.pos.model.directory.PosRegion;

import java.util.List;

/**
 * Created by Johan on 1/12/17.
 * Magestore
 * dong.le@trueplus.vn
 */

public class PosOrderShippingAddress extends PosCustomerAddress implements OrderShippingAddress {
    String address_type;
    String city;
    String country_id;
    String customer_address_id;
    String email;
    String entity_id;
    String firstname;
    String lastname;
    String parent_id;
    String postcode;
    List<String> street;
    PosRegion region;
    String telephone;

    @Override
    public String getID() {
        return entity_id;
    }

    @Override
    public String getAddressType() {
        return address_type;
    }

    @Override
    public String getEmail() {
        return email;
    }
}