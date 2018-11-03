
import java.sql.Timestamp;
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
public class Checkout {
    
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty bookId;
    private SimpleIntegerProperty userId;
    private Timestamp date;

    public Checkout(int id, int bookId, int userId, Timestamp date) {
        this.id = new SimpleIntegerProperty(id);
        this.bookId = new SimpleIntegerProperty(bookId);
        this.userId = new SimpleIntegerProperty(userId);
        this.date = date;
    }
    
    public int getId() {
    return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId.get();
    }

    public void setBookId(SimpleIntegerProperty bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId.get();
    }

    public void setUserId(SimpleIntegerProperty userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    
}
