package com.magestore.app.pos.service.config;

import com.magestore.app.lib.model.config.Config;
import com.magestore.app.lib.resourcemodel.config.ConfigDataAccess;
import com.magestore.app.pos.model.config.PosConfigDefault;
import com.magestore.app.lib.resourcemodel.DataAccessFactory;
import com.magestore.app.lib.service.config.ConfigService;
import com.magestore.app.pos.service.AbstractService;
import com.magestore.app.util.ConfigUtil;
import com.magestore.app.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * Trả lại config chung của hệ thống
 * Created by Mike on 12/28/2016.
 * Magestore
 * mike@trueplus.vn
 */

public class POSConfigService extends AbstractService implements ConfigService {
    private static final String FILE_CONFIG_PATH = "/MagestorePOS/Config/config.json";

    /**
     * Trả lại config chung từ server
     *
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public Config retrieveConfig() throws InstantiationException, IllegalAccessException, IOException, ParseException {
        // Nếu chưa khởi tạo customer gateway factory
        DataAccessFactory factory = DataAccessFactory.getFactory(getContext());
        ConfigDataAccess configDataAccess = factory.generateConfigDataAccess();

        // Lấy list config đầu tiên
        ConfigUtil.mConfig = configDataAccess.getConfig();
        return ConfigUtil.mConfig;
    }

    /**
     * Trả lại config đã được lưu trên hệ thống
     *
     * @return
     */
    @Override
    public Config getConfig() {
        if (ConfigUtil.mConfig == null) ConfigUtil.mConfig = new PosConfigDefault();
        return ConfigUtil.mConfig;
    }
}
