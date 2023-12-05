package utils;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadFile {

    ClassLoader classLoader = getClass().getClassLoader();
    File file;


    public int countUniqueWords(String input) {
        String[] words = new String[0];
        try {
            file  = new File(Objects.requireNonNull(classLoader.getResource("input.txt")).getFile());
            String inputToString = FileUtils.readFileToString(file, "UTF-8");
            if (!inputToString.isBlank()) {
                words = inputToString.split(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        return uniqueWords.size();
    }

    public void writeCountUniqueWords() {
        try {
            file  = new File(Objects.requireNonNull(classLoader.getResource("input.txt")).getFile());
            FileUtils.write(file, "\nNo. of Unique Words are: " + countUniqueWords("input.txt"), "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}