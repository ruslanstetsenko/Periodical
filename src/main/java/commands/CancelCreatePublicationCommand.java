package commands;

import resource.PageConfigManager;
import service.AdminWindowsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelCreatePublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.login");
        }
//        HttpSession session = request.getSession(true);
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

        return PageConfigManager.getProperty("path.page.adminPage");
    }
}
