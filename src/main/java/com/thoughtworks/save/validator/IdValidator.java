package com.thoughtworks.save.validator;

public class IdValidator {

    private static final String ID_FORMAT = "([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)";

    public static boolean isValid(String historyDataRow) {
        return historyDataRow.matches(ID_FORMAT);
    }
}
