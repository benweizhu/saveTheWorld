package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;

import static java.lang.Integer.parseInt;

public class AnimalParser {

    private static final String ANIMAL_FORMAT = "([0-9a-zA-Z]*) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+)";
    private static final String ANIMAL_NO_OFFSET_FORMAT = "([0-9a-zA-Z]*) ((-|\\+)?\\d+) ((-|\\+)?\\d+)";
    private static final String SPACE = " ";

    private static final int FIRST_TIME_SHOW_FORMAT_LENGTH = 3;

    private static final int NAME_FIELD_INDEX = 0;
    private static final int X_FIELD_INDEX = 1;
    private static final int Y_FIELD_INDEX = 2;
    private static final int X_OFFSET_FIELD_INDEX = 3;
    private static final int Y_OFFSET_FIELD_INDEX = 4;

    public void parseAnimal(Snapshot snapshot, String historyDataRow) {
        if (isAnimalFormat(historyDataRow)) {
            snapshot.addOrOverrideAnimal(parseAnimalHistoryData(historyDataRow));
        }
    }

    private  Animal parseAnimalHistoryData(String historyDataRow) {
        String[] animalFields = historyDataRow.split(SPACE);
        Animal animal = new Animal();
        animal.setName(animalFields[NAME_FIELD_INDEX]);
        animal.setX(parseInt(animalFields[X_FIELD_INDEX]));
        animal.setY(parseInt(animalFields[Y_FIELD_INDEX]));
        if (animalFields.length > FIRST_TIME_SHOW_FORMAT_LENGTH) {
            animal.setxOffset(parseInt(animalFields[X_OFFSET_FIELD_INDEX]));
            animal.setyOffset(parseInt(animalFields[Y_OFFSET_FIELD_INDEX]));
        }
        return animal;
    }

    private  boolean isAnimalFormat(String historyDataRow) {
        return historyDataRow.matches(ANIMAL_FORMAT) || historyDataRow.matches(ANIMAL_NO_OFFSET_FORMAT);
    }
}
