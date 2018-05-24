package commands;

import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelEditUserCommand implements Command {
    //    private static final Logger logger = Logger.getLogger(CancelEditUserCommand.class);
    private static final Logger logger = LogManager.getLogger(CancelEditUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        User user = (User) session.getAttribute("currentUser");
        if (user.getUserRoleId() == 1) {
            return PageConfigManager.getProperty("path.page.users");
        }

        logger.info("Edit user: " + user.getSurname() + " " + user.getName() + " " + user.getLastName());
        return PageConfigManager.getProperty("path.page.aboutUser");
    }
}
