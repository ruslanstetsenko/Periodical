package commands;

import service.Login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OkLoginComand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int role = new Login().enterInAccount(request.getParameter("login"), request.getParameter("password"));
        if (role == 1) {
            return "/jsps/adminPage.jsp";
        } else if (role == 2) {
            return "/jsps/userPage.jsp";
        } else {
            return "/jsps/error.jsp";
        }
    }
}
