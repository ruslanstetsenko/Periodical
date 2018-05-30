package commands;

import beans.SubscriptionBill;
import beans.User;
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

public class SelectBillsCommand implements Command {
private static final Logger LOGGER = LogManager.getLogger(SelectBillsCommand.class);

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
        session.setAttribute("currentBillPaidId", currentBillPaidId);
        if (user.getUserRoleId() == 1) {
            try {
                List<SubscriptionBill> list = new SubscriptionBillService().selectBillsByStatus(currentBillPaidId);
                if (list != null) {
                    session.setAttribute("subscriptionBillList", list);
                } else {
                    session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
                    session.setAttribute("previousPage", "path.page.adminPageBills");
                    LOGGER.error("Can't load bills");
                    return PageConfigManager.getProperty("path.page.error");
                }
            } catch (DataBaseWorkException e) {
                session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
                session.setAttribute("previousPage", "path.page.adminPageBills");
                LOGGER.error("Can't load bills. DB error", e.getCause());
                return PageConfigManager.getProperty("path.page.error");
            }

            LOGGER.info("Bills selected");
            return PageConfigManager.getProperty("path.page.adminPageBills");
        } else if (user.getUserRoleId() == 2) {
            try {
                List<SubscriptionBill> list = new SubscriptionBillService().selectBillsByUserByStatus(user.getId(), currentBillPaidId);
                if (list != null) {
                    session.setAttribute("subscriptionBillList", list);
                } else {
                    session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
                    session.setAttribute("previousPage", "path.page.userPageBills");
                    LOGGER.error("Can't load bills");
                    return PageConfigManager.getProperty("path.page.error");
                }
            } catch (DataBaseWorkException e) {
                session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
                session.setAttribute("previousPage", "path.page.userPageBills");
                LOGGER.error("Can't load bills. DB error", e.getCause());
                return PageConfigManager.getProperty("path.page.error");
            }

            LOGGER.info("Bills selected");
            return PageConfigManager.getProperty("path.page.userPageBills");
        } else {
            LOGGER.info(MessageConfigManager.getProperty("message.error.cantFindUser"));
            session.invalidate();
            return PageConfigManager.getProperty("path.page.login");
        }
    }
}
