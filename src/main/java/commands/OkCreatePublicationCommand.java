package commands;

import beans.PublicationPeriodicityCost;
import resource.PageConfigManager;
import service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OkCreatePublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        String pubName = request.getParameter("pubName");
        int issn = Integer.valueOf(request.getParameter("ISSN"));
        String website = request.getParameter("website");
        Date setDate = new Date(1);
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("setDate"));
            setDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int pubType = Integer.valueOf(request.getParameter("type"));
        int pubStatus = Integer.valueOf(request.getParameter("status"));
        int pubTheme = Integer.valueOf(request.getParameter("theme"));

        System.out.println("pubTheme " + pubTheme);
        System.out.println("pubName " + pubName);

        double cost1M = Double.valueOf(request.getParameter("cost1Month"));
        double cost3M = Double.valueOf(request.getParameter("cost3Months"));
        double cost6M = Double.valueOf(request.getParameter("cost6Months"));
        double cost12M = Double.valueOf(request.getParameter("cost12Months"));
        PublicationPeriodicityCost pubCost1M = new PublicationPeriodicityCost.Builder()
                .setTimesPerYear(1).setCost(cost1M).build();
        PublicationPeriodicityCost pubCost3M = new PublicationPeriodicityCost.Builder()
                .setTimesPerYear(3).setCost(cost3M).build();
        PublicationPeriodicityCost pubCost6M = new PublicationPeriodicityCost.Builder()
                .setTimesPerYear(6).setCost(cost6M).build();
        PublicationPeriodicityCost pubCost12M = new PublicationPeriodicityCost.Builder()
                .setTimesPerYear(12).setCost(cost12M).build();
        PublicationPeriodicityCost[] costs = new PublicationPeriodicityCost[4];
        costs[0] = pubCost1M;
        costs[1] = pubCost3M;
        costs[2] = pubCost6M;
        costs[3] = pubCost12M;
        new PublicationService().createPublication(pubName, issn, website, setDate, pubType, pubStatus, pubTheme, costs);

        int currentPubTypeId = (Integer) session.getAttribute("currentPubTypeId");
        int currentPubThemeId = (Integer) session.getAttribute("currentPubThemeId");
        int currentPubStatusId = (Integer) session.getAttribute("currentPubStatusId");
        int currentBillPaidId = (Integer) session.getAttribute("currentBillPaidId");
//
        Object[] arr = new PublicationService().getSelectedPublication(currentPubTypeId, currentPubThemeId, currentPubStatusId, currentBillPaidId);
        session.setAttribute("publicationList", arr[0]);
        session.setAttribute("subscriptionBillList", arr[1]);
        session.setAttribute("publicationTypeList", arr[2]);
        session.setAttribute("publicationThemeList", arr[3]);
        session.setAttribute("publicationStatusList", arr[4]);

        return PageConfigManager.getProperty("path.page.adminPage");
    }
}
