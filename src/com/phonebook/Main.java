package com.phonebook;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {



        public static final String host = "jdbc:mysql://localhost:3306/contacts";
        public static final String uName = "root";
        public static final String uPass = "1234";
        public static Connection Con;
        public static Statement statement;
    static {
        try {
            Con = DriverManager.getConnection(host, uName, uPass);
            statement = Con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    @Override
    public void start(Stage loginStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("res/layout/Login.fxml"));

        loginStage.setTitle("Phonebook");
        loginStage.setScene(new Scene(root));
        loginStage.setResizable(false);
        loginStage.show();


       // HomeController homeController = new HomeController();




    }
    

    public static void main(String[] args) {
        launch(args);
        System.out.println("main called");
        //to get available font list in javafx
        //System.out.println(javafx.scene.text.Font.getFamilies());
    }
}
