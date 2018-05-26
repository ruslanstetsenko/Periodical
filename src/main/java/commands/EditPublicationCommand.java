package commands;

import beans.Publication;
import beans.PublicationPeriodicyCost;
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
import java.util.List;

public class EditPublicationCommand implements Command {
//    private static final Logger logger = Logger.getLogger("LoginComand");
private static final Logger logger = LogManager.getLogger(EditPublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        int publicationId = Integer.valueOf(request.getParameter("publicationId"));
//        System.out.println(publicationId);

//        session.setAttribute("currentPubTypeId", request.getParameter("currentPubTypeId"));
//        session.setAttribute("currentPubThemeId", request.getParameter("currentPubThemeId"));
//        session.setAttribute("currentPubStatusId", request.getParameter("currentPubStatusId"));
//        session.setAttribute("currentBillPaidId", request.getParameter("currentBillPaidId"));
//        session.setAttribute("billPaid", Integer.valueOf(request.getParameter("billPaid")));
//        request.setAttribute("publication", wrapper.getPublication());

        EditPublicationWrapper wrapper = new PublicationService().editPublication(publicationId);
        List<PublicationPeriodicyCost> costs = wrapper.getPublicationPeriodicyCostList();
        Publication publication = wrapper.getPublication();

        session.setAttribute("publicationId", publicationId);
        request.setAttribute("pubName", publication.getName());
        request.setAttribute("ISSN", publication.getIssnNumber());
        request.setAttribute("setDate", publication.getRegistrationDate());
        request.setAttribute("website", publication.getWebsite());
        request.setAttribute("publicationTypeList", wrapper.getPublicationTypeList());
        request.setAttribute("publicationThemeList", wrapper.getPublicationThemeList());
        request.setAttribute("publicationStatusList", wrapper.getPublicationStatusList());

        session.setAttribute("publicationPeriodicityCostList", costs);
        for (PublicationPeriodicyCost el : costs) {
            if (el.getTimesPerYear() == 1) {
                request.setAttribute("cost1Month", el.getCost());
            } else if (el.getTimesPerYear() == 3) {
                request.setAttribute("cost3Months", el.getCost());
            } else if (el.getTimesPerYear() == 6) {
                request.setAttribute("cost6Months", el.getCost());
            } else {
                request.setAttribute("cost12Months", el.getCost());
            }
        }

//        Publication publication1 = wrapper.getPublication();
//        session.setAttribute("selectedType", publication1.getPublicationTypeId());
//        session.setAttribute("previousPage", "/jsps/adminPage.jsp");
        logger.info("Edit publication " + wrapper.getPublication().getName());
        return PageConfigManager.getProperty("path.page.editPublication");
    }
}
