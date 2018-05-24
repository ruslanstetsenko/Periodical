package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.UserService;
import wrappers.AboutUserWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAboutUserCommand implements Command {
//    private static final Logger logger = Logger.getLogger(ShowAboutUserCommand.class);
private static final Logger logger = LogManager.getLogger(ShowAboutUserCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        int currentUserId = Integer.valueOf(request.getParameter("currentUserId"));
        AboutUserWrapper wrapper = new UserService().getUserInfo(currentUserId);
        session.setAttribute("user", wrapper.getUser());
        session.setAttribute("userAccount", wrapper.getAccount());
        session.setAttribute("userContactInfo", wrapper.getContactInfo());
        session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
        session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());

        logger.info("Show about user ");
        return PageConfigManager.getProperty("path.page.aboutUser");
    }
}
