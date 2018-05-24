package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.PublicationService;
import wrappers.EditPublicationWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditPublicationCommand implements Command {
//    private static final Logger logger = Logger.getLogger("LoginComand");
private static final Logger logger = LogManager.getLogger(EditPublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }

        int publicationId = Integer.valueOf(request.getParameter("publicationId"));
//        System.out.println(publicationId);

//        session.setAttribute("currentPubTypeId", request.getParameter("currentPubTypeId"));
//        session.setAttribute("currentPubThemeId", request.getParameter("currentPubThemeId"));
//        session.setAttribute("currentPubStatusId", request.getParameter("currentPubStatusId"));
//        session.setAttribute("currentBillPaidId", request.getParameter("currentBillPaidId"));

        EditPublicationWrapper wrapper = new PublicationService().editPublication(publicationId);
        session.setAttribute("publicationId", publicationId);
        session.setAttribute("publication", wrapper.getPublication());
        session.setAttribute("publicationTypeList", wrapper.getPublicationTypeList());
        session.setAttribute("publicationThemeList", wrapper.getPublicationThemeList());
        session.setAttribute("publicationStatusList", wrapper.getPublicationStatusList());
//        session.setAttribute("billPaid", Integer.valueOf(request.getParameter("billPaid")));
        session.setAttribute("publicationPeriodicityCostList", wrapper.getPublicationPeriodicyCostList());

//        Publication publication1 = wrapper.getPublication();
//        session.setAttribute("selectedType", publication1.getPublicationTypeId());
//        session.setAttribute("previousPage", "/jsps/adminPage.jsp");
        logger.info("Edit publication " + wrapper.getPublication().getName());
        return PageConfigManager.getProperty("path.page.editPublication");
    }
}
