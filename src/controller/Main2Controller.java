package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main2Controller implements Initializable {
	private Stage stage;
	
	@FXML
	private Button signupBtn;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void register(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Register.fxml"));
			AnchorPane root;
			root = loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Register Page");
			stage.show();
			Stage stage = (Stage) signupBtn.getScene().getWindow();
			stage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@FXML
	public void login(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/UserLogin.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Login Page");
			stage.show();
			Stage stage = (Stage) signupBtn.getScene().getWindow();
			stage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
