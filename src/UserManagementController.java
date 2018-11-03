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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
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
public class UserManagementController implements Initializable {
    
@FXML private TableView<Student> tableView;
@FXML private TableColumn<Student, Integer> idColumn;
@FXML private TableColumn<Student, String> firstNameColumn;
@FXML private TableColumn<Student, String> lastNameColumn;
@FXML private TableColumn<Student, String> emailColumn;
@FXML private TextField txtfFName;
@FXML private TextField txtfLName;
@FXML private TextField txtfEmail;
private PreparedStatement ps;
private Connection con; // manages connection

private ObservableList<Student> data;
    
private static final String URL = 
                    "jdbc:mysql://localhost:3306/students1?zeroDateTimeBehavior=convertToNull";
private static final String USERNAME = "root";
private static final String PASSWORD = "Password1";

    

    
    
    /**
     * Initializes the controller class.
     */
@Override
public void initialize(URL url, ResourceBundle rb) {
        
    idColumn.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
    ObservableList<Student> student = FXCollections.observableArrayList();
        
        try{
           
           Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

           //PreparedStatement ps = con.prepareStatement("select * from student");
           PreparedStatement ps = con.prepareStatement("select * from user2");
           ResultSet rs = ps.executeQuery();
           
           while(rs.next()){
               student.add(new Student(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email")
                ));
           }
           tableView.setItems(student);
           rs.close();
           ps.close();
           
}
        catch(Exception e){
           e.printStackTrace();
           System.out.println("error");
       }

    }
    
    
    //method to delete row from database
    
    public void deleteButton(){
        
        try{
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        
        //selected row to be deleted
        Student st = tableView.getSelectionModel().getSelectedItem();

        PreparedStatement ps = con.prepareStatement("delete from user2 where id = ?");
        ps.setInt(1, st.getId());
        ps.executeUpdate();
        
        ps.close();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("user deleted");
        alert.showAndWait();
        
        }
        catch(Exception e){
        e.printStackTrace();
        System.out.println("error");    
        }
  
    }

    
    
    @FXML
    private void addUser(ActionEvent event) {
    String fName = txtfFName.getText();
    String lName = txtfLName.getText();
    String email = txtfEmail.getText();

    try{
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           // create insert that adds a new entry into the database
        ps = con.prepareStatement("INSERT INTO user2 (firstName, lastName, email) VALUES (?,?,?)"); 

    
    ps.setString(1, fName);
    ps.setString(2, lName);
    ps.setString(3, email);
    ps.executeUpdate();
    ps.close();
    //label.setText("Student added succesfully!");
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("user added");
    alert.showAndWait();
        }
    catch (SQLException sqlException) {
        sqlException.printStackTrace();
        System.exit(1);
      } 
    }
    
    @FXML
    private void refreshButton(ActionEvent event) throws IOException{
    Parent home = FXMLLoader.load(getClass().getResource("UserManagement.fxml"));
    Scene homeScene = new Scene(home);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setTitle("Library Management");
    window.setScene(homeScene);
    window.show();
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
    private void showOnClick(){
        try{
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Student student=tableView.getSelectionModel().getSelectedItem();
            PreparedStatement ps = con.prepareStatement("select * from user2 where id = ?");
            
            txtfFName.setText(student.getFirstName());
            txtfLName.setText(student.getLastName());
            txtfEmail.setText(student.getEmail());
            
            ps.close();
            
            
        }
        catch(Exception e){
            
        }
    }
    
    @FXML
    private void updateButton(){
        
        try{
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //selected row to be deleted
        Student st = tableView.getSelectionModel().getSelectedItem();
        
        PreparedStatement ps = con.prepareStatement("update user2 set firstName=?, lastName=?, email=? where id=?");
        
        ps.setString(1, txtfFName.getText());
        ps.setString(2, txtfLName.getText());
        ps.setString(3, txtfEmail.getText());
        ps.setInt(4, st.getId());
        ps.executeUpdate();
        txtfFName.clear();
        txtfLName.clear();
        txtfEmail.clear();
        
        ps.close();
        con.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("user updated");
        alert.showAndWait();
        
        }
        catch(Exception e){
        e.printStackTrace();
           
        }
  
    }
    

    }
    

