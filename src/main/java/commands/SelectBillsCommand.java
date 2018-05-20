package commands;

import beans.SubscriptionBill;
import beans.User;
import resource.PageConfigManager;
import service.SubscriptionBillService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SelectBillsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        User user = (User) session.getAttribute("currentUser");
        int currentBillPaidId = Integer.valueOf(request.getParameter("currentBillPaidId"));
        System.out.println("currentBillPaidId " + currentBillPaidId);
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
        return PageConfigManager.getProperty("path.page.userPageBills");
    }
}
