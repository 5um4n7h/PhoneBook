package com.phonebook;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.Stage;
import java.io.*;
import java.sql.*;

public class LoginController {

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

	public String pass;
	public String UserType;


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
					Parent root = FXMLLoader.load(getClass().getResource("res/layout/Home.fxml"));
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
				Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong password!");
				alert.show();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();
				}
				alert.hide();


			}
		} catch (Exception e) {
			System.out.println("User not found");
			Alert alert = new Alert(Alert.AlertType.WARNING, "User not found");
			alert.show();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException interruptedException) {
				interruptedException.printStackTrace();
			}
			alert.hide();
			System.out.println("Error: " + e);
		}
	}


}