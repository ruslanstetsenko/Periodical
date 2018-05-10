package commands;

import beens.Publication;
import service.PublicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PressShowAboutPublicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int publicationId = Integer.parseInt(request.getParameter("publicationId"));
        Publication publication = new PublicationService().showAboutPublication(publicationId);
        return "/jsps/aboutPublication.jsp";
    }
}
