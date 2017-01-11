package com.magestore.app.lib;

import com.magestore.app.lib.model.customer.Customer;
import com.magestore.app.lib.model.catalog.Product;
import com.magestore.app.lib.resourcemodel.customer.CustomerDataAccess;
import com.magestore.app.lib.resourcemodel.DataAccessFactory;
import com.magestore.app.lib.resourcemodel.catalog.ProductDataAccess;
import com.magestore.app.lib.resourcemodel.user.UserDataAccess;
import com.magestore.app.pos.api.m2.POSDataAccessSession;
import com.magestore.app.pos.service.user.POSUserService;

import org.junit.Test;

import java.util.List;

/**
 * Created by Mike on 12/14/2016.
 * Magestore
 * mike@trueplus.vn
 * TODO: Add a class header comment!
 */

public class MagestoreDataAccessTest {
    @Test
    public void test_product_gateway_isCorrect() throws Exception {
        // Khởi tạo product gateway factory
        DataAccessFactory factory = DataAccessFactory.getFactory(null);
        ProductDataAccess product = factory.generateProductDataAccess();
        UserDataAccess user = factory.generateUserDataAccess();

        // Lấy list 30 products đầu tiên
        String strSession = user.login("http://demo-magento2.magestore.com/webpos", "demo", "demo123");
        POSUserService.session = new POSDataAccessSession();
        POSUserService.session.REST_SESSION_ID = strSession.trim().replace("\"", "");
        List<Product> list = product.getProducts(1, 1);
        return;
    }

    @Test
    public void test_customer_gateway_isCorrect() throws Exception {
        // Khởi tạo product gateway factory
        DataAccessFactory factory = DataAccessFactory.getFactory(null);
        CustomerDataAccess customerResourceModel = factory.generateCustomerDataAccess();
        UserDataAccess user = factory.generateUserDataAccess();

        // Lấy list 30 customer đầu tiên
        String strSession = user.login("http://demo-magento2.magestore.com/webpos", "demo", "demo123");
        POSUserService.session = new POSDataAccessSession();
        POSUserService.session.REST_SESSION_ID = strSession.trim().replace("\"", "");
        List<Customer> list = customerResourceModel.getCustomers(20, 1);
        return;
    }


}