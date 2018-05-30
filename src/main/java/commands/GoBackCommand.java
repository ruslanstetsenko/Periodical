package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoBackCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GoBackCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String page = (String) session.getAttribute("sessionId");

        if (!session.getId().equals(session.getAttribute("sessionId")) && page != null) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        String pageProperties = (String) session.getAttribute("previousPage");
        LOGGER.info("Go previous page after DB error, go to " + PageConfigManager.getProperty(pageProperties));
        session.removeAttribute("previousPage");
        session.removeAttribute("errorMessage");
        return PageConfigManager.getProperty(pageProperties);
    }
}
