package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import entity.PersonPojo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tools.AlertDialog;
import tools.Validator;

public class adminEditUserController implements Initializable {
	
	Alert alert = AlertDialog.getAlert();
	Validator validator = new Validator();
	private PersonDao personDao = new PersonDaoImpl();
	@FXML  public TextField fname;
	@FXML  public TextField lname;
	@FXML  public TextField password;
	@FXML  public TextField email;
	@FXML  public TextField address;
	@FXML  public TextField cardNumber;
	@FXML  public TextField expdate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		
		
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
				if(personDao.updatePersonPojo(p)) {
					AlertDialog.showAlert("Done", "you have updated your profile");
					Stage stage2 = (Stage) email.getScene().getWindow();
					stage2.close();
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
