package src;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Translator implements Serializable {

    private Map<String, String> dictionary = new HashMap<>();
    private String language;

    public Translator() {

    }

    public Translator(String language) {
        try {
            this.language = language;
            String filepath = String.format("config/languages/%s", language);
            FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                String[] parts = line.split(";");
                dictionary.put(parts[0], parts[1]);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String translate(String input) {
        if (!dictionary.containsKey(input)) {
            return input;
        }
        return dictionary.get(input);
    }

    public String getLanguage() {
        return language;
    }
}