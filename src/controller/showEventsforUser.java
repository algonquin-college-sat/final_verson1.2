package controller;

import java.util.List;

import application.viewAlter;
import dao.EventDao;
import dao.impl.EventDaoImpl;
import entity.EventProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Callback;
public class showEventsforUser {
	private EventDao eventDao = new EventDaoImpl();
	private viewAlter viewAlter = new viewAlter();

	public Region creatEventList() {
		ObservableList<EventProperty> list = FXCollections.observableArrayList();
		List<EventProperty> events = eventDao.readAllEvents();
        list.addAll(events);
        ListView<EventProperty> listView = new ListView<>(list);
        
        listView.setCellFactory(new Callback<ListView<EventProperty>, ListCell<EventProperty>>() {
            @Override
            public ListCell<EventProperty> call(ListView<EventProperty> param) {
                ListCell<EventProperty> listCell = new ListCell<EventProperty>() {
                    @Override
                    protected void updateItem(EventProperty item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            AnchorPane anchorPane = new AnchorPane();
                            GridPane gridPane = new GridPane();
                            gridPane.setAlignment(Pos.TOP_LEFT);
                            gridPane.setPadding(new Insets(10, 0, 10, 0));

//                            HBox.setHgrow(gridPane, Priority.ALWAYS);

//                            HBox hBox = new HBox();
                            Label title = new Label(item.getEventName());
                            title.setMinWidth(300.0);
//                            title.setMaxWidth(400.0);
                            title.setAlignment(Pos.CENTER_LEFT);
                            title.setFont(new Font(18));
                            GridPane.setMargin(title, new Insets(0, 0, 0, 30));
                            GridPane.setHgrow(title, Priority.ALWAYS);
                            Label time = new Label(item.getEventDate());
                            time.setMinWidth(340.0);
                            time.setAlignment(Pos.CENTER_LEFT);
                            time.setFont(new Font(12));
                            GridPane.setMargin(time, new Insets(0, 0, 0, 40));
                            GridPane.setHgrow(time, Priority.ALWAYS);
                            Label price = new Label("$" + item.getEventPrice());
                            price.setAlignment(Pos.CENTER_RIGHT);
                            price.setFont(new Font(14));
                            price.setMinWidth(90.0);
                            GridPane.setMargin(price, new Insets(0, 0, 0, 60));
                            GridPane.setHgrow(price, Priority.ALWAYS);
                            Button purchase = new Button("Purchase");
                            purchase.setAlignment(Pos.CENTER_RIGHT);
                            purchase.setFont(new Font(14));
                            purchase.setMinWidth(80.0);
                            GridPane.setMargin(purchase, new Insets(0, 0, 0, 40));
                            GridPane.setHgrow(purchase, Priority.ALWAYS);
                            purchase.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    viewAlter.gotoPurchase(item);
                                }
                            });
                            gridPane.add(title, 0, 0);
                            gridPane.add(time, 0, 1);
                            gridPane.add(price, 1, 0, 1, 2);
                            gridPane.add(purchase, 2, 0, 1, 2);
                            gridPane.setMinWidth(700.0);
                            gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                            anchorPane.getChildren().add(gridPane);
//                            AnchorPane.setLeftAnchor(title, 20.0);
//                            AnchorPane.setLeftAnchor(time, 160.0);
//                            AnchorPane.setRightAnchor(price, 120.0);
//                            AnchorPane.setRightAnchor(purchase, 20.0);
//                            hBox.getChildren().add(anchorPane);
//                            anchorPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//                            HBox.setHgrow(anchorPane, Priority.ALWAYS);
//                            HBox.setMargin(anchorPane, new Insets(10));
                            this.setGraphic(anchorPane);
                        }
                    }
                };
                return listCell;
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EventProperty>() {
            @Override
            public void changed(ObservableValue<? extends EventProperty> observable, EventProperty oldValue, EventProperty newValue) {
                viewAlter.gotoEventDetail(newValue);
            }
        });
        return listView;
    }
}
