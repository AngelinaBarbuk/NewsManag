package by.revotechs.barbuk.comands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by VVV on 18.08.2016.
 */
public class ChangeLangCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws IOException, ServletException {
        String lang = request.getParameter("lang");
        switch (lang){
            case "en":
                request.setAttribute("language", new Locale("en_US"));
                break;
            case "ru":
                request.setAttribute("language", new Locale("ru_RU"));
                break;
            default:
                request.getSession().setAttribute("language",request.getLocale());
                break;
        }
        return "#";
    }
}
