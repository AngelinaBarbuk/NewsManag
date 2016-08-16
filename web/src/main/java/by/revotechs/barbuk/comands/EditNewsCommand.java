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

/**
 * Created by VVV on 01.08.2016.
 */
public class EditNewsCommand implements ActionCommand {
    static Logger logger = Logger.getLogger(EditNewsCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("EditNewsCommand used...");
        String page = null;
        HttpSession session = request.getSession();
        News oldNews = (News) session.getAttribute("news");

        String author = request.getParameter("author");
        Date date = (Date) request.getAttribute("date");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Image image = (Image) request.getAttribute("image");
        if(date==null)
            date= new Date(System.currentTimeMillis());
        try {
            NewsService.getInstance().deleteNews(oldNews.getId());
            NewsService.getInstance().addNews(new News(title,author,date,content));
            session.setAttribute("newsList",NewsService.getInstance().getNewsList());
        } catch (SQLException e) {
            page = "/fail.jsp";
            logger.debug("EditNewsCommand returned: " +page);
            return page;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        page = "/newsList.jsp";
        logger.debug("EditNewsCommand returned: " +page);
        return page;
    }
}
