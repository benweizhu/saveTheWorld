package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StorageParser {

    private static final String ID_FORMAT = "([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)";
    private static final String ANIMAL_FORMAT = "([0-9a-zA-Z]*) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+)";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public List<Snapshot> parse(String historyData) {
        ArrayList<Snapshot> snapshots = new ArrayList<>();
        String[] historyDataRows = historyData.split("\n");
        Snapshot snapshot = null;
        for (String historyDataRow : historyDataRows) {
            if(isIdFormat(historyDataRow)){
                snapshot = new Snapshot();
                snapshots.add(snapshot);
            }

            Object timeStamp = getTimeStamp(historyDataRow);
            if (timeStamp instanceof Date) {
                snapshot.setTimeStamp((Date) timeStamp);
            }

            if(isAnimalFormat(historyDataRow)){
                snapshot.setAnimal(new Animal());
            }
        }
        return snapshots;
    }

    private boolean isAnimalFormat(String historyDataRow) {
        return historyDataRow.matches(ANIMAL_FORMAT);
    }

    private Object getTimeStamp(String historyDataRow) {
        try {
            return DATE_FORMAT.parse(historyDataRow);
        } catch (ParseException e) {
            return false;
        }
    }


    private boolean isIdFormat(String historyDataRow) {
        return historyDataRow.matches(ID_FORMAT);
    }
}
