package commands;

import beans.SubscriptionBill;
import resource.PageConfigManager;
import service.SubscriptionBillService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ResetBillListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }
        session.setAttribute("currentBillPaidId", 0);
        List<SubscriptionBill> list = new SubscriptionBillService().selectBillsByStatus(0);
        session.setAttribute("subscriptionBillList", list);

        return PageConfigManager.getProperty("path.page.adminPage");
    }
}
