package commands;

import beens.PublicationPeriodicityCost;
import beens.Subscription;
import service.PublicationPeriodicityCostServive;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OkCreateSubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return "/jsps/login.jsp";
        }

        int userId = (Integer) session.getAttribute("userId");
        List<Integer> publicationsId = Arrays.stream(request.getParameterValues("curentPubid"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        List<Integer> periodicyCostIs = Arrays.stream(request.getParameterValues("curentCostid"))
                .filter(element -> !element.equals(""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        PublicationPeriodicityCostServive costServive = new PublicationPeriodicityCostServive();

        List<Subscription> subscriptionList = new ArrayList<>();
        for (int i = 0; i < publicationsId.size(); i++) {
            subscriptionList.add(new Subscription.Builder().setPublicationId(publicationsId.get(i)).setUsersId(userId).setSubscriptionBillsId(periodicyCostIs.get(i)).setSubscriptionDate(new Date(new java.util.Date().getTime())).setSubscriptionStatusId(3).setSubscriptionCost(costServive.getCostValue(periodicyCostIs.get(i))).build());
        }



        Subscription subscription = new Subscription.Builder().

        return "/jsps/userPage.jsp";
    }
}
