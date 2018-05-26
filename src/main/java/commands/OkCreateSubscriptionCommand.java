package commands;

import beans.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
//    private static final Logger logger = Logger.getLogger(OkCreateSubscriptionCommand.class);
private static final Logger logger = LogManager.getLogger(OkCreateSubscriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        PublicationPeriodicyCost costBean;
        PublicationPeriodicityCostService publicationPeriodicityCostService = new PublicationPeriodicityCostService();
        PublicationService publicationService = new PublicationService();
        List<PublicationPeriodicyCost> publicationPeriodicyCostList = new ArrayList<>();
        List<Publication> publicationList = new ArrayList<>();

        User user = (User) session.getAttribute("currentUser");

        List<Integer> periodicyCostId = Arrays.stream(request.getParameterValues("curentCostId"))
                .filter(element -> !element.equals(""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        System.out.println(periodicyCostId);

//        for (Integer costId : periodicyCostId) {
//            costBean = publicationPeriodicityCostService.getPubPeriodicyCost(costId);
//            publicationPeriodicyCostList.add(costBean);
//            publicationList.add(publicationService.getPublication(costBean.getPublicationId()));
//        }
        System.out.println(publicationList);

        new SubscriptionService().createSubscription(user.getId(), publicationPeriodicyCostList, publicationList, periodicyCostId);

//        int subsBillId = new SubscriptionBillService().createBill(user.getId(), publicationPeriodicyCostList);

//        for (int i = 0; i < publicationList.size(); i++) {
//            subscription = new Subscription.Builder()
//                    .setSubscriptionDate(new Date(new java.util.Date().getTime()))
//                    .setSubscriptionCost(publicationPeriodicyCostList.get(i).getCost())
//                    .setPublicationId(publicationList.get(i).getId())
//                    .setSubscriptionStatusId(3)
//                    .setUsersId(user.getId())
//                    .build();
//            subscriptionService.createSubscription(subscription);
//            logger.info("Subscription " + subscription.toString() + " created");
//        }

        int currentSubStatusId = (Integer) session.getAttribute("currentSubStatusId");
        int currentBillPaidId = (Integer) session.getAttribute("currentBillPaidId");
        UserWindowsService userWindowsService = new UserWindowsService();
        Map<String, Subscription> map = userWindowsService.loadSelectedUserWindow(user.getId(), currentSubStatusId);
        List<SubscriptionBill> billList = new SubscriptionBillService().selectBillsByUserByStatus(user.getId(), currentBillPaidId);
        session.setAttribute("mapPubNameSubscription", map);
        session.setAttribute("subscriptionBillList", billList);

        return PageConfigManager.getProperty("path.page.userPageSubsc");
    }
}
