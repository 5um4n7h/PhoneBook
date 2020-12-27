package com.phonebook;

import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class FeedBackController {
	@FXML
	private Button BackButton;
	@FXML
	private Button SendButton;
	public void initialize(){

		BackButton.setOnMouseClicked(mouseEvent -> {
			Stage stage = (Stage) BackButton.getScene().getWindow();
			stage.close();

		});

	}



}
