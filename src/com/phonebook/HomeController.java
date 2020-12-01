package com.phonebook;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label HomeLabel;
    @FXML
    public Button ElectriciansButton;
    @FXML
    public Button DoctorsButton;
    @FXML
    public HBox base;
    @FXML
    private ImageView DoctorsImage;
    @FXML
    private ImageView ElectriciansImage;
    @FXML
    private ImageView PlumbersImage;
    @FXML
    private Button PlumbersButton;
    @FXML
    private ImageView CarpentorsImage;
    @FXML
    private Button CarpentorsButton;
    @FXML
    private ImageView LawyersImage;
    @FXML
    private Button LawyersButton;
    @FXML
    private ImageView ShopsImage;
    @FXML
    private Button ShopsButton;
    @FXML
    private ImageView EIImage;
    @FXML
    private Button EIButton;
    @FXML
    private ImageView BanksImage;
    @FXML
    private Button BanksButton;
    @FXML
    private Button LogoutButton;

    public static int Cat;

    public static int getCategory() {

        return Cat;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoctorsImage.setOnMouseClicked(event -> DoctorsClicked());
        DoctorsButton.setOnMouseClicked(event -> DoctorsClicked());
        ElectriciansImage.setOnMouseClicked(event -> ElectriciansClicked());
        ElectriciansButton.setOnMouseClicked(event -> ElectriciansClicked());
        PlumbersImage.setOnMouseClicked(event -> PlumbersClicked());
        PlumbersButton.setOnMouseClicked(event -> PlumbersClicked());
        CarpentorsImage.setOnMouseClicked(mouseEvent -> CarpentorsClicked());
        CarpentorsButton.setOnMouseClicked(mouseEvent -> CarpentorsClicked());
        LawyersImage.setOnMouseClicked(mouseEvent -> LawyersClicked());
        LawyersButton.setOnMouseClicked(mouseEvent -> LawyersClicked());
        ShopsImage.setOnMouseClicked(mouseEvent -> ShopsClicked());
        ShopsButton.setOnMouseClicked(mouseEvent -> ShopsClicked());
        EIImage.setOnMouseClicked(mouseEvent -> EIClicked());
        EIButton.setOnMouseClicked(mouseEvent -> EIClicked());
        BanksImage.setOnMouseClicked(mouseEvent -> BanksClicked());
        BanksButton.setOnMouseClicked(mouseEvent -> BanksClicked());

        LogoutButton.setOnMouseClicked(mouseEvent -> {
            try {
                // get a handle to the stage
                Stage currentStage = (Stage) LogoutButton.getScene().getWindow();
                // do what you have to do

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
        });


        // JFXRippler rippler = new JFXRippler(DoctorsLabel);

        //  base.getChildren().add(rippler);

    }




    private void DoctorsClicked() {
        Cat = 0;
        //System.out.println("Doctors Clicked");
        OpenContactsViewWindow();
    }


    private void ElectriciansClicked() {
        Cat = 1;
        //System.out.println("Electricians Clicked");
        OpenContactsViewWindow();
    }

    private void PlumbersClicked() {
        Cat = 2;
        OpenContactsViewWindow();
    }

    private void CarpentorsClicked(){
        Cat = 3;
        OpenContactsViewWindow();
    }

    private void LawyersClicked(){
        Cat = 4;
        OpenContactsViewWindow();
    }

    private void ShopsClicked(){
        Cat = 5;
        OpenContactsViewWindow();
    }

    private void EIClicked(){
        Cat = 6;
        OpenContactsViewWindow();
    }

    private void BanksClicked(){
        Cat = 7;
        OpenContactsViewWindow();
    }

    private void OpenContactsViewWindow() {

        try {
            // get a handle to the stage
            Stage currentStage = (Stage) HomeLabel.getScene().getWindow();
            // do what you have to do

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
}



