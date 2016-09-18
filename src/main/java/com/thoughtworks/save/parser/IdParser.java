package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.save.validator.IdValidator.isValid;

public class IdParser {

    public Snapshot parseIdAndCreateNewSnapshot(ArrayList<Snapshot> snapshots, Snapshot renewSnapshot, String historyDataRow) {
        if (isValid(historyDataRow)) {
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

}
