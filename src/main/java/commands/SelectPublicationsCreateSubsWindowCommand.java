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

public class SelectPublicationsCreateSubsWindowCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }

        int currentPubTypeId = Integer.valueOf(request.getParameter("currentPubTypeId"));
        int currentPubThemeId = Integer.valueOf(request.getParameter("currentPubThemeId"));

        List<Publication> publicationList = new PublicationService().selectPublicationsByTypeByTheme(currentPubTypeId, currentPubThemeId);
        session.setAttribute("publicationList", publicationList);
//        session.setAttribute("publicationThemeList", arrLists[1]);
        System.out.println("publicationList " + publicationList);
        System.out.println("currentPubTypeId " + currentPubTypeId);
        System.out.println("currentPubThemeId " + currentPubThemeId);

        session.setAttribute("currentPubTypeId", request.getParameter("currentPubTypeId"));
        session.setAttribute("currentPubThemeId", request.getParameter("currentPubThemeId"));

        PublicationService publicationService = new PublicationService();
        Map<Publication, List<PublicationPeriodicityCost>> map = publicationService.getPublicationWithCosts(currentPubTypeId, currentPubThemeId, 1);
//        List[] arrLists = publicationService.getPubThemesAndTypes();
        session.setAttribute("publicationListWithCost", map.entrySet());
//        session.setAttribute("publicationTypeList", arrLists[0]);
//        session.setAttribute("publicationThemeList", arrLists[1]);

        return PageConfigManager.getProperty("path.page.createSubscription");
    }
}