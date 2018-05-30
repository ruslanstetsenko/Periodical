package commands;

import beans.*;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OkCreateSubscriptionCommand implements Command {
private static final Logger LOGGER = LogManager.getLogger(OkCreateSubscriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        List<PublicationPeriodicyCost> publicationPeriodicyCostList = new ArrayList<>();
        List<Publication> publicationList = new ArrayList<>();
        UserWindowsService userWindowsService = new UserWindowsService();
        User user = (User) session.getAttribute("currentUser");
        int currentSubStatusId = (Integer) session.getAttribute("currentSubStatusId");
        int currentBillPaidId = (Integer) session.getAttribute("currentBillPaidId");

        List<Integer> periodicyCostId = Arrays.stream(request.getParameterValues("curentCostId"))
                .filter(element -> !element.equals(""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        try {
            new SubscriptionService().createSubscription(user.getId(), publicationPeriodicyCostList, publicationList, periodicyCostId);
            Map<String, Subscription> map = userWindowsService.loadSelectedUserWindow(user.getId(), currentSubStatusId);
            List<SubscriptionBill> billList = new SubscriptionBillService().selectBillsByUserByStatus(user.getId(), currentBillPaidId);

            if (map != null && billList !=null) {
                session.setAttribute("mapPubNameSubscription", map);
                session.setAttribute("subscriptionBillList", billList);
            } else {
                request.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.loadData"));
                request.setAttribute("previousPage", "path.page.userPageSubsc");
                LOGGER.error("Can't load subscriptions from DB");
                return PageConfigManager.getProperty("path.page.error");
            }

            LOGGER.info("Subscription created");
        } catch (DataBaseWorkException e) {
            request.setAttribute( "errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            request.setAttribute("previousPage", "path.page.userPageSubsc");
            LOGGER.error("Subscription was not created. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        return PageConfigManager.getProperty("path.page.userPageSubsc");
    }
}
