/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class LibraryManagementController implements Initializable {

@FXML private TableView<Book> tableView;
@FXML private TableColumn<Book, Integer> idColumn;
@FXML private TableColumn<Book, String> titleColumn;
@FXML private TableColumn<Book, String> authorColumn;
@FXML private TableColumn<Book, String> isbnColumn;
@FXML private TextField txtfTitle;
@FXML private TextField txtfAuthor;
@FXML private TextField txtfIsbn;
private PreparedStatement ps;
private Connection con; // manages connection

private ObservableList<Student> data;
    
private static final String URL = 
                    "jdbc:mysql://localhost:3306/students1?zeroDateTimeBehavior=convertToNull";
private static final String USERNAME = "root";
private static final String PASSWORD = "Password1";  
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    idColumn.setCellValueFactory(new PropertyValueFactory<Book,Integer>("id"));
    titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
    authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
    isbnColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("isbn"));

    ObservableList<Book> book = FXCollections.observableArrayList();
        
        try{
           
           Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

           //PreparedStatement ps = con.prepareStatement("select * from student");
           PreparedStatement ps = con.prepareStatement("select * from library2");
           ResultSet rs = ps.executeQuery();
           
           while(rs.next()){
               book.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("isbn")                   
                )
               );
               
           }
           tableView.setItems(book);
           rs.close();
           ps.close();
           
}
        catch(Exception e){
           e.printStackTrace();
           System.out.println("error");
       }  

    }   
    
    
    @FXML
    private void addBook(ActionEvent event) {
    String title = txtfTitle.getText();
    String author = txtfAuthor.getText();
    String isbn = txtfIsbn.getText();

    try{
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           // create insert that adds a new entry into the database
        ps = con.prepareStatement("INSERT INTO library2 (title, author, isbn) VALUES (?,?,?)"); 

    ps.setString(1, title);
    ps.setString(2, author);
    ps.setString(3, isbn);
    ps.executeUpdate();
    ps.close();
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("book added");
    alert.showAndWait();
        }
    catch (SQLException sqlException) {
        sqlException.printStackTrace();
        System.exit(1);
      } 
    }
    
    public void refreshButton(ActionEvent event) throws IOException{
    Parent home = FXMLLoader.load(getClass().getResource("LibraryManagement.fxml"));
    Scene homeScene = new Scene(home);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setTitle("Library Management");
    window.setScene(homeScene);
    window.show();
    }
    
    public void backHomeButton(ActionEvent event) throws IOException{
    Parent home = FXMLLoader.load(getClass().getResource("Management.fxml"));
    Scene homeScene = new Scene(home);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setTitle("Management");
    window.setScene(homeScene);
    window.show();
    }
        
    public void deleteButton(){
        
        try{
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        
        //selected row to be deleted
        Book st = tableView.getSelectionModel().getSelectedItem();

        PreparedStatement ps = con.prepareStatement("delete from library2 where id = ?");
        ps.setInt(1, st.getId());
        ps.executeUpdate();
        
        ps.close();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("book deleted");
        alert.showAndWait();
        
        }
        catch(Exception e){
        e.printStackTrace();
        System.out.println("error");    
        }
  
    }
    
    @FXML
    public void showOnClick(){
        try{
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Book book=tableView.getSelectionModel().getSelectedItem();
            PreparedStatement ps = con.prepareStatement("select * from library2 where id = ?");
            
            txtfTitle.setText(book.getTitle());
            txtfAuthor.setText(book.getAuthor());
            txtfIsbn.setText(book.getIsbn());
            
            ps.close();
            
            
        }
        catch(Exception e){
            
        }
    }
    
    @FXML
    public void updateButton(){
        
        try{
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //selected row to be deleted
        Book st = tableView.getSelectionModel().getSelectedItem();
        
        PreparedStatement ps = con.prepareStatement("update library2 set title=?, author=?, isbn=? where id=?");
        
        ps.setString(1, txtfTitle.getText());
        ps.setString(2, txtfAuthor.getText());
        ps.setString(3, txtfIsbn.getText());
        ps.setInt(4, st.getId());
        ps.executeUpdate();
        txtfTitle.clear();
        txtfAuthor.clear();
        txtfIsbn.clear();
        
        ps.close();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("book updated");
        alert.showAndWait();
        
        }
        catch(Exception e){
        e.printStackTrace();
        System.out.println("error");    
        }
  
    }
    
}