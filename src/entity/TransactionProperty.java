package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TransactionProperty {
	private SimpleIntegerProperty tranId = new SimpleIntegerProperty(0);
	private SimpleStringProperty tranName = new SimpleStringProperty("");
	private SimpleStringProperty tranDate = new SimpleStringProperty("");
	private SimpleStringProperty tranCreator = new SimpleStringProperty("");
	private SimpleStringProperty tranPrice = new SimpleStringProperty("");
	private SimpleStringProperty tranCardNo = new SimpleStringProperty("");
	private SimpleStringProperty eventDesp = new SimpleStringProperty("");
	private boolean selected;
	
	public TransactionProperty() {
		
	}
	
	/*public EventProperty(String name, String date, String creater,String price, String Available) {
        this.tranName = new SimpleStringProperty(name);
        this.tranDate = new SimpleStringProperty(date);
        this.tranCreator = new SimpleStringProperty(creater);  
        this.tranPrice = new SimpleStringProperty(price);
        this.tranCardNo = new SimpleStringProperty(Available);
    }*/
	
	public int getTranId() {
        return tranId.get();
    }
    public void setTranId(int id) {
        tranId.set(id);
    }

	public String getTranName() {
        return tranName.get();
    }
    public void setTranName(String name) {
        tranName.set(name);
    }
        
    public String getTranDate() {
        return tranDate.get();
    }
    public void setTranDate(String date) {
        tranDate.set(date);
    }
    public String getTranPrice() {
        return tranPrice.get();
    }
    public void setTranPrice(String price) {
        tranPrice.set(price);
    }
    
    public String getTranCreater() {
        return tranCreator.get();
    }
    public void setTranCreater(String creater) {
        tranCreator.set(creater);
    }
    public String getTranCardNo() {
        return tranCardNo.get();
    }
    public void setTranCardNo(String available) {
        tranCardNo.set(available);
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
