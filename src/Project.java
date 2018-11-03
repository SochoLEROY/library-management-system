/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class Project extends Application {
    
         private static final String URL = 
                    "jdbc:mysql://localhost:3306/students1?zeroDateTimeBehavior=convertToNull";
     private static final String USERNAME = "root";
     private static final String PASSWORD = "Password1";
     private Connection conn; // manages connection
     private PreparedStatement ps;
     private ResultSet rs;
     
        
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Project.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
        public boolean login(String userName, String password) throws SQLException{
    try{

    conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    ps = conn.prepareStatement("select * from user where username = ? and password=?");
    ps.setString(1, userName);
    ps.setString(2, password);
    rs = ps.executeQuery();
    if(rs.next()){
        return true;
    }
    else{
        return false;
    }
    
    }
    catch(Exception e){
    e.printStackTrace();
    return false;    
    }
    finally{
    rs.close();
    ps.close();
    }
    }
        

    
}
