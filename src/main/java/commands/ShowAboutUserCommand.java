package commands;

import beans.User;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.UserService;
import wrappers.AboutUserWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAboutUserCommand implements Command {
//    private static final Logger LOGGER = Logger.getLogger(ShowAboutUserCommand.class);
private static final Logger LOGGER = LogManager.getLogger(ShowAboutUserCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            LOGGER.error(MessageConfigManager.getProperty("message.error.cantFindUser"));
            session.invalidate();
            return PageConfigManager.getProperty("path.page.login");
        }

        int currentUserId = Integer.valueOf(request.getParameter("currentUserId"));

        try {
            AboutUserWrapper wrapper = new UserService().getUserInfo(currentUserId);
            session.setAttribute("currentUser", wrapper.getUser());
            session.setAttribute("userAccount", wrapper.getAccount());
            session.setAttribute("userContactInfo", wrapper.getContactInfo());
            session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
            session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());

            LOGGER.info("Show about user ");
        } catch (DataBaseWorkException e) {
            session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
            choicePrevPage(session, user);
            LOGGER.error("Can't load user info. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        } catch (NullPointerException npe) {
            session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
            choicePrevPage(session, user);
            LOGGER.error("Can't load user info", npe.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        return PageConfigManager.getProperty("path.page.aboutUser");
    }

    private void choicePrevPage(HttpSession session, User currentUser) {
        if (currentUser.getUserRoleId() == 1) {
            session.setAttribute("previousPage", "path.page.users");
        } else {
            session.setAttribute("previousPage", "path.page.aboutUser");
        }
    }
}
