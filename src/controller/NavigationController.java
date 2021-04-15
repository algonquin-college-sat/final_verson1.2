package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.EventDao;
import dao.impl.EventDaoImpl;
import dao.impl.PersonDaoImpl;
import entity.EventProperty;
import entity.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class NavigationController implements Initializable {
	
	private Stage stage;
	
    @FXML
    private AnchorPane subPane;
    
    @FXML
    private Button adminBtn;
    
    @FXML
    private Button creatorBtn;
    
    EventDao eventDao = new EventDaoImpl();
    List<EventProperty> events = eventDao.readAllEvents();
    
    public void toAdminPage() {
    	try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/AdminPage.fxml"));
				AnchorPane root = loader.load();
				Scene scene = new Scene(root);
				stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("Admin System");
				stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void toCreatorPage() {
    	showEventsforCreator sec = new showEventsforCreator();
    	Pane pane = sec.createEventsPane();
    	AnchorPane ap = new AnchorPane(pane);
    	stage = new Stage();
    	stage.setScene(new Scene(ap));
    	stage.setTitle("creator page");
    	stage.show();
    }

    public void toProfile() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/UserProfile.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Information for user "+ PersonDaoImpl.currentUser.getEmail());
			stage.show();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    
    @FXML
    public void viewEvents() {
    	try {
    		EventsforUserController.staticEvents = eventDao.readAllEvents();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(PersonDaoImpl.currentUser.getIsAdmin()!=1) {
			adminBtn.setDisable(true);
			
		}
		if(PersonDaoImpl.currentUser.getIsCreator()!=1) {
			creatorBtn.setDisable(true);
		}
		
	}
	
    @FXML
    public void viewHistory() {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PurchaseHistory.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Overview of Purchase History");
			stage.show();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    public void exit() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Main2.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
            stage.setTitle("Tickets Management System");
            stage.setScene(scene);
            stage.show();
    		Stage stage2 = (Stage) adminBtn.getScene().getWindow();
    		stage2.close();
    	
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    

}
