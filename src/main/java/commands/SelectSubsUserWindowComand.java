package commands;

import beans.Subscription;
import beans.User;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.UserWindowsService;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class SelectSubsUserWindowComand implements Command {
private static final Logger LOGGER = LogManager.getLogger(SelectSubsUserWindowComand.class);

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
        int userId = user.getId();
        int currentSubStatusId = Integer.valueOf(request.getParameter("currentSubStatusId"));
        session.setAttribute("currentSubStatusId", currentSubStatusId);

        UserWindowsService userWindowsService = new UserWindowsService();

        try {
            Map<String, Subscription> map = userWindowsService.loadSelectedUserWindow(userId, currentSubStatusId);
            if (map != null) {
                session.setAttribute("mapPubNameSubscription", map);
            } else {
                session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
                session.setAttribute("previousPage", "path.page.userPageSubsc");
                LOGGER.error("Can't load subscriptions");
                return PageConfigManager.getProperty("path.page.error");
            }
        } catch (DataBaseWorkException e) {
            session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            session.setAttribute("previousPage", "path.page.userPageSubsc");
            LOGGER.error("Can't load subscriptions. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        LOGGER.info("Subscription selected");
        return PageConfigManager.getProperty("path.page.userPageSubsc");
    }
}
