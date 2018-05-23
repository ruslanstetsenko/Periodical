package commands;

import beans.User;
import resourceBundle.PageConfigManager;
import service.UserService;
import wrappers.AboutUserWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OkEditUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        User currentUser = (User) session.getAttribute("currentUser");
        int userId;
        if (currentUser.getUserRoleId() == 1) {
            userId = (Integer) session.getAttribute("currentUserId");
        } else {
            userId = currentUser.getId();
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
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Date userBirthDate = new Date(1);
        Date userRegistrationDate = Date.valueOf(request.getParameter("userRegistrationDate"));
        Date passportDateOfIssue = Date.valueOf(request.getParameter("passportDateOfIssue"));
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("userBirthDate"));
            userBirthDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(userSurName);
        System.out.println(userName);
        System.out.println(userLastName);
        new UserService().updateUser(userId, userName, userSurName, userLastName, userBirthDate, userRegistrationDate, passportSerial, passportNumber, passportDateOfIssue, passportIssuedBy, identNuber, region, district, city, street, building, appartment, userPhoneNumber, userEmail, login, password);


        if (currentUser.getUserRoleId() == 1) {
            List<User> userList = new UserService().getAllUsers();
            session.setAttribute("userList", userList);
            return PageConfigManager.getProperty("path.page.users");
        }
        AboutUserWrapper wrapper = new UserService().getUserInfo(currentUser.getId());
        session.setAttribute("user", wrapper.getUser());
        session.setAttribute("userAccount", wrapper.getAccount());
        session.setAttribute("userContactInfo", wrapper.getContactInfo());
        session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
        session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());

        session.setAttribute("currentPage", "path.page.aboutUser");
        return PageConfigManager.getProperty("path.page.aboutUser");
    }
}
