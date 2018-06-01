package controller;

import commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public final class RequestHelper {

    private static RequestHelper instance = null;
    private HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        commands.put("cancelCreatePublication", new CancelCreatePublicationCommand());
        commands.put("cancelEditPublication", new CancelEditPublicationCommand());
        commands.put("cancelWievSubscription", new CancelViewSubscriptionCommand());
        commands.put("cancelWievAboutBill", new CancelWievAboutBillComand());
        commands.put("createPublication", new CreatePublicationCommand());
        commands.put("createSubscription", new CreateSubscriptionCommand());
        commands.put("cancelCreateSubscription", new CancelCreateSubscriptionCommand());
        commands.put("cancelCreareUser", new CancelCreareUserCommand());
        commands.put("aboutPublicationForUpdate", new EditPublicationCommand());
        commands.put("editUser", new EditUserCommand());
        commands.put("editPublication", new EditPublicationCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("okCreatePublication", new OkCreatePublicationCommand());
        commands.put("okCreateSubscription", new OkCreateSubscriptionCommand());
        commands.put("okEditPublication", new OkEditPublicationCommand());
        commands.put("okLogin", new OkLoginComand());
        commands.put("showAboutBill", new ShowAboutBillCommand());
        commands.put("aboutPublication", new ShowAboutPublicationCommand());
        commands.put("showAboutSubscription", new ShowAboutSubscriptionCommand());
        commands.put("selectPublicationsAdminWindow", new SelectPublicationsAdminWindowCommand());
        commands.put("selectPublicationsCreateSubsWindow", new SelectPublicationsCreateSubsWindowCommand());
        commands.put("selectSubsUserWindow", new SelectSubsUserWindowComand());
        commands.put("showAboutUser", new ShowAboutUserCommand());
        commands.put("cancelEditUser", new CancelEditUserCommand());
        commands.put("createUser", new CreateUserCommand());
        commands.put("okEditUser", new OkEditUserCommand());
        commands.put("okCreateUser", new OkCreateUserCommand());
        commands.put("selectBills", new SelectBillsCommand());
        commands.put("resetBillList", new ResetBillListCommand());
        commands.put("setLocale", new SetLocaleCommand());
        commands.put("goBack", new GoBackCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String actionName = request.getParameter("command");
        if (actionName == null) {
            actionName = "noCommand";
        }
        return commands.get(actionName);
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
