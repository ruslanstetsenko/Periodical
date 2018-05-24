package commands;

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

import java.sql.Date;
import java.util.List;

public class OkEditPublicationCommand implements Command {
//    private static final Logger logger = Logger.getLogger(OkEditPublicationCommand.class);
private static final Logger logger = LogManager.getLogger(OkEditPublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        int publicationId = (Integer) session.getAttribute("publicationId");
        String pubName = request.getParameter("pubName");
        int issn = Integer.valueOf(request.getParameter("ISSN")) ;
        String website = request.getParameter("website");
        Date setDate = Date.valueOf(request.getParameter("setDate"));
//        try {
//            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("setDate"));
//            setDate = new Date(utilDate.getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        int publicationType = Integer.valueOf(request.getParameter("type"));
        int publicationTheme = Integer.valueOf(request.getParameter("theme"));
        int publicationStatus = Integer.valueOf(request.getParameter("status"));
        List<PublicationPeriodicyCost> costBeens = (List<PublicationPeriodicyCost>) session.getAttribute("publicationPeriodicityCostList");
        String[] costs = request.getParameterValues("cost");

        new PublicationService().updatePublication(publicationId, pubName, issn, website, setDate, publicationType, publicationTheme, publicationStatus, costs, costBeens);

        PublicationService publicationService = new PublicationService();
        int pubTypeId = (Integer) session.getAttribute("currentPubTypeId");
        int pubThemeId = (Integer) session.getAttribute("currentPubThemeId");
        int pubStatusId = (Integer) session.getAttribute("currentPubStatusId");
//        int billPaidId = (Integer) session.getAttribute("currentBillPaidId");

        session.setAttribute("publicationList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId));
//        session.setAttribute("subscriptionBillList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId, billPaidId)[1]);
//        session.setAttribute("publicationTypeList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId, billPaidId)[2]);
//        session.setAttribute("publicationThemeList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId, billPaidId)[3]);
//        session.setAttribute("publicationStatusList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId, billPaidId)[4]);

        logger.info("Publication " + pubName + " has updated");
        return PageConfigManager.getProperty("path.page.adminPage");
    }
}
