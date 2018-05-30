package commands;

import beans.Subscription;
import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.UserWindowsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class CancelCreateSubscriptionCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CancelCreateSubscriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

//        User user = (User) session.getAttribute("currentUser");
//        Integer currentSubStatusId = (Integer) session.getAttribute("currentSubStatusId");
//
//        if (user == null) {
//            LOGGER.error("Unexpected exception, user was not found");
//            return PageConfigManager.getProperty("path.page.login");
//        }
//
//        if (currentSubStatusId != null) {
//            session.setAttribute("currentSubStatusId", currentSubStatusId);
//        }
//
//        UserWindowsService userWindowsService = new UserWindowsService();
//        Map<String, Subscription> map = userWindowsService.loadSelectedUserWindow(user.getId(), currentSubStatusId);
//        session.setAttribute("mapPubNameSubscription", map);

        LOGGER.info("Cancel create subscription");
        return PageConfigManager.getProperty("path.page.userPageSubsc");
    }
}
