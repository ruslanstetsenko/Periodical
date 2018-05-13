package commands;

import service.AdminWindowsService;
import service.LoginService;
import service.UserWindowsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OkLoginComand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int[] arr = new LoginService().enterInAccount(request.getParameter("login"), request.getParameter("password"));
        int role = arr[0];
        int userId = arr[1];
        session.setAttribute("userId", userId);
        session.setAttribute("userRole", role);
        session.setAttribute("currentPubTypeId", 0);
        session.setAttribute("currentPubThemeId", 0);
        session.setAttribute("currentPubStatusId", 0);
        session.setAttribute("currentBillPaidId", 0);
        if (role == 1) {
            Object[] arrObj = new AdminWindowsService().loadAdminWindow();
            session.setAttribute("publicationList", arrObj[0]);
            session.setAttribute("subscriptionBillList", arrObj[1]);
            session.setAttribute("publicationTypeList", arrObj[2]);
            session.setAttribute("publicationThemeList", arrObj[3]);
            session.setAttribute("publicationStatusList", arrObj[4]);

            return "/jsps/adminPage.jsp";

        } else if (role == 2) {
            UserWindowsService userWindowsService = new UserWindowsService();
            int billId = Integer.valueOf(request.getParameter("billId"));
            session.setAttribute("mapPubNameSubscription", userWindowsService.loadUserWindow(userId, billId)[0]);
            session.setAttribute("subscriptionBillListUser", userWindowsService.loadUserWindow(userId, billId)[1]);

            return "/jsps/userPage.jsp";
        }
        return "/jsps/error.jsp";
    }
}
