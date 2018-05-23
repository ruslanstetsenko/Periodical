package commands;

import beans.Subscription;
import beans.User;
import resourceBundle.PageConfigManager;
import service.UserWindowsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class CancelCreateSubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        User user = (User) session.getAttribute("currentUser");
        int currentSubStatusId = (Integer) session.getAttribute("currentSubStatusId");
        session.setAttribute("currentSubStatusId", currentSubStatusId);

        UserWindowsService userWindowsService = new UserWindowsService();
        Map<String, Subscription> map = userWindowsService.loadSelectedUserWindow(user.getId(), currentSubStatusId);
        session.setAttribute("mapPubNameSubscription", map);

        session.setAttribute("currentPage", "path.page.userPageSubsc");
        return PageConfigManager.getProperty("path.page.userPageSubsc");
    }
}
