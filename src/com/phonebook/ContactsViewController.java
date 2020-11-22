package com.phonebook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;



public class ContactsViewController {

    @FXML
    private ListView itemListView;
    @FXML
    private Label CategoryLabel;
    @FXML
    private Label ContactNameLabel;
    @FXML
    private Label ContactNumberLabel;
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

    private static boolean isUserContact;

    public static boolean isCreate;
    public static int id;
    public static int newid=0;

    //for jdbc connection
    private final String host = "jdbc:mysql://localhost:3306/contacts";
    private final String uName = "root";
    private final String uPass = "1234";
    public static String Name;
    public static String Number;
    private final Connection con = DriverManager.getConnection(host, uName, uPass);


    //for listview
    private ObservableList<Object> items;

    public static int CategoryFlag = HomeController.getCategory();

    public ContactsViewController() throws SQLException {

    }


    public void initialize() {

        items = FXCollections.observableArrayList();
        switch (CategoryFlag) {
            case 0: {
                CategoryLabel.setText("Doctors");
                String sql = "SELECT * FROM defaultcontacts WHERE category=0";
                DisplayContent(sql);
            }
            break;

            case 1: {
                CategoryLabel.setText("Electricians");
                String sql = "SELECT * FROM electricians";
                DisplayContent(sql);
            }
            break;
        }
        itemListView.setOnMouseClicked(event -> {
            Label label = (Label) itemListView.getSelectionModel().getSelectedItem();
            try {
                Statement Stat = con.createStatement();
                String s = label.getText();
                String sql = "SELECT * FROM " + GetCategoryName() + " WHERE name=\"" + s + "\"";
                ResultSet rs = Stat.executeQuery(sql);
                while (rs.next()) {
                    id = rs.getInt("id");
                    String phn = rs.getString("phone");
                    String name = rs.getString("name");
                    String GroupFlag = rs.getString("GroupFlag");
                    Boolean flag = Boolean.valueOf(GroupFlag);
                    //System.out.println("boolean value is"+flag);
                    if(!flag){

                        //System.out.println("If is false");
                        isUserContact = false;

                        ContactDeleteButton.setVisible(false);
                        ContactEditButton.setVisible(false);


                    }
                    else {
                        isUserContact = true;
                       // System.out.println("If is true");
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
                    ContactNumberLabel.setText(phn);
                    Number = phn;

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
        });

        SearchText.textProperty().addListener((observable, oldValue, newValue) ->
        {
            itemListView.getItems().clear();
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                // TODO: place custom component creation code here

                Statement Stat = con.createStatement();
                String sql = "SELECT * FROM " + GetCategoryName() + " WHERE name LIKE '%" + newValue + "%'";
                ResultSet rs = Stat.executeQuery(sql);


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
               // currentStage.close();

            } catch (Exception exception) {
                System.out.println(exception);
            }

        });


        backtoHome.setOnMouseClicked(mouseEvent -> {
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

    }




    private void DisplayContent(String sql) {
        try {
            Statement Stat = con.createStatement();

            ResultSet rs = Stat.executeQuery(sql);


            while (rs.next()) {

                String name = rs.getString("name");
                itemListView.setItems(items);
               // Object object = new Object();
                Label label = new Label(name);
                // label.setFont(Font.font("Segoe UI Semibold",FontWeight.BOLD, 18));
                items.add(label);

                id = rs.getInt("id");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static String GetCategoryName() {
        switch (CategoryFlag) {
            case 0: return "doctors";
            case  1:return "electricians";
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
