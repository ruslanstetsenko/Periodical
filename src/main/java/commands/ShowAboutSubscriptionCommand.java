package commands;

import beans.Publication;
import beans.Subscription;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.PublicationService;
import service.SubscriptionService;
import wrappers.FullPublicationInfoWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAboutSubscriptionCommand implements Command {
private static final Logger LOGGER = LogManager.getLogger(ShowAboutSubscriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }
        int currentSubsId = Integer.valueOf(request.getParameter("currentSubsId"));

        try {
            Subscription subscription = new SubscriptionService().getSubscription(currentSubsId);
            Publication publication = new PublicationService().getPublication(subscription.getPublicationId());
            FullPublicationInfoWrapper wrapper = new PublicationService().aboutPublication(publication.getId());

            session.setAttribute("publicationName", publication.getName());

            session.setAttribute("subscriptionDate", subscription.getSubscriptionDate());
            session.setAttribute("subscriptionCost", subscription.getSubscriptionCost());
            session.setAttribute("subscriptionStatusId", subscription.getSubscriptionStatusId());

            session.setAttribute("publication", wrapper.getPublication());
            session.setAttribute("publicationType", wrapper.getPublicationType());
            session.setAttribute("publicationTheme", wrapper.getPublicationTheme());
            session.setAttribute("publicationStatus", wrapper.getPublicationStatus());
            session.setAttribute("publicationPeriodicityCostList", wrapper.getPublicationPeriodicyCostList());

            LOGGER.info("Show about subscription " + wrapper.getPublication().getName());
        } catch (DataBaseWorkException e) {
            request.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            request.setAttribute("previousPage", "path.page.userPageSubsc");
            LOGGER.error("Can't load subscription info. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        } catch (NullPointerException npe) {
            request.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
            request.setAttribute("previousPage", "path.page.userPageSubsc");
            LOGGER.error("Can't load subscription info", npe.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        return PageConfigManager.getProperty("path.page.aboutSubscription");
    }
}
