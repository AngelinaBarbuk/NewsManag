package by.revotechs.barbuk.comands;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by VVV on 28.07.2016.
 */
public class AddNewsFormCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/addNews.jsp";
    }
}
