package by.revotechs.barbuk.entity;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by VVV on 12.07.2016.
 */
public class News implements Serializable {
    private int id;
    private String title;
    private String author;
    private Date date;
    private String content;
    private String imageURI;


    public News() {
    }
    public News(String title, String author, Date date, String content) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public News(String title, String author, Date date, String content, String imageURI) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
        this.imageURI = imageURI;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShortContent(){
        if(content.length()<50)
            return content;
        else return content.substring(0,200)+"...";
    }




    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", image=" + imageURI +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (author != null ? !author.equals(news.author) : news.author != null) return false;
        if (date != null ? !date.equals(news.date) : news.date != null) return false;
        if (content != null ? !content.equals(news.content) : news.content != null) return false;
        return imageURI != null ? imageURI.equals(news.imageURI) : news.imageURI == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (imageURI != null ? imageURI.hashCode() : 0);
        return result;
    }
}
