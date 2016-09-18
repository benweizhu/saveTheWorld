package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.save.validator.IdValidator.isValid;

public class IdParser {

    public Snapshot parseIdAndCreateNewSnapshotIfMatch(List<Snapshot> snapshots, Snapshot newSnapshot, String historyDataRow) {
        if (isValid(historyDataRow)) {
            List<Animal> cloneRemainedAnimalsRecords = cloneList(newSnapshot.getAnimals());
            newSnapshot = new Snapshot();
            newSnapshot.setId(historyDataRow);
            newSnapshot.setAnimals(cloneRemainedAnimalsRecords);
            snapshots.add(newSnapshot);
        }
        return newSnapshot;
    }

    private  List<Animal> cloneList(List<Animal> animals) {
        List<Animal> cloneAnimals = new ArrayList<>(animals.size());
        for (Animal animal : animals) {
            cloneAnimals.add(animal.deepClone());
        }
        return cloneAnimals;
    }

}
