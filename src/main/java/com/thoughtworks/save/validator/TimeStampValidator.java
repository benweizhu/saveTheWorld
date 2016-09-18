package com.thoughtworks.save.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampValidator {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public static boolean isValid(String historyDataRow) {
        Object result;
        try {
            result = DATE_FORMAT.parse(historyDataRow);
        } catch (ParseException e) {
            result = false;
        }
        return result instanceof Date ? true : false;
    }

}
