package utils;

import org.apache.commons.io.FileUtils;
import org.sonatype.aether.spi.io.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;


public class ReadFile {
    URL resource = FileProcessor.class.getClassLoader().getResource("input.txt");
    File file = new File(resource.getFile());

    public int countUniqueWords() {
        String[] words = new String[0];
        try {
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
            FileUtils.write(file, "\n No. of Unique Words are: " + Integer.toString(countUniqueWords()), "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}