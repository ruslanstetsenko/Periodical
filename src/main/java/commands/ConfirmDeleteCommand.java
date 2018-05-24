package commands;

import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//public class ConfirmDeleteCommand implements Command {
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(true);
//        if (!session.getId().equals(session.getAttribute("sessionId"))) {
//            return PageConfigManager.getProperty("path.page.index");
//        }
//        return null;
//    }
//}
