package com.phonebook;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label HomeLabel;
    @FXML
    public Label ElectriciansLabel;
    @FXML
    public Label DoctorsLabel;
    @FXML
    public HBox base;

    public static int Cat;

    public static int getCategory() {

        return Cat;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoctorsLabel.setOnMouseClicked(event -> DoctorsClicked());
        ElectriciansLabel.setOnMouseClicked(event -> ElectriciansClicked());


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



