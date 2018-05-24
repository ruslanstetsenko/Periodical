package commands;

import beans.Publication;
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

public class SelectPublicationsAdminWindowCommand implements Command {
//    private static final Logger logger = Logger.getLogger(SelectPublicationsAdminWindowCommand.class);
private static final Logger logger = LogManager.getLogger(SelectPublicationsAdminWindowCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        int currentPubTypeId = Integer.valueOf(request.getParameter("currentPubTypeId"));
        int currentPubThemeId = Integer.valueOf(request.getParameter("currentPubThemeId"));
        int currentPubStatusId = Integer.valueOf(request.getParameter("currentPubStatusId"));
//        int currentBillPaidId = Integer.valueOf(request.getParameter("currentBillPaidId"));

//        System.out.println("currentPubTypeId = " + currentPubTypeId);

        session.setAttribute("currentPubTypeId", currentPubTypeId);
        session.setAttribute("currentPubThemeId", currentPubThemeId);
        session.setAttribute("currentPubStatusId", currentPubStatusId);
//        session.setAttribute("currentBillPaidId", currentBillPaidId);

//        session.setAttribute("currentPubTypeId", request.getParameter("currentPubTypeId"));
//        session.setAttribute("currentPubThemeId", request.getParameter("currentPubThemeId"));
//        session.setAttribute("currentPubStatusId", request.getParameter("currentPubStatusId"));
//        session.setAttribute("currentBillPaidId", request.getParameter("currentBillPaidId"));

        List<Publication> publicationList = new PublicationService().getSelectedPublication(currentPubTypeId, currentPubThemeId, currentPubStatusId);
        session.setAttribute("publicationList", publicationList);
//        session.setAttribute("subscriptionBillList", arr[1]);
//        session.setAttribute("publicationTypeList", arr[2]);
//        session.setAttribute("publicationThemeList", arr[3]);
//        session.setAttribute("publicationStatusList", arr[4]);
        logger.info("Publications selected");
        return PageConfigManager.getProperty("path.page.adminPage");
    }
}
