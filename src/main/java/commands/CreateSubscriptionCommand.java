package commands;

import beens.Publication;
import beens.PublicationPeriodicityCost;
import beens.Subscription;
import service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateSubscriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return "/jsps/login.jsp";
        }

        PublicationService publicationService = new PublicationService();
        Map<Publication, List<PublicationPeriodicityCost>> map = publicationService.getPublicationWithCosts(0, 0, 1);
        session.setAttribute("currentSubStatusId", 0);
        session.setAttribute("currentBillPaidId", 0);

        List[] arrLists = publicationService.getPubThemesAndTypes();
        session.setAttribute("publicationListWithCost", map.entrySet());
        session.setAttribute("publicationTypeList", arrLists[0]);
        session.setAttribute("publicationThemeList", arrLists[1]);


        return "/jsps/createSubscription.jsp";
    }
}
