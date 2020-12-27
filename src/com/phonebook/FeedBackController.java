package com.phonebook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;


public class FeedBackController {
	@FXML
	private Button BackButton;
	@FXML
	private Button SendButton;
	@FXML
	private TextArea MessageArea;
	@FXML
	private StackPane stackPane;

	public String msg;
	public String sql;
	public void initialize(){

		BackButton.setOnMouseClicked(mouseEvent -> {
			Stage stage = (Stage) BackButton.getScene().getWindow();
			stage.close();

		});


		SendButton.setOnMouseClicked(mouseEvent -> {
			msg = MessageArea.getText();
			sql = "insert into feedbacks (message) values ('"+msg+"')";
			try{

				Main.statement.executeUpdate(sql);

				Text title = new Text("Alert");
				title.setStyle("-fx-font-size:20");
				Text text = new Text("Feedback Sent Successfully");
				text.setStyle("-fx-font-size:14");
				JFXDialogLayout dialogContent = new JFXDialogLayout();
				dialogContent.setHeading(title);
				dialogContent.setBody(text);
				JFXButton close = new JFXButton("Close");
				close.setButtonType(JFXButton.ButtonType.RAISED);
				close.setStyle("-fx-background-color:#69FF81;-fx-font-size:15;-fx-font-weight:bold;");
				dialogContent.setActions(close);
				JFXDialog dialog = new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.CENTER);
				close.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent __) {
						dialog.close();
						Stage stage = (Stage) BackButton.getScene().getWindow();
						stage.close();




					}
				});
				dialog.show();


			}catch (SQLException sqlException){
				Text title = new Text("Alert");
				title.setStyle("-fx-font-size:20");
				Text text = new Text("Failed!");
				text.setStyle("-fx-font-size:14");
				JFXDialogLayout dialogContent = new JFXDialogLayout();
				dialogContent.setHeading(title);
				dialogContent.setBody(text);
				JFXButton close = new JFXButton("Close");
				close.setButtonType(JFXButton.ButtonType.RAISED);
				close.setStyle("-fx-background-color:#69FF81;-fx-font-size:15;-fx-font-weight:bold;");
				dialogContent.setActions(close);
				JFXDialog dialog = new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.CENTER);
				close.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent __) {
						dialog.close();





					}
				});
				dialog.show();


			}


		});
	}



}
