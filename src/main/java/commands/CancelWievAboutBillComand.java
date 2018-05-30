package commands;

import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelWievAboutBillComand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CancelWievAboutBillComand.class);

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

        if (user.getUserRoleId() == 1) {
//            session.setAttribute("currentPage", "path.page.adminPageBills");
            LOGGER.info("Cancel show about bill");
            return PageConfigManager.getProperty("path.page.adminPageBills");
        }

        LOGGER.info("Cancel show about bill");
        return PageConfigManager.getProperty("path.page.userPageBills");
    }
}
