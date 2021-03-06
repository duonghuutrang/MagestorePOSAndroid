package com.magestore.app.lib.model.config;

import com.magestore.app.lib.model.Model;

/**
 * Created by Mike on 1/14/2017.
 * Magestore
 * mike@trueplus.vn
 */

public interface ConfigCurrency extends Model {
    String getCode();
    String getName();
    String getSymbol();
    boolean isDefault();
    float getRate();
}
