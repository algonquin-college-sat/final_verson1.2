package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.TransactionDao;
import dao.impl.PersonDaoImpl;
import dao.impl.TransactionDaoImpl;
import entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.AlertDialog;

public class HistoryController implements Initializable {
	
	TransactionDao tDao = new TransactionDaoImpl();
	Alert alert = AlertDialog.getAlert();
	List<Transaction> tList = tDao.readTransByEmail(PersonDaoImpl.currentUser.getEmail());
	ObservableList<Transaction> data = FXCollections.observableArrayList();


    @FXML
    private TableColumn<Transaction, String> date;

    @FXML
    private TableColumn<Transaction, String> price;

    @FXML
    private TableColumn<Transaction, String> event;

    @FXML
    private TableColumn<Transaction, String> cardno;

    @FXML
    private TextField account;
    
    @FXML
    private TableView <Transaction> viewhistory;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		account.setDisable(true);
		account.setText(PersonDaoImpl.currentUser.getEmail());
		tList = tDao.readTransByEmail(PersonDaoImpl.currentUser.getEmail());
		data.addAll(tList);
		event.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		cardno.setCellValueFactory(cellData-> cellData.getValue().getNumberProperty());
		price.setCellValueFactory(cellData-> cellData.getValue().getcreditProperty());
		date.setCellValueFactory(cellData-> cellData.getValue().getDateProperty());
		viewhistory.setItems(data);
		viewhistory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
		loadrefundPage(newValue));
	
	}
	
	public void loadrefundPage(Transaction t) {
		
		try {
			//System.out.println("load refund page");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/TransactionRefund.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Information for user "+ t.getAccount_email());
			RefundController rc = loader.getController();
			rc.transid.setText(Integer.toString(t.getId()));
			rc.event.setText(t.getEventName());
			rc.card.setText(t.getCardno());
			rc.price.setText(t.getCredit().toString());
			stage.show();
	       } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	}
	
	public void refresh() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PurchaseHistory.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Overview of Purchase History");
			stage.show();
			
			Stage stage2 = (Stage) account.getScene().getWindow();
			stage2.close();
			
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
