package com.phonebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {

    @FXML
    private Label HomeLabel;
    @FXML
    public Button ElectriciansButton;
    @FXML
    public Button DoctorsButton;
    @FXML
    public HBox base;

    public static int Cat;


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
            Stage currentStage = (Stage) HomeLabel.getScene().getWindow();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("res/layout/ContactsView.fxml"));
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




    public void OnLogoutClicked(){
        try {

            Stage currentStage = (Stage) DoctorsButton.getScene().getWindow();
            Stage newStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("res/layout/Login.fxml"));
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

}



