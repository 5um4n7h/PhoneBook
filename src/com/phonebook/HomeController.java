package com.phonebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {

    @FXML
    private Button DoctorsButton;
    @FXML
    private Button RequestsButton;
    @FXML
    private Button FeedBacksButton;
    @FXML
    private Button LogoutButton;
    @FXML
    private Button StatsButton;

    public static int Cat;
//    Stage HomeStage = (Stage) DoctorsButton.getScene().getWindow();

    public void initialize(){
        if(LoginController.isUser)
        {   LogoutButton.setLayoutX(243.0);
            LogoutButton.setLayoutY(-3.0);
            RequestsButton.setVisible(false);
            FeedBacksButton.setVisible(false);
            StatsButton.setVisible(false);

        }else {
            RequestsButton.setVisible(true);
            FeedBacksButton.setVisible(true);
        }
        RequestsButton.setOnMouseClicked(mouseEvent -> {
            try {
                Stage HomeStage = (Stage) DoctorsButton.getScene().getWindow();
                Stage LoginStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("res/layout/Requests.fxml"));
                LoginStage.setTitle("Phonebook");
                LoginStage.setScene(new Scene(root));
                LoginStage.setWidth(HomeStage.getWidth());
                LoginStage.setHeight(HomeStage.getHeight());
                LoginStage.setResizable(false);
                LoginStage.show();
                HomeStage.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();

            }

        });

        FeedBacksButton.setOnMouseClicked(mouseEvent -> {
            try {
                Stage HomeStage = (Stage) DoctorsButton.getScene().getWindow();
                Stage LoginStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("res/layout/FeedbackView.fxml"));
                LoginStage.setTitle("Phonebook");
                LoginStage.setScene(new Scene(root));
                LoginStage.setWidth(HomeStage.getWidth());
                LoginStage.setHeight(HomeStage.getHeight());
                LoginStage.setResizable(false);
                LoginStage.show();
                HomeStage.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();

            }

        });

        StatsButton.setOnMouseClicked(mouseEvent -> {
            try {
                Stage HomeStage = (Stage) DoctorsButton.getScene().getWindow();
                Stage LoginStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("res/layout/Stats.fxml"));
                LoginStage.setTitle("Phonebook");
                LoginStage.setScene(new Scene(root));
                LoginStage.setWidth(HomeStage.getWidth());
                LoginStage.setHeight(HomeStage.getHeight());
                LoginStage.setResizable(false);
                LoginStage.show();
                HomeStage.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();

            }
        });
    }

    //to get Category No in other classes
    public static int getCategory() {

        return Cat;
    }




    //OnClick method for categories
    //called from fxml
    public void DoctorsClicked() {
        Cat = 0;
        OpenContactsViewWindow();
    }


    public void ElectriciansClicked() {
        Cat = 1;
        OpenContactsViewWindow();
    }

    public void PlumbersClicked() {
        Cat = 2;
        OpenContactsViewWindow();
    }

    public void CarpentorsClicked(){
        Cat = 3;
        OpenContactsViewWindow();
    }

    public void LawyersClicked(){
        Cat = 4;
        OpenContactsViewWindow();
    }

    public void ShopsClicked(){
        Cat = 5;
        OpenContactsViewWindow();
    }

    @FXML
    private void EIClicked(){
        Cat = 6;
        OpenContactsViewWindow();
    }

    @FXML
    private void BanksClicked(){
        Cat = 7;
        OpenContactsViewWindow();
    }

    private void OpenContactsViewWindow() {
        try {
            Stage HomeStage = (Stage) DoctorsButton.getScene().getWindow();
            Stage ContactViewStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("res/layout/ContactsView.fxml"));
            ContactViewStage.setTitle("Phonebook");
            ContactViewStage.setScene(new Scene(root));
            ContactViewStage.setWidth(HomeStage.getWidth());
            ContactViewStage.setHeight(HomeStage.getHeight());
            ContactViewStage.setResizable(false);
            ContactViewStage.show();
            HomeStage.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }




    public void OnLogoutClicked(){
        try {
            Stage HomeStage = (Stage) DoctorsButton.getScene().getWindow();
            Stage LoginStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("res/layout/Login.fxml"));
            LoginStage.setTitle("Phonebook");
            LoginStage.setScene(new Scene(root));
            LoginStage.setWidth(HomeStage.getWidth());
            LoginStage.setHeight(HomeStage.getHeight());
            LoginStage.setResizable(false);
            LoginStage.show();
            HomeStage.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();

        }
    }

}



