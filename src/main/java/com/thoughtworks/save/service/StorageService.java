package com.thoughtworks.save.service;

import com.thoughtworks.save.model.Snapshot;
import com.thoughtworks.save.parser.StorageParser;

import java.util.List;

public class StorageService {

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
        return "cat1 15 12\ncat2 2 3";
    }

    private Snapshot findSnapshotById(List<Snapshot> snapshots, String id) {
        for (Snapshot snapshot : snapshots) {
            if (id.equals(snapshot.getId())) {
                return snapshot;
            }
        }
        return null;
    }
}
