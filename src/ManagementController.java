/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class ManagementController implements Initializable {

    
    
    public void changeScreenButton(ActionEvent event) throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("LibraryManagement.fxml"));
        Scene homeScene = new Scene(home);
        
//get Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Library Management");
        window.setScene(homeScene);
        window.show();
    }
    
    
        public void changeScreenButton2(ActionEvent event) throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("UserManagement.fxml"));
        Scene homeScene = new Scene(home);
        
//get Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("User Management");
        window.setScene(homeScene);
        window.show();
    }
        
     
        public void changeScreenButton3(ActionEvent event) throws IOException{
        Parent home = FXMLLoader.load(getClass().getResource("CheckoutManagement.fxml"));
        Scene homeScene = new Scene(home);
        
//get Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("User Management");
        window.setScene(homeScene);
        window.show();
    }

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
