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

public class LogoutCommand implements Command {
//    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);
private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            LOGGER.info("User " + user.getSurname() + " " + user.getName() + " " + user.getLastName() + " log out");
        }
        session.invalidate();
        return PageConfigManager.getProperty("path.page.login");
    }
}
