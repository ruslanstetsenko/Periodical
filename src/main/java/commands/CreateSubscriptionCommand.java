package commands;

import beans.Publication;
import beans.PublicationPeriodicyCost;
import beans.Subscription;
import beans.User;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
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
    private static final Logger LOGGER = LogManager.getLogger(CreateSubscriptionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            LOGGER.error(MessageConfigManager.getProperty("message.error.cantFindUser"));
            session.invalidate();
            return PageConfigManager.getProperty("path.page.login");
        }

        PublicationService publicationService = new PublicationService();
        UserWindowsService userWindowsService = new UserWindowsService();

        try {
            Map<Publication, List<PublicationPeriodicyCost>> map = publicationService.getPublicationWithCosts(0, 0, 1);
            Map<String, Subscription> subscriptionMap = userWindowsService.loadSelectedUserWindow(user.getId(), 0);
            PublicThemeAndTypeWrapper wrapper = publicationService.getPubThemesAndTypes();

            session.setAttribute("mapAllPubNameSubscription", subscriptionMap);
            session.setAttribute("publicationListWithCost", map.entrySet());
            session.setAttribute("publicationTypeList", wrapper.getPublicationTypes());
            session.setAttribute("publicationThemeList", wrapper.getPublicationThemes());

            LOGGER.info("Start creating new subscription");
        } catch (DataBaseWorkException e) {
            session.setAttribute( "errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            session.setAttribute("previousPage", "path.page.userPageSubsc");
            LOGGER.error("Can't start creating new subscription. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        } catch (NullPointerException npe) {
            session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
            session.setAttribute("previousPage", "path.page.userPageSubsc");
            LOGGER.error("Can't start creating new subscription", npe.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        return PageConfigManager.getProperty("path.page.createSubscription");
    }
}
