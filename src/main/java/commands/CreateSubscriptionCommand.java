package commands;

import beens.Publication;
import beens.PublicationPeriodicityCost;
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
        Map<Publication, List<PublicationPeriodicityCost>> map = publicationService.getPublicationWithCosts();
        List[] arrLists = publicationService.getPubThemesAndTypes();
//        List[] arrLists = new PublicationService().getAllPublication();
        session.setAttribute("publicationListWithCost", map.entrySet());
        session.setAttribute("publicationTypeList", arrLists[0]);
        session.setAttribute("publicationThemeList", arrLists[1]);

//        Set<> set = map.entrySet();
//        for (Map.Entry<Publication, List<PublicationPeriodicityCost>> entry : map.entrySet()) {
//            entry.ge
//        }

        return "/jsps/createSubscription.jsp";
    }
}
