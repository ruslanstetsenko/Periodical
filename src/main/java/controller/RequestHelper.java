package controller;

import commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public final class RequestHelper {

    private static RequestHelper instance = null;
    HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
//        commands.put("addPublicationToSubscription", new AddPublicationToSubscriptionCommand());
//        commands.put("backPrevPage", new BackPrevPageCommand());
        commands.put("cancelCreatePublication", new CancelCreatePublicationCommand());
        commands.put("cancelEditPublication", new CancelEditPublicationCommand());
        commands.put("cancelWievSubscription", new CancelWievSubscriptionCommand());
        commands.put("cancelLogin", new CancelLoginComand());
        commands.put("cancelWievAboutBill", new CancelWievAboutBillComand());
//        commands.put("confirmDelete", new ConfirmDeleteCommand());
        commands.put("createPublication", new CreatePublicationCommand());
        commands.put("createSubscription", new CreateSubscriptionCommand());
        commands.put("cancelCreateSubscription", new CancelCreateSubscriptionCommand());
//        commands.put("deleteBill", new DeleteBillCommand());
        commands.put("editPublication", new EditPublicationCommand());
        commands.put("editUser", new EditUserCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("NoCommand", new NoCommand());
        commands.put("okCreatePublication", new OkCreatePublicationCommand());
        commands.put("okCreateSubscription", new OkCreateSubscriptionCommand());
        commands.put("okEditPublication", new OkEditPublicationCommand());
//        commands.put("okEditSubscription", new OkEditSubscriptionCommand());
        commands.put("okLogin", new OkLoginComand());
        commands.put("showAboutBill", new ShowAboutBillCommand());
        commands.put("aboutPublication", new ShowAboutPublicationCommand());
        commands.put("showAboutSubscription", new ShowAboutSubscriptionCommand());
//        commands.put("selectBillsByStatusByUser", new SelectBillsByStatusByUserCommand());
//        commands.put("selectBillsByStatus", new SelectBillsByStatusCommand());
        commands.put("selectPublicationsAdminWindow", new SelectPublicationsAdminWindowCommand());
        commands.put("selectPublicationsCreateSubsWindow", new SelectPublicationsCreateSubsWindowCommand());
//        commands.put("selectPublicationsByTypeByTheme", new SelectPublicationsByTypeByThemeCommand());
        commands.put("selectSubsUserWindow", new SelectSubsUserWindowComand());
        commands.put("showAboutUser", new ShowAboutUserCommand());
        commands.put("cancelEditUser", new CancelEditUserCommand());
        commands.put("createUser", new CreateUserCommand());
        commands.put("okEditUser", new OkEditUserCommand());
        commands.put("okCreateUser", new OkCreateUserCommand());
        commands.put("selectBills", new SelectBillsCommand());
        commands.put("resetBillList", new ResetBillListCommand());
        commands.put("setLocale", new SetLocaleCommand());

    }

    public Command getCommand(HttpServletRequest request) {
        String actionName = request.getParameter("command");
        Command command = commands.get(actionName);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
