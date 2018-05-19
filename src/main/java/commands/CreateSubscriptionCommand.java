package commands;

import beans.Publication;
import beans.PublicationPeriodicityCost;
import resource.PageConfigManager;
import service.PublicationService;

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

        PublicationService publicationService = new PublicationService();
        Map<Publication, List<PublicationPeriodicityCost>> map = publicationService.getPublicationWithCosts(0, 0, 1);
        session.setAttribute("currentSubStatusId", 0);
        session.setAttribute("currentBillPaidId", 0);

        List[] arrLists = publicationService.getPubThemesAndTypes();
        session.setAttribute("publicationListWithCost", map.entrySet());
        session.setAttribute("publicationTypeList", arrLists[0]);
        session.setAttribute("publicationThemeList", arrLists[1]);

        return PageConfigManager.getProperty("path.page.createSubscription");
    }
}
