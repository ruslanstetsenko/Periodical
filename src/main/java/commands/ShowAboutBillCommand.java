package commands;

import beens.Subscription;
import beens.SubscriptionBill;
import service.SubscriptionBillService;
import service.SubscriptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ShowAboutBillCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int billId = Integer.valueOf(request.getParameter("billId"));
        SubscriptionBill bill = new SubscriptionBillService().getBill(billId);
        Map<String, Subscription> subscriptions = new SubscriptionService().getSubscByBill(billId);

        session.setAttribute("mapSubscriptions", subscriptions);
        session.setAttribute("bill", bill);

        return "/jsps/aboutBill.jsp";
    }
}
