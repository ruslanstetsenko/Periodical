package controller;

import commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public final class RequestHelper {

    private static RequestHelper instance = null;
    HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        commands.put("addPublicationToSubscription", new AddPublicationToSubscriptionCommand());
        commands.put("backPrevPage", new BackPrevPageCommand());
        commands.put("cancelCreatePublication", new CancelCreatePublicationCommand());
        commands.put("cancelEditPublication", new CancelEditPublicationCommand());
        commands.put("cancelEditSubscription", new CancelEditPublicationCommand());
        commands.put("cancelLogin", new CancelLoginComand());
        commands.put("confirmDelete", new ConfirmDeleteCommand());
        commands.put("createPublication", new CreatePublicationCommand());
        commands.put("createSubscription", new CreatePublicationCommand());
        commands.put("deleteBill", new DeleteBillCommand());
        commands.put("editPublication", new EditPublicationCommand());
        commands.put("editUser", new EditUserCommand());
        commands.put("login", new LoginCommand());
        commands.put("NoCommand", new NoCommand());
        commands.put("okEditPublication", new OkEditPublicationCommand());
        commands.put("okEditSubscription", new OkEditSubscriptionCommand());
        commands.put("okLogin", new OkLoginComand());
        commands.put("showAboutBill", new ShowAboutBillCommand());
        commands.put("aboutPublication", new ShowAboutPublicationCommand());
        commands.put("showAboutSubscription", new ShowAboutSubscriptionCommand());
        commands.put("selectBillsByStatusByUser", new SelectBillsByStatusByUserCommand());
        commands.put("selectBillsByStatus", new SelectBillsByStatusCommand());
        commands.put("selectPublicationsAdminWindow", new SelectPublicationsAdminWindowCommand());
        commands.put("selectPublicationsByTypeByTheme", new SelectPublicationsByTypeByThemeCommand());
        commands.put("selectSubscriptionsByStatus", new SelectSubscriptionsByStatusCommand());
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
