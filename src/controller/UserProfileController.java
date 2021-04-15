package controller;



import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import entity.PersonPojo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.AlertDialog;
import tools.Validator;

public class UserProfileController implements Initializable {
	Alert alert = AlertDialog.getAlert();
	Validator validator = new Validator();
	private PersonDao personDao = new PersonDaoImpl();
	@FXML  private TextField fname;
	@FXML  private TextField lname;
	@FXML  private TextField password;
	@FXML  private TextField email;
	@FXML  private TextField address;
	@FXML  private TextField cardNumber;
	@FXML  private TextField expdate;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fname.setText(PersonDaoImpl.currentUser.getFirst_Name());
		lname.setText(PersonDaoImpl.currentUser.getLast_Name());
		password.setText(PersonDaoImpl.currentUser.getPassword());
		email.setText(PersonDaoImpl.currentUser.getEmail());
		address.setText(PersonDaoImpl.currentUser.getAddress());
		cardNumber.setText(PersonDaoImpl.currentUser.getCardno());
		expdate.setText(PersonDaoImpl.currentUser.getExpdate());
		email.setDisable(true);
	}
	
	public void update() {
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get()==ButtonType.OK) {
			if(validator.validateName(fname.getText())&&validator.validateName(lname.getText())
					&&validator.validatePwd(password.getText())) {
				PersonPojo p = new PersonPojo();
				p.setFirst_Name(fname.getText());
				p.setLast_Name(lname.getText());
				p.setPassword(password.getText());
				p.setAddress(address.getText());
				p.setCardno(cardNumber.getText());
				p.setExpdate(expdate.getText());
				p.setEmail(email.getText());
				PersonDaoImpl.currentUser = p;
				if(personDao.updatePersonPojo(p)) {
					AlertDialog.showAlert("Done", "you have updated your profile");
				}
			}
			else {
				AlertDialog.showAlert("Warnings", "please use valid information");
			}
		}
		else {
			alert.close();
		}
		
	}
	
	public void exit() {
		Stage stage2 = (Stage) email.getScene().getWindow();
		stage2.close();
	}

	
	
	
	

}
