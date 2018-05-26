package validate;

import resourceBundle.RegexValidationManager;

import java.util.HashMap;
import java.util.Map;

public class LoginValidator {

    public static Map<String, Boolean> validate(String login, String password) {
        Map<String, Boolean> map = new HashMap<>();

        if (login == null || !login.matches(RegexValidationManager.getProperty("account.login"))) {
            map.put("incorectLogin", true);
        }
        if (password == null || !password.matches(RegexValidationManager.getProperty("account.password"))) {
            map.put("incorectPassword", true);
        }
        return map;
    }
}
