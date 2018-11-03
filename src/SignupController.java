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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class SignupController implements Initializable {
    
    @FXML
    private PasswordField pw1;
    
    @FXML
    private PasswordField pw2;
    
    
    @FXML
    private TextField txtfUsername;
    //@FXML private TextField txtfPassword;
    
     private static final String URL = 
                    "jdbc:mysql://localhost:3306/students1?zeroDateTimeBehavior=convertToNull";
     private static final String USERNAME = "root";
     private static final String PASSWORD = "Password1";
     private Connection conn; // manages connection
     private PreparedStatement ps;
    
    @FXML
    public void registerActionButton(ActionEvent event) {
    String username1 = txtfUsername.getText();
    String password1 = pw1.getText();
    String password2 = pw2.getText();
    
    
    if(validatePassword() && password1.equals(password2)){
    try{
    conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           // create insert that adds a new entry into the database
    ps = conn.prepareStatement("INSERT INTO user (username, password) VALUES (?,?)"); 
    
    ps.setString(1, username1);
    ps.setString(2, password1);

    ps.executeUpdate();
    txtfUsername.clear();
    pw1.clear();
    pw2.clear();
    ps.close();
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText("User added successfully!");
    alert.showAndWait();
    }
       catch (SQLException sqlException) {
        sqlException.printStackTrace();
         System.exit(1);
      }   
    }
    //added below
    else{
         Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("password does not match!");
    alert.showAndWait();   
    }
    //added above
    }
    
    
        public void backToLoginActionButton(ActionEvent event) throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("Project.fxml"));
        Scene homeScene = new Scene(home);
        
//get Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Login");
        window.setScene(homeScene);
        window.show();
    }
        
    //validation
    public boolean validatePassword(){
    Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-z]).{8,}"); 
    Matcher m = p.matcher(pw1.getText());
    
    if(m.matches()){
    return true;}
    else{
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("invalid password format");
    alert.showAndWait();
    return false;
    }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
