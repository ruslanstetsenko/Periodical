package commands;

import beans.Subscription;
import beans.SubscriptionBill;
import resource.PageConfigManager;
import service.SubscriptionBillService;
import service.SubscriptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ShowAboutBillCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }
//        System.out.println("session id = " + session.getId());

        int currentBillPaidId = Integer.valueOf(request.getParameter("currentBillPaidId"));
        SubscriptionBill selectedBill = new SubscriptionBillService().getBill(currentBillPaidId);
        Map<String, Subscription> subscriptions = new SubscriptionService().getSubscByBill(currentBillPaidId);

        session.setAttribute("mapSubscriptions", subscriptions);
        session.setAttribute("selectedBill", selectedBill);

        return PageConfigManager.getProperty("path.page.aboutBill");
    }
}
