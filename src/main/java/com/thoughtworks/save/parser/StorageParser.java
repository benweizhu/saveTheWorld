package com.thoughtworks.save.parser;

import com.thoughtworks.save.exception.ConflictException;
import com.thoughtworks.save.exception.InvalidFormatException;
import com.thoughtworks.save.model.Snapshot;
import com.thoughtworks.save.validator.AnimalValidator;
import com.thoughtworks.save.validator.IdValidator;
import com.thoughtworks.save.validator.TimeStampValidator;

import java.util.ArrayList;
import java.util.List;

public class StorageParser {

    private static final String INVALID_FORMAT_MESSAGE = "Invalid format.";
    private static final String NEW_LINE = "\n";

    private IdParser idParser;
    private TimeStampParser timeStampParser;
    private AnimalParser animalParser;

    public StorageParser(IdParser idParser, TimeStampParser timeStampParser, AnimalParser animalParser) {
        this.idParser = idParser;
        this.timeStampParser = timeStampParser;
        this.animalParser = animalParser;
    }

    public List<Snapshot> parse(String historyData) throws InvalidFormatException, ConflictException {
        ArrayList<Snapshot> snapshots = new ArrayList<>();
        String[] historyDataRows = historyData.split(NEW_LINE);
        Snapshot newSnapshot = new Snapshot();
        for (String historyDataRow : historyDataRows) {
            throwInvalidFormatExceptionIfTrue(historyDataRow);
            newSnapshot = idParser.parseIdAndCreateNewSnapshot(snapshots, newSnapshot, historyDataRow);
            timeStampParser.parseTimeStamp(newSnapshot, historyDataRow);
            animalParser.parseAnimal(newSnapshot, historyDataRow);
        }
        return snapshots;
    }

    private void throwInvalidFormatExceptionIfTrue(String historyDataRow) throws InvalidFormatException {
        if (isInvalidFormat(historyDataRow)) {
            throw new InvalidFormatException(INVALID_FORMAT_MESSAGE);
        }
    }

    private boolean isInvalidFormat(String historyDataRow) {
        return !IdValidator.isValid(historyDataRow) && !TimeStampValidator.isValid(historyDataRow) && !AnimalValidator.isValid(historyDataRow);
    }

}
