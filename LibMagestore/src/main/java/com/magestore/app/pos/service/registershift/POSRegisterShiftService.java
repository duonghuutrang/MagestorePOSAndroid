package com.magestore.app.pos.service.registershift;

import com.magestore.app.lib.model.registershift.CashTransaction;
import com.magestore.app.lib.model.registershift.RegisterShift;
import com.magestore.app.lib.resourcemodel.DataAccessFactory;
import com.magestore.app.lib.resourcemodel.registershift.RegisterShiftDataAccess;
import com.magestore.app.lib.service.registershift.RegisterShiftService;
import com.magestore.app.pos.model.registershift.PosCashTransaction;
import com.magestore.app.pos.service.AbstractService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johan on 1/18/17.
 * Magestore
 * dong.le@trueplus.vn
 */

public class POSRegisterShiftService extends AbstractService implements RegisterShiftService {

    @Override
    public List<RegisterShift> retrieveRegisterShiftList() throws InstantiationException, IllegalAccessException, IOException, ParseException {
        // Khởi tạo register shift gateway factory
        DataAccessFactory factory = DataAccessFactory.getFactory(getContext());
        RegisterShiftDataAccess registerShiftDataAccess = factory.generateRegisterShiftDataAccess();
        return registerShiftDataAccess.getRegisterShifts();
    }

    @Override
    public List<RegisterShift> insertMakeAdjustment(RegisterShift registerShift) throws InstantiationException, IllegalAccessException, IOException, ParseException {
        // Khởi tạo register shift gateway factory
        CashTransaction cashTransaction = createCashTransaction();
        cashTransaction.setBalance(registerShift.getBalance());
        cashTransaction.setBaseBalance(registerShift.getBaseBalance());
        cashTransaction.setBaseCurrencyCode(registerShift.getBaseCurrencyCode());
        cashTransaction.setCreateAt(registerShift.getParamCash().getCreatedAt());
        cashTransaction.setBaseValue(registerShift.getParamCash().getValue());
        cashTransaction.setLocationId(registerShift.getLocationId());
        cashTransaction.setNote(registerShift.getParamCash().getNote());
        cashTransaction.setShiftId(registerShift.getShiftId());
        cashTransaction.setType(registerShift.getParamCash().getType());
        cashTransaction.setValue(registerShift.getParamCash().getValue());
        DataAccessFactory factory = DataAccessFactory.getFactory(getContext());
        RegisterShiftDataAccess registerShiftDataAccess = factory.generateRegisterShiftDataAccess();
        return registerShiftDataAccess.insertMakeAdjustment(cashTransaction);
    }

    @Override
    public CashTransaction createCashTransaction() {
        CashTransaction cashTransaction = new PosCashTransaction();
        return cashTransaction;
    }

    @Override
    public List<CashTransaction> createListCashTransaction(RegisterShift registerShift, String openShift, String balance) {
        PosCashTransaction posCashTransaction = new PosCashTransaction();
        posCashTransaction.setCreateAt(registerShift.getOpenedAt());
        posCashTransaction.setOpenShiftTitle(openShift);
        posCashTransaction.setBalanceTitle(balance);
        posCashTransaction.setFloatAmount(registerShift.getFloatAmount());
        posCashTransaction.setCheckOpenShift(true);

        List<CashTransaction> nListCash = new ArrayList<CashTransaction>();
        nListCash.add(posCashTransaction);
        return nListCash;
    }
}
