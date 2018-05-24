package commands;

import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class OkCreateUserCommand implements Command {
//    private static final Logger logger = Logger.getLogger(OkCreateUserCommand.class);
private static final Logger logger = LogManager.getLogger(OkCreateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        String userSurName = request.getParameter("userSurName");
        String userName = request.getParameter("userName");
        String userLastName = request.getParameter("userLastName");
        String passportSerial = request.getParameter("passportSerial");
        int passportNumber = Integer.valueOf(request.getParameter("passportNumber"));
        String passportIssuedBy = request.getParameter("passportIssuedBy");
        int identNuber = Integer.valueOf(request.getParameter("identNuber"));
        String region = request.getParameter("region");
        String district = request.getParameter("district");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String appartment = request.getParameter("appartment");
        String userPhoneNumber = request.getParameter("userPhoneNumber");
        String userEmail = request.getParameter("userEmail");
        Date userBirthDate = Date.valueOf(request.getParameter("userBirthDate"));
        Date userRegistrationDate = Date.valueOf(request.getParameter("userRegistrationDate"));
        Date passportDateOfIssue = Date.valueOf(request.getParameter("passportDateOfIssue"));

        new UserService().createUser(userName, userSurName, userLastName, userBirthDate, userRegistrationDate, passportSerial, passportNumber, passportDateOfIssue, passportIssuedBy, identNuber, region, district, city, street, building, appartment, userPhoneNumber, userEmail);

        List<User> userList = new UserService().getAllUsers();
        session.setAttribute("userList", userList);

        logger.info("User " + userSurName + " " + userName + " " + userLastName + " has created");
        return PageConfigManager.getProperty("path.page.users");
    }
}
