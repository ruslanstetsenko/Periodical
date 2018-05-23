package commands;

import beans.Account;
import beans.User;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.*;
import wrappers.AboutUserWrapper;
import wrappers.FullPublicationInfoWrapper;
import wrappers.LoadUserWindowWrapper;

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

            session.setAttribute("currentPage", "path.page.index");
            return PageConfigManager.getProperty("path.page.index");
        }

        String sessionId = session.getId();
        LoginService loginService = new LoginService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Account account = loginService.checkAccount(login, password);
        User currentUser = new User();

        if (account != null) {
            currentUser = loginService.getUser(account);
        } else {
            String errorMessage = MessageConfigManager.getProperty("message.errorLogin");
            request.setAttribute("errorLoginMessage", errorMessage);
//            session.setAttribute("login", login);
//            session.setAttribute("password", password);
            return PageConfigManager.getProperty("path.page.login");
        }

        if (currentUser != null) {
            session.setAttribute("currentUser", currentUser);
            session.setAttribute("currentPubTypeId", 0);
            session.setAttribute("currentPubThemeId", 0);
            session.setAttribute("currentPubStatusId", 0);
            session.setAttribute("currentBillPaidId", 0);
            if (currentUser.getUserRoleId() == 1) {
                session.setAttribute("sessionId", sessionId);
                FullPublicationInfoWrapper wrapper = new PublicationService().getAllPublication();
                List<User> userList = new UserService().getAllUsers();
                session.setAttribute("publicationList", wrapper.getPublicationList());
                session.setAttribute("subscriptionBillList", wrapper.getSubscriptionBillList());
                session.setAttribute("publicationTypeList", wrapper.getPublicationTypeList());
                session.setAttribute("publicationThemeList", wrapper.getPublicationThemeList());
                session.setAttribute("publicationStatusList", wrapper.getPublicationStatusList());
                session.setAttribute("userList", userList);

                session.setAttribute("loginFormAction", "admin");
                session.setAttribute("currentPage", "path.page.adminPage");
                return PageConfigManager.getProperty("path.page.adminPage");
            } else if (currentUser.getUserRoleId() == 2) {
                session.setAttribute("sessionId", sessionId);
                LoadUserWindowWrapper wrapper = new UserWindowsService().loadUserWindow(currentUser.getId());
                SubscriptionService subscriptionService = new SubscriptionService();
                session.setAttribute("currentSubStatusId", 0);
                session.setAttribute("currentBillPaidId", 0);
                session.setAttribute("mapPubNameSubscription", wrapper.getMap());
                session.setAttribute("subscriptionBillList", wrapper.getSubscriptionBillList());
                session.setAttribute("subsStatusList", subscriptionService.getSubsStatusList());

                AboutUserWrapper wrapper1 = new UserService().getUserInfo(currentUser.getId());
                session.setAttribute("user", wrapper1.getUser());
                session.setAttribute("userAccount", wrapper1.getAccount());
                session.setAttribute("userContactInfo", wrapper1.getContactInfo());
                session.setAttribute("userLivingAddress", wrapper1.getLivingAddress());
                session.setAttribute("userPassportIdNumb", wrapper1.getPassportIdentNumber());

                session.setAttribute("loginFormAction", "user");
                session.setAttribute("currentPage", "path.page.userPageSubsc");
                return PageConfigManager.getProperty("path.page.userPageSubsc");
            }
        }
        String errorMessage = MessageConfigManager.getProperty("message.userNotFound");
        request.setAttribute("errorFoundUser", errorMessage);

        session.setAttribute("currentPage", "path.page.error");
        return PageConfigManager.getProperty("path.page.error");
    }
}
