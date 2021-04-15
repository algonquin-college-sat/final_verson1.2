package controller;

import java.net.URL;
import java.time.Instant;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.text.html.CSS;

import dao.EventDao;
import dao.PersonDao;
import dao.TransactionDao;
import dao.impl.EventDaoImpl;
import dao.impl.PersonDaoImpl;
import dao.impl.TransactionDaoImpl;
import entity.Person;
import entity.PersonPojo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tools.AlertDialog;

public class PurPageController implements Initializable {
	Alert alert = AlertDialog.getAlert();
	private TransactionDao tDao = new TransactionDaoImpl();
	private PersonDao personDao = new PersonDaoImpl();
	private EventDao eDao = new EventDaoImpl();
	
	
	@FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField cardNum;

    @FXML
    private TextField cvv;

    @FXML
    private Label eventTitle;

    @FXML
    private TextField address;

    @FXML
    private Label eventPrice;

    @FXML
    private TextField expireDate;

    @FXML
    private TextField email;

    @FXML
    void exit(ActionEvent event) {
    	Stage stage = (Stage) email.getScene().getWindow();
		stage.close();

    }

    @FXML
    void confirm(ActionEvent event) {
    	System.out.println(cvv.getText());
    	if(cardNum.getText()==null||cardNum.getText().trim().equals("")||expireDate.getText()==null
    			||expireDate.getText().trim().equals("")||cvv.getText()==null||cvv.getText().trim().equals("")) 
    	{
    		AlertDialog.showAlert("Warning","please use valid card infomation");
    		return;
    	}
    	else {
    		Optional<ButtonType> result = alert.showAndWait();
        	if(result.get()==ButtonType.OK) {	
        		tDao.createTrans(EventsforUserController.staticEP.getEventName(), 
        				cardNum.getText(), EventsforUserController.staticEP.getEventPrice(), 
        				email.getText(), Instant.now());
        		int avaNo = Integer.parseInt(EventsforUserController.staticEP.getEventAvailable());
        		eDao.updateEventById(Integer.toString(avaNo-1),EventsforUserController.staticEP.getEventId());	
        		PersonPojo p = new PersonPojo();
        		p.setFirst_Name(firstName.getText());
        		p.setLast_Name(lastName.getText());
    			p.setPassword(PersonDaoImpl.currentUser.getPassword());
    			p.setAddress(address.getText());
    			p.setCardno(cardNum.getText());
    			p.setExpdate(expireDate.getText());
    			p.setEmail(email.getText());
    			PersonDaoImpl.currentUser = p;
        		personDao.updatePersonPojo(p);
        		System.out.println("insert 1 row to transaction table");
        		System.out.println(p.getCardno());
        		AlertDialog.showAlert("Confirm page", "you have completed the transaction");
        		Stage stage = (Stage) email.getScene().getWindow();
        		stage.close();
            }
        	else {
        		alert.close();
        	}
    	}
    	
    	

    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		email.setEditable(false);
		firstName.setText(PersonDaoImpl.currentUser.getFirst_Name());
		lastName.setText(PersonDaoImpl.currentUser.getLast_Name());
		email.setText(PersonDaoImpl.currentUser.getEmail());
		address.setText(PersonDaoImpl.currentUser.getAddress());
		cardNum.setText(PersonDaoImpl.currentUser.getCardno());
		expireDate.setText(PersonDaoImpl.currentUser.getExpdate());
		eventTitle.setText(EventsforUserController.staticEP.getEventName());
		eventPrice.setText("$"+EventsforUserController.staticEP.getEventPrice());
	}
	
	

}
