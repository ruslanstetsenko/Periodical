package commands;

import beans.Publication;
import beans.PublicationPeriodicyCost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SelectPublicationsCreateSubsWindowCommand implements Command {
//    private static final Logger logger = Logger.getLogger(SelectPublicationsCreateSubsWindowCommand.class);
private static final Logger logger = LogManager.getLogger(SelectPublicationsCreateSubsWindowCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        int currentPubTypeId = Integer.valueOf(request.getParameter("currentPubTypeId"));
        int currentPubThemeId = Integer.valueOf(request.getParameter("currentPubThemeId"));

        List<Publication> publicationList = new PublicationService().selectPublicationsByTypeByTheme(currentPubTypeId, currentPubThemeId);
        session.setAttribute("publicationList", publicationList);
//        session.setAttribute("publicationThemeList", arrLists[1]);
//        System.out.println("publicationList " + publicationList);
//        System.out.println("currentPubTypeId " + currentPubTypeId);
//        System.out.println("currentPubThemeId " + currentPubThemeId);

        session.setAttribute("currentPubTypeId", request.getParameter("currentPubTypeId"));
        session.setAttribute("currentPubThemeId", request.getParameter("currentPubThemeId"));

        PublicationService publicationService = new PublicationService();
        Map<Publication, List<PublicationPeriodicyCost>> map = publicationService.getPublicationWithCosts(currentPubTypeId, currentPubThemeId, 1);
//        List[] arrLists = publicationService.getPubThemesAndTypes();
        session.setAttribute("publicationListWithCost", map.entrySet());
//        session.setAttribute("publicationTypeList", arrLists[0]);
//        session.setAttribute("publicationThemeList", arrLists[1]);

        session.setAttribute("currentPage", "path.page.createSubscription");
        return PageConfigManager.getProperty("path.page.createSubscription");
    }
}
