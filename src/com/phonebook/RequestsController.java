package com.phonebook;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestsController {

	@FXML
	private ListView ListView;
	@FXML
	private Button ReviewButton;
	@FXML
	private Button DeleteButton;
	@FXML
	private Button BackButton;
	@FXML
	private StackPane stackPane;

	private ObservableList<Object> items;
	public String sql;

	public static Boolean isReview = false;

	public static int category;
	public static int id;
	public static String Name;
	public static String Desc;
	public static String Address;
	public static String AddressLink;
	public static String Phn1;
	public static String Phn2;
	public static String Email;
	public static String Website;
	public static String EmailLink;
	public static String FacebookLink;
	public static String InstagramLink;


	public void initialize() {
		items = FXCollections.observableArrayList();

		try {


			sql = "call getContacts(0,'user')";
			ResultSet rs = Main.statement.executeQuery(sql);


			while (rs.next()) {

				String name = rs.getString("name");
				String username = rs.getString("username");
				//id = rs.getInt("id");

				// Object object = new Object();
				Label label = new Label(name);
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
				// label.setFont(Font.font("Segoe UI Semibold",FontWeight.BOLD, 18));
				items.add(label);
				ListView.setMouseTransparent( true );
				ListView.setFocusTraversable( false );
				ListView.setItems(items);
				ReviewButton.setDisable(true);
				ReviewButton.setDisable(true);






			}


		} catch (SQLException ex) {
			ex.printStackTrace();
		}


		ListView.setOnMouseClicked(mouseEvent -> {
			ListViewItemSelected();

		});

		ReviewButton.setOnMouseClicked(mouseEvent -> {
			isReview =true;
			try {


				Stage currentStage = (Stage) ReviewButton.getScene().getWindow();


				Stage newStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("res/layout/CreateContact.fxml"));
				newStage.setTitle("Phonebook");
				newStage.setScene(new Scene(root));
				newStage.setWidth(currentStage.getWidth());
				newStage.setHeight(currentStage.getHeight());
				newStage.setResizable(false);
				newStage.show();
				currentStage.close();

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});


		BackButton.setOnMouseClicked(mouseEvent -> {
			try {


				Stage currentStage = (Stage) ReviewButton.getScene().getWindow();


				Stage newStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("res/layout/Home.fxml"));
				newStage.setTitle("Phonebook");
				newStage.setScene(new Scene(root));
				newStage.setWidth(currentStage.getWidth());
				newStage.setHeight(currentStage.getHeight());
				newStage.setResizable(false);
				newStage.show();
				currentStage.close();

			} catch (Exception exception) {
				exception.printStackTrace();
			}


		});

		DeleteButton.setOnMouseClicked(mouseEvent -> {

			Text title = new Text("Alert");
			title.setStyle("-fx-font-size:20");
			Text text = new Text("Are you really want to delete this Request ?");
			text.setStyle("-fx-font-size:14");
			JFXDialogLayout dialogContent = new JFXDialogLayout();
			dialogContent.setHeading(title);
			dialogContent.setBody(text);
			JFXButton cancel = new JFXButton("Cancel");
			cancel.setButtonType(JFXButton.ButtonType.RAISED);
			cancel.setStyle("-fx-background-color:#69FF81;-fx-font-size:15;-fx-font-weight:bold;");
			JFXButton yes = new JFXButton("Yes");
			yes.setButtonType(JFXButton.ButtonType.RAISED);
			yes.setStyle("-fx-background-color:#FF6E6E;-fx-font-size:15;-fx-font-weight:bold;");
			dialogContent.setActions(yes,cancel);

			JFXDialog dialog = new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.CENTER);

			cancel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent __) {
					dialog.close();
				}
			});



			yes.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					dialog.close();

					try{

						sql = "delete from usercontacts where id="+id;
						Main.statement.executeUpdate(sql);
						ListView.getItems().clear();
						initialize();

						Text title = new Text("Alert");
						title.setStyle("-fx-font-size:20");
						Text text = new Text("Request Deletion Successfull");
						text.setStyle("-fx-font-size:14");
						JFXDialogLayout dialogContent = new JFXDialogLayout();
						dialogContent.setHeading(title);
						dialogContent.setBody(text);
						JFXButton Close = new JFXButton("Close");
						Close.setButtonType(JFXButton.ButtonType.RAISED);
						Close.setStyle("-fx-background-color:#FFF46C;-fx-font-size:15;-fx-font-weight:bold;");
						dialogContent.setActions(Close);

						JFXDialog dialog = new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.CENTER);
						Close.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent __) {
								dialog.close();
								int no = 0;
								try{
									String sql = "select totalreqrej from statistics";
									ResultSet resultSet = Main.statement.executeQuery(sql);
									while (resultSet.next()){
										no = resultSet.getInt("totalreqrej");
									}
									no = no +1;
									sql = "update statistics set totalreqrej="+no;
									Main.statement.executeUpdate(sql);
								}catch (SQLException sqlException){
									sqlException.printStackTrace();

								}

							}
						});
						dialog.show();






					}catch (Exception e){


						e.printStackTrace();


						Text title = new Text("Alert");
						title.setStyle("-fx-font-size:20");
						Text text = new Text("Error, Request Deletion unsuccessful!");
						text.setStyle("-fx-font-size:14");
						JFXDialogLayout dialogContent = new JFXDialogLayout();
						dialogContent.setHeading(title);
						dialogContent.setBody(text);
						JFXButton Close = new JFXButton("Close");
						Close.setButtonType(JFXButton.ButtonType.RAISED);
						Close.setStyle("-fx-background-color:#FFF46C;-fx-font-size:15;-fx-font-weight:bold;");
						dialogContent.setActions(Close);

						JFXDialog dialog = new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.CENTER);
						Close.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent __) {
								dialog.close();


							}
						});
						dialog.show();





					}

				}
			});
			dialog.show();






		});

	}

	/*
	private void setListView() {

		try {
			items.clear();
			sql = "call getContacts(0,'default')";
			ResultSet rs = Main.statement.executeQuery(sql);


			while (rs.next()) {

				String name = rs.getString("name");
				//id = rs.getInt("id");

				// Object object = new Object();
				Label label = new Label(name);
				// label.setFont(Font.font("Segoe UI Semibold",FontWeight.BOLD, 18));
				items.add(label);


				ListView.setItems(items);
				// itemListView.refresh();

			}
			ListView.getSelectionModel().select(0);
			ListViewItemSelected();


		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}


	 */
	private void ListViewItemSelected() {
		Label label = (Label) ListView.getSelectionModel().getSelectedItem();
		try {
			String s = label.getText();

			String sql = "SELECT * FROM allcontacts WHERE name='" + s + "'";
			System.out.println(s);
			ResultSet rs = Main.statement.executeQuery(sql);
			while (rs.next()) {
				id = Integer.parseInt(rs.getString("id"));
				category = rs.getInt("category");
				Name = rs.getString("name");
				Desc = rs.getString("description");
				Address = rs.getString("address");
				AddressLink = rs.getString("address_link");
				Phn1 = rs.getString("no1");
				Phn2 = rs.getString("no2");
				EmailLink = rs.getString("email");
				Website = rs.getString("website");
				FacebookLink = rs.getString("facebook");
				InstagramLink = rs.getString("instagram");

			}


		} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			label = new Label("No pending requests");
			// label.setFont(Font.font("Segoe UI Semibold",FontWeight.BOLD, 18));
			ListView.getSelectionModel().clearSelection();
			ListView.getItems().clear();
			items.add(label);
			ListView.setMouseTransparent( true );
			ListView.setFocusTraversable( false );

		}
	}
}