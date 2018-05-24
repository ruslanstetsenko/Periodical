package commands;

import beans.Publication;
import beans.PublicationPeriodicyCost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class OkCreatePublicationCommand implements Command {
//    private static final Logger logger = Logger.getLogger(OkCreatePublicationCommand.class);
private static final Logger logger = LogManager.getLogger(OkCreatePublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        String pubName = request.getParameter("pubName");
        int issn = Integer.valueOf(request.getParameter("ISSN"));
        String website = request.getParameter("website");
        Date setDate = Date.valueOf(request.getParameter("setDate"));
//        try {
//            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("setDate"));
//            setDate = new Date(utilDate.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        int pubType = Integer.valueOf(request.getParameter("type"));
        int pubStatus = Integer.valueOf(request.getParameter("status"));
        int pubTheme = Integer.valueOf(request.getParameter("theme"));
        double cost1M = Double.valueOf(request.getParameter("cost1Month"));
        double cost3M = Double.valueOf(request.getParameter("cost3Months"));
        double cost6M = Double.valueOf(request.getParameter("cost6Months"));
        double cost12M = Double.valueOf(request.getParameter("cost12Months"));
        PublicationPeriodicyCost pubCost1M = new PublicationPeriodicyCost.Builder()
                .setTimesPerYear(1).setCost(cost1M).build();
        PublicationPeriodicyCost pubCost3M = new PublicationPeriodicyCost.Builder()
                .setTimesPerYear(3).setCost(cost3M).build();
        PublicationPeriodicyCost pubCost6M = new PublicationPeriodicyCost.Builder()
                .setTimesPerYear(6).setCost(cost6M).build();
        PublicationPeriodicyCost pubCost12M = new PublicationPeriodicyCost.Builder()
                .setTimesPerYear(12).setCost(cost12M).build();
        PublicationPeriodicyCost[] costs = new PublicationPeriodicyCost[4];
        costs[0] = pubCost1M;
        costs[1] = pubCost3M;
        costs[2] = pubCost6M;
        costs[3] = pubCost12M;
        new PublicationService().createPublication(pubName, issn, website, setDate, pubType, pubStatus, pubTheme, costs);

        int currentPubTypeId = (Integer) session.getAttribute("currentPubTypeId");
        int currentPubThemeId = (Integer) session.getAttribute("currentPubThemeId");
        int currentPubStatusId = (Integer) session.getAttribute("currentPubStatusId");
//        int currentBillPaidId = (Integer) session.getAttribute("currentBillPaidId");
        List<Publication> publicationList = new PublicationService().getSelectedPublication(currentPubTypeId, currentPubThemeId, currentPubStatusId);
        session.setAttribute("publicationList", publicationList);

        logger.info("Publication " + pubName + " was created");
        return PageConfigManager.getProperty("path.page.adminPage");
    }
}
