package commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resourceBundle.PageConfigManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand implements Command {
private static final Logger logger = LogManager.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PageConfigManager.getProperty("path.page.error");
    }
}
