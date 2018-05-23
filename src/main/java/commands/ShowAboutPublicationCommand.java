package commands;

import resourceBundle.PageConfigManager;
import service.PublicationService;
import wrappers.FullPublicationInfoWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAboutPublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
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
        session.setAttribute("publicationPeriodicityCostList", wrapper.getPublicationPeriodicityCostList());
//        session.setAttribute("previousPage", "/jsps/adminPage.jsp");

        session.setAttribute("currentPage", "path.page.aboutPublication");
        return PageConfigManager.getProperty("path.page.aboutPublication");
    }
}
