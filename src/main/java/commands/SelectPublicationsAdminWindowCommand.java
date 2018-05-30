package commands;

import beans.Publication;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SelectPublicationsAdminWindowCommand implements Command {
private static final Logger LOGGER = LogManager.getLogger(SelectPublicationsAdminWindowCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }

        int currentPubTypeId = Integer.valueOf(request.getParameter("currentPubTypeId"));
        int currentPubThemeId = Integer.valueOf(request.getParameter("currentPubThemeId"));
        int currentPubStatusId = Integer.valueOf(request.getParameter("currentPubStatusId"));

        session.setAttribute("currentPubTypeId", currentPubTypeId);
        session.setAttribute("currentPubThemeId", currentPubThemeId);
        session.setAttribute("currentPubStatusId", currentPubStatusId);

        try {
            List<Publication> publicationList = new PublicationService().getSelectedPublication(currentPubTypeId, currentPubThemeId, currentPubStatusId);
            if (publicationList != null) {
                session.setAttribute("publicationList", publicationList);
            } else {
                session.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.loadData"));
                session.setAttribute("previousPage", "path.page.adminPage");
                LOGGER.error("Can't load publicationss from DB");
                return PageConfigManager.getProperty("path.page.error");
            }
        } catch (DataBaseWorkException e) {
            session.setAttribute( "errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            session.setAttribute("previousPage", "path.page.adminPage");
            LOGGER.error("Can't load publicationss. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        LOGGER.info("Publications selected");
        return PageConfigManager.getProperty("path.page.adminPage");
    }
}
