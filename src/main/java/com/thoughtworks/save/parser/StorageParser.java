package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Snapshot;

import java.util.ArrayList;
import java.util.List;

public class StorageParser {

    public List<Snapshot> parse(String historyData) {
        ArrayList<Snapshot> snapshots = new ArrayList<>();
        String[] historyDataRows = historyData.split("\n");
        for (String historyDataRow : historyDataRows) {
            if(isIdFormat(historyDataRow)){
                snapshots.add(new Snapshot());
            }
        }
        return snapshots;
    }

    private boolean isIdFormat(String historyDataRow) {
        return historyDataRow.matches("([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)");
    }
}
