package application;

import entity.EventProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class viewAlter {
	private Stage stage;

	public void gotoPurchase(EventProperty data) {
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/Purchase.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Purchase Page");
			stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void gotoEventDetail(EventProperty data) {
    	try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/EventDetailPage.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("detail Page");
			stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}
}
