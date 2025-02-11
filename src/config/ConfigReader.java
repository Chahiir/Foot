package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {

    /**
     * Reads the config.txt file and returns a map of key-value pairs.
     * @param filePath The path to the config.txt file.
     * @return A map containing the configuration values.
     */
    public static Map<String, String> readConfig(String filePath) {
        Map<String, String> config = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line by the first "=" to separate key and value
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    config.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read config file: " + filePath, e);
        }

        return config;
    }
}