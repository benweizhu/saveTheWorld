package com.thoughtworks.save;

import com.thoughtworks.save.parser.AnimalParser;
import com.thoughtworks.save.parser.IdParser;
import com.thoughtworks.save.parser.StorageParser;
import com.thoughtworks.save.parser.TimeStampParser;
import com.thoughtworks.save.processor.FileProcessor;
import com.thoughtworks.save.service.StorageService;

public class Application {

    public static void main(String[] args) {
        String filePath = isFilePathArgValid(args) ? "historyData.txt" : args[0];
        String id = isIdArgValid(args) ? "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155" : args[1];

        StorageService storageService = new StorageService(new StorageParser(new IdParser(), new TimeStampParser(), new AnimalParser()));
        String result = storageService.getSnapShot(new FileProcessor().readFileToString(filePath), id);

        System.out.println(result);
    }

    private static boolean isIdArgValid(String[] args) {
        return args.length < 2 || isEmptyOrNull(args[1]);
    }

    private static boolean isFilePathArgValid(String[] args) {
        return args.length < 1 || isEmptyOrNull(args[0]);
    }

    private static boolean isEmptyOrNull(String input) {
        return input == null || "".equals(input);
    }
}
