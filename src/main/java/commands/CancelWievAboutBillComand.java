package commands;

import beans.SubscriptionBill;
import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelWievAboutBillComand implements Command {
    //    private static final Logger logger = Logger.getLogger(CancelWievAboutBillComand.class);
    private static final Logger logger = LogManager.getLogger(CancelWievAboutBillComand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        User user = (User) session.getAttribute("currentUser");
        if (user.getUserRoleId() == 1) {

            session.setAttribute("currentPage", "path.page.adminPageBills");
            return PageConfigManager.getProperty("path.page.adminPageBills");
        }

        SubscriptionBill bill = (SubscriptionBill) session.getAttribute("selectedBill");
        logger.info("Cancel show about bill # " + bill.getBillNumber());
        return PageConfigManager.getProperty("path.page.userPageBills");
    }
}
