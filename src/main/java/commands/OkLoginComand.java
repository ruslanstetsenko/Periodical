package commands;

import beans.User;
import resource.PageConfigManager;
import service.*;
import wrappers.AboutUserWrapper;

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
        User currentUser = new LoginService().enterInAccount(request.getParameter("login"), request.getParameter("password"));

        session.setAttribute("currentUser", currentUser);
//        session.setAttribute("userId", currentUser.getId());
//        session.setAttribute("userRole", currentUser.getUserRoleId());
        session.setAttribute("currentPubTypeId", 0);
        session.setAttribute("currentPubThemeId", 0);
        session.setAttribute("currentPubStatusId", 0);
        session.setAttribute("currentBillPaidId", 0);
        if (currentUser.getUserRoleId() == 1) {
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
        } else if (currentUser.getUserRoleId() == 2) {
            session.setAttribute("sessionId", sessionId);
            UserWindowsService userWindowsService = new UserWindowsService();
            SubscriptionService subscriptionService = new SubscriptionService();
            session.setAttribute("currentSubStatusId", 0);
            session.setAttribute("currentBillPaidId", 0);
            session.setAttribute("mapPubNameSubscription", userWindowsService.loadUserWindow(currentUser.getId())[0]);
            session.setAttribute("subscriptionBillList", userWindowsService.loadUserWindow(currentUser.getId())[1]);
            session.setAttribute("subsStatusList", subscriptionService.getSubsStatusList());

            AboutUserWrapper wrapper = new UserService().getUserInfo(currentUser.getId());
            session.setAttribute("user", wrapper.getUser());
            session.setAttribute("userAccount", wrapper.getAccount());
            session.setAttribute("userContactInfo", wrapper.getContactInfo());
            session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
            session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());

            session.setAttribute("loginFormAction", "user");

            return PageConfigManager.getProperty("path.page.userPageSubsc");
        }
        return PageConfigManager.getProperty("path.page.error");
    }
}
