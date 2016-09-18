package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Snapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampParser {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public void parseTimeStamp(Snapshot snapshot, String historyDataRow) {
        Object timeStamp = getTimeStamp(historyDataRow);
        if (timeStamp instanceof Date) {
            snapshot.setTimeStamp((Date) timeStamp);
        }
    }

    private Object getTimeStamp(String historyDataRow) {
        try {
            return DATE_FORMAT.parse(historyDataRow);
        } catch (ParseException e) {
            return false;
        }
    }
}
