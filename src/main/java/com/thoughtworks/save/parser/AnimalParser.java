package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;

import static java.lang.Integer.parseInt;

public class AnimalParser {

    private static final String ANIMAL_FORMAT = "([0-9a-zA-Z]*) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+)";
    private static final String ANIMAL_NO_OFFSET_FORMAT = "([0-9a-zA-Z]*) ((-|\\+)?\\d+) ((-|\\+)?\\d+)";
    private static final String SPACE = " ";
    private static final int FIRST_TIME_SHOW_FORMAT_LENGTH = 3;

    public void parseAnimal(Snapshot snapshot, String historyDataRow) {
        if (isAnimalFormat(historyDataRow)) {
            snapshot.addOrOverrideAnimal(parseAnimalHistoryData(historyDataRow));
        }
    }

    private  Animal parseAnimalHistoryData(String historyDataRow) {
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

    private  boolean isAnimalFormat(String historyDataRow) {
        return historyDataRow.matches(ANIMAL_FORMAT) || historyDataRow.matches(ANIMAL_NO_OFFSET_FORMAT);
    }
}
