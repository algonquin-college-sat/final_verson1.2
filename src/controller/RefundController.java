package controller;

import java.net.URL;
import java.time.Instant;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.TransactionDao;
import dao.impl.PersonDaoImpl;
import dao.impl.TransactionDaoImpl;
import entity.PersonPojo;
import entity.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.AlertDialog;

public class RefundController implements Initializable {
	Alert alert = AlertDialog.getAlert();
	TransactionDao tranDao = new TransactionDaoImpl();

    @FXML
    public Text price;

    @FXML
    public Text event;

    @FXML
    public Text card;

    @FXML
    public Button refund;
    
    @FXML
    public Text transid;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void refund() {
		Optional<ButtonType> result = alert.showAndWait();
    	if(result.get()==ButtonType.OK) {	
    		tranDao.deleteTranById(Integer.parseInt(transid.getText()));
    		AlertDialog.showAlert("Confirm page", "you have refunded this event");
    		Stage stage = (Stage) event.getScene().getWindow();
    		stage.close();
        }
    	else {
    		alert.close();
    	}
		
	}

}
