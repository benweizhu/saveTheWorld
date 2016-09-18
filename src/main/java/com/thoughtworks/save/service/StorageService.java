package com.thoughtworks.save.service;

import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;
import com.thoughtworks.save.parser.StorageParser;

import java.util.List;

public class StorageService {

    private static final String SPACE = " ";
    private static final String ENTER = "\n";

    private StorageParser storageParser;

    public StorageService(StorageParser storageParser) {
        this.storageParser = storageParser;
    }

    public String getSnapShot(String historyData, String id) {
        List<Snapshot> snapshots = storageParser.parse(historyData);
        Snapshot snapshot = findSnapshotById(snapshots, id);
        return concatSnapshotResult(snapshot);
    }

    private String concatSnapshotResult(Snapshot snapshot) {
        StringBuilder stringBuffer = new StringBuilder();
        List<Animal> animals = snapshot.getAnimals();
        for (Animal animal : animals) {
            stringBuffer.append(animal.getName() + SPACE + animal.getCalculatedX() + SPACE + animal.getCalculatedY() + ENTER);
        }
        return stringBuffer.toString();
    }

    private Snapshot findSnapshotById(List<Snapshot> snapshots, String id) {
        for (Snapshot snapshot : snapshots) {
            if (snapshot.getId().contains(id)) {
                return snapshot;
            }
        }
        return null;
    }
}
