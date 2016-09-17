package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Snapshot;

import java.util.ArrayList;
import java.util.List;

public class StorageParser {

    public List<Snapshot> parse(String historyData) {
        ArrayList<Snapshot> snapshots = new ArrayList<>();
        snapshots.add(new Snapshot());
        snapshots.add(new Snapshot());
        snapshots.add(new Snapshot());
        return snapshots;
    }
}
