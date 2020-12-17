package com.phonebook;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

    //private static boolean isUserContact = LoginController.isUser;

    public static boolean isCreate;
    public static String id;
    public static String sql;
    public static String Sql;




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
        System.out.println("Init called");
        items = FXCollections.observableArrayList();
        switch (HomeController.getCategory()) {
            case 0: {
                System.out.println("Case 0");
                CategoryLabel.setText("Doctors");
                //sql = "SELECT * FROM defaultcontacts,defaultcontactdetails,defaultcontactsocial WHERE category = 0";
                Sql = "select * from allcontacts where category=0";
                DisplayContent(Sql);
                //sql = "SELECT * FROM usercontacts WHERE usercontacts.category=0";
               // DisplayContent(sql);
            }
            break;

            case 1: {
                System.out.println("Case 1");
                CategoryLabel.setText("Electricians");
                Sql = "select * from allcontacts WHERE category=1";
                DisplayContent(Sql);
            }
            break;

            case 2:{
                CategoryLabel.setText("Plumbers");
                Sql = "select * from allcontacts WHERE category=2";
                DisplayContent(Sql);
            }
            break;

            case 3:{
                CategoryLabel.setText("Carpentors");
                Sql = "select * from allcontacts WHERE category=3";
                DisplayContent(Sql);
            }
            break;

            case 4:{
                CategoryLabel.setText("Lawyers");
                Sql = "SELECT * FROM defaultcontacts WHERE category=4";
                DisplayContent(Sql);
            }
            break;

            case 5:{
                CategoryLabel.setText("Shops");
                Sql = "select * from allcontacts WHERE category=5";
                DisplayContent(Sql);
            }
            break;

            case 6:{
                CategoryLabel.setText("Educational Institutions");
                Sql = "select * from allcontacts WHERE category=6";
                DisplayContent(Sql);
            }
            break;

            case 7:{
                CategoryLabel.setText("Banks");
                Sql = "select * from allcontacts WHERE category=7";
                DisplayContent(Sql);
            }
            break;
        }


        itemListView.setOnMouseClicked(event -> {
            ListViewItemSelected();
        });

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
            System.out.println("Delete Clicked");
            try{
                if(id.contains("u")){
                    sql = "DELETE FROM usercontacts WHERE id='"+id+"'";
                }else
                {
                    sql = "DELETE FROM defaultcontacts WHERE id='"+id+"'";
                }
                Main.statement.executeUpdate(sql);
                itemListView.refresh();
                DisplayContent(Sql);

            }catch (Exception e){
                e.printStackTrace();
            }
        });

        SearchText.textProperty().addListener((observable, oldValue, newValue) ->
        {
            itemListView.getItems().clear();
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                // TODO: place custom component creation code here

                String sql = "SELECT * FROM "  + " WHERE name LIKE '%" + newValue + "%'";
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

                // get a handle to the stage
                Stage currentStage = (Stage) CreateContactButton.getScene().getWindow();
                // do what you have to do

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
                id = rs.getString("id");
                Name= rs.getString("name");
                Desc = rs.getString("description");
                Address = rs.getString("address");
                AddressLink  = rs.getString("address_link");
                Phn1 = rs.getString("no1");
                Phn2 = rs.getString("no2");
                EmailLink = rs.getString("email");
                Website = rs.getString("website");
                FacebookLink = rs.getString("facebook");
                InstagramLink = rs.getString("instagram");


                if(LoginController.isUser && id.contains("d")){
                    // System.out.println("If is true");
                    ContactDeleteButton.setVisible(false);
                    ContactEditButton.setVisible(false);
                }
                else {

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
                Ph1Label.setText(Phn1);
                Ph2Label.setText(Phn2);
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
