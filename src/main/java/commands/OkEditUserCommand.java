package commands;

import beans.User;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.UserService;
import validate.UserValidator;
import wrappers.AboutUserWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class OkEditUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(OkEditUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            LOGGER.error(MessageConfigManager.getProperty("message.error.cantFindUser"));
            session.invalidate();
            return PageConfigManager.getProperty("path.page.login");
        }

        int currentUserId = (Integer) session.getAttribute("currentUserId");

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
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String userBirthDate = request.getParameter("userBirthDate");
        String passportDateOfIssue = request.getParameter("passportDateOfIssue");
        String userRegistrationDate = request.getParameter("userRegistrationDate");

        UserService userService = new UserService();

        Map<String, Boolean> map = UserValidator.validate(userSurName, userName, userLastName, passportSerial, passportNumber, passportIssuedBy, identNuber, region, district, city, street, building, appartment, userPhoneNumber, userEmail, userBirthDate, passportDateOfIssue);

        if (map.isEmpty()) {
            int passportNumber1 = Integer.valueOf(passportNumber);
            Date userBirthDate1 = Date.valueOf(userBirthDate);
            Date passportDateOfIssue1 = Date.valueOf(passportDateOfIssue);

            try {
                userService.updateUser(currentUserId, userName, userSurName, userLastName, userBirthDate1, passportSerial, passportNumber1, passportDateOfIssue1, passportIssuedBy, identNuber, region, district, city, street, building, appartment, userPhoneNumber, userEmail, login, password);
                if (currentUser.getUserRoleId() != 1) {
                    AboutUserWrapper wrapper = new UserService().getUserInfo(currentUser.getId());
                    session.setAttribute("currentUser", wrapper.getUser());
                    session.setAttribute("userAccount", wrapper.getAccount());
                    session.setAttribute("userContactInfo", wrapper.getContactInfo());
                    session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
                    session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());
                    session.removeAttribute("currentUserId");
                } else {
                    List<User> userList = new UserService().getAllUsers();
                    session.setAttribute("userList", userList);
                    User user = (User) session.getAttribute("currentUser");
                    currentUser = new UserService().read(user.getId());
                    session.setAttribute("currentUser", currentUser);
                }

            } catch (DataBaseWorkException e) {
                session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
                choicePreviousPage(session, currentUser);
                LOGGER.error("Can't update user info. DB error", e.getCause());
                return PageConfigManager.getProperty("path.page.error");
            } catch (NullPointerException npe) {
                session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
                choicePreviousPage(session, currentUser);
                LOGGER.error("Can't load user data", npe.getCause());
                return PageConfigManager.getProperty("path.page.error");
            }

            LOGGER.info("User " + userSurName + " " + userName + " " + userLastName + " has updated");
            return currentUser.getUserRoleId() == 1 ? PageConfigManager.getProperty("path.page.users") : PageConfigManager.getProperty("path.page.aboutUser");
        }
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        request.setAttribute("userSurName", userSurName);
        request.setAttribute("userName", userName);
        request.setAttribute("userLastName", userLastName);
        request.setAttribute("userBirthDate", userBirthDate);
        request.setAttribute("userRegistrationDate", userRegistrationDate);
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

        LOGGER.info("Can't ubdate user. Incorrect data");
        return PageConfigManager.getProperty("path.page.editUser");
    }

    private void choicePreviousPage(HttpSession session, User currentUser) {
        if (currentUser.getUserRoleId() == 1) {
            session.setAttribute("previousPage", "path.page.users");
        } else {
            session.setAttribute("previousPage", "path.page.aboutUser");
        }
    }
}
