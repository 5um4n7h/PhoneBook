package com.phonebook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.sql.*;

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
}
