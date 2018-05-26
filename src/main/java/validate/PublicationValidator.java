package validate;

import resourceBundle.RegexValidationManager;

import java.util.HashMap;
import java.util.Map;

public class PublicationValidator {

    public static Map<String, Boolean> validate(String pubName, String issn, String website, String setDate, String pubType, String pubStatus, String pubTheme, String cost1M, String cost3M, String cost6M, String cost12M) {
        Map<String, Boolean> map = new HashMap<>();
        if (pubName == null || !pubName.matches(RegexValidationManager.getProperty("publication.name"))) {
            map.put("incorectName", true);
        }
        if (issn == null || !issn.matches(RegexValidationManager.getProperty("publication.ISSN"))) {
            map.put("incorectISSN", true);
        }
        if (website == null || !website.matches(RegexValidationManager.getProperty("publication.website"))) {
            map.put("incorectWebsite", true);
        }
        if (setDate == null || !setDate.matches(RegexValidationManager.getProperty("date"))) {
            map.put("incorectSetDate", true);
        }
        if (pubType.equals("0")) {
            map.put("incorectPubType", true);
        }
        if (pubStatus.equals("0")) {
            map.put("incorectPubStatus", true);
        }
        if (pubTheme.equals("0")) {
            map.put("incorectPubTheme", true);
        }
        if (cost1M == null || !cost1M.matches(RegexValidationManager.getProperty("publication.cost"))) {
            map.put("incorectCost1M", true);
        }
        if (cost3M == null || !cost3M.matches(RegexValidationManager.getProperty("publication.cost"))) {
            map.put("incorectCost3M", true);
        }
        if (cost6M == null || !cost6M.matches(RegexValidationManager.getProperty("publication.cost"))) {
            map.put("incorectCost6M", true);
        }
        if (cost12M == null || !cost12M.matches(RegexValidationManager.getProperty("publication.cost"))) {
            map.put("incorectCost12M", true);
        }
        return map;
    }
}
