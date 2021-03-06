package com.magestore.app.lib.resourcemodel;

import com.magestore.app.lib.context.MagestoreContext;
import com.magestore.app.lib.resourcemodel.catalog.ProductDataAccess;
import com.magestore.app.lib.resourcemodel.config.ConfigDataAccess;
import com.magestore.app.lib.resourcemodel.customer.CustomerDataAccess;
import com.magestore.app.lib.resourcemodel.registershift.RegisterShiftDataAccess;
import com.magestore.app.lib.resourcemodel.sales.OrderDataAccess;
import com.magestore.app.lib.resourcemodel.user.UserDataAccess;
import com.magestore.app.pos.api.m2.POSDataAccessFactory;

/**
 * Factory tạo các object API đến magestore pos server
 * Created by Mike on 12/14/2016.
 * Magestore
 * mike@trueplus.vn
 */

public abstract class DataAccessFactory {
    private MagestoreContext mContext;

    public abstract ProductDataAccess generateProductDataAccess();
    public abstract UserDataAccess generateUserDataAccess();
    public abstract OrderDataAccess generateOrderDataAccess();
    public abstract CustomerDataAccess generateCustomerDataAccess();
    public abstract RegisterShiftDataAccess generateRegisterShiftDataAccess();
    public abstract ConfigDataAccess generateConfigDataAccess();

    public MagestoreContext getContext() {
        return mContext;
    }

    /**
     * Khởi tạo DataAccess factory để kết nối, gọi các API đến DataAccessFactory
     * @param context ngữ cảnh xử lý
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static DataAccessFactory getFactory(MagestoreContext context) throws IllegalAccessException, InstantiationException {
        DataAccessFactory dataAccessFactory = (DataAccessFactory) POSDataAccessFactory.class.newInstance();
        dataAccessFactory.mContext = context;
        return dataAccessFactory;
    }

    /**
     * Khởi tạo DataAccess factory để kết nối, gọi các API đến DataAccessFactory
     * @param context ngữ cảnh xử lý
     * @param gatewayFactoryClass class để sinh gateway
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static DataAccessFactory getFactory(MagestoreContext context, Class gatewayFactoryClass) throws IllegalAccessException, InstantiationException {
        DataAccessFactory dataAccessFactory = (DataAccessFactory) gatewayFactoryClass.newInstance();
        dataAccessFactory.mContext = context;
        return dataAccessFactory;
    }
}
