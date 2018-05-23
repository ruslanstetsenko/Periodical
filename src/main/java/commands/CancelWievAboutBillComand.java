package commands;

import beans.User;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelWievAboutBillComand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        User user = (User)session.getAttribute("currentUser");
        if (user.getUserRoleId() == 1) {

            session.setAttribute("currentPage", "path.page.adminPageBills");
            return PageConfigManager.getProperty("path.page.adminPageBills");
        }

        session.setAttribute("currentPage", "path.page.userPageBills");
        return PageConfigManager.getProperty("path.page.userPageBills");
    }
}
