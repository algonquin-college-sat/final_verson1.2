package tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertDialog {
	
	public static Alert getAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Confirmation Message");
		alert.setContentText("Confirm your information");
		return alert;
	}
	
	public static void showAlert(String header, String message) {
		  Alert alert = new Alert(Alert.AlertType.INFORMATION);
		  alert.setHeaderText(header);
		  alert.setContentText(message);
		  alert.show();
		 }
	
	

}
