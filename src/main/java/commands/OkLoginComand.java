package commands;

import service.AdminWindowsService;
import service.LoginService;
import service.SubscriptionService;
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
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return "/index.jsp";
        }
        String sessionId = session.getId();
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
            session.setAttribute("sessionId", sessionId);
            Object[] arrObj = new AdminWindowsService().loadAdminWindow();
            session.setAttribute("publicationList", arrObj[0]);
            session.setAttribute("subscriptionBillList", arrObj[1]);
            session.setAttribute("publicationTypeList", arrObj[2]);
            session.setAttribute("publicationThemeList", arrObj[3]);
            session.setAttribute("publicationStatusList", arrObj[4]);

            return "/jsps/adminPage.jsp";

        } else if (role == 2) {
            session.setAttribute("sessionId", sessionId);
            UserWindowsService userWindowsService = new UserWindowsService();
            SubscriptionService subscriptionService = new SubscriptionService();
            session.setAttribute("currentSubStatusId", 0);
            session.setAttribute("currentBillPaidId", 0);
            session.setAttribute("mapPubNameSubscription", userWindowsService.loadUserWindow(userId)[0]);
            session.setAttribute("subscriptionBillList", userWindowsService.loadUserWindow(userId)[1]);
            session.setAttribute("subsStatusList", subscriptionService.getSubsStatusList());

            return "/jsps/userPage.jsp";
        }
        return "/jsps/error.jsp";
    }
}
