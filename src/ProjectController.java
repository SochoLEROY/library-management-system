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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class ProjectController implements Initializable {
    Project project = new Project();
    
    @FXML PasswordField pw;
    @FXML TextField txtfUsername;
    @FXML TextField txtfPassword;
    
    private static final String URL = 
                    "jdbc:mysql://localhost:3306/students1?zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Password1";
    private PreparedStatement ps;
    private Connection conn; // manages connection
    private ResultSet rs;
    
    
    @FXML
    private void loginButtonAction(ActionEvent event) throws SQLException {
       
    String userName = txtfUsername.getText();
    String password = pw.getText(); 
    
    try{
    if(project.login(userName,password)){
            Parent signup = FXMLLoader.load(getClass().getResource("Management.fxml"));
    Scene scene = new Scene(signup);
        
    //get Stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setTitle("Management");
    window.setScene(scene);
    window.show();
        
    }
    else{
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setContentText("invalid");
    alert.showAndWait();   
    }
    }
    catch(Exception e){
     
    }
    }
    
    @FXML
    private void changeScreenButton(ActionEvent event) throws IOException{
    Parent signup = FXMLLoader.load(getClass().getResource("Signup.fxml"));
    Scene signupScene = new Scene(signup);
        
    //get Stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setTitle("Sign Up");
    window.setScene(signupScene);
    window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
