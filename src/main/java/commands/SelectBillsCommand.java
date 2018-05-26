package commands;

import beans.SubscriptionBill;
import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.SubscriptionBillService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SelectBillsCommand implements Command {
//    private static final Logger logger = Logger.getLogger(SelectBillsCommand.class);
private static final Logger logger = LogManager.getLogger(SelectBillsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        User user = (User) session.getAttribute("currentUser");
        int currentBillPaidId = Integer.valueOf(request.getParameter("currentBillPaidId"));
//        System.out.println("currentBillPaidId " + currentBillPaidId);
        session.setAttribute("currentBillPaidId", currentBillPaidId);
        if (user.getUserRoleId() == 1) {
            List<SubscriptionBill> list = new SubscriptionBillService().selectBillsByStatus(currentBillPaidId);
            session.setAttribute("subscriptionBillList", list);
        } else {
            List<SubscriptionBill> list = new SubscriptionBillService().selectBillsByUserByStatus(user.getId(), currentBillPaidId);
            session.setAttribute("subscriptionBillList", list);
        }

        if (user.getUserRoleId() == 1) {
            return PageConfigManager.getProperty("path.page.adminPageBills");
        }

        logger.info("Bills selected");
        return PageConfigManager.getProperty("path.page.userPageBills");
    }
}
