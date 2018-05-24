package commands;

import beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;
import service.UserService;
import wrappers.AboutUserWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditUserCommand implements Command {
//    private static final Logger logger = Logger.getLogger(EditUserCommand.class);
    private static final Logger logger = LogManager.getLogger(EditUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            logger.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.index");
        }
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser.getUserRoleId() == 1) {
            int currentUserId = Integer.valueOf(request.getParameter("currentUserId"));
            session.setAttribute("currentUserId", currentUserId);
            AboutUserWrapper wrapper = new UserService().getUserInfo(currentUserId);
            session.setAttribute("user", wrapper.getUser());
            session.setAttribute("userAccount", wrapper.getAccount());
            session.setAttribute("userContactInfo", wrapper.getContactInfo());
            session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
            session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());
            logger.info("Edit user: " + wrapper.getUser().getSurname() + " " + wrapper.getUser().getName() + " " + wrapper.getUser().getLastName());
        } else {
            session.setAttribute("currentUserId", currentUser.getId());
            AboutUserWrapper wrapper = new UserService().getUserInfo(currentUser.getId());
            session.setAttribute("user", wrapper.getUser());
            session.setAttribute("userAccount", wrapper.getAccount());
            session.setAttribute("userContactInfo", wrapper.getContactInfo());
            session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
            session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());
            logger.info("Edit user: " + wrapper.getUser().getSurname() + " " + wrapper.getUser().getName() + " " + wrapper.getUser().getLastName());
        }

        return PageConfigManager.getProperty("path.page.editUser");
    }
}
