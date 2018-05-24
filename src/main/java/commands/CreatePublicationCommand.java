package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreatePublicationCommand implements Command {
//    private static final Logger logger = Logger.getLogger(CreatePublicationCommand.class);
    private static final Logger logger = LogManager.getLogger(CreatePublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        logger.info("Start creating new publication");
        return PageConfigManager.getProperty("path.page.createPublication");
    }
}
