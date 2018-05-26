package commands;

import beans.Account;
import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.LoginService;
import service.UserService;
import validate.UserValidator;
import wrappers.AboutUserWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

public class OkCreateUserCommand implements Command {
    //    private static final Logger logger = Logger.getLogger(OkCreateUserCommand.class);
    private static final Logger logger = LogManager.getLogger(OkCreateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        String userSurName = request.getParameter("userSurName");
        String userName = request.getParameter("userName");
        String userLastName = request.getParameter("userLastName");
        String passportSerial = request.getParameter("passportSerial");
        String passportNumber = request.getParameter("passportNumber");
        String passportIssuedBy = request.getParameter("passportIssuedBy");
        String identNuber = request.getParameter("identNuber");
        String region = request.getParameter("region");
        String district = request.getParameter("district");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String appartment = request.getParameter("appartment");
        String userPhoneNumber = request.getParameter("userPhoneNumber");
        String userEmail = request.getParameter("userEmail");
        String userBirthDate = request.getParameter("userBirthDate");
//        String userRegistrationDate = request.getParameter("userRegistrationDate");
        String passportDateOfIssue = request.getParameter("passportDateOfIssue");
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");
//        System.out.println(login);
//        System.out.println(password);
        UserService userService = new UserService();

        Map<String, Boolean> map = UserValidator.validate(userSurName, userName, userLastName, passportSerial, passportNumber, passportIssuedBy, identNuber, region, district, city, street, building, appartment, userPhoneNumber, userEmail, userBirthDate, passportDateOfIssue);
        if (map.isEmpty()) {
            int passportNumber1 = Integer.valueOf(passportNumber);
            int identNuber1 = Integer.valueOf(identNuber);
            Date userBirthDate1 = Date.valueOf(userBirthDate);
            Date userRegistrationDate1 = Date.valueOf(LocalDate.now());
            Date passportDateOfIssue1 = Date.valueOf(passportDateOfIssue);

            userService.createUser(userName, userSurName, userLastName, userBirthDate1, userRegistrationDate1, passportSerial, passportNumber1, passportDateOfIssue1, passportIssuedBy, identNuber1, region, district, city, street, building, appartment, userPhoneNumber, userEmail, login, password);

//            LoginService loginService = new LoginService();
//            Account account = loginService.checkAccount(login, password);
//            if (account != null) {
//                User currentUser = loginService.getUser(account);
//                session.setAttribute("currentUser", currentUser);
//
//            }
            User currentUser = userService.getLastAddedUser();
            session.setAttribute("currentUser", currentUser);
            session.setAttribute("currentUserId", currentUser.getId());
            AboutUserWrapper wrapper = new UserService().getUserInfo(currentUser.getId());
            session.setAttribute("user", wrapper.getUser());
            session.setAttribute("userAccount", wrapper.getAccount());
            session.setAttribute("userContactInfo", wrapper.getContactInfo());
            session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
            session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());

            logger.info("User " + userSurName + " " + userName + " " + userLastName + " has created");
            return PageConfigManager.getProperty("path.page.userPageSubsc");
        } else {
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            request.setAttribute("userSurName", userSurName);
            request.setAttribute("userName", userName);
            request.setAttribute("userLastName", userLastName);
            request.setAttribute("userBirthDate", userBirthDate);
            request.setAttribute("passportSerial", passportSerial);
            request.setAttribute("passportNumber", passportNumber);
            request.setAttribute("passportIssuedBy", passportIssuedBy);
            request.setAttribute("passportDateOfIssue", passportDateOfIssue);
            request.setAttribute("identNuber", identNuber);
            request.setAttribute("region", region);
            request.setAttribute("district", district);
            request.setAttribute("city", city);
            request.setAttribute("street", street);
            request.setAttribute("building", building);
            request.setAttribute("appartment", appartment);
            request.setAttribute("userPhoneNumber", userPhoneNumber);
            request.setAttribute("userEmail", userEmail);
            request.setAttribute("login", login);
            request.setAttribute("password", password);

            logger.info("Can't create user. Incorrect data");
            return PageConfigManager.getProperty("path.page.createUser");
        }
//        List<User> userList = new UserService().getAllUsers();
//        session.setAttribute("userList", userList);

    }
}
