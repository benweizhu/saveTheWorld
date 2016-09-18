package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Snapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.thoughtworks.save.validator.TimeStampValidator.isValid;

public class TimeStampParser {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public void parseAndAddToTimeStampIfMatch(Snapshot snapshot, String historyDataRow) {
        if (isValid(historyDataRow)) {
            try {
                snapshot.setTimeStamp(DATE_FORMAT.parse(historyDataRow));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
