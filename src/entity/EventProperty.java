package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class EventProperty {
	private SimpleIntegerProperty eventId = new SimpleIntegerProperty(0);
	private SimpleStringProperty eventName = new SimpleStringProperty("");
	private SimpleStringProperty eventDate = new SimpleStringProperty("");
	private SimpleStringProperty eventCreater = new SimpleStringProperty("");
	private SimpleStringProperty eventPrice = new SimpleStringProperty("");
	private SimpleStringProperty eventAvailable = new SimpleStringProperty("");
	private SimpleStringProperty eventDesp = new SimpleStringProperty("");
	private boolean selected;
	
	public EventProperty() {
		
	}
	
	/*public EventProperty(String name, String date, String creater,String price, String Available) {
        this.eventName = new SimpleStringProperty(name);
        this.eventDate = new SimpleStringProperty(date);
        this.eventCreater = new SimpleStringProperty(creater);  
        this.eventPrice = new SimpleStringProperty(price);
        this.eventAvailable = new SimpleStringProperty(Available);
    }*/
	
	public int getEventId() {
        return eventId.get();
    }
    public void setEventId(int id) {
        eventId.set(id);
    }

	public String getEventName() {
        return eventName.get();
    }
    public void setEventName(String name) {
        eventName.set(name);
    }
        
    public String getEventDate() {
        return eventDate.get();
    }
    public void setEventDate(String date) {
        eventDate.set(date);
    }
    public String getEventPrice() {
        return eventPrice.get();
    }
    public void setEventPrice(String price) {
        eventPrice.set(price);
    }
    
    public String getEventCreater() {
        return eventCreater.get();
    }
    public void setEventCreater(String creater) {
        eventCreater.set(creater);
    }
    public String getEventAvailable() {
        return eventAvailable.get();
    }
    public void setEventAvailable(String available) {
        eventAvailable.set(available);
    }
	
    public boolean getSelected() {
        return this.selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    } 
    public String getEventDesp() {
        return eventDesp.get();
    }
    public void setEventDesp(String desp) {
        eventDesp.set(desp);
    }

}
