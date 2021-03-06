package com.thoughtworks.save.processor;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FileProcessorTest {
    @Test
    public void shouldReadFileCorrectly() {
        FileProcessor fileProcessor = new FileProcessor();
        String result = fileProcessor.readFileToString("historyData.txt");
        String value =
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
        assertThat(result, is(value));
    }
}