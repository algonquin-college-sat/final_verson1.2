package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.AlertDialog;
import tools.Validator;

public class RegisterController implements Initializable {
	
	@FXML private Stage stage;
	@FXML private TextField first_name;
	@FXML private TextField last_name;
	@FXML private TextField emailAddress;
	@FXML private PasswordField password;
	Validator validator = new Validator();
	PersonDao personDao = new PersonDaoImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML
	public void registerNewAccount() {

		String fname = first_name.getText();
		String lname = last_name.getText();
		String email = emailAddress.getText();
		String pwd = password.getText();
		if(validator.validateName(fname)&&validator.validateName(lname)&&validator.validateEmail(email)
				&&validator.validatePwd(pwd)) {
			if(!personDao.validateRegister(email)) {
				personDao.createPersonPojo(fname, lname, pwd, email);
				AlertDialog.showAlert("Congratulations", "Registered successfully, you can login now");
			}
			else {
				AlertDialog.showAlert("Warning", "this account has already been registered");
			}
		}
		else {
			AlertDialog.showAlert("Error", "Please input valid information");
		}
		
	}
	
	public void backtoLogin() {
		try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/Main2.fxml"));
		AnchorPane root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
        stage.setTitle("Tickets Management System");
        stage.setScene(scene);
        stage.show();
		Stage stage2 = (Stage) emailAddress.getScene().getWindow();
		stage2.close();
		}
		catch(Exception e) {
            e.printStackTrace();
            }
		
	}
	
	public void clear() {
		first_name.clear();
		last_name.clear();
		password.clear();
		emailAddress.clear();
	}
	
	
	

}
