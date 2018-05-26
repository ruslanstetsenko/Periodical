package commands;

import beans.PublicationPeriodicyCost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.PublicationService;
import validate.PublicationValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OkEditPublicationCommand implements Command {
//    private static final Logger logger = Logger.getLogger(OkEditPublicationCommand.class);
private static final Logger logger = LogManager.getLogger(OkEditPublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        List<PublicationPeriodicyCost> costBeens = (List<PublicationPeriodicyCost>) session.getAttribute("publicationPeriodicityCostList");
        Map<Integer, String> costs = new LinkedHashMap<>();
        int publicationId = (Integer) session.getAttribute("publicationId");
        String pubName = request.getParameter("pubName");
        String issn = request.getParameter("ISSN");
        String website = request.getParameter("website");
        String setDate = request.getParameter("setDate");
        String publicationType = request.getParameter("type");
        String publicationTheme = request.getParameter("theme");
        String publicationStatus = request.getParameter("status");
        String cost1M = request.getParameter("cost1Month");
        String cost3M = request.getParameter("cost3Months");
        String cost6M = request.getParameter("cost6Months");
        String cost12M = request.getParameter("cost12Months");

        Map<String, Boolean> map = PublicationValidator.validate(pubName, issn, website, setDate, publicationType, publicationStatus, publicationTheme, cost1M, cost3M, cost6M, cost12M);

        if (map.isEmpty()) {
            costs.put(1, cost1M);
            costs.put(3, cost3M);
            costs.put(6, cost6M);
            costs.put(12, cost12M);

            new PublicationService().updatePublication(publicationId, pubName, issn, website, setDate, publicationType, publicationTheme, publicationStatus, costs, costBeens);
            logger.info("Publication " + pubName + " has updated");

            PublicationService publicationService = new PublicationService();
            int pubTypeId = (Integer) session.getAttribute("currentPubTypeId");
            int pubThemeId = (Integer) session.getAttribute("currentPubThemeId");
            int pubStatusId = (Integer) session.getAttribute("currentPubStatusId");

            session.setAttribute("publicationList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId));
            return PageConfigManager.getProperty("path.page.adminPage");
        } else {
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
            request.setAttribute("pubName", pubName);
            request.setAttribute("ISSN", issn);
            request.setAttribute("website", website);
            request.setAttribute("setDate", setDate);
            request.setAttribute("cost1Month", cost1M);
            request.setAttribute("cost3Months", cost3M);
            request.setAttribute("cost6Months", cost6M);
            request.setAttribute("cost12Months", cost12M);

            logger.info("Publication " + pubName + " has not updated, try agan");
            return PageConfigManager.getProperty("path.page.editPublication");
        }
    }
}
