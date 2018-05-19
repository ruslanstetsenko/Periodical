package resource;

import java.util.ResourceBundle;

public class PageConfigManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    private PageConfigManager() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
