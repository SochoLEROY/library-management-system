
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victor
 */
public class Book {
    private SimpleIntegerProperty id;
    private SimpleStringProperty title; 
    private SimpleStringProperty author; 
    private SimpleStringProperty isbn;

    public Book(int id, String title, String author, String isbn) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.isbn = new SimpleStringProperty(isbn);
    }

    public int getId() {
        return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(SimpleStringProperty title) {
        this.title = title;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(SimpleStringProperty author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn(SimpleStringProperty isbn) {
        this.isbn = isbn;
    }
    
    
    
}
