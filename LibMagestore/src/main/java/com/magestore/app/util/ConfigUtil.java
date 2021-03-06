package com.magestore.app.util;

import com.magestore.app.lib.model.config.Config;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Các tiện ích cấu hình hệ thống, các format
 * Created by Mike on 12/28/2016.
 * Magestore
 * mike@trueplus.vn
 */

public class ConfigUtil {
    public static Config mConfig;
    private static DecimalFormat mCurrencyFormat;
    private static DecimalFormat mFloatFormat;
    private static DecimalFormat mIntegerFormat;

    /**
     * Trả lại format price
     *
     * @param number
     * @return
     */
    public static String formatPrice(float number) {
        return formatCurrency(number);
    }

    /**
     * Trả lại format price
     *
     * @param quantity
     * @return
     */
    public static String formatPrice(String quantity) {
        return formatPrice(Float.parseFloat(quantity));
    }

    /**
     * Trả lại format price
     *
     * @param quantity
     * @return
     */
    public static String formatPrice(CharSequence quantity) {
        return formatPrice(Float.parseFloat(quantity.toString()));
    }

    /**
     * Trả lại format số lượng
     *
     * @param quantity
     * @return
     */
    public static String formatQuantity(float quantity) {
        return formatNumber(quantity);
    }

    /**
     * Trả lại format số lượng
     *
     * @param quantity
     * @return
     */
    public static String formatQuantity(int quantity) {
        return formatNumber(quantity);
    }

    /**
     * Trả lại format số lượng
     *
     * @param quantity
     * @return
     */
    public static String formatQuantity(String quantity) {
        return formatNumber(Float.parseFloat(quantity));
    }

    /**
     * Trả lại format số lượng
     *
     * @param quantity
     * @return
     */
    public static String formatQuantity(CharSequence quantity) {
        return formatNumber(Float.parseFloat(quantity.toString()));
    }

    /**
     * Format tiền
     *
     * @param number
     * @return
     */
    private static String formatCurrency(float number) {
        if (mCurrencyFormat == null) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            symbols.setGroupingSeparator(',');
            symbols.setCurrencySymbol("$");
            symbols.setInternationalCurrencySymbol("USD");
            String pattern = "'$'###,###.00";
            mCurrencyFormat = new DecimalFormat(pattern);
        }
        return mCurrencyFormat.format(number);
    }

    /**
     * Format con số
     *
     * @param nummber
     * @return
     */
    private static String formatNumber(float nummber) {
        if (mFloatFormat == null) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            symbols.setGroupingSeparator(',');

            String pattern = "###,###.##";
            mFloatFormat = new DecimalFormat(pattern, symbols);
        }
        return mFloatFormat.format(nummber);
    }

    /**
     * Format con số
     *
     * @param number
     * @return
     */
    private static String formatNumber(int number) {
        if (mIntegerFormat == null) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            symbols.setGroupingSeparator(',');

            String pattern = "###,###.##";
            mIntegerFormat = new DecimalFormat(pattern, symbols);
        }
        return mIntegerFormat.format(number);
    }

    /**
     * Format date
     *
     * @param date
     * @return
     */
    public static String formatDate(String date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateFormat = null;
        try {
            dateFormat = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dateFormat == null) {
            return date;
        }
        return DateFormat.getDateInstance().format(dateFormat);
    }

    /**
     * Format time
     *
     * @param date
     * @return
     */
    public static String formatTime(String date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateFormat = null;
        try {
            dateFormat = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dateFormat == null) {
            return date;
        }
        return DateFormat.getTimeInstance(DateFormat.SHORT).format(dateFormat);
    }

    /**
     * Format date and time
     *
     * @param date
     * @return
     */
    public static String formatDateTime(String date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateFormat = null;
        try {
            dateFormat = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dateFormat == null) {
            return date;
        }
        return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(dateFormat);
    }
}
