package commands;

import beans.Subscription;
import beans.SubscriptionBill;
import beans.User;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
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
private static final Logger LOGGER = LogManager.getLogger(ShowAboutBillCommand.class);

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

        int currentBillPaidId = Integer.valueOf(request.getParameter("currentBillPaidId"));
        try {
            SubscriptionBill selectedBill = new SubscriptionBillService().getBill(currentBillPaidId);
            Map<String, Subscription> subscriptions = new SubscriptionService().getSubscByBill(currentBillPaidId);
            if (selectedBill !=null && subscriptions != null) {
                session.setAttribute("mapSubscriptions", subscriptions);
                session.setAttribute("selectedBill", selectedBill);
            } else {
                request.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
                choicePreviousPage(request, user);
                LOGGER.info("Can't show about bill");
                return PageConfigManager.getProperty("path.page.error");
            }
            LOGGER.info("Show about bill # " + selectedBill.getBillNumber());
        } catch (DataBaseWorkException e) {
            request.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            choicePreviousPage(request, user);
            LOGGER.error("Can't load bill info. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        return PageConfigManager.getProperty("path.page.aboutBill");
    }

    private void choicePreviousPage(HttpServletRequest request, User user) {
        if (user.getUserRoleId() == 1) {
            request.setAttribute("previousPage", "path.page.adminPageBills");
        } else {
            request.setAttribute("previousPage", "path.page.userPageBills");
        }
    }
}
