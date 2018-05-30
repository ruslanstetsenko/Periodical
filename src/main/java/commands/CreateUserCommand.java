package commands;

import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.LoginService;
import validate.LoginValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class CreateUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("sessionId") == null) {
            session.setAttribute("sessionId", session.getId());
            LOGGER.info("Session " + session.getId() + " has started");
        }

        LoginService loginService = new LoginService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            if (loginService.checkLogin(login)) {
                request.setAttribute("dublicateAccount", true);
                LOGGER.info("Can't create account. Dublicate login");
                return PageConfigManager.getProperty("path.page.login");
            } else {
                Map<String, Boolean> map = LoginValidator.validate(login, password);
                if (map.isEmpty()) {
                    session.setAttribute("login", login);
                    session.setAttribute("password", password);
                    LOGGER.info("Start creating user");
                    return PageConfigManager.getProperty("path.page.createUser");
                } else {
                    for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                        request.setAttribute(entry.getKey(), entry.getValue());
                    }
                    LOGGER.info("Can't create account. Incorrect login or password");
                    return PageConfigManager.getProperty("path.page.login");
                }
            }
        } catch (DataBaseWorkException e) {
            request.setAttribute("errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            request.setAttribute("previousPage", "path.page.userPageSubsc");
            LOGGER.error("Can't check account. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.login");
        }
    }
}
