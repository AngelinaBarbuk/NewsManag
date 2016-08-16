package by.revotechs.barbuk.controllers;

import by.revotechs.barbuk.comands.*;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(location="/DBImages", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class NewsServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(NewsServlet.class.getName());

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }


    /*

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Post");

        String act = req.getParameter("act");
        switch (act){
            case "View":{
                int number = Integer.parseInt(req.getParameter("number"));
                //List<News> list = (List<News>) req.getAttribute("listNews");
                req.setAttribute("number",number);
                req.getRequestDispatcher("/news.jsp").forward(req, resp);
                break;
            }
            case "Edit":{
                break;
            }case "Delete selected":{
                String[] selectedItems = req.getParameterValues("id");
                for(String s:selectedItems){
                    System.out.println(s);
                    try {
                        newsDAO.deleteNews(Integer.parseInt(s));
                        req.getSession().setAttribute("newsList", newsDAO.getListNews());
                        //req.setAttribute("id",Integer.parseInt(s));
                        req.getRequestDispatcher("/newsList.jsp").forward(req, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (PropertyVetoException e) {
                        e.printStackTrace();
                    }
                }

                break;
            }
        }


    }


*/


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.debug("controller used...");
        String page = "";
        // определение команды, пришедшей из JSP
        String action = request.getParameter("command");
        logger.debug("controller recieved parameter: " +action);
		/*
		 * вызов реализованного метода execute() и передача параметров
		 * классу-обработчику конкретной команды
		 */
        if(action==null)
            action="";
        ActionCommand command = null;
        switch (action) {
            case "login":
                //command = new LoginCommand();
                break;
            case "logout":
                //command = new LogoutCommand();
                break;
            case "GetNewsList":
                command = new ShowNewsListCommand();
                break;
            case "ViewNews":
                command = new ViewNewsCommand();
                break;
            case "DeleteSelectedNews":
                command=new DeleteNewsCommand();
                break;
            case "AddNewsForm":
                command = new AddNewsFormCommand();
                break;
            case "AddNews":
                command = new AddNewsCommand();
                break;
            case "ShowEditNews":
                command = new ShowEditNewsCommand();
                break;
            case "EditNews":
                command = new EditNewsCommand();
                break;
            default:
                command = new ShowNewsListCommand();
                //command = new EmptyCommand();
                break;
        }

        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            // вызов страницы ответа на запрос
            dispatcher.forward(request, response);
            logger.debug("controller forward client to: " +page);
        } else {
            // установка страницы c cообщением об ошибке
            page = "newsList.jsp";
            request.getSession().setAttribute("nullPage", "Page not found. Business logic error.");
            response.sendRedirect(request.getContextPath() + page);
            logger.error("controller could not found the page");
        }
    }
}
