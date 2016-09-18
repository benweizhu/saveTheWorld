package com.thoughtworks.save.validator;

public class AnimalValidator {
    private static final String ANIMAL_FORMAT = "([0-9a-zA-Z]*) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+) ((-|\\+)?\\d+)";
    private static final String ANIMAL_NO_OFFSET_FORMAT = "([0-9a-zA-Z]*) ((-|\\+)?\\d+) ((-|\\+)?\\d+)";

    public static boolean isValid(String historyDataRow) {
        return historyDataRow.matches(ANIMAL_FORMAT) || historyDataRow.matches(ANIMAL_NO_OFFSET_FORMAT);
    }
}
