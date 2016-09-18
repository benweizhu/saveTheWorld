package com.thoughtworks.save.parser;

import com.thoughtworks.save.exception.InvalidFormatException;
import com.thoughtworks.save.model.Snapshot;
import com.thoughtworks.save.validator.AnimalValidator;
import com.thoughtworks.save.validator.IdValidator;
import com.thoughtworks.save.validator.TimeStampValidator;

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

    public List<Snapshot> parse(String historyData) throws InvalidFormatException {
        ArrayList<Snapshot> snapshots = new ArrayList<>();
        String[] historyDataRows = historyData.split("\n");
        Snapshot newSnapshot = new Snapshot();
        for (String historyDataRow : historyDataRows) {
            if (isInvalidFormat(historyDataRow)) {
                throw new InvalidFormatException("Invalid format.");
            }
            newSnapshot = idParser.parseIdAndCreateNewSnapshot(snapshots, newSnapshot, historyDataRow);
            timeStampParser.parseTimeStamp(newSnapshot, historyDataRow);
            animalParser.parseAnimal(newSnapshot, historyDataRow);
        }
        return snapshots;
    }

    private boolean isInvalidFormat(String historyDataRow) {
        return !IdValidator.isValid(historyDataRow) && !TimeStampValidator.isValid(historyDataRow) && !AnimalValidator.isValid(historyDataRow);
    }

}
