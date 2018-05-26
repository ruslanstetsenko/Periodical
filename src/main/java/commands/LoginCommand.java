package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//
//public class LoginCommand implements Command {
//    //    private static final Logger logger = Logger.getLogger(LoginCommand.class);
//    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(true);
//        session.setAttribute("sessionId", session.getId());
//
//        logger.info("Session " + session.getId() + " is started");
//        return PageConfigManager.getProperty("path.page.login");
//    }
//}
