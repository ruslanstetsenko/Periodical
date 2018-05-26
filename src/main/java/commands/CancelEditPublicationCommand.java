package commands;

import beans.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelEditPublicationCommand implements Command {
    //    private static final Logger logger = Logger.getLogger(CancelEditPublicationCommand.class);
    private static final Logger logger = LogManager.getLogger(CancelEditPublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }
//        HttpSession session = request.getSession(true);
//        System.out.println(session.getAttribute("currentPubTypeId"));
//        int currentPubTypeId = (Integer) session.getAttribute("currentPubTypeId");
//        int currentPubThemeId = (Integer) session.getAttribute("currentPubThemeId");
//        int currentPubStatusId = (Integer) session.getAttribute("currentPubStatusId");
//        int currentBillPaidId = (Integer) session.getAttribute("currentBillPaidId");
////
//        Object[] arr = new AdminWindowsService().selectedloadAdminWindow(currentPubTypeId, currentPubThemeId, currentPubStatusId, currentBillPaidId);
//        session.setAttribute("publicationList", arr[0]);
//        session.setAttribute("subscriptionBillList", arr[1]);
//        session.setAttribute("publicationTypeList", arr[2]);
//        session.setAttribute("publicationThemeList", arr[3]);
//        session.setAttribute("publicationStatusList", arr[4]);
        Publication publication = (Publication) session.getAttribute("publication");
//        logger.info("Cancel edit publivation " + publication.getName());
        logger.info("Cancel edit publivation ");
        return PageConfigManager.getProperty("path.page.adminPage");
    }
}
