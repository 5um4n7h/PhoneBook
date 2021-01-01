package com.phonebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsController {

	public String sql;
	@FXML
	public Label ttlcon;
	@FXML
	public Label ttldel;
	@FXML
	public Label ttlreq;
	@FXML
	public Label ttlreqacc;
	@FXML
	public Label ttlreqrej;
	@FXML
	public Label users;



	@FXML
	private Button BackButton;

	public void initialize() {

		try {
			sql = "select totalcontacts from statistics";
			ResultSet resultSet = Main.statement.executeQuery(sql);
			while (resultSet.next()){
				ttlcon.setText(String.valueOf(resultSet.getInt("totalcontacts")));
			}
			//ttlcon.setText(String.valueOf(TotalContacts));
		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}


		try {
			sql = "select totaldeleted from statistics";
			ResultSet resultSet = Main.statement.executeQuery(sql);
			while (resultSet.next()){
				ttldel.setText(String.valueOf(resultSet.getInt("totaldeleted")));
			}
			//ttlcon.setText(String.valueOf(TotalContacts));
		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}


		try {
			sql = "select totalreq from statistics";
			ResultSet resultSet = Main.statement.executeQuery(sql);
			while (resultSet.next()){
				ttlreq.setText(String.valueOf(resultSet.getInt("totalreq")));
			}
			//ttlcon.setText(String.valueOf(TotalContacts));
		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}


		try {
			sql = "select totalreqacc from statistics";
			ResultSet resultSet = Main.statement.executeQuery(sql);
			while (resultSet.next()){
				ttlreqacc.setText(String.valueOf(resultSet.getInt("totalreqacc")));
			}
			//ttlcon.setText(String.valueOf(TotalContacts));
		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}


		try {
			sql = "select totalreqrej from statistics";
			ResultSet resultSet = Main.statement.executeQuery(sql);
			while (resultSet.next()){
				ttlreqrej.setText(String.valueOf(resultSet.getInt("totalreqrej")));
			}
			//ttlcon.setText(String.valueOf(TotalContacts));
		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}



		try {
			sql = "select users from statistics";
			ResultSet resultSet = Main.statement.executeQuery(sql);
			while (resultSet.next()){
				users.setText(String.valueOf(resultSet.getInt("users")));
			}
			//ttlcon.setText(String.valueOf(TotalContacts));
		} catch (SQLException sqlException) {

			sqlException.printStackTrace();
		}





		BackButton.setOnMouseClicked(mouseEvent -> {
			try {
				Stage HomeStage = (Stage) BackButton.getScene().getWindow();
				Stage LoginStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("res/layout/Home.fxml"));
				LoginStage.setTitle("Phonebook");
				LoginStage.setScene(new Scene(root));
				LoginStage.setWidth(HomeStage.getWidth());
				LoginStage.setHeight(HomeStage.getHeight());
				LoginStage.setResizable(false);
				LoginStage.show();
				HomeStage.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();

			}
		});
	}
}