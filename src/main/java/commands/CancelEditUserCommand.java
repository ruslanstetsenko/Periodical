package commands;

import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelEditUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CancelEditUserCommand.class);

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
        if (user.getUserRoleId() == 1) {
            LOGGER.info("Cancel edit user: " + user.getSurname() + " " + user.getName() + " " + user.getLastName());
            return PageConfigManager.getProperty("path.page.users");
        }

        LOGGER.info("Cancel edit user: " + user.getSurname() + " " + user.getName() + " " + user.getLastName());
        return PageConfigManager.getProperty("path.page.aboutUser");
    }
}
