package commands;

import beans.Publication;
import beans.PublicationPeriodicyCost;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SelectPublicationsCreateSubsWindowCommand implements Command {
private static final Logger LOGGER = LogManager.getLogger(SelectPublicationsCreateSubsWindowCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        int currentPubTypeId = Integer.valueOf(request.getParameter("currentPubTypeId"));
        int currentPubThemeId = Integer.valueOf(request.getParameter("currentPubThemeId"));
        PublicationService publicationService = new PublicationService();

        try {
            List<Publication> publicationList = new PublicationService().selectPublicationsByTypeByTheme(currentPubTypeId, currentPubThemeId);
            Map<Publication, List<PublicationPeriodicyCost>> map = publicationService.getPublicationWithCosts(currentPubTypeId, currentPubThemeId, 1);

            if (publicationList != null && map != null) {
                session.setAttribute("publicationList", publicationList);
                session.setAttribute("currentPubTypeId", currentPubTypeId);
                session.setAttribute("currentPubThemeId", currentPubThemeId);
                session.setAttribute("publicationListWithCost", map.entrySet());
            } else {
                request.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
                request.setAttribute("previousPage", "path.page.userPageSubsc");
                LOGGER.error("Can't load publications");
                return PageConfigManager.getProperty("path.page.error");
            }
        } catch (DataBaseWorkException e) {
            request.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            request.setAttribute("previousPage", "path.page.userPageSubsc");
            LOGGER.error("Can't load publications. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        LOGGER.info("Publications selected.");
        return PageConfigManager.getProperty("path.page.createSubscription");
    }
}
