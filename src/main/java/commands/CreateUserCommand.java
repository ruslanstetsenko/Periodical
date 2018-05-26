package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import resourceBundle.RegexValidationManager;
import service.LoginService;
import validate.LoginValidator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class CreateUserCommand implements Command {
    //    private static final Logger logger = Logger.getLogger(CreateUserCommand.class);
    private static final Logger logger = LogManager.getLogger(CreateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("sessionId") == null) {
            session.setAttribute("sessionId", session.getId());
            logger.info("Session " + session.getId() + " has started");
        }

//        if (!session.getId().equals(session.getAttribute("sessionId"))) {
//            logger.info("Session " + session.getId() + " has finished");
//            return PageConfigManager.getProperty("path.page.login");
//        }

        LoginService loginService = new LoginService();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        session.setAttribute("login", login);
        session.setAttribute("password", password);

        if (loginService.checkLogin(login)) {
            request.setAttribute("dublicateAccount", true);
            logger.info("Can't create account. Dublicate login");
            return PageConfigManager.getProperty("path.page.login");
        } else {
            Map<String, Boolean> map = LoginValidator.validate(login, password);
            if (map.isEmpty()) {
//                session.setAttribute("login", login);
//                session.setAttribute("password", password);
                logger.info("Start creating user");
//                map.clear();
                return PageConfigManager.getProperty("path.page.createUser");
            } else {
                for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                logger.info("Can't create account. Incorrect login or password");
                return PageConfigManager.getProperty("path.page.login");
            }
        }
    }
}
