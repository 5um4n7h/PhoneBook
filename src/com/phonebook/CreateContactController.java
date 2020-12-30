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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class CreateContactController {



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
	@FXML
	private ComboBox ComboBox;
	@FXML
	private Label CategoryLabel;


	public int Id;
	public String ContactID;
	public String Sql1;
	public String Sql2;
	public String Sql3;
	public String Phn1;
	public String Phn2;
	public String page;
	public String Name;
	public String Desc;
	public String Address;
	public String AddressLink;
	public String Email;
	public String Website;
	public String Facebook;
	public String Instagram;



	// private int newid = ContactsViewController.newid;}

	public void initialize() {

		if(!LoginController.isUser)
			ContactSaveButton.setText("Create");
		else
			ContactSaveButton.setText("Send Request");
		ComboBox.setVisible(false);
		CategoryLabel.setVisible(false);



		if (ContactsViewController.isCreate) {


			/*
			NameText.setText("");
			Phn1Text.setText("");
			Phn2Text.setText("");
			EmailText.setText("");
			WebsiteText.setText("");

			 */

			ContactSaveButton.setOnMouseClicked(event -> {


				if (FormValidation()==1)
				{

				Name = NameText.getText();
				Desc = DesriptionText.getText();
				Address = AddressText.getText();
				AddressLink = AddressLinkText.getText();


				Phn1 = Phn1Text.getText();
				Phn2 = Phn2Text.getText();
				Email = EmailText.getText();
				Website = WebsiteText.getText();
				Facebook = FacebookLinkText.getText();
				Instagram = InstagramLinkText.getText();


				try {
					String sql = "select id from statistics";
					ResultSet resultSet = Main.statement.executeQuery(sql);
					while (resultSet.next()){
						Id = resultSet.getInt("id");

					}


				}catch(SQLException sqlDataException){
					sqlDataException.printStackTrace();

				}



				if (!LoginController.isUser) {


					Sql1 = "INSERT INTO defaultcontacts (id,category,name,description,address,address_link) VALUES("+Id+"," + HomeController.getCategory() + ",'" + Name + "','" + Desc + "','" + Address + "','" + AddressLink + "')";
					Sql2 = "INSERT INTO defaultcontactdetails (id,no1,no2,email,website) VALUES("+Id+",'" + Phn1 + "','" + Phn2 + "','" + Email + "','" + Website + "')";
					Sql3 = "INSERT INTO defaultcontactsocial (id,facebook,instagram) VALUES("+Id+",'" + Facebook + "','" + Instagram + "')";
					try {
						Main.statement.executeUpdate(Sql1);
						Main.statement.executeUpdate(Sql2);
						Main.statement.executeUpdate(Sql3);

						Text title = new Text("Alert");
						title.setStyle("-fx-font-size:20");
						Text text = new Text("Contact Created Successfully");
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
								ContactsViewController.isCreate = false;
								BackClicked();


							}
						});
						dialog.show();


					} catch (SQLException sqlException) {
						sqlException.printStackTrace();

						Text title = new Text("Alert");
						title.setStyle("-fx-font-size:20");
						Text text = new Text("Failed !");
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
				} else {


					Sql1 = "INSERT INTO usercontacts (id,category,name,description,address,address_link,username) VALUES(" + Id + "," + HomeController.getCategory() + ",'" + Name + "','" + Desc + "','" + Address + "','" + AddressLink + "','user')";
					Sql2 = "INSERT INTO usercontactdetails (id,no1,no2,email,website) VALUES(" + Id + ",'" + Phn1 + "','" + Phn2 + "','" + Email + "','" + Website + "')";
					Sql3 = "INSERT INTO usercontactsocial (id,facebook,instagram) VALUES(" + Id + ",'" + Facebook + "','" + Instagram + "')";
					try {


						Main.statement.executeUpdate(Sql1);
						Main.statement.executeUpdate(Sql2);
						Main.statement.executeUpdate(Sql3);

						Text title = new Text("Alert");
						title.setStyle("-fx-font-size:20");
						Text text = new Text("Request  Successful");
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
								ContactsViewController.isCreate = false;


							}
						});
						dialog.show();


					} catch (SQLException sqlException) {
						sqlException.printStackTrace();

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


				}
				}else {
					System.out.println("Failed");
				}
			});



		}

		else if(RequestsController.isReview){


			System.out.println("called!");
			ComboBox.setVisible(true);
			CategoryLabel.setVisible(true);


			ComboBox.getItems().addAll("Doctors", "Electricians", "Plumbers",
					  "Carpenters", "Lawyers","Shops","Educational Institutions","Banks" );

         ComboBox.getSelectionModel().select(RequestsController.category);
			NameText.setText(RequestsController.Name);
			DesriptionText.setText(RequestsController.Desc);
			AddressText.setText(RequestsController.Address);
			AddressLinkText.setText(RequestsController.AddressLink);
			Phn1Text.setText(RequestsController.Phn1);
			Phn2Text.setText(RequestsController.Phn2);
			EmailText.setText(RequestsController.EmailLink);
			WebsiteText.setText(RequestsController.Website);
			FacebookLinkText.setText(RequestsController.FacebookLink);
			InstagramLinkText.setText(RequestsController.InstagramLink);

			ContactSaveButton.setText("Create");

			ContactSaveButton.setOnMouseClicked(mouseEvent -> {

				if(FormValidation()==1) {

					Name = NameText.getText();
					Desc = DesriptionText.getText();
					Address = AddressText.getText();
					AddressLink = AddressLinkText.getText();


					Phn1 = Phn1Text.getText();
					Phn2 = Phn2Text.getText();
					Email = EmailText.getText();
					Website = WebsiteText.getText();
					Facebook = FacebookLinkText.getText();
					Instagram = InstagramLinkText.getText();


					try {
						String sql = "select id from statistics";
						ResultSet resultSet = Main.statement.executeQuery(sql);
						while (resultSet.next()) {
							Id = resultSet.getInt("id");

						}


					} catch (SQLException sqlDataException) {
						sqlDataException.printStackTrace();

					}

					System.out.println("Review and save Called");
					Sql1 = "INSERT INTO defaultcontacts (id,category,name,description,address,address_link) VALUES(" + Id + "," + ComboBox.getSelectionModel().getSelectedIndex() + ",'" + Name + "','" + Desc + "','" + Address + "','" + AddressLink + "')";
					Sql2 = "INSERT INTO defaultcontactdetails (id,no1,no2,email,website) VALUES(" + Id + ",'" + Phn1 + "','" + Phn2 + "','" + Email + "','" + Website + "')";
					Sql3 = "INSERT INTO defaultcontactsocial (id,facebook,instagram) VALUES(" + Id + ",'" + Facebook + "','" + Instagram + "')";
					try {
						Main.statement.executeUpdate(Sql1);
						Main.statement.executeUpdate(Sql2);
						Main.statement.executeUpdate(Sql3);


						try {
							String sql = "delete from usercontacts where id=" + RequestsController.id;
							Main.statement.executeUpdate(sql);
							initialize();
						} catch (SQLException sqlException) {

						}

						Text title = new Text("Alert");
						title.setStyle("-fx-font-size:20");
						Text text = new Text("Contact Created Successfully");
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

								int no = 0;
								try{
									String sql = "select totalreqacc from statistics";
									ResultSet resultSet = Main.statement.executeQuery(sql);
									while (resultSet.next()){
										no = resultSet.getInt("totalreqacc");
									}
									no = no +1;
									sql = "update statistics set totalreqacc="+no;
									Main.statement.executeUpdate(sql);
								}catch (SQLException sqlException){
									sqlException.printStackTrace();

								}







								BackClicked();


							}
						});
						dialog.show();


					} catch (SQLException sqlException) {
						sqlException.printStackTrace();

						Text title = new Text("Alert");
						title.setStyle("-fx-font-size:20");
						Text text = new Text("Failed !");
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


				}else {
					System.out.println("Failed");

				}
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

				if(FormValidation()==1) {
					String ContactId = String.valueOf(ContactsViewController.id);
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


						Sql1 = "update defaultcontacts set name='" + Name + "',description=\"" + Desc + "\",address='" + Address + "',address_link='" + AddressLink + "' where id='" + ContactId + "'";
						Sql2 = "update defaultcontactdetails set no1='" + Phn1 + "',no2='" + Phn2 + "',email='" + Email + "',website='" + Website + "' where id='" + ContactId + "'";
						Sql3 = "update defaultcontactsocial set facebook='" + Facebook + "',instagram='" + Instagram + "' where id='" + ContactId + "'";


						Main.statement.executeUpdate(Sql1);
						Main.statement.executeUpdate(Sql2);
						Main.statement.executeUpdate(Sql3);

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

					} catch (Exception e) {
						e.printStackTrace();
						Text title = new Text("Alert");
						title.setStyle("-fx-font-size:20");
						Text text = new Text("Contact Update Failed !");
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


				}else {
					System.out.println("Failed");
				}

			});
		}

		}

	private int FormValidation() {
		String msg="";
		if (NameText.getText().equals("")){
			msg = msg + "Name is blank";
		}

		if (!(EmailText.getText().equals(""))&&!(EmailText.getText().contains("@"))&&!(EmailText.getText().contains("."))){
			msg = msg + "\n"+"Enter Valid email id";
		}

		if(Phn1Text.getText().equals("")&&Phn2Text.getText().equals("")){
			msg = msg + "\n"+"Phone Number is blank";
		}else{
			if(!Phn1Text.getText().equals("")){
				try {
					long no = Long.parseLong(Phn1Text.getText());

					if (Phn1Text.getText().length()<8||Phn1Text.getText().length()>12){
						msg = msg + "\n Enter Valid Phone No 1";
					}
				}catch (NumberFormatException numberFormatException){
					msg = msg + "\n Phone no 1 Contain Characters";
				}


			}

			if(!Phn2Text.getText().equals("")){
				try {
					long no = Long.parseLong(Phn2Text.getText());
					if (Phn2Text.getText().length()<8||Phn2Text.getText().length()>12){
						msg = msg + "\n Enter Valid Phone No 2";
					}
				}catch (NumberFormatException numberFormatException){
					msg = msg + "\n Phone no 2 Contain Characters";
				}


			}



		}




		if (!(WebsiteText.getText().equals(""))&&!(WebsiteText.getText().contains("."))){
			msg = msg + "\n"+"Enter Valid website address";
		}


		if (!msg.equals("")){

			System.out.println(msg);
			Text title = new Text("Alert");
			title.setStyle("-fx-font-size:20");
			Text text = new Text(msg);
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
			return 0;

		}else {
			return 1;
		}
	}

	public void BackClicked () {
		if(RequestsController.isReview){
			page= "res/layout/Requests.fxml";
			RequestsController.isReview = false;
		}else
			page ="res/layout/ContactsView.fxml";

			try {
				Stage CreateContactStage = (Stage) ContactSaveButton.getScene().getWindow();
				Stage ContactViewStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource(page));
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
