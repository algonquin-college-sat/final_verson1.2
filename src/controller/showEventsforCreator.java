package controller;

import java.util.List;

import dao.EventDao;
import dao.impl.EventDaoImpl;
import dao.impl.PersonDaoImpl;
import entity.EventProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import tools.AlertDialog;
import tools.CellFactory;
import tools.Validator;

public class showEventsforCreator {
	Alert alert = AlertDialog.getAlert();
	private ObservableList<EventProperty> data = FXCollections.observableArrayList();
	EventDao eventDao = new EventDaoImpl();
	List<EventProperty> events = eventDao.getEventByCreator(PersonDaoImpl.currentUser.getEmail());
	Validator validator = new Validator();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pane createEventsPane() {
		TableView<EventProperty> table = new TableView<EventProperty>();
		final HBox hb = new HBox();
		final Label label = new Label("Events Information by "+ PersonDaoImpl.currentUser.getEmail());
        label.setFont(new Font("Arial", 20));
        table.setEditable(true);
        
		TableColumn eventNameCol = new TableColumn("Event Name");
        eventNameCol.setMinWidth(160);
        eventNameCol.setCellValueFactory(
                new PropertyValueFactory<EventProperty, String>("eventName"));
        eventNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        eventNameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<EventProperty, String>>() {
                @Override
                public void handle(CellEditEvent<EventProperty, String> t) {
                    ((EventProperty) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setEventName(t.getNewValue());
                }
            }
        );
        
        TableColumn eventDateCol = new TableColumn("Event Date");
        eventDateCol.setMinWidth(200);
        eventDateCol.setCellValueFactory(
            new PropertyValueFactory<EventProperty, String>("eventDate"));
        eventDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        eventDateCol.setOnEditCommit(
            new EventHandler<CellEditEvent<EventProperty, String>>() {
                @Override
                public void handle(CellEditEvent<EventProperty, String> t) {
                    ((EventProperty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEventDate(t.getNewValue());
                }
            }
        );
        
        TableColumn eventCreaterCol = new TableColumn("Creater");
        eventCreaterCol.setMinWidth(180);
        eventCreaterCol.setCellValueFactory(
            new PropertyValueFactory<EventProperty, String>("eventCreater"));
        eventCreaterCol.setCellFactory(TextFieldTableCell.forTableColumn());
        eventCreaterCol.setOnEditCommit(
            new EventHandler<CellEditEvent<EventProperty, String>>() {
                @Override
                public void handle(CellEditEvent<EventProperty, String> t) {
                    ((EventProperty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEventCreater(t.getNewValue());
                }
            }
        );
        
        TableColumn eventPriceCol = new TableColumn("Price");
        eventPriceCol.setMinWidth(120);
        eventPriceCol.setCellValueFactory(
            new PropertyValueFactory<EventProperty, String>("eventPrice"));
        eventPriceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        eventPriceCol.setOnEditCommit(
            new EventHandler<CellEditEvent<EventProperty, String>>() {
                @Override
                public void handle(CellEditEvent<EventProperty, String> t) {
                    ((EventProperty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEventPrice(t.getNewValue());
                }
            }
        );
        
        TableColumn eventAvailableCol = new TableColumn("Available");
        eventAvailableCol.setMinWidth(30);
        eventAvailableCol.setCellValueFactory(
            new PropertyValueFactory<EventProperty, String>("eventAvailable"));
        eventAvailableCol.setCellFactory(TextFieldTableCell.forTableColumn());
        eventAvailableCol.setOnEditCommit(
            new EventHandler<CellEditEvent<EventProperty, String>>() {
                @Override
                public void handle(CellEditEvent<EventProperty, String> t) {
                    ((EventProperty) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEventPrice(t.getNewValue());
                }
            }
        );
        
        TableColumn selectedCol = new TableColumn("Selected");
        selectedCol.setMinWidth(120);
        selectedCol.setCellFactory(
                CellFactory.tableCheckBoxColumn(new Callback<Integer, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(Integer index) {
                        final EventProperty ep = (EventProperty) table.getItems().get(index);
                        ObservableValue<Boolean> ret =
                                new SimpleBooleanProperty(ep, "selected", ep.getSelected());
                        ret.addListener(new ChangeListener<Boolean>() {
                            @Override
                            public void changed(
                                    ObservableValue<? extends Boolean> observable,
                                    Boolean oldValue, Boolean newValue) {
                                ep.setSelected(newValue);
                            }
                        });
                        return ret;
                    }
                }));
        
        data.addAll(events);
        table.setItems(data);
        table.getColumns().addAll(eventNameCol, eventDateCol, eventCreaterCol, eventPriceCol,eventAvailableCol,selectedCol);
        
        final TextField addEventName = new TextField();
        addEventName.setPromptText("EventName");
        addEventName.setMinWidth(160);
        addEventName.setMaxWidth(eventNameCol.getPrefWidth());
        
        final TextField addEventDate = new TextField();
        addEventDate.setMaxWidth(eventDateCol.getPrefWidth());
        addEventDate.setPromptText("Format:yyyy-MM-dd HH:mm");
        addEventDate.setMinWidth(240);
        
        final TextField addEventCreater = new TextField();
        addEventCreater.setPromptText("EventCreator");
        addEventCreater.setMaxWidth(eventCreaterCol.getPrefWidth());
        addEventCreater.setMinWidth(160);
        
        final TextField addEventPrice = new TextField();
        addEventPrice.setMaxWidth(eventPriceCol.getPrefWidth());
        addEventPrice.setPromptText("EventPrice");
        addEventPrice.setMinWidth(100);
        
        final TextField addEventAvailable = new TextField();
        addEventAvailable.setMaxWidth(eventAvailableCol.getPrefWidth());
        addEventAvailable.setPromptText("Available");
        addEventAvailable.setMinWidth(20);
        
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	if(validator.validateEvent(addEventName.getText(), addEventDate.getText(), addEventCreater.getText(), 
            			addEventPrice.getText(),addEventAvailable.getText())) {
            		EventProperty event = eventDao.createEvent(addEventName.getText(), addEventDate.getText(), addEventCreater.getText(), 
            				addEventPrice.getText(), addEventAvailable.getText());
            		if(event!=null) {
            			data.add(event);
                        addEventName.clear();
                        addEventDate.clear();
                        addEventCreater.clear();
                        addEventPrice.clear();
                        addEventAvailable.clear();
            		}
            		else {
            			AlertDialog.showAlert("Error", "error in crearing new events");
            		}
            	}
            	else {
            		AlertDialog.showAlert("Error", "Please input valid type of information");
            	}
                
            }
        });
        
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	int size = data.size();
                for (int i = size - 1; i >= 0; i--) {
                    EventProperty ep = data.get(i);
                    if (ep.getSelected()) {
                    	eventDao.deleteEvent(ep.getEventId());
                        data.remove(ep);
                    }
                }
            }
        });
	
        hb.getChildren().addAll(addEventName, addEventDate,addEventCreater,addEventPrice,addEventAvailable,addButton,deleteButton);
        hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        return vbox;
        }

}
