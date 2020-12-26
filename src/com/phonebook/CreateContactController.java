package com.phonebook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class CreateContactController {


	public String Name;
	@FXML
	private Button ContactSaveButton;
	@FXML
	private TextField NameText;
	@FXML
	private TextField DesriptionText;
	@FXML
	private TextField AddressText;
	@FXML
	private TextField AddressLinkText;
	@FXML
	private TextField Phn1Text;
	@FXML
	private TextField Phn2Text;
	@FXML
	private TextField EmailText;
	@FXML
	private TextField WebsiteText;
	@FXML
	private TextField FacebookLinkText;
	@FXML
	private TextField InstagramLinkText;
	@FXML
	private StackPane stackPane;
	@FXML
	private HBox hbox;


	public int Id;
	public String ContactID;
	public String Sql1;
	public String Sql2;
	public String Sql3;
	public String Phn1;
	public String Phn2;


	public int CategoryFlag = HomeController.getCategory();

	// private int newid = ContactsViewController.newid;
	public CreateContactController() throws SQLException {
	}

	public void initialize() throws FileNotFoundException {
		//FileInputStream input = new FileInputStream("C:\\Users\\Sumanth Hegde\\IdeaProjects\\PhoneBook\\src\\com\\phonebook\\CreateContact.png");

		// create a image
		//Image image = new Image(input);

		// create a background image
		//BackgroundImage backgroundimage = new BackgroundImage(image,
				 // BackgroundRepeat.NO_REPEAT,
				 // BackgroundRepeat.NO_REPEAT,
				 // BackgroundPosition.DEFAULT,
				 // BackgroundSize.DEFAULT);

		// create Background
		//Background background = new Background(backgroundimage);

		// set background
	//	hbox.setBackground(background);


		if (ContactsViewController.isCreate) {

			ContactSaveButton.setOnMouseClicked(event -> {

				try {

					String Sql = "select * from id";
					ResultSet rs = Main.statement.executeQuery(Sql);
					while (rs.next()) {
						Id = (rs.getInt("id"));
						System.out.println(Id);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}


				if (LoginController.isUser) {
					ContactID = "u" + Id;
				} else {
					ContactID = "d" + Id;
				}

				String Name = NameText.getText();
				String Desc = DesriptionText.getText();
				String Address = AddressText.getText();
				String AddressLink = AddressLinkText.getText();


				Phn1 = Phn1Text.getText();
				Phn2 = Phn2Text.getText();
				String Email = EmailText.getText();
				String Website = WebsiteText.getText();
				String Facebook = FacebookLinkText.getText();
				String Instagram = InstagramLinkText.getText();

				try {

					if (LoginController.isUser) {
						Sql1 = "INSERT INTO usercontacts  VALUES('" + ContactID + "','" + HomeController.getCategory() + "','" + Name + "','" + Desc + "','" + Address + "','" + AddressLink + "')";
						Sql2 = "INSERT INTO usercontactdetails VALUES('" + ContactID + "','" + Phn1 + "','" + Phn2 + "','" + Email + "','" + Website + "')";
						Sql3 = "INSERT INTO usercontactsocial VALUES('" + ContactID + "','" + Facebook + "','" + Instagram + "')";
					} else {
						Sql1 = "INSERT INTO defaultcontacts  VALUES('" + ContactID + "','" + HomeController.getCategory() + "','" + Name + "','" + Desc + "','" + Address + "','" + AddressLink + "')";
						Sql2 = "INSERT INTO defaultcontactdetails VALUES('" + ContactID + "','" + Phn1 + "','" + Phn2 + "','" + Email + "','" + Website + "')";
						Sql3 = "INSERT INTO defaultcontactsocial VALUES('" + ContactID + "','" + Facebook + "','" + Instagram + "')";
					}

					Main.statement.executeUpdate(Sql1);
					Main.statement.executeUpdate(Sql2);
					Main.statement.executeUpdate(Sql3);

				} catch (Exception e) {
					e.printStackTrace();

					Text title = new Text("Alert");
					title.setStyle("-fx-font-size:20");
					Text text = new Text("Contact Creation Failed");
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
							Stage currentStage = (Stage) NameText.getScene().getWindow();

							try {
								Stage newStage = new Stage();
								Parent root = FXMLLoader.load(getClass().getResource("res/layout/CreateContact.fxml"));
								newStage.setTitle("Phonebook");
								newStage.setScene(new Scene(root));
								newStage.setWidth(currentStage.getWidth());
								newStage.setHeight(currentStage.getHeight());
								newStage.setResizable(false);
								newStage.show();
								currentStage.close();
							} catch (Exception ex) {
								ex.printStackTrace();
							}


						}
					});
					dialog.show();


				}


				Text title = new Text("Alert");
				title.setStyle("-fx-font-size:20");
				Text text = new Text("Contact Added Successfully");
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
					   BackClicked();


					}
				});
				dialog.show();


			});
		} else {

			ContactSaveButton.setText("Update");

			NameText.setText(ContactsViewController.Name);
			DesriptionText.setText(ContactsViewController.Desc);
			AddressText.setText(ContactsViewController.Address);
			AddressLinkText.setText(ContactsViewController.AddressLink);
			Phn1Text.setText(ContactsViewController.Phn1);
			Phn2Text.setText(ContactsViewController.Phn2);
			EmailText.setText(ContactsViewController.EmailLink);
			WebsiteText.setText(ContactsViewController.Website);
			FacebookLinkText.setText(ContactsViewController.FacebookLink);
			InstagramLinkText.setText(ContactsViewController.InstagramLink);

			ContactSaveButton.setOnMouseClicked(mouseEvent -> {
				String ContactId = ContactsViewController.id;
				String Name = NameText.getText();
				String Desc = DesriptionText.getText();
				String Address = AddressText.getText();
				String AddressLink = AddressLinkText.getText();
				String Phn1 = Phn1Text.getText();
				String Phn2 = Phn2Text.getText();
				String Email = EmailText.getText();
				String Website = WebsiteText.getText();
				String Facebook = FacebookLinkText.getText();
				String Instagram = InstagramLinkText.getText();

				try {
					if (ContactId.contains("u")) {

						Sql1 = "update usercontacts set name='" + Name + "',description=\"" + Desc + "\",address='" + Address + "',address_link='" + AddressLink + "' where id='" + ContactId + "'";
						Sql2 = "update usercontactdetails set no1='" + Phn1 + "',no2='" + Phn2 + "',email='" + Email + "',website='" + Website + "' where id='" + ContactId + "'";
						Sql3 = "update usercontactsocial set facebook='" + Facebook + "',instagram='" + Instagram + "' where id='" + ContactId + "'";

					} else {

						Sql1 = "update defaultcontacts set name='" + Name + "',description=\"" + Desc + "\",address='" + Address + "',address_link='" + AddressLink + "' where id='" + ContactId + "'";
						Sql2 = "update defaultcontactdetails set no1='" + Phn1 + "',no2='" + Phn2 + "',email='" + Email + "',website='" + Website + "' where id='" + ContactId + "'";
						Sql3 = "update defaultcontactsocial set facebook='" + Facebook + "',instagram='" + Instagram + "' where id='" + ContactId + "'";

					}
					Main.statement.executeUpdate(Sql1);
					Main.statement.executeUpdate(Sql2);
					Main.statement.executeUpdate(Sql3);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Stage currentStage = (Stage) NameText.getScene().getWindow();




				Text title = new Text("Alert");
				title.setStyle("-fx-font-size:20");
				Text text = new Text("Contact Updated Successfully");
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
						BackClicked();


					}

				});
				dialog.show();





			});
		}

		}

		public void BackClicked () {
			try {
				Stage CreateContactStage = (Stage) ContactSaveButton.getScene().getWindow();
				Stage ContactViewStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("res/layout/ContactsView.fxml"));
				ContactViewStage.setTitle("Phonebook");
				ContactViewStage.setScene(new Scene(root));
				ContactViewStage.setWidth(CreateContactStage.getWidth());
				ContactViewStage.setHeight(CreateContactStage.getHeight());
				ContactViewStage.setResizable(false);
				ContactViewStage.show();
				CreateContactStage.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
