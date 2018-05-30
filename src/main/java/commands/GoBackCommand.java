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

public class GoBackCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoBackCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String page = (String) session.getAttribute("sessionId");
//        System.out.println(page);

        if (!session.getId().equals(session.getAttribute("sessionId")) && page != null) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        String pageProperties = request.getParameter("previousPage");
        logger.info("Go previous page after DB error, go to " + PageConfigManager.getProperty(pageProperties));
        return PageConfigManager.getProperty(pageProperties);
    }
}
