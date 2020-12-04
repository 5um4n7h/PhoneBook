package com.phonebook;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


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
    private Label EmailLabel;
    @FXML
    private Hyperlink WebsiteHyperLink;

    private static boolean isUserContact = LoginController.getUserType();

    public static boolean isCreate;
    public String id;
    public static int newid=0;
    public static String sql;
    public static String Sql;

    //for jdbc connection
    private final String host = "jdbc:mysql://localhost:3306/contacts";
    private final String uName = "root";
    private final String uPass = "1234";
    public static String Name;
    public static String Number;

    public String website;

    public static String name;


    //for listview
    private ObservableList<Object> items;

    public ContactsViewController() throws SQLException {

    }

    @Override
    public void start(Stage stage) throws Exception {

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
                Sql = "SELECT * FROM defaultcontacts,usercontacts WHERE category=1";
                DisplayContent(Sql);
            }
            break;

            case 2:{
                CategoryLabel.setText("Plumbers");
                Sql = "SELECT * FROM defaultcontacts WHERE category=2";
                DisplayContent(Sql);
            }
            break;

            case 3:{
                CategoryLabel.setText("Carpentors");
                Sql = "SELECT * FROM defaultcontacts WHERE category=3";
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
                Sql = "SELECT * FROM defaultcontacts WHERE category=5";
                DisplayContent(Sql);
            }
            break;

            case 6:{
                CategoryLabel.setText("Educational Institutions");
                Sql = "SELECT * FROM defaultcontacts WHERE category=6";
                DisplayContent(Sql);
            }
            break;

            case 7:{
                CategoryLabel.setText("Banks");
                Sql = "SELECT * FROM defaultcontacts WHERE category=7";
                DisplayContent(Sql);
            }
            break;
        }
        itemListView.setOnMouseClicked(event -> {
            Label label = (Label) itemListView.getSelectionModel().getSelectedItem();
            try {
                String s = label.getText();
                String sql = "SELECT * FROM allcontacts WHERE name=\'" + s + "\'";
                ResultSet rs = Main.statement.executeQuery(sql);
                while (rs.next()) {
                    id = rs.getString("id");
                    name = rs.getString("name");
                    String desc = rs.getString("description");
                    String address = rs.getString("address");
                    String Phn1 = rs.getString("no1");
                    String Phn2 = rs.getString("no2");
                    String email = rs.getString("email");
                    website = rs.getString("website");


                    if(LoginController.isUser && id.contains("d")){
                        isUserContact = true;
                        // System.out.println("If is true");
                        ContactDeleteButton.setVisible(false);
                        ContactEditButton.setVisible(false);
                    }
                    else {

                        //System.out.println("If is false");
                        isUserContact = false;

                        ContactDeleteButton.setVisible(true);
                        ContactEditButton.setVisible(true);

                    }

                   // System.out.println(phn);
                    //System.out.println(name);
                   // System.out.println(GroupFlag);
                   // System.out.println("---------------------");
                    ContactNameLabel.setText(name);
                    Name = name;
                    // ContactNameLabel.setFont(Font.font("Segoe UI Semibold", FontPosture.REGULAR, 20));
                    DescriptionLabel.setText(desc);
                    AddressLabel.setText(address);
                    Ph1Label.setText(Phn1);
                    Ph2Label.setText(Phn2);
                    EmailLabel.setText(email);
                    WebsiteHyperLink.setText(website);

                }

            } catch (Exception e) {
                System.out.println(e);
            }

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
                    sql = "DELETE FROM usercontacts WHERE id3='"+id+"'";
                }else
                {
                    sql = "DELETE FROM defaultcontacts WHERE id0='"+id+"'";
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

                String sql = "SELECT * FROM " + GetCategoryName() + " WHERE name LIKE '%" + newValue + "%'";
                ResultSet rs = Main.statement.executeQuery(sql);


                while (rs.next()) {

                    String name = rs.getString("name");
                    itemListView.setItems(items);
                    Object object = new Object();
                    Label label = new Label(name);
                    items.add(label);


                }

            } catch (SQLException ex) {
                System.out.println(ex);
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
                System.out.println(exception);
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



        WebsiteHyperLink.setOnAction(actionEvent -> {
            getHostServices().showDocument(website);

        });

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

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static String GetCategoryName() {
        switch (HomeController.getCategory()) {
            case 0: return "doctors";
            case 1:return "electricians";
        }
    return null;
    }





    public static String getData(String data){
        switch (data){
            case "name": return Name;
            case "number" : return Number;

        }
        return null;
    }




}
