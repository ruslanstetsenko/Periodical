package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelWievSubscriptionCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CancelWievSubscriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        session.setAttribute("currentPubTypeId", 0);
        session.setAttribute("currentPubThemeId", 0);

        logger.info("Cancel edit subscription ");
        return PageConfigManager.getProperty("path.page.userPageSubsc");
    }
}
