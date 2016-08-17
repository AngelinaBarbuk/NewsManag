package by.revotechs.barbuk;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import by.revotechs.barbuk.dao.*;
import by.revotechs.barbuk.entity.News;

/**
 * Created by VVV on 26.07.2016.
 */
public class NewsService {
    private NewsDAO<News> dao;
    private static NewsService newsService;

    private NewsService() {
        dao = new NewsDAOImpl();
    }

    public static NewsService getInstance() {
        if (newsService == null) {
            newsService = new NewsService();
        }
            return newsService;
    }

    public void addNews(News news) throws SQLException, IOException, PropertyVetoException {
        dao.addNews(news);
    }

    public void deleteNews(int id) throws SQLException, IOException, PropertyVetoException {
        dao.deleteNews(id);
    }


    public List<News> getNewsList() throws PropertyVetoException, SQLException, IOException {
        return dao.getListNews();
    }
    public void editNews(News news) throws PropertyVetoException, SQLException, IOException {
        dao.changeNews(news);
    }

    public static void main(String args[]) throws PropertyVetoException, IOException, SQLException {
        List<News> list= getInstance().getNewsList();
        for(News n:list)
            System.out.println(n);
    }
}
