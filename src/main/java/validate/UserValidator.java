package validate;

import resourceBundle.RegexValidationManager;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {

    public static Map<String, Boolean> validate(String userSurName, String userName, String userLastName, String passportSerial, String passportNumber, String passportIssuedBy, String identNuber, String region, String district, String city, String street, String building,String appartment, String userPhoneNumber, String userEmail, String userBirthDate, String passportDateOfIssue) {
        Map<String, Boolean> map = new HashMap<>();

        if (userSurName == null || !userSurName.matches(RegexValidationManager.getProperty("user.surname"))) {
            map.put("incorectSurname", true);
        }
        if (userName == null || !userName.matches(RegexValidationManager.getProperty("user.name"))) {
            map.put("incorectName", true);
        }
        if (userLastName == null || !userLastName.matches(RegexValidationManager.getProperty("user.lastname"))) {
            map.put("incorectLastName", true);
        }
        if (passportSerial == null || !passportSerial.matches(RegexValidationManager.getProperty("passport.serial"))) {
            map.put("incorectPassportSerial", true);
        }
        if (passportNumber == null || !passportNumber.matches(RegexValidationManager.getProperty("passport.number"))) {
            map.put("incorectPassportNumber", true);
        }
        if (passportIssuedBy == null || !passportIssuedBy.matches(RegexValidationManager.getProperty("passport.issuedBy"))) {
            map.put("incorectPassportIssuedBy", true);
        }
        if (identNuber == null || !identNuber.matches(RegexValidationManager.getProperty("passport.INnumber"))) {
            map.put("incorectIdentNuber", true);
        }
        if (region == null || !region.matches(RegexValidationManager.getProperty("address.region"))) {
            map.put("incorectRegion", true);
        }
        if (district == null || !district.matches(RegexValidationManager.getProperty("address.district"))) {
            map.put("incorectDistrict", true);
        }
        if (city == null || !city.matches(RegexValidationManager.getProperty("address.city"))) {
            map.put("incorectCity", true);
        }
        if (street == null || !street.matches(RegexValidationManager.getProperty("address.street"))) {
            map.put("incorectStreet", true);
        }
        if (building == null || !building.matches(RegexValidationManager.getProperty("address.buildingNumber"))) {
            map.put("incorectBuildingNumber", true);
        }
        if (appartment == null || !appartment.matches(RegexValidationManager.getProperty("address.appartment"))) {
            map.put("incorectAppartment", true);
        }
        if (userPhoneNumber == null || !userPhoneNumber.matches(RegexValidationManager.getProperty("contacts.phoneNumber"))) {
            map.put("incorectPhoneNumber", true);
        }
        if (userEmail == null || !userEmail.matches(RegexValidationManager.getProperty("contacts.email"))) {
            map.put("incorectEmail", true);
        }
        if (userBirthDate == null || !userBirthDate.matches(RegexValidationManager.getProperty("date"))) {
            map.put("incorectUserBirthDate", true);
        }
        if (passportDateOfIssue == null || !passportDateOfIssue.matches(RegexValidationManager.getProperty("date"))) {
            map.put("incorectParrportDateOfIssue", true);
        }
//        if (login == null || !login.matches(RegexValidationManager.getProperty("account.login"))) {
//            map.put("incorectLogin", true);
//        }
//        if (password == null || !password.matches(RegexValidationManager.getProperty("account.password"))) {
//            map.put("incorectPassword", true);
//        }
        return map;
    }
}
