package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.AlertDialog;

public class AdminLoginController implements Initializable {
	PersonDao personDao = new PersonDaoImpl();
	
	
	@FXML private Stage stage;
	@FXML private TextField account;
	@FXML private PasswordField password;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void login() {
		try {
			/*if(personDao.validateAdmin(account.getText(), password.getText())) */
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/AdminPage.fxml"));
				AnchorPane root = loader.load();
				Scene scene = new Scene(root);
				stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Admin System");
				stage.show();
			}
			/*else {
				AlertDialog.showAlert("Error", "please input valid account name and password");
			}*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
