package entity;

import javafx.beans.property.SimpleStringProperty;

public  class Person {
	private  SimpleStringProperty firstName = new SimpleStringProperty("");
    private  SimpleStringProperty lastName = new SimpleStringProperty("");
    private  SimpleStringProperty password = new SimpleStringProperty("");
    private  SimpleStringProperty email = new SimpleStringProperty("");
    private  SimpleStringProperty address = new SimpleStringProperty("");
    private  SimpleStringProperty cardNo = new SimpleStringProperty("");
    private  SimpleStringProperty expDate = new SimpleStringProperty("");
    private boolean selected;
 
    
    public Person() {
    	
    }
    
    public Person(String fName, String lName, String password, String email, boolean selected) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
        this.selected = selected;
        
    }
    
 
    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String fName) {
        firstName.set(fName);
    }
    public String getAddress() {
        return address.get();
    }
    public void setAddress(String add) {
        address.set(add);
    }
    
    public String getCard() {
        return cardNo.get();
    }
    public void setCard(String card) {
        cardNo.set(card);
    }
    
    public String getExp() {
        return expDate.get();
    }
    public void setExp(String exp) {
        expDate.set(exp);
    }
        
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String fName) {
        lastName.set(fName);
    }
    public String getPassword() {
        return password.get();
    }
    public void setPassword(String pwd) {
        password.set(pwd);
    }
    
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String fName) {
        email.set(fName);
    }
    
    public boolean getSelected() {
        return this.selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }  
}
