package commands;

import beans.Account;
import beans.User;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOGGER = LogManager.getLogger(OkLoginComand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String sessionId = session.getId();

        if (session.getAttribute("sessionId") == null) {
            session.setAttribute("sessionId", sessionId);
            LOGGER.info("Session " + session.getId() + " has started");
        }

        LoginService loginService = new LoginService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Account account = null;
        User currentUser = null;

        try {
            account = loginService.checkAccount(login, password);
        } catch (DataBaseWorkException e) {
            session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            session.setAttribute("previousPage", "path.page.login");
            LOGGER.error("Can't get user account. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        if (account != null) {
            try {
                currentUser = loginService.getUser(account);
            } catch (DataBaseWorkException e) {
                session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
                session.setAttribute("previousPage", "path.page.login");
                LOGGER.error("Can't get user. DB error", e.getCause());
                return PageConfigManager.getProperty("path.page.error");
            }
            LOGGER.info("Account was found");
        } else {
            LOGGER.info("Account was not found");
            request.setAttribute("errorLoginMessage", true);
            return PageConfigManager.getProperty("path.page.login");
        }

        if (currentUser != null && currentUser.getUserRoleId() == 1) {

            session.setAttribute("currentUser", currentUser);
            session.setAttribute("currentPubTypeId", 0);
            session.setAttribute("currentPubThemeId", 0);
            session.setAttribute("currentPubStatusId", 0);
            session.setAttribute("currentBillPaidId", 0);
            session.setAttribute("sessionId", sessionId);

            try {
                FullPublicationInfoWrapper wrapper = new PublicationService().getAllPublication();
                List<User> userList = new UserService().getAllUsers();
                session.setAttribute("publicationList", wrapper.getPublicationList());
//                session.setAttribute("subscriptionBillList", wrapper.getSubscriptionBillList());
                session.setAttribute("publicationTypeList", wrapper.getPublicationTypeList());
                session.setAttribute("publicationThemeList", wrapper.getPublicationThemeList());
                session.setAttribute("publicationStatusList", wrapper.getPublicationStatusList());
                session.setAttribute("subscriptionBillListWithUser", wrapper.getSubscriptionBillUserMap().entrySet());//
                session.setAttribute("userList", userList);
                session.setAttribute("loginFormAction", "admin");
            } catch (DataBaseWorkException e) {
                session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
                session.setAttribute("previousPage", "path.page.login");
                LOGGER.error("Can't get data. DB error", e.getCause());
                return PageConfigManager.getProperty("path.page.error");
            } catch (NullPointerException npe) {
                session.setAttribute("errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
                session.setAttribute("previousPage", "path.page.login");
                LOGGER.error("Can't load user info", npe.getCause());
                return PageConfigManager.getProperty("path.page.error");
            }

            LOGGER.info("Admin logined successful");
            return PageConfigManager.getProperty("path.page.adminPage");
        } else if (currentUser != null && currentUser.getUserRoleId() == 2) {
            LoadUserWindowWrapper wrapper = new UserWindowsService().loadUserWindow(currentUser.getId());
            SubscriptionService subscriptionService = new SubscriptionService();
            session.setAttribute("currentUser", currentUser);
            session.setAttribute("currentSubStatusId", 0);
            session.setAttribute("currentBillPaidId", 0);
            session.setAttribute("mapPubNameSubscription", wrapper.getMap());
            session.setAttribute("subscriptionBillList", wrapper.getSubscriptionBillList());
            session.setAttribute("subsStatusList", subscriptionService.getSubsStatusList());
            session.setAttribute("sessionId", sessionId);

            try {
                AboutUserWrapper wrapper1 = new UserService().getUserInfo(currentUser.getId());
                session.setAttribute("user", wrapper1.getUser());
                session.setAttribute("userAccount", wrapper1.getAccount());
                session.setAttribute("userContactInfo", wrapper1.getContactInfo());
                session.setAttribute("userLivingAddress", wrapper1.getLivingAddress());
                session.setAttribute("userPassportIdNumb", wrapper1.getPassportIdentNumber());
                session.setAttribute("loginFormAction", "user");
                session.setAttribute("currentPage", "path.page.userPageSubsc");
            } catch (DataBaseWorkException e) {
                session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
                session.setAttribute("previousPage", "path.page.login");
                LOGGER.error("Can't get data. DB error", e.getCause());
                return PageConfigManager.getProperty("path.page.error");
            } catch (NullPointerException npe) {
                session.setAttribute("errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
                session.setAttribute("previousPage", "path.page.login");
                LOGGER.error("Can't load user info", npe.getCause());
                return PageConfigManager.getProperty("path.page.error");
            }

            LOGGER.info("User " + currentUser.getSurname() + " " + currentUser.getName() + " logined successful");
            return PageConfigManager.getProperty("path.page.userPageSubsc");
        } else {
            request.setAttribute("errorLoginMessage", true);
            LOGGER.info("Unsuccessful authorization, user was not found. Try again");
            return PageConfigManager.getProperty("path.page.login");
        }
    }
}
