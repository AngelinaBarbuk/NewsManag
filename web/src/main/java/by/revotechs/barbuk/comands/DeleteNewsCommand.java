package by.revotechs.barbuk.comands;

import by.revotechs.barbuk.NewsService;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by VVV on 27.07.2016.
 */
public class DeleteNewsCommand implements ActionCommand {
    static Logger logger = Logger.getLogger(DeleteNewsCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("DeleteNewsCommand used...");
        String page = null;
        HttpSession session = request.getSession();
        String[] selectedItems = request.getParameterValues("delete");
        try {
            for(String s:selectedItems)
                NewsService.getInstance().deleteNews(Integer.parseInt(s));
            session.setAttribute("newsList", NewsService.getInstance().getNewsList());
        } catch (SQLException e) {
            page = "/fail.jsp";
            logger.debug("DeleteNewsCommand returned: " +page);
            return page;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        page = "/newsList.jsp";
        logger.debug("DeleteNewsCommand returned: " +page);
        return page;
    }
}
