package com.thoughtworks.save.processor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileProcessor {
    public String readFileToString(String fileName) {
        StringBuilder result = new StringBuilder("");
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream in = classLoader.getResourceAsStream(fileName);
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
            scanner.close();
        }
        return result.toString();
    }
}
