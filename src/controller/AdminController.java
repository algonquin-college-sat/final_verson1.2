package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.AlertDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;



public class AdminController implements Initializable {
	
	@FXML
	private MenuItem accountsDisplay;
	
	@FXML
	private AnchorPane apane;
	
    @FXML
    private AnchorPane subPane;
    
    @FXML
    private Menu accountsmanagement;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}	
	
	public void displayAccounts() {
		ShowAccounts sa = new ShowAccounts();
        subPane.getChildren().clear();
        subPane.getChildren().add(sa.createAccountPane());
	}
	
	public void displayEvents() {
		ShowEvents se = new ShowEvents();
		subPane.getChildren().clear();
		subPane.getChildren().add(se.createEventsPane());
	}
	
	public void displayTrans() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/AllRecords.fxml"));
			AnchorPane root;
			root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Overview of Transactions");
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refresh() {
		ShowAccounts sa = new ShowAccounts();
        subPane.getChildren().clear();
        subPane.getChildren().add(sa.createAccountPane());
	}
	
	public void about() {
		AlertDialog.showAlert("About The Program", "CST8334_Team6 by Leijian, Justin, Lily, Juju, Elyse");
	}
	
	public void exit() {
		Stage stage2 = (Stage) apane.getScene().getWindow();
		stage2.close();
	}
	
}
