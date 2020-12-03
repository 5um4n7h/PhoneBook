package com.phonebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField Phn1Text;
    @FXML
    private TextField Phn2Text;
    @FXML
    private TextField EmailText;
    @FXML
    private TextField WebsiteText;

    private final String host = "jdbc:mysql://localhost:3306/contacts";
    private final String uName = "root";
    private final String uPass = "1234";

    public int newId;
    public int oldId;
    public String ContactID;
    public String Sql1;
    public String Sql2;
    public String Sql3;

    private Connection con = DriverManager.getConnection(host, uName, uPass);

    public int CategoryFlag = HomeController.getCategory();
   // private int newid = ContactsViewController.newid;
    public CreateContactController() throws SQLException {
    }

    public void initialize() {


       // System.out.println(ContactsViewController.getData("name"));
      //  System.out.println(ContactsViewController.getData("number"));
       // ContactNameText.setText(ContactsViewController.getData("name"));


        if(ContactsViewController.isCreate) {


            ContactSaveButton.setOnMouseClicked(event -> {

                try{
                    Statement Stat = con.createStatement();
                    String Sql = "select * from id";
                    ResultSet rs = Stat.executeQuery(Sql);
                    while (rs.next()){
                        oldId = (rs.getInt("id"));
                        newId = oldId+1;
                        System.out.println(newId);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    Statement Stat = con.createStatement();
                    String Sql = "UPDATE id SET id ="+newId+" where id ="+oldId;
                    Stat.executeUpdate(Sql);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                if(LoginController.isUser) {
                    ContactID = "u" + newId;
                }
                else{
                    ContactID = "d" + newId;
                }

                String Name = "\""+NameText.getText()+"\"";
                String Desc = "\""+DesriptionText.getText()+"\"";
                String Address = "\""+AddressText.getText()+"\"";
                String AddressLink = "\"\"";
                String Phn1 = "\""+Phn1Text.getText()+"\"";
                String Phn2 = "\""+Phn2Text.getText()+"\"";
                String Email = "\""+EmailText.getText()+"\"";
                String Website = "\""+WebsiteText.getText()+"\"";
                String Facebook = "\"\"";
                String Instagram = "\"\"";

                try {
                    Statement Stat = con.createStatement();
                    if(LoginController.isUser) {
                        Sql1 = "INSERT INTO usercontacts  VALUES(\"" + ContactID + "\"," + HomeController.getCategory() + "," + Name + "," + Desc + "," + Address + "," + AddressLink + ")" ;
                        Sql2 = "INSERT INTO usercontactdetails VALUES(\"" + ContactID+ "\"," +Phn1+","+Phn2+","+Email+ ")";
                        Sql3 = "INSERT INTO usercontactsocial VALUES(\"" + ContactID + "\","+ Website + "," + Facebook +","+ Instagram+ ")";
                    }
                    else {
                        Sql1 = "INSERT INTO defaultcontacts  VALUES(\"" + ContactID + "\"," + HomeController.getCategory() + "," + Name + "," + Desc + "," + Address + "," + AddressLink + ")" ;
                        Sql2 = "INSERT INTO defaultcontactdetails VALUES(\"" + ContactID + "\"," +Phn1+","+Phn2+","+Email+ ")";
                        Sql3 = "INSERT INTO defaultcontactsocial VALUES(\"" + ContactID + "\","+ Website +","+ Facebook +","+ Instagram+ ")";
                    }
                    Stat.executeUpdate(Sql1);
                    Stat.executeUpdate(Sql2);
                    Stat.executeUpdate(Sql3);
                    //useruStat.executeUpdate(Sql3);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Stage currentStage = (Stage) NameText.getScene().getWindow();

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
                    System.out.println(ex);
                }
            });
        }
            else {

               // NameText.setText(ContactsViewController.getData("name"));
                //DesriptionText.setText(ContactsViewController.getData("desc"));
                //Phn1Text.setText(ContactsViewController.getData("number"));




            }


    }
}
