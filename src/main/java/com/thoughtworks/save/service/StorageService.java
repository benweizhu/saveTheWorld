package com.thoughtworks.save.service;

import com.thoughtworks.save.exception.ConflictException;
import com.thoughtworks.save.exception.InvalidFormatException;
import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;
import com.thoughtworks.save.parser.StorageParser;

import java.util.List;

import static java.lang.String.format;

public class StorageService {

    private static final String outputFormat = "%s %d %d\n";

    private StorageParser storageParser;

    public StorageService(StorageParser storageParser) {
        this.storageParser = storageParser;
    }

    public String getSnapShot(String historyData, String id) {
        List<Snapshot> snapshots;
        try {
            snapshots = storageParser.parse(historyData);
        } catch (InvalidFormatException e) {
            return e.getMessage();
        } catch (ConflictException e) {
            return e.getMessage();
        }
        Snapshot selectedSnapshot = findSnapshotById(snapshots, id);
        return concatSnapshotResult(selectedSnapshot);
    }

    private String concatSnapshotResult(Snapshot snapshot) {
        StringBuilder stringBuffer = new StringBuilder();
        List<Animal> animals = snapshot.getAnimals();
        for (Animal animal : animals) {
            stringBuffer.append(format(outputFormat, animal.getName(), animal.getCalculatedX(), animal.getCalculatedY()));
        }
        return removeLastNewLineSymbol(stringBuffer.toString());
    }

    private Snapshot findSnapshotById(List<Snapshot> snapshots, String id) {
        for (Snapshot snapshot : snapshots) {
            if (snapshot.getId().contains(id)) {
                return snapshot;
            }
        }
        return null;
    }

    private String removeLastNewLineSymbol(String output) {
        return output.substring(0, output.length() - 1);
    }
}
