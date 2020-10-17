package com.phonebook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;


public class ContactViewCont {

    @FXML
    private ListView itemListView;
    @FXML
    private Label CategoryLabel;
    @FXML
    private Label ContactNameLabel;
    @FXML
    private Label ContactNumberLabel;
    @FXML
    private TextField SearchText;
    @FXML
    private Button CreateContactButton;
    @FXML
    private Button ContactEditButton;
    @FXML
    private Button ContactDeleteButton;

    private static boolean isUserContact;



    //for jdbc connection
    private final String host = "jdbc:mysql://localhost:3306/contacts";
    private final String uName = "root";
    private final String uPass = "1234";
    public static String Name;
    public static String Number;
    private Connection con = null;

    {
        try {
            con = DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //for listview
    private ObservableList<Object> items;

    public static int CategoryFlag = HomeController.getCategory();



    public void initialize(){

    }
}
