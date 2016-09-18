package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Snapshot;

import java.util.ArrayList;
import java.util.List;

public class StorageParser {

    private IdParser idParser;
    private TimeStampParser timeStampParser;
    private AnimalParser animalParser;

    public StorageParser(IdParser idParser, TimeStampParser timeStampParser, AnimalParser animalParser) {
        this.idParser = idParser;
        this.timeStampParser = timeStampParser;
        this.animalParser = animalParser;
    }

    public List<Snapshot> parse(String historyData) {
        ArrayList<Snapshot> snapshots = new ArrayList<>();
        String[] historyDataRows = historyData.split("\n");
        Snapshot newSnapshot = new Snapshot();
        for (String historyDataRow : historyDataRows) {
            newSnapshot = idParser.parseIdAndCreateNewSnapshot(snapshots, newSnapshot, historyDataRow);
            timeStampParser.parseTimeStamp(newSnapshot, historyDataRow);
            animalParser.parseAnimal(newSnapshot, historyDataRow);
        }
        return snapshots;
    }

}
