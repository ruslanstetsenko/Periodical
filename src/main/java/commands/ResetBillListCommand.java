package commands;

import beans.SubscriptionBill;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.SubscriptionBillService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ResetBillListCommand implements Command {
private static final Logger LOGGER = LogManager.getLogger(ResetBillListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }
        session.setAttribute("currentBillPaidId", 0);
        try {
            List<SubscriptionBill> list = new SubscriptionBillService().selectBillsByStatus(0);
            if (list != null) {
                session.setAttribute("subscriptionBillList", list);
            } else {
                session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
                session.setAttribute("previousPage", "path.page.adminPage");
                LOGGER.error("Can't load bills informations");
                return PageConfigManager.getProperty("path.page.error");
            }
        } catch (DataBaseWorkException e) {
            session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            session.setAttribute("previousPage", "path.page.login");
            LOGGER.error("New user was not created. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        return PageConfigManager.getProperty("path.page.adminPage");
    }
}
