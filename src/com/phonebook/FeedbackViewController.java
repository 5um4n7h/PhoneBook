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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackViewController {

	@FXML
	private ListView ListView;
	@FXML
	private Label msgLabel;
	@FXML
	private Button DeleteButton;
	@FXML
	private Button BackButton;

	private ObservableList<Object> items;
	public String sql;
	public String Message;
	public Label label;

	public void initialize(){
		items = FXCollections.observableArrayList();
		msgLabel.setWrapText(true);

		try {
			sql = "select * from feedbacks";
			ResultSet rs = Main.statement.executeQuery(sql);


			while (rs.next()) {

				String msg = rs.getString("message");

				//id = rs.getInt("id");

				// Object object = new Object();
				try {
					label = new Label(msg.substring(0, 2));
				}catch (Exception e){

				}
				// label.setFont(Font.font("Segoe UI Semibold",FontWeight.BOLD, 18));
				items.add(label);


				ListView.setItems(items);
				// itemListView.refresh();

			}

			try {
				ListView.getSelectionModel().select(0);
				ListViewItemSelected();
			}catch (NullPointerException nullPointerException){
				nullPointerException.fillInStackTrace();
				ListView.getSelectionModel().clearSelection();
				ListView.getItems().clear();
				Label label = new Label("No pending requests");
				msgLabel.setText("");
				// label.setFont(Font.font("Segoe UI Semibold",FontWeight.BOLD, 18));
				items.add(label);


				ListView.setItems(items);
				ListView.setMouseTransparent( true );
				ListView.setFocusTraversable( false );
				DeleteButton.setDisable(true);

			}


		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		ListView.setOnMouseClicked(mouseEvent -> {
			ListViewItemSelected();
		});

		DeleteButton.setOnMouseClicked(mouseEvent -> {
			try {
				sql = "delete from feedbacks where message='" + msgLabel.getText() + "'";
				Main.statement.executeUpdate(sql);
				ListView.getItems().clear();
				initialize();
			}catch (SQLException sqlException){
				sqlException.printStackTrace();
			}
		});

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

	private void ListViewItemSelected() {
		Label label = (Label) ListView.getSelectionModel().getSelectedItem();
      String s = label.getText();
     try {
	     sql = "select * from feedbacks where message like '" + s + "%'";
	     ResultSet rs = Main.statement.executeQuery(sql);


	     while (rs.next()) {
	     	Message = rs.getString("message");

	     }
     }catch (SQLException sqlException){
     	sqlException.printStackTrace();
     }
		msgLabel.setText(Message);
	}
}
