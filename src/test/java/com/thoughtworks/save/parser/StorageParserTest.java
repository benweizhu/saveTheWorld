package com.thoughtworks.save.parser;

import com.thoughtworks.save.model.Snapshot;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class StorageParserTest {

    private String historyData;
    private StorageParser storageParser;
    private SimpleDateFormat simpleDateFormat;

    @Before
    public void setUp() throws Exception {
        storageParser = new StorageParser();
        simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    }

    @Test
    public void shouldHaveTwoSnapshotWhenParseHistoryData() {
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
    }

    @Test
    public void shouldHaveThreeSnapshotWhenParseHistoryData() {
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
    }

    @Test
    public void shouldHaveTimeStampInSnapshot() throws ParseException {
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
    public void shouldHaveAnimalInSnapshot() throws ParseException {
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
        assertThat(snapshots.get(1).getAnimal(), is(notNullValue()));
//        assertThat(snapshots.get(1).getAnimal().getName(), is("cat1"));
//        assertThat(snapshots.get(1).getAnimal().getX(), is(10));
//        assertThat(snapshots.get(1).getAnimal().getY(), is(9));
//        assertThat(snapshots.get(1).getAnimal().getxOffset(), is(2));
//        assertThat(snapshots.get(1).getAnimal().getyOffset(), is(-1));
    }




}