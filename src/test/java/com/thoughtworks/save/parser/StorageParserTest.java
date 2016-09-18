package com.thoughtworks.save.parser;

import com.thoughtworks.save.exception.InvalidFormatException;
import com.thoughtworks.save.model.Animal;
import com.thoughtworks.save.model.Snapshot;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class StorageParserTest {

    private String historyData;
    private StorageParser storageParser;
    private SimpleDateFormat simpleDateFormat;
    private IdParser idParser;
    private TimeStampParser timeStampParser;
    private AnimalParser animalParser;

    @Before
    public void setUp() throws Exception {
        idParser = new IdParser();
        timeStampParser = new TimeStampParser();
        animalParser = new AnimalParser();

        storageParser = new StorageParser(idParser, timeStampParser, animalParser);

        simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    }

    @Test
    public void shouldHaveTwoSnapshotWhenParseHistoryData() throws InvalidFormatException {
        historyData =
                "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                        "2016/09/02 22:30:46\n" +
                        "cat1 10 9\n" +
                        "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                        "2016/09/02 22:30:52\n" +
                        "cat1 10 9 2 -1\n" +
                        "cat2 2 3\n" +
                        "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                        "2016/09/02 22:31:02\n" +
                        "cat1 12 8 3 4\n";

        List<Snapshot> snapshots = storageParser.parse(historyData);
        assertThat(snapshots.size(), is(3));
        assertThat(snapshots.get(0).getId(),is("e4e87cb2-8e9a-4749-abb6-26c59344dfee"));
    }

    @Test
    public void shouldHaveThreeSnapshotWhenParseHistoryData() throws InvalidFormatException {
        historyData =
                "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                        "2016/09/02 22:30:46\n" +
                        "cat1 10 9\n" +
                        "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                        "2016/09/02 22:30:52\n" +
                        "cat1 10 9 2 -1\n" +
                        "cat2 2 3\n";

        List<Snapshot> snapshots = storageParser.parse(historyData);
        assertThat(snapshots.size(), is(2));
        assertThat(snapshots.get(0).getId(), containsString("e4e87cb2-8e9a-4749-abb6-26c59344dfee"));
        assertThat(snapshots.get(1).getId(), containsString("351055db-33e6-4f9b-bfe1-16f1ac446ac1"));
    }

    @Test
    public void shouldHaveTimeStampInSnapshot() throws ParseException, InvalidFormatException {
        historyData =
                "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                        "2016/09/02 22:30:46\n" +
                        "cat1 10 9\n" +
                        "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                        "2016/09/02 22:30:52\n" +
                        "cat1 10 9 2 -1\n" +
                        "cat2 2 3\n" +
                        "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                        "2016/09/02 22:31:02\n" +
                        "cat1 12 8 3 4\n";

        List<Snapshot> snapshots = storageParser.parse(historyData);
        assertThat(snapshots.get(0).getTimeStamp(), is(simpleDateFormat.parse("2016/09/02 22:30:46")));
        assertThat(snapshots.get(1).getTimeStamp(), is(simpleDateFormat.parse("2016/09/02 22:30:52")));
    }

    @Test
    public void shouldHaveAnimalInSnapshot() throws ParseException, InvalidFormatException {
        historyData =
                "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n" +
                        "2016/09/02 22:30:46\n" +
                        "cat1 10 9\n" +
                        "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                        "2016/09/02 22:30:52\n" +
                        "cat1 10 9 2 -1\n" +
                        "cat2 2 3\n" +
                        "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                        "2016/09/02 22:31:02\n" +
                        "cat1 12 8 3 4\n";

        List<Snapshot> snapshots = storageParser.parse(historyData);

        Snapshot snapshot = snapshots.get(0);
        snapshot.getAnimals().get(0);
        assertThat(snapshot.getAnimals().size(),is(1));
        assertThat(snapshot.getAnimals().get(0).getName(), is("cat1"));
        assertThat(snapshot.getAnimals().get(0).getX(), is(10));
        assertThat(snapshot.getAnimals().get(0).getY(), is(9));

        Snapshot snapshotSecond = snapshots.get(1);
        List<Animal> animals = snapshotSecond.getAnimals();
        Animal animal = animals.get(0);
        assertThat(animals.size(), is(2));
        assertThat(animal, is(notNullValue()));
        assertThat(animal.getName(), is("cat1"));
        assertThat(animal.getX(), is(10));
        assertThat(animal.getY(), is(9));
        assertThat(animal.getxOffset(), is(2));
        assertThat(animal.getyOffset(), is(-1));

        Animal animalSecond = animals.get(1);
        assertThat(animalSecond, is(notNullValue()));
        assertThat(animalSecond.getName(), is("cat2"));
        assertThat(animalSecond.getX(), is(2));
        assertThat(animalSecond.getY(), is(3));
    }

    @Test(expected = InvalidFormatException.class)
    public void shouldThrowInvalidFormatExceptionWhenIdIsInvalid() throws InvalidFormatException {
        historyData =
                "e4e87cb2-8e9a-4749\n" +
                        "2016/09/02 22:30:46\n" +
                        "cat1 10 9\n" +
                        "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n" +
                        "2016/09/02 22:30:52\n" +
                        "cat1 10 9 2 -1\n" +
                        "cat2 2 3\n" +
                        "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n" +
                        "2016/09/02 22:31:02\n" +
                        "cat1 12 8 3 4\n";

        storageParser.parse(historyData);
    }

}