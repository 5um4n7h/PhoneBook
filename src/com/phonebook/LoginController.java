package com.phonebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    private final String host = "jdbc:mysql://localhost:3306/contacts";
    private final String uName = "root";
    private final String uPass = "1234";
    public String pass;

    private Connection con = DriverManager.getConnection(host, uName, uPass);

    public LoginController() throws SQLException {
    }

    public void initialize(){

        ChangeLogin.setOnAction(actionEvent -> {

        //System.out.println("Change Login Button Clicked");
            if(isUser){
                LoginType.setText("Admin Login");
                ChangeLogin.setText("Login as User");
                isUser=false;


            }else {
                LoginType.setText("User Login");
                ChangeLogin.setText("Login as Admin");
                isUser=true;
            }

    });


        LoginButton.setOnAction(actionEvent -> {
            System.out.println("Clicked");
            if(isUser) {
                try {
                    Statement Stat = con.createStatement();
                    String sql ="SELECT password FROM authentication where username='"+username.getText()+"'";
                    ResultSet rs = Stat.executeQuery(sql);
                    while (rs.next()){
                        pass = rs.getString("password");
                        System.out.println("fetched password is: "+pass);
                        System.out.println("Entered password is: "+password.getText());
                    }

                    if(pass!=password.getText()){
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

                        System.out.println("error in password");


                    }
                } catch (Exception e) {
                    System.out.println("Error: "+e);
                }
            }

        });

}
}