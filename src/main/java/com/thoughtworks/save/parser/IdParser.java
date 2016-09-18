package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;

import java.util.ArrayList;
import java.util.List;

public class IdParser {

    private static final String ID_FORMAT = "([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)+-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)-([0-9a-zA-Z]*)";

    public Snapshot parseIdAndCreateNewSnapshot(ArrayList<Snapshot> snapshots, Snapshot renewSnapshot, String historyDataRow) {
        if (createNewSnapshotIfIdFormatMatched(historyDataRow)) {
            List<Animal> cloneAnimals = cloneList(renewSnapshot.getAnimals());
            renewSnapshot = new Snapshot();
            renewSnapshot.setId(historyDataRow);
            renewSnapshot.setAnimals(cloneAnimals);
            snapshots.add(renewSnapshot);
        }
        return renewSnapshot;
    }

    private  List<Animal> cloneList(List<Animal> animals) {
        List<Animal> cloneAnimals = new ArrayList<>(animals.size());
        for (Animal animal : animals) {
            cloneAnimals.add(animal.deepClone());
        }
        return cloneAnimals;
    }

    private  boolean createNewSnapshotIfIdFormatMatched(String historyDataRow) {
        return historyDataRow.matches(ID_FORMAT);
    }
}
