package commands;

import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.PublicationService;
import wrappers.FullPublicationInfoWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAboutPublicationCommand implements Command {
private static final Logger LOGGER = LogManager.getLogger(ShowAboutPublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        int publicationId = Integer.valueOf(request.getParameter("publicationId"));

        try {
            FullPublicationInfoWrapper wrapper = new PublicationService().aboutPublication(publicationId);
            session.setAttribute("publication", wrapper.getPublication());
            session.setAttribute("publicationType", wrapper.getPublicationType());
            session.setAttribute("publicationTheme", wrapper.getPublicationTheme());
            session.setAttribute("publicationStatus", wrapper.getPublicationStatus());
            session.setAttribute("publicationPeriodicityCostList", wrapper.getPublicationPeriodicyCostList());
            LOGGER.info("Show about publication " + wrapper.getPublication().getName());
        } catch (DataBaseWorkException e) {
            session.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            session.setAttribute("previousPage", "path.page.adminPage");
            LOGGER.error("Can't load publicationss. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        } catch (NullPointerException npe) {
            session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
            session.setAttribute("previousPage", "path.page.adminPage");
            LOGGER.error("Can't load publications", npe.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        return PageConfigManager.getProperty("path.page.aboutPublication");
    }
}
