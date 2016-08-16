package by.revotechs.barbuk.dao;

import by.revotechs.barbuk.connector.DBConnector;
import by.revotechs.barbuk.entity.News;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class NewsDAOImpl implements NewsDAO<News>{

    public static final String ADD_NEWS = "INSERT INTO NEWS_LIST (AUTHOR, TITLE, CONTENT, CREATE_DATE, IMAGE_URI) VALUES(?,?,?,?,?)";
    public static final String GET_ALL = "SELECT l.ID, a.AUTHOR, l.TITLE, l.CONTENT, l.CREATE_DATE, l.IMAGE_URI  FROM NEWS_LIST l INNER JOIN AUTHORS a ON l.AUTHOR=a.ID";
    public static final String ADD_AUTHOR = "INSERT INTO AUTHORS (AUTHOR) VALUES(?)";
    public static final String DELETE_NEWS = "DELETE FROM NEWS_LIST WHERE ID=?";
    public static final String GET_AUTHOR_ID = "SELECT ID FROM AUTHORS WHERE AUTHOR LIKE ?";
    public static final String CHANGE_NEWS = "UPDATE NEWS_LIST SET TITLE=?, CONTENT=?, CREATE_DATE=?, IMAGE=? WHERE ID=?";


    public NewsDAOImpl(){
    }

    public List getListNews() throws SQLException, IOException, PropertyVetoException {
        ArrayList<News> list = new ArrayList();
        PreparedStatement preparedStatement = DBConnector.getInstance().getConnection().prepareStatement(GET_ALL);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            News news = new News();
            news.setId(rs.getInt(1));
            news.setAuthor(rs.getString(2));
            news.setTitle(rs.getString(3));
            news.setContent(rs.getString(4));
            news.setDate(rs.getDate(5));
            news.setImageURI(rs.getString(6));
            list.add(news);
        }
        return list;
    }

    public int addNews(News news) throws SQLException, IOException, PropertyVetoException {
        int authorId = getAuthorId(news);
        if(authorId==-1)
            authorId = addAuthor(news);
        PreparedStatement st= DBConnector.getInstance().getConnection().prepareStatement(ADD_NEWS, new String[]{"ID"});
        st.setInt(1,authorId);
        st.setString(2,news.getTitle());
        st.setString(3,news.getContent());
        st.setDate(4, (Date) news.getDate());
        st.setString(5,news.getImageURI());
        st.executeUpdate();

        ResultSet resultSet = st.getGeneratedKeys();
        if(resultSet.next()){
            int newsId =resultSet.getInt(1);
            System.out.println(newsId);
            return newsId;
        }
        else return -1;
    }

    private int getAuthorId(News news) throws SQLException, IOException, PropertyVetoException {
        int authorId = -1;
        PreparedStatement st= DBConnector.getInstance().getConnection().prepareStatement(GET_AUTHOR_ID);
        st.setString(1,news.getAuthor());
        ResultSet rs = st.executeQuery();
        if(rs.next())
            authorId=rs.getInt(1);
        return authorId;
    }

    private int addAuthor(News news) throws SQLException, IOException, PropertyVetoException {
        ResultSet rs;
        PreparedStatement st= DBConnector.getInstance().getConnection().prepareStatement(ADD_AUTHOR, new String[]{"ID"});
        st.setString(1,news.getAuthor());
        st.executeUpdate();
        rs = st.getGeneratedKeys();
        if(rs.next())
            return rs.getInt(1);
        return -1;
    }


    public void deleteNews(int id) throws SQLException, IOException, PropertyVetoException {
        PreparedStatement st = DBConnector.getInstance().getConnection().prepareStatement(DELETE_NEWS);
        st.setInt(1,id);
        st.executeUpdate();
    }

    public void changeNews(News news) throws SQLException, IOException, PropertyVetoException {
        PreparedStatement st = DBConnector.getInstance().getConnection().prepareStatement(CHANGE_NEWS);
        st.setString(1,news.getTitle());
        st.setString(2,news.getContent());
        st.setDate(3, (Date) news.getDate());
        st.setString(4, news.getImageURI());
        st.setInt(5,news.getId());
        st.executeUpdate();
    }


    public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException, PropertyVetoException {
        NewsDAOImpl dao= new NewsDAOImpl();

        dao.addNews(new News("English","Angelina",new Date(System.currentTimeMillis()),"Anyone who reads Old and Middle English literary texts will be familiar with the mid-brown volumes of the EETS, with the symbol of Alfred's jewel embossed on the front cover. Most of the works attributed to King Alfred or to Aelfric, along with some of those by bishop Wulfstan and much anonymous prose and verse from the pre-Conquest period, are to be found within the Society's three series; all of the surviving medieval drama, most of the Middle English romances, much religious and secular prose and verse including the English works of John Gower, Thomas Hoccleve and most of Caxton's prints all find their place in the publications. Without EETS editions, study of medieval English texts would hardly be possible.\n" +
                "\n" +
                "As its name states, EETS was begun as a 'club', and it retains certain features of that even now. It has no physical location, or even office, no paid staff or editors, but books in the Original Series are published in the first place to satisfy subscriptions paid by individuals or institutions. This means that there is need for a regular sequence of new editions, normally one or two per year; achieving that sequence can pose problems for the Editorial Secretary, who may have too few or too many texts ready for publication at any one time. Details on a separate sheet explain how individual (but not institutional) members can choose to take certain back volumes in place of the newly published volumes against their subscriptions. On the same sheet are given details about the very advantageous discount available to individual members on all back numbers. In 1970 a Supplementary Series was begun, a series which only appears occasionally (it currently has 24 volumes within it); some of these are new editions of texts earlier appearing in the main series.","http://localhost:8082/0.jpg"));

    }
}

