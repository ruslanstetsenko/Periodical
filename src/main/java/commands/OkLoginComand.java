package commands;

import beans.Account;
import beans.Publication;
import beans.User;
import dao.implementations.PublicationDaoImpl;
import exceptions.DataBaseWorkException;
import exceptions.ErrorMassageException;
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
    //    private static final Logger logger = Logger.getLogger(OkLoginComand.class);
//    static {
//        System.setProperty("log4j2.configurationFile", "resources/log4j2.xml");
//    }

    private static final Logger logger = LogManager.getLogger(OkLoginComand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("sessionId") == null) {
            session.setAttribute("sessionId", session.getId());
            logger.info("Session " + session.getId() + " has started");
        }

        String sessionId = session.getId();
        LoginService loginService = new LoginService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Account account;
        User currentUser;
        try {
            account = loginService.checkAccount(login, password);
        } catch (DataBaseWorkException e) {
            request.setAttribute( "errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            request.setAttribute("previousPage", "path.page.login");
            return PageConfigManager.getProperty("path.page.error");
        }

        if (account != null) {
            currentUser = loginService.getUser(account);
            logger.info("Account was found");
        } else {
            logger.info("Account was not found");
            request.setAttribute("errorLoginMessage", true);
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

                logger.info("Admin logined successful");
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

                logger.info("User " + currentUser.getSurname() + " " + currentUser.getName() + " logined successful");
                return PageConfigManager.getProperty("path.page.userPageSubsc");
            }
        }
        request.setAttribute("errorLoginMessage", true);

        session.setAttribute("currentPage", "path.page.error");
        logger.info("Unsuccessful authorization");
        return PageConfigManager.getProperty("path.page.error");
    }
}
