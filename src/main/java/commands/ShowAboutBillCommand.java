package commands;

import beans.Subscription;
import beans.SubscriptionBill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.SubscriptionBillService;
import service.SubscriptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class ShowAboutBillCommand implements Command {
//    private static final Logger logger = Logger.getLogger(ShowAboutBillCommand.class);
private static final Logger logger = LogManager.getLogger(ShowAboutBillCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        int currentBillPaidId = Integer.valueOf(request.getParameter("currentBillPaidId"));
        SubscriptionBill selectedBill = new SubscriptionBillService().getBill(currentBillPaidId);
        Map<String, Subscription> subscriptions = new SubscriptionService().getSubscByBill(currentBillPaidId);

        session.setAttribute("mapSubscriptions", subscriptions);
        session.setAttribute("selectedBill", selectedBill);

        logger.info("Show about bill # " + selectedBill.getBillNumber());
        return PageConfigManager.getProperty("path.page.aboutBill");
    }
}
