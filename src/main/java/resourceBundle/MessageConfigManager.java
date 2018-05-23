package resourceBundle;

import java.util.ResourceBundle;

public class MessageConfigManager {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    private MessageConfigManager() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
