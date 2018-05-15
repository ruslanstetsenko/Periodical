package commands;

import beens.PublicationPeriodicityCost;
import service.AdminWindowsService;
import service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OkEditPublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return "/jsps/login.jsp";
        }

        int publicationId = (Integer) session.getAttribute("publicationId");
        String pubName = request.getParameter("pubName");
        int issn = Integer.valueOf(request.getParameter("ISSN")) ;
        String website = request.getParameter("website");
        Date setDate = new Date(1);
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("setDate"));
            setDate = new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int publicationType = Integer.valueOf(request.getParameter("type"));
        int publicationTheme = Integer.valueOf(request.getParameter("theme"));
        int publicationStatus = Integer.valueOf(request.getParameter("status"));
        List<PublicationPeriodicityCost> costBeens = (List<PublicationPeriodicityCost>) session.getAttribute("publicationPeriodicityCostList");
        String[] costs = request.getParameterValues("cost");
        new PublicationService().updatePublication(publicationId, pubName, issn, website, setDate, publicationType, publicationTheme, publicationStatus, costs, costBeens);

        PublicationService publicationService = new PublicationService();
        int pubTypeId = (Integer) session.getAttribute("currentPubTypeId");
        int pubThemeId = (Integer) session.getAttribute("currentPubThemeId");
        int pubStatusId = (Integer) session.getAttribute("currentPubStatusId");
        int billPaidId = (Integer) session.getAttribute("currentBillPaidId");

        session.setAttribute("publicationList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId, billPaidId)[0]);
        session.setAttribute("subscriptionBillList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId, billPaidId)[1]);
        session.setAttribute("publicationTypeList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId, billPaidId)[2]);
        session.setAttribute("publicationThemeList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId, billPaidId)[3]);
        session.setAttribute("publicationStatusList", publicationService.getSelectedPublication(pubTypeId, pubThemeId, pubStatusId, billPaidId)[4]);

        return "/jsps/adminPage.jsp";
    }
}
