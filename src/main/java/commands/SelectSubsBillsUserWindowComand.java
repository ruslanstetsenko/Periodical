package commands;

import resource.PageConfigManager;
import service.UserWindowsService;

//import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SelectSubsBillsUserWindowComand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        int userId = (Integer) session.getAttribute("userId");
        int currentSubStatusId = Integer.valueOf(request.getParameter("currentSubStatusId"));
        int currentBillPaidId = Integer.valueOf(request.getParameter("currentBillPaidId"));
        session.setAttribute("currentSubStatusId", currentSubStatusId);
        session.setAttribute("currentBillPaidId", currentBillPaidId);

        UserWindowsService userWindowsService = new UserWindowsService();
        Object[] parameters = userWindowsService.loadSelectedUserWindow(userId, currentSubStatusId, currentBillPaidId);
        session.setAttribute("mapPubNameSubscription", parameters[0]);
        session.setAttribute("subscriptionBillList", parameters[1]);

        return PageConfigManager.getProperty("path.page.userPage");
    }
}
