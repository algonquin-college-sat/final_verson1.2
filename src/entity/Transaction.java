package entity;

import java.math.BigDecimal;
import java.time.Instant;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Transaction {
	private int id;
	private String eventName;
	private String cardno;
	private BigDecimal credit;
	private String account_email;
	private Instant created;
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
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getAccount_email() {
		return account_email;
	}
	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}
	public Instant getCreated() {
		return created;
	}
	public long getCreatedEpochMilli() {
		return created.toEpochMilli();
	}
	public void setCreated(Instant created) {
		this.created = created;
	}
	public void setCreated( long created) {
		this.created = Instant.ofEpochMilli( created);
	}
	
	public SimpleStringProperty getNameProperty() {
		return new SimpleStringProperty(this.getEventName());
	}
	
	public SimpleStringProperty getNumberProperty() {
		return new SimpleStringProperty(this.getCardno());
	}
	
	public SimpleStringProperty getcreditProperty() {
		return new SimpleStringProperty(this.getCredit().toString());

	}
	
	public SimpleStringProperty getaccountroperty() {
		return new SimpleStringProperty(this.getAccount_email());
	}
	
	public SimpleStringProperty getDateProperty() {
		return new SimpleStringProperty(this.getCreated().toString());
	}
	
	public SimpleIntegerProperty getIdProperty() {
		return new SimpleIntegerProperty(this.getId());
	}

}
