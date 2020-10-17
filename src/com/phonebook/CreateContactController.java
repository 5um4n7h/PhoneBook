package com.phonebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class CreateContactController {

    public String Name;
    @FXML
    private Button ContactCreateButton;
    @FXML
    private TextField ContactNameText;
    @FXML
    private TextField PhoneNumberText;

    private final String host = "jdbc:mysql://localhost:3306/contacts";
    private final String uName = "root";
    private final String uPass = "1234";

    private Connection con = DriverManager.getConnection(host, uName, uPass);

    public int CategoryFlag = HomeController.getCategory();

    public CreateContactController() throws SQLException {
    }

    public void initialize() {


       // System.out.println(ContactsViewController.getData("name"));
      //  System.out.println(ContactsViewController.getData("number"));
       // ContactNameText.setText(ContactsViewController.getData("name"));


        ContactCreateButton.setOnMouseClicked(event -> {
            String Name = ContactNameText.getText();
            String Phone = PhoneNumberText.getText();
            try {
                Statement Stat = con.createStatement();
                String sql = "INSERT INTO " + ContactsViewController.GetCategoryName() + " VALUES('"+Name+"','"+Phone+"',"+'1'+")";
                Stat.executeUpdate(sql);
            } catch (Exception e) {
                System.out.println(e);
            }

            Stage currentStage = (Stage) ContactNameText.getScene().getWindow();
            // do what you have to do

            try {
                Stage newStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("res/layout/ContactsView.fxml"));
                newStage.setTitle("Phonebook");
                newStage.setScene(new Scene(root));
                newStage.setWidth(currentStage.getWidth());
                newStage.setHeight(currentStage.getHeight());
                newStage.setResizable(false);
                newStage.show();
                currentStage.close();
            }catch (Exception ex){
                System.out.println(ex);
            }
        });

    }
}
