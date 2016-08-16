package by.revotechs.barbuk.comands;

import by.revotechs.barbuk.NewsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import by.revotechs.barbuk.entity.News;
import org.apache.log4j.Logger;

/**
 * Created by VVV on 26.07.2016.
 */
public class ShowNewsListCommand implements ActionCommand {

    static Logger logger = Logger.getLogger(ShowNewsListCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("ShowNewsListCommand used...");
        String page = null;
        // извлечение из запроса выбора курса обучения
        HttpSession session = request.getSession();
        try {
            List<News> list = NewsService.getInstance().getNewsList();
            session.setAttribute("newsList",list);
        } catch (SQLException e) {
            page = "/fail.jsp";
            logger.debug("ShowNewsListCommand returned: " +page);
            return page;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        page = "/newsList.jsp";
        logger.debug("ShowNewsListCommand returned: " +page);
        return page;

    }
}
