package com.phonebook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Application;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;


public class ContactsViewController extends Application {

    @FXML
    private ListView itemListView;
    @FXML
    private Label CategoryLabel;
    @FXML
    private Label ContactNameLabel;
    @FXML
    private Label DescriptionLabel;
    @FXML
    private TextField SearchText;
    @FXML
    private Button CreateContactButton;
    @FXML
    private Button ContactEditButton;
    @FXML
    private Button ContactDeleteButton;
    @FXML
    private Button backtoHome;
    @FXML
    private Label AddressLabel;
    @FXML
    private Label Ph1Label;
    @FXML
    private Label Ph2Label;
    @FXML
    private Hyperlink WebsiteHyperLink;
    @FXML
    private Hyperlink AddressHyperLink;
    @FXML
    private Hyperlink EmailHyperLink;
    @FXML
    private Hyperlink FacebookHyperLink;
    @FXML
    private Hyperlink InstagramHyperLink;
    @FXML
    private StackPane stackPane;
    @FXML
    private Button FeedBackButton;

    //private static boolean isUserContact = LoginController.isUser;

    public static boolean isCreate;
    public static int id;
    public static String sql;
    public static String ViewSql;




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



    //for listview
    private ObservableList<Object> items;


    @Override
    public void start(Stage stage){

    }


