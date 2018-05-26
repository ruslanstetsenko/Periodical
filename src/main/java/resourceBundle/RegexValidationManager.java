package resourceBundle;

import java.util.ResourceBundle;

public class RegexValidationManager {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("regex_validation");

    private RegexValidationManager() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
