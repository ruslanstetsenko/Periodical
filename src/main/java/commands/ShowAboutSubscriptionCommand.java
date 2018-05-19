package commands;

import beans.Publication;
import beans.Subscription;
import resource.PageConfigManager;
import service.PublicationService;
import service.SubscriptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAboutSubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }
        int currentSubsId = Integer.valueOf(request.getParameter("currentSubsId"));

        Subscription subscription = new SubscriptionService().getSubscription(currentSubsId);
        Publication publication = new PublicationService().getPublication(subscription.getPublicationId());
        session.setAttribute("publicationName", publication.getName());
        session.setAttribute("subscriptionDate", subscription.getSubscriptionDate());
        session.setAttribute("subscriptionCost", subscription.getSubscriptionCost());
        session.setAttribute("subscriptionStatusId", subscription.getSubscriptionStatusId());

        Object[] publicationParam = new PublicationService().aboutPublication(publication.getId());
        session.setAttribute("publication", publicationParam[0]);
        session.setAttribute("publicationType", publicationParam[1]);
        session.setAttribute("publicationTheme", publicationParam[2]);
        session.setAttribute("publicationStatus", publicationParam[3]);
        session.setAttribute("publicationPeriodicityCostList", publicationParam[4]);

        return PageConfigManager.getProperty("path.page.aboutSubscription");
    }
}
