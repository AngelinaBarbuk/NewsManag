package by.revotechs.barbuk.comands;

import by.revotechs.barbuk.ImageService;
import by.revotechs.barbuk.NewsService;
import by.revotechs.barbuk.entity.News;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
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
    public String execute(HttpServletRequest request) throws IOException, ServletException {
        logger.debug("EditNewsCommand used...");
        String page = null;
        HttpSession session = request.getSession();
        News oldNews = (News) session.getAttribute("news");

        String author = request.getParameter("author");
        if(author!=null)
            oldNews.setAuthor(author);

        Date date = (Date) request.getAttribute("date");
        if(date!=null)
            oldNews.setDate(date);

        String title = request.getParameter("title");
        if(title!=null)
            oldNews.setTitle(title);

        String content = request.getParameter("content");
        if(content!=null)
            oldNews.setContent(content);
        //Image image = (Image) request.getAttribute("image");

        String imgPath=  ImageService.uploadImage(request,title,date);
        if(imgPath!=null)
            oldNews.setImageURI(imgPath);

        try {
            NewsService.getInstance().editNews(oldNews);
            //NewsService.getInstance().deleteNews(oldNews.getId());
            //NewsService.getInstance().addNews(new News(title,author,date,content,imgPath));
            session.setAttribute("newsList",NewsService.getInstance().getNewsList());
        } catch (SQLException e) {
            e.printStackTrace();
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
