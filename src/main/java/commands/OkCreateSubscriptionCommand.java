package commands;

import beans.Publication;
import beans.PublicationPeriodicityCost;
import beans.Subscription;
import beans.User;
import resourceBundle.PageConfigManager;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OkCreateSubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        PublicationPeriodicityCost costBean;
        Subscription subscription;
        PublicationPeriodicityCostService publicationPeriodicityCostService = new PublicationPeriodicityCostService();
        PublicationService publicationService = new PublicationService();
        SubscriptionService subscriptionService = new SubscriptionService();
        List<PublicationPeriodicityCost> publicationPeriodicityCostList = new ArrayList<>();
        List<Publication> publicationList = new ArrayList<>();

        User user = (User) session.getAttribute("currentUser");

        List<Integer> periodicyCostId = Arrays.stream(request.getParameterValues("curentCostId"))
                .filter(element -> !element.equals(""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

//        System.out.println("periodicyCostId " + periodicyCostId);

        for (Integer costId : periodicyCostId) {
            costBean = publicationPeriodicityCostService.getPubPeriodicyCost(costId);
//            System.out.println(costBean);
//            System.out.println("periodicyCostId " + periodicyCostId.size());

            publicationPeriodicityCostList.add(costBean);
            publicationList.add(publicationService.getPublication(costBean.getPublicationId()));
        }

        int subsBillId = new SubscriptionBillService().createBill(user.getId(), publicationPeriodicityCostList);

        for (int i = 0; i < publicationList.size(); i++) {
            subscription = new Subscription.Builder()
                    .setSubscriptionDate(new Date(new java.util.Date().getTime()))
                    .setSubscriptionCost(publicationPeriodicityCostList.get(i).getCost())
                    .setPublicationId(publicationList.get(i).getId())
                    .setSubscriptionStatusId(3)
                    .setUsersId(user.getId())
                    .setSubscriptionBillsId(subsBillId).build();
            subscriptionService.createSubscription(subscription);
        }

        int currentSubStatusId = (Integer) session.getAttribute("currentSubStatusId");
//        int currentBillPaidId = (Integer) session.getAttribute("currentBillPaidId");
//        session.setAttribute("currentSubStatusId", currentSubStatusId);
//        session.setAttribute("currentBillPaidId", currentBillPaidId);

        UserWindowsService userWindowsService = new UserWindowsService();
        Map<String, Subscription> map = userWindowsService.loadSelectedUserWindow(user.getId(), currentSubStatusId);
        session.setAttribute("mapPubNameSubscription", map);
//        session.setAttribute("subscriptionBillList", parameters[1]);

        session.setAttribute("currentPage", "path.page.userPageSubsc");
        return PageConfigManager.getProperty("path.page.userPageSubsc");
    }
}
