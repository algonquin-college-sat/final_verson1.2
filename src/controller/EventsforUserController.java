package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.text.TabableView;

import dao.EventDao;
import dao.impl.EventDaoImpl;
import dao.impl.PersonDaoImpl;
import entity.EventProperty;
import entity.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.AlertDialog;

public class EventsforUserController implements Initializable {
	public static EventProperty staticEP;
	public static List<EventProperty> epList = new ArrayList<EventProperty>();
	private EventDao eDao = new EventDaoImpl();
	private Stage stage;
	
	@FXML
	private TableView<EventProperty> eventView;
	
	@FXML
	private TableColumn<EventProperty, String> title;
	
    @FXML
    private TableColumn<EventProperty, String> date;

    @FXML
    private TableColumn<EventProperty, String> creator;

    @FXML
    private TableColumn<EventProperty, String> price;

    @FXML
    private TableColumn<EventProperty, String> available;

    @FXML
    private TextField eventName;

    @FXML
    private TextArea despcription;

	Alert alert = AlertDialog.getAlert();
	private ObservableList<EventProperty> data = FXCollections.observableArrayList();
	EventDao eventDao = new EventDaoImpl();
	List<EventProperty> events = eventDao.readAllEvents();
	public static List<EventProperty> staticEvents;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		data.addAll(staticEvents);
		title.setCellValueFactory(new PropertyValueFactory<EventProperty, String>("eventName"));
		date.setCellValueFactory(new PropertyValueFactory<EventProperty, String>("eventDate"));
		creator.setCellValueFactory(new PropertyValueFactory<EventProperty, String>("eventCreater"));
		price.setCellValueFactory(new PropertyValueFactory<EventProperty, String>("eventPrice"));
		available.setCellValueFactory(new PropertyValueFactory<EventProperty, String>("eventAvailable"));
		eventView.setItems(data);
		
		eventView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
		showDesp(newValue));
	}
	
	public void showDesp(EventProperty ep) {
		despcription.setText(ep.getEventDesp());
		staticEP = ep;
		//System.out.println(staticEP.getEventId());
	}
	
	public void addtoCart() {
		/*epList.add(staticEP);*/
		AlertDialog.showAlert("Congratulations", "the item has been moved to your cart");	
	}
	
	public void search() {
		staticEvents = eDao.readEventsByKeyWords(eventName.getText());
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/EventsforUser.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Account "+ PersonDaoImpl.currentUser.getEmail());
			stage.show();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	public void purchase() {
		try {
			String ava = eDao.getAvaByEventId(staticEP.getEventId());
			//System.out.println(ava);
			if(Integer.parseInt(ava)==0) {
				AlertDialog.showAlert("Warning", "there are no available ticket for current event");
			}
			else {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/PurchasePage.fxml"));
				AnchorPane root;
				root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Purchase Page");
				stage.show();
				
				Stage stage2 = (Stage) eventName.getScene().getWindow();
	    		stage2.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
