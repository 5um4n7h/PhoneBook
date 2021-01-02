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
	@FXML
	private StackPane stackPane;

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
					label = new Label(msg.substring(0, 15));
				}catch (Exception e){

					try{
						label = new Label(msg.substring(0, 4));
					}catch (Exception exception){
						label = new Label(msg.substring(0,1));
					}
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
				Text title = new Text("Alert");
				title.setStyle("-fx-font-size:20");
				Text text = new Text("Feedback deleted Successful");
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
			}catch (SQLException sqlException){
				sqlException.printStackTrace();
				Text title = new Text("Alert");
				title.setStyle("-fx-font-size:20");
				Text text = new Text("Error !");
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
