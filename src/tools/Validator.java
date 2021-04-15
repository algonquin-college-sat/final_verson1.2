package tools;

import java.util.regex.Pattern;

import dao.EventDao;
import dao.impl.EventDaoImpl;




public class Validator {
	
	EventDao dao = new EventDaoImpl();
	//simple email pattern: at-least-1-letter, '@', at-least-1-letter
	private final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");
	
	public boolean validateEmail(String email) {
		if(email==null|email.trim()==""||!EMAIL_PATTERN.matcher(email).matches()) {
			return false;
		}
		return true;
	}
	
	//the password is at least one letter and one digit, size is from 6 to 15;
	public boolean validatePwd(String str) {
		if(str==null||str.trim()=="") {
			return false;
		}
		
	    boolean isDigit = false;
	    boolean isLetter = false;
	    for (int i = 0; i < str.length(); i++) {
	        if (Character.isDigit(str.charAt(i))) {   
	            isDigit = true;
	        } else if (Character.isLetter(str.charAt(i))) {  
	            isLetter = true;
	        }
	    }
	    String regex = "^[a-zA-Z0-9]{6,15}$";
	    boolean isRight = isDigit && isLetter && str.matches(regex);
	    return isRight;
	}
	
	public boolean validateName(String name) {
		if(name==null||name.trim()=="") {
			return false;
		}
		//must begin with a letter and _ is allowed, could not be more than 16 letters
		Pattern p = Pattern.compile("[a-zA-Z]{1}[a-zA-Z0-9_]{1,15}");
		return (p.matcher(name).matches());
	}
	
	public boolean validateEvent(String name,String date,String creator, String price, String ava) {
		if(name==null||name.trim().equals("")) {
			AlertDialog.showAlert("Error", "Please input correct event name");
			return false;
		}
		else if(!dao.validateCreator(creator)) {
			AlertDialog.showAlert("Error", "Please input correct creator name");
			return false;
		}
		else if (price==null||name.trim().equals("")||Double.parseDouble(price)<=0) {
			AlertDialog.showAlert("Error", "Please input correct price");
			return false;
		}
		else if(Integer.parseInt(ava)<=0) {
			AlertDialog.showAlert("Error", "Please input correct available numbers");
			return false;
		}
		return true;
	}
	
	
	
	
	

	

}
