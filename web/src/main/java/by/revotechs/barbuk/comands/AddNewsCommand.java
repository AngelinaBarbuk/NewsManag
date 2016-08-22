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
import java.util.Locale;

import static by.revotechs.barbuk.ImageService.uploadImage;


/**
 * Created by VVV on 28.07.2016.
 */
public class AddNewsCommand implements ActionCommand {
    static Logger logger = Logger.getLogger(AddNewsCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) throws IOException, ServletException {
        logger.debug("AddNewsCommand used...");
        String page = null;
        HttpSession session = request.getSession();
        String author = request.getParameter("author");
        Date date = (Date) request.getAttribute("date");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        //Image image = (Image) request.getAttribute("image");

        System.out.println("Add command");
        if(date==null)
            date= new Date(System.currentTimeMillis());
        String imgPath=  ImageService.uploadImage(request,title,date);

        try {
            NewsService.getInstance().addNews(new News(title,author,date,content,imgPath));
            session.setAttribute("newsList",NewsService.getInstance().getNewsList());
        } catch (SQLException e) {
            page = "/fail.jsp";
            logger.debug("AddNewsCommand returned: " +page);
            return page;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        page = "/newsList.jsp";
        logger.debug("AddNewsCommand returned: " +page);
        return page;
    }
}