    public void initialize() {
        if(!LoginController.isUser) {
            FeedBackButton.setDisable(true);
            CreateContactButton.setText("Create");
        }else {
            CreateContactButton.setText("Request to Create New");
            FeedBackButton.setDisable(false);
        }

        System.out.println("Init called");
        ViewSql = "call getContacts("+HomeController.Cat+",'default')";
        items = FXCollections.observableArrayList();
        switch (HomeController.getCategory()) {
            case 0: {
                System.out.println("Case 0");
                CategoryLabel.setText("Doctors");
                //sql = "SELECT * FROM defaultcontacts,defaultcontactdetails,defaultcontactsocial WHERE category = 0";

                DisplayContent(ViewSql);
                //sql = "SELECT * FROM usercontacts WHERE usercontacts.category=0";
               // DisplayContent(sql);
            }
            break;

            case 1: {
                System.out.println("Case 1");
                CategoryLabel.setText("Electricians");
               // ViewSql = "call getAllContacts("+HomeController.Cat+")";
                DisplayContent(ViewSql);
            }
            break;

            case 2:{
                CategoryLabel.setText("Plumbers");
               // ViewSql = "call getAllContacts("+HomeController.Cat+")";
                DisplayContent(ViewSql);
            }
            break;

            case 3:{
                CategoryLabel.setText("Carpentors");
               // ViewSql = "call getAllContacts("+HomeController.Cat+")";
                DisplayContent(ViewSql);
            }
            break;

            case 4:{
                CategoryLabel.setText("Lawyers");
               // ViewSql = "SELECT * FROM defaultcontacts WHERE category=4";
                DisplayContent(ViewSql);
            }
            break;

            case 5:{
                CategoryLabel.setText("Shops");
               // ViewSql = "select * from allcontacts WHERE category=5";
                DisplayContent(ViewSql);
            }
            break;

            case 6:{
                CategoryLabel.setText("Educational Institutions");
               // ViewSql = "select * from allcontacts WHERE category=6";
                DisplayContent(ViewSql);
            }
            break;

            case 7:{
                CategoryLabel.setText("Banks");
              //  ViewSql = "select * from allcontacts WHERE category=7";
                DisplayContent(ViewSql);
            }
            break;
        }

        /*
        ReportButton.setOnMouseClicked(mouseEvent -> {
            Text title = new Text("Report");
            title.setStyle("-fx-font-size:20");
            Text text = new Text("Please describe the issue..");
            text.setStyle("-fx-font-size:14");
            JFXDialogLayout dialogContent = new JFXDialogLayout();
            dialogContent.setHeading(title);
            dialogContent.setBody(text);
            JFXTextArea jfxTextArea = new JFXTextArea("type here");
            jfxTextArea.setPrefHeight(80);
            jfxTextArea.setStyle("-fx-font-size:16;-fx-font-weight:bold;-fx-font-color:black;-fx-border-insets:px;-fx-background-insets:8px;");
            dialogContent.setBody(text,jfxTextArea);
            Insets insets1 = new Insets(0,16,16,16);
            JFXButton cancel = new JFXButton("Cancel");
            cancel.setButtonType(JFXButton.ButtonType.RAISED);
            cancel.setStyle("-fx-background-color:#69FF81;-fx-font-size:15;-fx-font-weight:bold;");
            JFXButton yes = new JFXButton("Yes");

            yes.setButtonType(JFXButton.ButtonType.RAISED);
            //yes.setMinHeight(30);
            //yes.setStyle("-fx-background-color:#FF6E6E;-fx-font-size:15;-fx-font-weight:bold;-fx-padding:5px;-fx-border-insets:5px;-fx-background-insets:5px;");

            dialogContent.setActions(jfxTextArea,yes,cancel);
            JFXDialog dialog = new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.CENTER);
         dialog.setStyle("-fx-padding:16px");
            dialog.show();



        });

         */


        FeedBackButton.setOnMouseClicked(mouseEvent -> {

            Stage currentStage = (Stage) ContactDeleteButton.getScene().getWindow();

            try {
                Stage newStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("res/layout/Feedback.fxml"));
                newStage.setTitle("Phonebook");
                newStage.setScene(new Scene(root));
                newStage.initStyle(StageStyle.UNDECORATED);
                newStage.initModality(Modality.WINDOW_MODAL);
                newStage.initOwner(currentStage);
                newStage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                    if (! isNowFocused) {
                        newStage.hide();
                    }
                });

                newStage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }



        });

        itemListView.setOnMouseClicked(event -> ListViewItemSelected());

        ContactEditButton.setOnMouseClicked(event -> {
            System.out.println("Edit Clicked");
            isCreate = false;
            // get a handle to the stage
            Stage currentStage = (Stage) CreateContactButton.getScene().getWindow();
            // do what you have to do

            Stage newStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("res/layout/CreateContact.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            newStage.setTitle("Phonebook");
            assert root != null;
            newStage.setScene(new Scene(root));
            newStage.setWidth(currentStage.getWidth());
            newStage.setHeight(currentStage.getHeight());
            newStage.setResizable(false);
            newStage.show();
            currentStage.close();

        });

        ContactDeleteButton.setOnMouseClicked(event -> {


            Text title = new Text("Alert");
            title.setStyle("-fx-font-size:20");
            Text text = new Text("Aare you really want to delete this contact ?");
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

                            sql = "DELETE FROM defaultcontacts WHERE id='"+id+"'";
                        Main.statement.executeUpdate(sql);
                        itemListView.refresh();
                        DisplayContent(ViewSql);

                        Text title = new Text("Alert");
                        title.setStyle("-fx-font-size:20");
                        Text text = new Text("Contact Deletion Successfull");
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






                    }catch (Exception e){
                        e.printStackTrace();

                        Text title = new Text("Alert");
                        title.setStyle("-fx-font-size:20");
                        Text text = new Text("Error, Contact Deletion unsuccessful!");
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
                                Stage currentStage = (Stage) ContactDeleteButton.getScene().getWindow();

                                try {
                                    Stage newStage = new Stage();
                                    Parent root = FXMLLoader.load(getClass().getResource("res/layout/ContactsView.fxml"));
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

                }
            });
            dialog.show();

        });

        SearchText.textProperty().addListener((observable, oldValue, newValue) ->
        {
            itemListView.getItems().clear();
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                // TODO: place custom component creation code here

                String sql = "CALL searchByName("+HomeController.getCategory()+",'%" + newValue + "%')";
                ResultSet rs = Main.statement.executeQuery(sql);


                while (rs.next()) {

                    String name = rs.getString("name");
                    itemListView.setItems(items);
                    Label label = new Label(name);
                    items.add(label);


                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });


        CreateContactButton.setOnMouseClicked(event -> {
            isCreate = true;
            try {


                Stage currentStage = (Stage) CreateContactButton.getScene().getWindow();


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


        backtoHome.setOnMouseClicked(mouseEvent -> {
            sql = null;
            try {
                // get a handle to the stage
                Stage currentStage = (Stage) backtoHome.getScene().getWindow();
                // do what you have to do

                Stage newStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("res/layout/Home.fxml"));
                newStage.setTitle("Phonebook");
                newStage.setScene(new Scene(root));
                newStage.setWidth(currentStage.getWidth());
                newStage.setHeight(currentStage.getHeight());
                newStage.setResizable(false);
                newStage.show();
                currentStage.close();
            } catch (IOException ioException) {

                ioException.printStackTrace();


            }
        });

        AddressHyperLink.setOnAction(actionEvent -> getHostServices().showDocument(AddressLink));
        EmailHyperLink.setOnAction(actionEvent -> getHostServices().showDocument("mailto:"+EmailLink));
        WebsiteHyperLink.setOnAction(actionEvent -> getHostServices().showDocument(Website));
        FacebookHyperLink.setOnAction(actionEvent -> getHostServices().showDocument(FacebookLink));
        InstagramHyperLink.setOnAction(actionEvent -> getHostServices().showDocument(InstagramLink));

    }




    private void DisplayContent(String sql) {
        try {
            items.clear();
            System.out.println(sql);
            ResultSet rs = Main.statement.executeQuery(sql);


            while (rs.next()) {

                String name = rs.getString("name");
                //id = rs.getInt("id");

               // Object object = new Object();
                Label label = new Label(name);
                // label.setFont(Font.font("Segoe UI Semibold",FontWeight.BOLD, 18));
                items.add(label);


                itemListView.setItems(items);
               // itemListView.refresh();

            }
            itemListView.getSelectionModel().select(0);
            ListViewItemSelected();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private void ListViewItemSelected(){
        Label label = (Label) itemListView.getSelectionModel().getSelectedItem();
        try {
            String s = label.getText();
            String sql = "SELECT * FROM allcontacts WHERE name='" + s + "'";
            ResultSet rs = Main.statement.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("id");
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


                if (LoginController.isUser) {
                    // System.out.println("If is true");
                    ContactDeleteButton.setVisible(false);
                    ContactEditButton.setVisible(false);
                } else {

                    //System.out.println("If is false");

                    ContactDeleteButton.setVisible(true);
                    ContactEditButton.setVisible(true);

                }

                // System.out.println(phn);
                //System.out.println(name);
                // System.out.println(GroupFlag);
                // System.out.println("---------------------");
                ContactNameLabel.setWrapText(true);
                ContactNameLabel.setText(Name);
                DescriptionLabel.setWrapText(true);
                DescriptionLabel.setText(Desc);
                AddressLabel.setWrapText(true);
                AddressLabel.setText(Address);
                AddressHyperLink.setText(AddressLink);
                Ph1Label.setText(String.valueOf(Phn1));

                Ph2Label.setText(String.valueOf(Phn2));
                EmailHyperLink.setText(EmailLink);
                WebsiteHyperLink.setText(Website);
                FacebookHyperLink.setText(FacebookLink);
                InstagramHyperLink.setText(InstagramLink);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }







}
