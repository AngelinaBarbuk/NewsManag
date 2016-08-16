package by.revotechs.barbuk.comands;

import by.revotechs.barbuk.NewsService;
import by.revotechs.barbuk.entity.News;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by VVV on 01.08.2016.
 */
public class ShowEditNewsCommand implements ActionCommand  {

    static Logger logger = Logger.getLogger(ShowEditNewsCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("ShowEditNewsCommand used...");
        String page = null;
        int number = Integer.parseInt(request.getParameter("number"));
        HttpSession session = request.getSession();
        List<News> list = (List<News>) session.getAttribute("newsList");
        session.setAttribute("news",list.get(number));
        page = "/editNews.jsp";
        logger.debug("ShowEditNewsCommand returned: " +page);
        return page;
    }
}
