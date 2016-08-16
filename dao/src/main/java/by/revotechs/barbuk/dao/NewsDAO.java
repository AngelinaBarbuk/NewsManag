package by.revotechs.barbuk.dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by VVV on 13.07.2016.
 */
public interface NewsDAO<T> {

    List<T> getListNews() throws SQLException, IOException, PropertyVetoException;
    int addNews(T news) throws SQLException, IOException, PropertyVetoException;
    void deleteNews(int id) throws SQLException, IOException, PropertyVetoException;
    void changeNews(T news) throws SQLException, IOException, PropertyVetoException;

}
