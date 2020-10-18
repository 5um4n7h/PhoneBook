package com.phonebook;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class LoginController {

    private boolean isUser=true;
    @FXML
    private Button ChangeLogin;
    @FXML
    private Label LoginType;
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
}
}