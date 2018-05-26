package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.PublicationService;
import wrappers.FullPublicationInfoWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAboutPublicationCommand implements Command {
//    private static final Logger logger = Logger.getLogger(ShowAboutPublicationCommand.class);
private static final Logger logger = LogManager.getLogger(ShowAboutPublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }
//        System.out.println("session id = " + session.getId());

        int publicationId = Integer.valueOf(request.getParameter("publicationId"));

//        System.out.println("currentPubTypeId " + session.getAttribute("currentPubTypeId"));
//        System.out.println("currentPubThemeId " + session.getAttribute("currentPubThemeId"));
//        System.out.println("currentPubStatusId " + session.getAttribute("currentPubStatusId"));
//        System.out.println("currentBillPaidId " + session.getAttribute("currentBillPaidId"));
//        System.out.println();

        FullPublicationInfoWrapper wrapper = new PublicationService().aboutPublication(publicationId);
        session.setAttribute("publication", wrapper.getPublication());
        session.setAttribute("publicationType", wrapper.getPublicationType());
        session.setAttribute("publicationTheme", wrapper.getPublicationTheme());
        session.setAttribute("publicationStatus", wrapper.getPublicationStatus());
        session.setAttribute("publicationPeriodicityCostList", wrapper.getPublicationPeriodicyCostList());
//        session.setAttribute("previousPage", "/jsps/adminPage.jsp");

        logger.info("Show about publication " + wrapper.getPublication().getName());
        return PageConfigManager.getProperty("path.page.aboutPublication");
    }
}
