package entity;

import java.math.BigDecimal;
import java.time.Instant;

public class Event {
	private int id;
	private String eventName;
	private Instant eventDate;
	private String eventCreater;
	private BigDecimal eventPrice;
	private int available;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Instant getEventDate() {
		return eventDate;
	}
	public long getCreatedEpochMilli() {
		return eventDate.toEpochMilli();
	}
	public void setEventDate(Instant eventDate) {
		this.eventDate = eventDate;
	}
	public void setEventDate(long eventDate) {
		this.eventDate = Instant.ofEpochMilli(eventDate);
	}
	public String getEventCreater() {
		return eventCreater;
	}
	public void setEventCreater(String eventCreater) {
		this.eventCreater = eventCreater;
	}
	public BigDecimal getEventPrice() {
		return eventPrice;
	}
	public void setEventPrice(BigDecimal eventPrice) {
		this.eventPrice = eventPrice;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}

	

	
    
    
    

}
