package by.valvik.dmdevcaffe.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public final class PropertiesUtils {

    private static final String[] PROPERTIES_FILES_NAME = {"application"};

    private static final String PROPERTIES_EXT = ".properties";

    private static final Properties PROPERTIES = new Properties();

    private PropertiesUtils() {
    }

    static {
        Arrays.stream(PROPERTIES_FILES_NAME)
              .map(fileName -> fileName + PROPERTIES_EXT)
              .forEach(PropertiesUtils::loadProperties);
    }

    public static String getValue(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static int getAsInt(String key) {
        return Integer.parseInt(getValue(key));
    }

    private static void loadProperties(String propertiesName) {
        ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
        try (InputStream resource = classLoader.getResourceAsStream(propertiesName)) {
            PROPERTIES.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
