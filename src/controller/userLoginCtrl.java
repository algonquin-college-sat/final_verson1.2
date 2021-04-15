package controller;
import tools.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import entity.PersonPojo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class userLoginCtrl implements Initializable {
	
	private Stage stage;
	private PersonDao personDao = new PersonDaoImpl();
	
	@FXML private TextField account;
	@FXML private PasswordField password;
	
	
	public void backtoMain(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Main2.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
            stage.setTitle("Tickets Management System");
            stage.setScene(scene);
            stage.show();
            
    		Stage stage2 = (Stage) account.getScene().getWindow();
    		stage2.close();
    		
        } catch(Exception e) {
            e.printStackTrace();
        }

	}


	public void login(ActionEvent event) {
		try {
			if(personDao.validateLogin(account.getText(), password.getText())) 
			{
				System.out.println(PersonDaoImpl.currentUser.getEmail());
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/Navigate.fxml"));
				AnchorPane root = loader.load();
				Scene scene = new Scene(root);
				stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Main Page");
				stage.show();
				Stage stage2 = (Stage) account.getScene().getWindow();
				stage2.close();
				
			}
			else {
				AlertDialog.showAlert("Error", "please input valid account name and password");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	

}
