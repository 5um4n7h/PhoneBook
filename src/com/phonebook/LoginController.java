package com.phonebook;

import com.jfoenix.controls.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;

import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.sql.ResultSet;


public class LoginController{

	public static boolean isUser = true;
	@FXML
	private Button ChangeLogin;
	@FXML
	private Label LoginType;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private ImageView icon;
	@FXML
	private StackPane stackPane;

	public String pass;
	public String UserType;
	public Parent root;

	public void OnChangeLoginClick() {

		//System.out.println("Change Login Button Clicked");
		if (isUser) {
			LoginType.setText("Admin Login");
			ChangeLogin.setText("Login as User");
			try {
				icon.setImage(new Image(new FileInputStream("src\\com\\phonebook\\res\\images\\admin.png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			isUser = false;


		} else {
			LoginType.setText("User Login");
			ChangeLogin.setText("Login as Admin");
			try {
				icon.setImage(new Image(new FileInputStream("src\\com\\phonebook\\res\\images\\user.png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			isUser = true;
		}

	}

	public void OnLoginButtonClick() {
		if(username.getText().isBlank()||password.getText().isBlank()){

			Text title =new Text( "Alert");
			title.setStyle("-fx-font-size:20");
			Text text = new Text("Enter User Name and Password !!");
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
			return;


		}



		System.out.println("Clicked");
		if (isUser) {
			UserType = "user";

		} else {
			UserType = "admin";
		}
		try {

			String sql = "SELECT password FROM authentication where username='" + username.getText() + "' AND type='" + UserType + "'";
			ResultSet rs = Main.statement.executeQuery(sql);
			while (rs.next()) {
				pass = rs.getString("password");
				System.out.println("fetched password is: " + pass);
				System.out.println("Entered password is: " + password.getText());
			}

			if (pass.equals(password.getText())) {
				try {
					// get a handle to the stage
					Stage currentStage = (Stage) LoginType.getScene().getWindow();
					// do what you have to do

					Stage HomeStage = new Stage();
					root = FXMLLoader.load(getClass().getResource("res/layout/Home.fxml"));
					HomeStage.setTitle("Phonebook");
					HomeStage.setScene(new Scene(root));
					HomeStage.setWidth(currentStage.getWidth());
					HomeStage.setHeight(currentStage.getHeight());
					HomeStage.setResizable(false);
					HomeStage.show();
					currentStage.close();
				} catch (IOException ioException) {

					ioException.printStackTrace();

				}
			} else {

				Text title =new Text( "Alert");
				title.setStyle("-fx-font-size:20");
				Text text = new Text("Wrong password!!");
				text.setStyle("-fx-font-size:14");
				JFXDialogLayout dialogContent = new JFXDialogLayout();
				dialogContent.setHeading(title);
				dialogContent.setBody(text);
				JFXButton close = new JFXButton("Close");
				close.setButtonType(JFXButton.ButtonType.RAISED);
				close.setStyle("-fx-background-color: #69FF81;-fx-font-size:15;-fx-font-weight:bold;");
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
		} catch (Exception e) {
			Text title =new Text( "Alert");
			title.setStyle("-fx-font-size:20");
			Text text = new Text("User not found!!");
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
			e.printStackTrace();
		}
	}


}