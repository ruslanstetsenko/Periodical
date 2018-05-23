package commands;

import beans.User;
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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
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

        session.setAttribute("currentPage", "path.page.users");
        return PageConfigManager.getProperty("path.page.users");
    }
}
