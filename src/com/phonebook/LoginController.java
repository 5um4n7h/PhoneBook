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
import java.sql.SQLException;


public class LoginController{

	public static boolean isUser = true;
	public static boolean isNew = false;
	@FXML
	private Button ChangeLogin;
	@FXML
	private Label LoginType;
	@FXML
	public TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private ImageView icon;
	@FXML
	private StackPane stackPane;
	@FXML
	private Button UserCreateButton;
	@FXML
	private Button LoginButton;

	public String pass;
	public String Sql;
	public String UserType;
	public Parent root;
	public Boolean Clickedflag=false;


	public void initialize(){
		isUser = true;
		isNew = false;
		UserType = "user";
		System.out.println(isUser);
		System.out.println(UserType);
		UserCreateButton.setOnMouseClicked(mouseEvent -> {
			if(Clickedflag)
			{
				Clickedflag = false;
				isNew = false;
				isUser = true;
				LoginButton.setText("Login");
				LoginType.setText("User Log in");
				ChangeLogin.setVisible(true);
				ChangeLogin.setText("login as Admin");
				UserCreateButton.setText("Create New User");
				MakeEmpty();
				try {
					icon.setImage(new Image(new FileInputStream("src\\com\\phonebook\\res\\images\\user.png")));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return;
			}
         Clickedflag = true;
			LoginType.setText("Create User");
			try {
				icon.setImage(new Image(new FileInputStream("src\\com\\phonebook\\res\\images\\user.png")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			LoginButton.setText("Sign Up");
			isNew = true;
			ChangeLogin.setVisible(false);
			UserCreateButton.setText("Back to login");

		});

	}

	public void OnChangeLoginClick() {
		MakeEmpty();
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

	public void MakeEmpty(){
		username.setText("");
		password.setText("");
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

		if(username.getText().length()<6||password.getText().length()<6){
			Text title =new Text( "Alert");
			title.setStyle("-fx-font-size:20");
			Text text = new Text("User name and password must be 6 characters !!");
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









		if(isNew){

			try {
				Sql = "SELECT * FROM contacts.authentication where username='" + username.getText() + "'";
				ResultSet resultSet = Main.statement.executeQuery(Sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("username"));
				if(!(resultSet.getString("username").isEmpty())){
					Text title =new Text( "Alert");
					title.setStyle("-fx-font-size:20");
					Text text = new Text("username already taken, try different username !");
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
			}
			}catch (SQLException sqlException){
				sqlException.printStackTrace();
				return;
			}


			Sql ="insert into authentication set type='user',username='"+username.getText()+"',password='"+password.getText()+"'";
			try {
				Main.statement.executeUpdate(Sql);
				Text title =new Text( "Alert");
				title.setStyle("-fx-font-size:20");
				Text text = new Text("Sign up Successful, Now you can login with your username and password.");
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
						Clickedflag = false;
						isNew = false;
						isUser = true;
						LoginButton.setText("Login");
						LoginType.setText("User Log in");
						ChangeLogin.setVisible(true);
						ChangeLogin.setText("login as Admin");
						UserCreateButton.setText("Create New User");
						MakeEmpty();
						try {
							icon.setImage(new Image(new FileInputStream("src\\com\\phonebook\\res\\images\\user.png")));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

					}

				});
				dialog.show();


			} catch (SQLException throwables) {
				throwables.printStackTrace();

				Text title =new Text( "Alert");
				title.setStyle("-fx-font-size:20");
				Text text = new Text("Sign up Failed");
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
			return;
		}








		System.out.println("Clicked");
		if (isUser) {
			UserType = "user";

		} else {
			UserType = "admin";
		}
		try {
         System.out.println(UserType);
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