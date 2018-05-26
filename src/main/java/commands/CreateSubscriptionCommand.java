package commands;

import beans.Publication;
import beans.PublicationPeriodicyCost;
import beans.Subscription;
import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.PublicationService;
import service.UserWindowsService;
import wrappers.PublicThemeAndTypeWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CreateSubscriptionCommand implements Command {
    //    private static final Logger logger = Logger.getLogger(CreateSubscriptionCommand.class);
    private static final Logger logger = LogManager.getLogger(CreateSubscriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        User user = (User) session.getAttribute("currentUser");
        PublicationService publicationService = new PublicationService();
        Map<Publication, List<PublicationPeriodicyCost>> map = publicationService.getPublicationWithCosts(0, 0, 1);
        UserWindowsService userWindowsService = new UserWindowsService();
        Map<String, Subscription> subscriptionMap = userWindowsService.loadSelectedUserWindow(user.getId(), 0);
        session.setAttribute("mapAllPubNameSubscription", subscriptionMap);

        PublicThemeAndTypeWrapper wrapper = publicationService.getPubThemesAndTypes();
        session.setAttribute("publicationListWithCost", map.entrySet());
        session.setAttribute("publicationTypeList", wrapper.getPublicationTypes());
        session.setAttribute("publicationThemeList", wrapper.getPublicationThemes());

        logger.info("Start creating new subscription");
        return PageConfigManager.getProperty("path.page.createSubscription");
    }
}
