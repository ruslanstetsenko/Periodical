package commands;

import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            return PageConfigManager.getProperty("path.page.index");
        }
        String key = request.getParameter("currentPage");
//        String key = (String) session.getAttribute("currentPage");
        String locale = request.getParameter("locale");
        session.setAttribute("locale", locale);
        System.out.println("key " + key);
        System.out.println("page " + PageConfigManager.getProperty(key));
        System.out.println("locale " + locale);

        return PageConfigManager.getProperty(key);
    }
}
