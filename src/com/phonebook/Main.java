package com.phonebook;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("res/layout/Login.fxml"));

        primaryStage.setTitle("Phonebook");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

       // HomeController homeController = new HomeController();




    }
    

    public static void main(String[] args) {
        launch(args);

        //to get available font list in javafx
        //System.out.println(javafx.scene.text.Font.getFamilies());
    }
}
