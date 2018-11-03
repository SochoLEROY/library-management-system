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
import java.sql.Timestamp;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class CheckoutManagementController implements Initializable {

    @FXML private TextField txtfUserId;
    @FXML private TextField txtfBookId;
    
    @FXML private Label lblbookTitle;
    @FXML private Label lblUser;
    
    //added below
    @FXML private TableView<Checkout> tableView;
    @FXML private TableColumn<Checkout, Integer> idColumn;
@FXML private TableColumn<Checkout, Integer> bookIdColumn;
@FXML private TableColumn<Checkout, Integer> userIdColumn;
@FXML private TableColumn<Checkout, Timestamp> dateColumn;
private ObservableList<Checkout> data;
    
    //added above
    
    private static final String URL = 
                    "jdbc:mysql://localhost:3306/students1?zeroDateTimeBehavior=convertToNull";
private static final String USERNAME = "root";
private static final String PASSWORD = "Password1";  
private PreparedStatement ps;
private Connection con; // manages connection
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // added below
        idColumn.setCellValueFactory(new PropertyValueFactory<Checkout,Integer>("id"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<Checkout,Integer>("bookId"));
    bookIdColumn.setCellValueFactory(new PropertyValueFactory<Checkout,Integer>("userId"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<Checkout,Timestamp>("date"));
    
    ObservableList<Checkout> checkout = FXCollections.observableArrayList();
    
            try{
           
           Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

           
           PreparedStatement ps = con.prepareStatement("select * from checkout2");
           ResultSet rs = ps.executeQuery();
           
           while(rs.next()){
               checkout.add(new Checkout(
                    rs.getInt("id"),
                    rs.getInt("bookId"),
                    rs.getInt("userId"),
                    rs.getTimestamp("date")
                ));
           }
           tableView.setItems(checkout);
           rs.close();
           ps.close();
           
}
        catch(Exception e){
           e.printStackTrace();
           System.out.println("error");
       }
        
        //added above
    }    
    
    
        
    @FXML
    private void bookEnter(ActionEvent event){
        int id =  Integer.parseInt(txtfBookId.getText());
             
        try{
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           
        ps = con.prepareStatement("select * from library2 where id= '" + id + "'");
        
    ResultSet rs = ps.executeQuery();
    boolean flag=false;
    
    while (rs.next()){
        //int id1= rs.getInt("id");
        String title1=rs.getString("title");
        lblbookTitle.setText(title1);
        flag=true;
        
        
        }
        if(!flag){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("book not found");
            alert.showAndWait();
        
        
    }
    ps.close();
    
    
        }
    catch (SQLException sqlException) {
        sqlException.printStackTrace();
            
        
      } 
    }
    
    
    
    @FXML
    private void userEnter(ActionEvent event){
        int id =  Integer.parseInt(txtfUserId.getText());
             
        try{
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           
        ps = con.prepareStatement("select * from user2 where id= '" + id + "'");
        
    ResultSet rs = ps.executeQuery();
    boolean flag=false;
    
    while (rs.next()){
        //int id1= rs.getInt("id");
        String lastName1=rs.getString("lastName");
        lblUser.setText(lastName1);
        flag=true;
        
        
        }
        if(!flag){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("user not found");
            alert.showAndWait();
        
        
    }
    ps.close();
    
    
        }
    catch (SQLException sqlException) {
        sqlException.printStackTrace();
            
        
      } 
    }
    
    
    
    
    @FXML
    private void checkoutBook(ActionEvent event) {
    int book = Integer.parseInt(txtfUserId.getText());
    int user = Integer.parseInt(txtfBookId.getText());
    

    try{
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           // create insert that adds a new entry into the database
        ps = con.prepareStatement("INSERT INTO checkout2 (userId, bookId) VALUES (?,?)"); 

    ps.setInt(1, book);
    ps.setInt(2, user);
    
    ps.executeUpdate();
    txtfUserId.clear();
    txtfBookId.clear();
    
    
    ps.close();
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("book checked out");
    alert.showAndWait();
        }
    catch (SQLException sqlException) {
        sqlException.printStackTrace();
        System.exit(1);
      } 
    }
    
    @FXML
    private void backHomeButton(ActionEvent event) throws IOException{
    Parent home = FXMLLoader.load(getClass().getResource("Management.fxml"));
    Scene homeScene = new Scene(home);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setTitle("Management");
    window.setScene(homeScene);
    window.show();
    }
    
    
    @FXML
    private void returnButton(){
        
        try{
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        
        //selected row to be deleted
        Checkout ck = tableView.getSelectionModel().getSelectedItem();

        PreparedStatement ps = con.prepareStatement("delete from checkout2 where id = ?");
        ps.setInt(1, ck.getId());
        ps.executeUpdate();
        
        ps.close();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("book returned");
        alert.showAndWait();
        
        }
        catch(Exception e){
        e.printStackTrace();
           
        }
  
    }
    
    @FXML
    private void refreshButton(ActionEvent event) throws IOException{
    Parent home = FXMLLoader.load(getClass().getResource("CheckoutManagement.fxml"));
    Scene homeScene = new Scene(home);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setTitle("Checkout Management");
    window.setScene(homeScene);
    window.show();
    }
    
    
    
}
