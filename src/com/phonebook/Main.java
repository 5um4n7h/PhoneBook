package com.phonebook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {
	//to connect to MySQL DB
	public final String host = "jdbc:mysql://localhost:3306/contacts";
	public final String uName = "root";
	public final String uPass = "1234";
	public Connection Con;
	public static Statement statement;

	{
		try {
			Con = DriverManager.getConnection(host, uName, uPass);
			statement = Con.createStatement();
		} catch (SQLException throwable) {
			throwable.printStackTrace();
		}
	}


	@Override
	public void start(Stage loginStage) throws Exception {
		//to display login page
		Parent root = FXMLLoader.load(getClass().getResource("res/layout/Login.fxml"));
		loginStage.setTitle("Phonebook");
		loginStage.setScene(new Scene(root));
		loginStage.setResizable(false);
		loginStage.show();

	}


	public static void main(String[] args) {
		launch(args);
	}

	public static void Dialog(StackPane stackPane,String text){

	}
}
