package commands;

import service.PublicationService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowAboutPublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int publicationId = Integer.valueOf(request.getParameter("publicationId"));

//        System.out.println("currentPubTypeId " + session.getAttribute("currentPubTypeId"));
//        System.out.println("currentPubThemeId " + session.getAttribute("currentPubThemeId"));
//        System.out.println("currentPubStatusId " + session.getAttribute("currentPubStatusId"));
//        System.out.println("currentBillPaidId " + session.getAttribute("currentBillPaidId"));
//        System.out.println();

        Object[] publication = new PublicationService().aboutPublication(publicationId);
        session.setAttribute("publication", publication[0]);
        session.setAttribute("publicationType", publication[1]);
        session.setAttribute("publicationTheme", publication[2]);
        session.setAttribute("publicationStatus", publication[3]);
        session.setAttribute("publicationPeriodicityCostList", publication[4]);
        session.setAttribute("previousPage", "/jsps/adminPage.jsp");

        return "/jsps/aboutPublication.jsp";
    }
}
