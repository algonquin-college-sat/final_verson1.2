package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.TransactionDao;
import dao.impl.PersonDaoImpl;
import dao.impl.TransactionDaoImpl;
import entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import tools.AlertDialog;

public class AllRecordsController implements Initializable {
	TransactionDao tDao = new TransactionDaoImpl();
	Alert alert = AlertDialog.getAlert();
	List<Transaction> tList = tDao.readAllTrans();
	ObservableList<Transaction> data = FXCollections.observableArrayList();
	
    @FXML
    private TableColumn<Transaction, String> date;

    @FXML
    private TableColumn<Transaction, String> price;

    @FXML
    private TableColumn<Transaction, String> event;

    @FXML
    private TableColumn<Transaction, String> cardNumber;

    @FXML
    private TableColumn<Transaction, String> account;
    
    @FXML
    private TableView <Transaction> viewAll;
    
    @FXML
    private Button exitBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		data.addAll(tList);
		event.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		cardNumber.setCellValueFactory(cellData-> cellData.getValue().getNumberProperty());
		price.setCellValueFactory(cellData-> cellData.getValue().getcreditProperty());
		date.setCellValueFactory(cellData-> cellData.getValue().getDateProperty());
		account.setCellValueFactory(cellData -> cellData.getValue().getaccountroperty());
		viewAll.setItems(data);
	}
	
	public void exit(){
		Stage stage2 = (Stage)exitBtn.getScene().getWindow();
		stage2.close();
	}

}
