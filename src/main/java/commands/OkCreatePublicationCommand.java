package commands;

import beans.Publication;
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
import java.util.List;
import java.util.Map;

public class OkCreatePublicationCommand implements Command {
//    private static final Logger logger = Logger.getLogger(OkCreatePublicationCommand.class);
private static final Logger logger = LogManager.getLogger(OkCreatePublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

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
            new PublicationService().createPublication(pubName, issn, website, setDate, publicationType, publicationStatus, publicationTheme, cost1M, cost3M, cost6M, cost12M);

            int currentPubTypeId = (Integer) session.getAttribute("currentPubTypeId");
            int currentPubThemeId = (Integer) session.getAttribute("currentPubThemeId");
            int currentPubStatusId = (Integer) session.getAttribute("currentPubStatusId");
            List<Publication> publicationList = new PublicationService().getSelectedPublication(currentPubTypeId, currentPubThemeId, currentPubStatusId);
            session.setAttribute("publicationList", publicationList);

            logger.info("Publication " + pubName + " was created");
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

            logger.info("Publication " + pubName + " was not created. Try again");
            return PageConfigManager.getProperty("path.page.createPublication");
        }

    }
}
