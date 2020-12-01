package com.phonebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;


public class LoginController {

    private boolean isUser=true;
    @FXML
    private Button ChangeLogin;
    @FXML
    private Label LoginType;
    @FXML
    private Button LoginButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView icon;

    private final String host = "jdbc:mysql://localhost:3306/contacts";
    private final String uName = "root";
    private final String uPass = "1234";
    public String pass;
    public  String UserType;

    private Connection con = DriverManager.getConnection(host, uName, uPass);

    public LoginController() throws SQLException {
    }

    public void initialize(){

        ChangeLogin.setOnAction(actionEvent -> {

        //System.out.println("Change Login Button Clicked");
            if(isUser){
                LoginType.setText("Admin Login");
                ChangeLogin.setText("Login as User");
                try {
                    icon.setImage(new Image(new FileInputStream("src\\com\\phonebook\\res\\imgaes\\admin.png")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                isUser=false;


            }else {
                LoginType.setText("User Login");
                ChangeLogin.setText("Login as Admin");
                try {
                    icon.setImage(new Image(new FileInputStream("src\\com\\phonebook\\res\\imgaes\\user.png")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                isUser=true;
            }

    });


        LoginButton.setOnAction(actionEvent -> {
            System.out.println("Clicked");
            if(isUser==true){
                UserType = "user";

            }else{
                UserType = "admin";
            }
                try {
                    Statement Stat = con.createStatement();
                    String sql ="SELECT password FROM authentication where username='"+username.getText()+"' AND type='"+UserType+"'";
                    ResultSet rs = Stat.executeQuery(sql);
                    while (rs.next()){
                        pass = rs.getString("password");
                        System.out.println("fetched password is: "+pass);
                        System.out.println("Entered password is: "+password.getText());
                    }

                    if(pass.equals(password.getText())){
                        try {
                            // get a handle to the stage
                            Stage currentStage = (Stage) LoginType.getScene().getWindow();
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
                    }
                    else {
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
                  /*  String toastMsg = "User not found";
                    int toastMsgTime = 3500; //3.5 seconds
                    int fadeInTime = 500; //0.5 seconds
                    int fadeOutTime= 500; //0.5 seconds
                    Stage stage;
                    stage=(Stage) LoginType.getScene().getWindow();
                    Toast.makeText(stage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime); */
                    Alert alert = new Alert(Alert.AlertType.WARNING, "User not found");
                    alert.show();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    alert.hide();
                    System.out.println("Error: "+e);
                }



        });

}
}