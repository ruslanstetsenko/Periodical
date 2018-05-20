package commands;

import beans.Publication;
import beans.PublicationPeriodicityCost;
import beans.Subscription;
import beans.User;
import resource.PageConfigManager;
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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        User user = (User) session.getAttribute("currentUser");
        PublicationService publicationService = new PublicationService();
        Map<Publication, List<PublicationPeriodicityCost>> map = publicationService.getPublicationWithCosts(0, 0, 1);
        UserWindowsService userWindowsService = new UserWindowsService();
        Map<String, Subscription> subscriptionMap = userWindowsService.loadSelectedUserWindow(user.getId(), 0);
        session.setAttribute("mapAllPubNameSubscription", subscriptionMap);

//        session.setAttribute("currentSubStatusId", 0);
//        session.setAttribute("currentBillPaidId", 0);

        PublicThemeAndTypeWrapper wrapper = publicationService.getPubThemesAndTypes();
        session.setAttribute("publicationListWithCost", map.entrySet());
        session.setAttribute("publicationTypeList", wrapper.getPublicationTypes());
        session.setAttribute("publicationThemeList", wrapper.getPublicationThemes());

        return PageConfigManager.getProperty("path.page.createSubscription");
    }
}
