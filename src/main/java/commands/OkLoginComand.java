package commands;

import service.AdminWindowsService;
import service.LoginService;
import service.UserWindowsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OkLoginComand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int[] arr = new LoginService().enterInAccount(request.getParameter("login"), request.getParameter("password"));
        int role = arr[0];
        int userId = arr[1];
        System.out.println(userId);
        if (role == 1) {
            AdminWindowsService adminWindowsService = new AdminWindowsService();
            request.setAttribute("publicationList", adminWindowsService.loadAdminWindow()[0]);
            request.setAttribute("subscriptionBillList", adminWindowsService.loadAdminWindow()[1]);
            return "/jsps/adminPage.jsp";
        } else if (role == 2) {
            UserWindowsService userWindowsService = new UserWindowsService();
            request.setAttribute("mapPubNameSubscription", userWindowsService.loadUserWindow(userId)[0]);
            request.setAttribute("subscriptionBillListUser", userWindowsService.loadUserWindow(userId)[1]);
            return "/jsps/userPage.jsp";
        }
        return "/jsps/error.jsp";
    }
}
