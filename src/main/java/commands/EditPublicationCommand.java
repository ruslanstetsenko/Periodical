package commands;

import beens.Publication;
import service.PublicationService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditPublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        int publicationId = Integer.valueOf(request.getParameter("publicationId"));
//        System.out.println(publicationId);

//        session.setAttribute("currentPubTypeId", request.getParameter("currentPubTypeId"));
//        session.setAttribute("currentPubThemeId", request.getParameter("currentPubThemeId"));
//        session.setAttribute("currentPubStatusId", request.getParameter("currentPubStatusId"));
//        session.setAttribute("currentBillPaidId", request.getParameter("currentBillPaidId"));

        Object[] publication = new PublicationService().editPublication(publicationId);
        session.setAttribute("publicationId", publicationId);
        session.setAttribute("publication", publication[0]);
        session.setAttribute("publicationTypeList", publication[1]);
        session.setAttribute("publicationThemeList", publication[2]);
        session.setAttribute("publicationStatusList", publication[3]);
//        session.setAttribute("billPaid", Integer.valueOf(request.getParameter("billPaid")));
        session.setAttribute("publicationPeriodicityCostList", publication[4]);

        Publication publication1 = (Publication)publication[0];
        session.setAttribute("selectedType", publication1.getPublicationTypeId());
        session.setAttribute("previousPage", "/jsps/adminPage.jsp");

        return "/jsps/editPublication.jsp";
    }
}
