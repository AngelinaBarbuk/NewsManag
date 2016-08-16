package by.revotechs.barbuk.comands;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by VVV on 26.07.2016.
 */
public class ViewNewsCommand implements ActionCommand {
    static Logger logger = Logger.getLogger(ViewNewsCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.debug("ViewNewsCommand used...");
        String page = null;
        // извлечение из запроса выбора курса обучения
        HttpSession session = request.getSession();
            int number = Integer.parseInt(request.getParameter("number"));
            request.setAttribute("number", number);

        page = "/news.jsp";
        logger.debug("ViewNewsCommand returned: " +page);
        return page;
    }
}
