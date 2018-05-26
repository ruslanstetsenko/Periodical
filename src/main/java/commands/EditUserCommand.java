package commands;

import beans.*;
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
            return PageConfigManager.getProperty("path.page.login");
        }
        User currentUser = (User) session.getAttribute("currentUser");
        int currentUserId;
        if (currentUser.getUserRoleId() == 1) {
            currentUserId = Integer.valueOf(request.getParameter("currentUserId"));
        } else {
            currentUserId = currentUser.getId();
        }
//        if (currentUser.getUserRoleId() == 1) {
//            int currentUserId = Integer.valueOf(request.getParameter("currentUserId"));
        AboutUserWrapper wrapper = new UserService().getUserInfo(currentUserId);
//            session.setAttribute("currentUserId", currentUserId);
//            session.setAttribute("user", wrapper.getUser());
//            session.setAttribute("userAccount", wrapper.getAccount());
//            session.setAttribute("userContactInfo", wrapper.getContactInfo());
//            session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
//            session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());
        User user = wrapper.getUser();
        Account account = wrapper.getAccount();
        ContactInfo contactInfo = wrapper.getContactInfo();
        LivingAddress livingAddress = wrapper.getLivingAddress();
        PassportIdentNumber passportIdentNumber = wrapper.getPassportIdentNumber();

        System.out.println(user);

        request.setAttribute("userSurName", user.getSurname());
        request.setAttribute("userName", user.getName());
        request.setAttribute("userLastName", user.getLastName());
        request.setAttribute("userBirthDate", user.getBirthday());
        request.setAttribute("passportSerial", passportIdentNumber.getSerial());
        request.setAttribute("passportNumber", passportIdentNumber.getNumber());
        request.setAttribute("passportDateOfIssue", passportIdentNumber.getDateOfIssue());
        request.setAttribute("passportIssuedBy", passportIdentNumber.getIssuedBy());
        request.setAttribute("identNuber", passportIdentNumber.getIdNumber());
        request.setAttribute("region", livingAddress.getRegion());
        request.setAttribute("district", livingAddress.getDistrict());
        request.setAttribute("city", livingAddress.getCity());
        request.setAttribute("street", livingAddress.getStreet());
        request.setAttribute("building", livingAddress.getBuilding());
        request.setAttribute("appartment", livingAddress.getAppartment());
        request.setAttribute("userPhoneNumber", contactInfo.getPhone());
        request.setAttribute("userEmail", contactInfo.getEmail());
        request.setAttribute("login", account.getLogin());
        request.setAttribute("password", account.getPassword());

//            logger.info("Edit user: " + wrapper.getUser().getSurname() + " " + wrapper.getUser().getName() + " " + wrapper.getUser().getLastName());
//        } else {
        session.setAttribute("currentUserId", currentUser.getId());
//            session.setAttribute("user", wrapper.getUser());
//            session.setAttribute("userAccount", wrapper.getAccount());
//            session.setAttribute("userContactInfo", wrapper.getContactInfo());
//            session.setAttribute("userLivingAddress", wrapper.getLivingAddress());
//            session.setAttribute("userPassportIdNumb", wrapper.getPassportIdentNumber());
        logger.info("Edit user: " + wrapper.getUser().getSurname() + " " + wrapper.getUser().getName() + " " + wrapper.getUser().getLastName());
//        }

        return PageConfigManager.getProperty("path.page.editUser");
    }
}
