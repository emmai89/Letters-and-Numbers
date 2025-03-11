package com.mannycode.letters_and_numbers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileIO {
        
        public static Set<String> readDictionary() {
            
            Set<String> dictionary = new HashSet<>();
            String filePath = "src/main/res/dictionary.csv";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    line = values[0].trim();
                    dictionary.add(line.toLowerCase());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return dictionary;
        }
}

