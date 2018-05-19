package commands;

import beans.User;
import resource.PageConfigManager;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OkLoginComand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        System.out.println("OK login comang " + session.getId());
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
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
            List[] arrLists = new PublicationService().getAllPublication();
            List<User> userList = new UserService().getAllUsers();
            session.setAttribute("publicationList", arrLists[0]);
            session.setAttribute("subscriptionBillList", arrLists[1]);
            session.setAttribute("publicationTypeList", arrLists[2]);
            session.setAttribute("publicationThemeList", arrLists[3]);
            session.setAttribute("publicationStatusList", arrLists[4]);
            session.setAttribute("userList", userList);
            session.setAttribute("loginFormAction", "admin");

            return PageConfigManager.getProperty("path.page.adminPage");
        } else if (role == 2) {
            session.setAttribute("sessionId", sessionId);
            UserWindowsService userWindowsService = new UserWindowsService();
            SubscriptionService subscriptionService = new SubscriptionService();
            session.setAttribute("currentSubStatusId", 0);
            session.setAttribute("currentBillPaidId", 0);
            session.setAttribute("mapPubNameSubscription", userWindowsService.loadUserWindow(userId)[0]);
            session.setAttribute("subscriptionBillList", userWindowsService.loadUserWindow(userId)[1]);
            session.setAttribute("subsStatusList", subscriptionService.getSubsStatusList());
            session.setAttribute("loginFormAction", "user");

            return PageConfigManager.getProperty("path.page.userPage");
        }
        return PageConfigManager.getProperty("path.page.users");
    }
}
