package commands;

import beans.Subscription;
import beans.User;
import resource.PageConfigManager;
import service.UserWindowsService;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class SelectSubsUserWindowComand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        User user = (User) session.getAttribute("currentUser");
        int userId = user.getId();
        int currentSubStatusId = Integer.valueOf(request.getParameter("currentSubStatusId"));
        session.setAttribute("currentSubStatusId", currentSubStatusId);

        UserWindowsService userWindowsService = new UserWindowsService();
        Map<String, Subscription> map = userWindowsService.loadSelectedUserWindow(userId, currentSubStatusId);
        session.setAttribute("mapPubNameSubscription", map);

        return PageConfigManager.getProperty("path.page.userPageSubsc");
    }
}
