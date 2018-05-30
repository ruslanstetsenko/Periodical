package commands;

import beans.*;
import exceptions.DataBaseWorkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.MessageConfigManager;
import resourceBundle.PageConfigManager;
import service.UserService;
import wrappers.AboutUserWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (!session.getId().equals(session.getAttribute("sessionId"))) {
            LOGGER.info("Session " + session.getId() + " has finished");
            return PageConfigManager.getProperty("path.page.login");
        }
        User currentUser = (User) session.getAttribute("currentUser");
        int currentUserId;

        currentUserId = getCurrentUserId(request, currentUser);
        String surnameEditegUser;
        String nameEditegUser;
        String lastnameEditegUser;

        session.setAttribute("currentUserId", currentUserId);
        try {
            AboutUserWrapper wrapper = new UserService().getUserInfo(currentUserId);
            User user = wrapper.getUser();
            Account account = wrapper.getAccount();
            ContactInfo contactInfo = wrapper.getContactInfo();
            LivingAddress livingAddress = wrapper.getLivingAddress();
            PassportIdentNumber passportIdentNumber = wrapper.getPassportIdentNumber();
            surnameEditegUser = wrapper.getUser().getSurname();
            nameEditegUser = wrapper.getUser().getName();
            lastnameEditegUser = wrapper.getUser().getLastName();

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
        } catch (DataBaseWorkException e) {
            request.setAttribute( "errorMessage", MessageConfigManager.getProperty(e.getMessage()));
            choicePrevPage(request, currentUser);
            LOGGER.error("Can't start edit user. DB error", e.getCause());
            return PageConfigManager.getProperty("path.page.error");
        } catch (NullPointerException npe) {
            request.setAttribute( "errorMessage", MessageConfigManager.getProperty("message.error.vrongParameters"));
            choicePrevPage(request, currentUser);
            LOGGER.error("Can't start edit user", npe.getCause());
            return PageConfigManager.getProperty("path.page.error");
        }

        LOGGER.info("Edit user: " + surnameEditegUser + " " + nameEditegUser + " " + lastnameEditegUser);
        return PageConfigManager.getProperty("path.page.editUser");
    }

    private int getCurrentUserId(HttpServletRequest request, User currentUser) {
        int currentUserId;
        if (currentUser.getUserRoleId() == 1) {
            currentUserId = Integer.valueOf(request.getParameter("currentUserId"));
        } else {
            currentUserId = currentUser.getId();
        }
        return currentUserId;
    }

    private void choicePrevPage(HttpServletRequest request, User currentUser) {
        if (currentUser.getUserRoleId() == 1) {
            request.setAttribute("previousPage", "path.page.users");
        } else {
            request.setAttribute("previousPage", "path.page.aboutUser");
        }
    }
}
