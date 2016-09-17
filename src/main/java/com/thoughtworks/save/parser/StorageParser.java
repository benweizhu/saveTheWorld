package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class StorageParser {

    private static final String ID_FORMAT = "([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)";
    private static final String ANIMAL_FORMAT = "([0-9a-zA-Z]*) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+)";
    private static final String ANIMAL_NO_OFFSET_FORMAT = "([0-9a-zA-Z]*) ((-|\\+)?\\d+) ((-|\\+)?\\d+)";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static final String SPACE = " ";
    private static final int FIRST_TIME_SHOW_FORMAT_LENGTH = 3;

    public List<Snapshot> parse(String historyData) {
        ArrayList<Snapshot> snapshots = new ArrayList<>();
        String[] historyDataRows = historyData.split("\n");
        Snapshot snapshot = null;
        for (String historyDataRow : historyDataRows) {
            snapshot = parseIdAndCreateNewSnapshot(snapshots, snapshot, historyDataRow);
            parseTimeStamp(snapshot, historyDataRow);
            parseAnimal(snapshot, historyDataRow);
        }
        return snapshots;
    }

    private Snapshot parseIdAndCreateNewSnapshot(ArrayList<Snapshot> snapshots, Snapshot snapshot, String historyDataRow) {
        if (createNewSnapshotIfIdFormatMatched(historyDataRow)) {
            snapshot = new Snapshot();
            snapshot.setId(historyDataRow);
            snapshots.add(snapshot);
        }
        return snapshot;
    }

    private void parseTimeStamp(Snapshot snapshot, String historyDataRow) {
        Object timeStamp = getTimeStamp(historyDataRow);
        if (timeStamp instanceof Date) {
            snapshot.setTimeStamp((Date) timeStamp);
        }
    }

    private void parseAnimal(Snapshot snapshot, String historyDataRow) {
        if (isAnimalFormat(historyDataRow)) {
            snapshot.addAnimal(parseAnimal(historyDataRow));
        }
    }

    private Animal parseAnimal(String historyDataRow) {
        String[] animalFields = historyDataRow.split(SPACE);
        Animal animal = new Animal();
        animal.setName(animalFields[0]);
        animal.setX(parseInt(animalFields[1]));
        animal.setY(parseInt(animalFields[2]));
        if (animalFields.length > FIRST_TIME_SHOW_FORMAT_LENGTH) {
            animal.setxOffset(parseInt(animalFields[3]));
            animal.setyOffset(parseInt(animalFields[4]));
        }
        return animal;
    }

    private boolean isAnimalFormat(String historyDataRow) {
        return historyDataRow.matches(ANIMAL_FORMAT) || historyDataRow.matches(ANIMAL_NO_OFFSET_FORMAT);
    }

    private Object getTimeStamp(String historyDataRow) {
        try {
            return DATE_FORMAT.parse(historyDataRow);
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean createNewSnapshotIfIdFormatMatched(String historyDataRow) {
        return historyDataRow.matches(ID_FORMAT);
    }
}
