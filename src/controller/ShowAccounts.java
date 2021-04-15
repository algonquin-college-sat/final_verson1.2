package controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import tools.*;
import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import entity.Person;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import tools.AlertDialog;
import tools.CellFactory;

public class ShowAccounts {
	Alert alert = AlertDialog.getAlert();
	private ObservableList<Person> data = FXCollections.observableArrayList();
	PersonDao personDao = new PersonDaoImpl();
	List<Person> people = personDao.readAllPersonPojo();
	Validator validator = new Validator();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public Pane createAccountPane() {
		TableView<Person> table = new TableView<Person>();
		final HBox hb = new HBox();
		final Label label = new Label("Accounts Information");
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(160);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setFirstName(t.getNewValue());
                }
            }
        );
        
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(160);
        lastNameCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());
                }
            }
        );
        
        TableColumn passwordCol = new TableColumn("Password");
        passwordCol.setMinWidth(200);
        passwordCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("password"));
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
                }
            }
        );
        
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
            new PropertyValueFactory<Person, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> t) {
                    ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
                }
            }
        );
        
        TableColumn selectedCol = new TableColumn("Selected");
        selectedCol.setMinWidth(160);
        selectedCol.setCellFactory(
                CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(Integer index) {
                        final Person p = (Person) table.getItems().get(index);
                        ObservableValue<Boolean> ret =
                                new SimpleBooleanProperty(p, "selected", p.getSelected());
                        ret.addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {
                                p.setSelected(newValue);
                            }
                        });
                        return ret;
                    }
                }));
        data.addAll(people);
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, passwordCol, emailCol, selectedCol);
        
        
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> 
		loadEditPage(newValue));
        
        
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMinWidth(180);
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        addLastName.setMinWidth(180);
        
        final TextField addPassword = new TextField();
        addPassword.setPromptText("password");
        addPassword.setMaxWidth(passwordCol.getPrefWidth());
        addPassword.setMinWidth(200);
        
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");
        addEmail.setMinWidth(200);
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	
            	if(validator.validatePwd(addPassword.getText())&&validator.validateEmail(addEmail.getText()))
            	{
            		if(!personDao.validateRegister(addEmail.getText())) {
            			personDao.createPersonPojo(addFirstName.getText(), addLastName.getText(), 
            					addPassword.getText(), addEmail.getText());
                        data.add(new Person(
                                addFirstName.getText(),
                                addLastName.getText(),
                                addPassword.getText(),
                                addEmail.getText(),false)
                        		);
                        addFirstName.clear();
                        addLastName.clear();
                        addPassword.clear();
                        addEmail.clear();
            		}
            		else {
            			showAlert("Error", "this email has already been registered");
            		}
            	}
            	else {
            		showAlert("Error", "Please input valid type of information");
            	}

            }
        });
        
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	Optional<ButtonType> result = alert.showAndWait();
            	if(result.get()==ButtonType.OK) {
                	int size = data.size();
                    for (int i = size - 1; i >= 0; i--) {
                        Person p = data.get(i);
                        if (p.getSelected()) {
                        	if(personDao.deletePersonPojo(p.getEmail())) {
                        		 data.remove(p);
                        	};
                           
                        }
                    }
                }
            	else {
            		alert.close();
            	}
            }

        });
	
        hb.getChildren().addAll(addFirstName, addLastName,addPassword,addEmail,addButton,deleteButton);
        hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        return vbox;
	}
	
	
	public void showAlert(String header, String message) {
		  Alert alert = new Alert(Alert.AlertType.INFORMATION);
		  alert.setHeaderText(header);
		  alert.setContentText(message);
		  alert.show();
		 }
	
	public void loadEditPage(Person p) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/adminEditUser.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Information for user "+ p.getEmail());
			adminEditUserController ad = loader.getController();
			ad.email.setText(p.getEmail());
			ad.password.setText(p.getPassword());
			ad.fname.setText(p.getFirstName());
			ad.lname.setText(p.getLastName());
			ad.address.setText(p.getAddress());
			ad.cardNumber.setText(p.getCard());
			ad.expdate.setText(p.getExp());
			stage.show();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
