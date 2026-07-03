package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;

    static {
        try {
            FileReader fileReader =
                    new FileReader("src/test/resources/config.properties");

            properties = new Properties();
            properties.load(fileReader);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}