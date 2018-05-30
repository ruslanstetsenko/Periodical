package commands;

import beans.Publication;
import beans.PublicationPeriodicyCost;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.PublicationService;
import wrappers.EditPublicationWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EditPublicationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditPublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        String publicationName;
        int publicationId = Integer.valueOf(request.getParameter("publicationId"));

        try {
            EditPublicationWrapper wrapper = new PublicationService().editPublication(publicationId);
            List<PublicationPeriodicyCost> costs = wrapper.getPublicationPeriodicyCostList();
            Publication publication = wrapper.getPublication();
            publicationName = wrapper.getPublication().getName();
            session.setAttribute("publicationId", publicationId);
            request.setAttribute("pubName", publication.getName());
            request.setAttribute("ISSN", publication.getIssnNumber());
            request.setAttribute("setDate", publication.getRegistrationDate());
            request.setAttribute("website", publication.getWebsite());
            request.setAttribute("publicationTypeList", wrapper.getPublicationTypeList());
            request.setAttribute("publicationThemeList", wrapper.getPublicationThemeList());
            request.setAttribute("publicationStatusList", wrapper.getPublicationStatusList());
            session.setAttribute("publicationPeriodicityCostList", costs);
            setCosts(request, costs);

            LOGGER.info("Edit publication started " + publicationName);
        } catch (DataBaseWorkException e) {
            request.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            request.setAttribute("previousPage", "path.page.adminPage");
            LOGGER.error("Can't start edit publication. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        } catch (NullPointerException npe) {
            request.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
            request.setAttribute("previousPage", "path.page.adminPage");
            LOGGER.error("Can't start edit publication", npe.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        return PageConfigManager.getProperty("path.page.editPublication");
    }

    private void setCosts(HttpServletRequest request, List<PublicationPeriodicyCost> costs) {
        for (PublicationPeriodicyCost el : costs) {
            if (el.getTimesPerYear() == 1) {
                request.setAttribute("cost1Month", el.getCost());
            } else if (el.getTimesPerYear() == 3) {
                request.setAttribute("cost3Months", el.getCost());
            } else if (el.getTimesPerYear() == 6) {
                request.setAttribute("cost6Months", el.getCost());
            } else {
                request.setAttribute("cost12Months", el.getCost());
            }
        }
    }
}
