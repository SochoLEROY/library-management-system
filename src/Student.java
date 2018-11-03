/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Victor
 */
public class Student {
    private SimpleIntegerProperty id;
    private SimpleStringProperty firstName; 
    private SimpleStringProperty lastName; 
    private SimpleStringProperty email;


    public Student(int studentID, String firstName, String lastName, String email) {
        this.id = new SimpleIntegerProperty(studentID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);

    }

    public int getId() {
        return id.get();
    }

    public void setId(SimpleIntegerProperty id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(SimpleStringProperty firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(SimpleStringProperty lastName) {
        this.lastName = lastName;
    }
    
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }







    

}
