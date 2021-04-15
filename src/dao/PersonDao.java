package dao;

import java.util.List;

import entity.Person;
import entity.PersonPojo;

public interface PersonDao {
	
		//C
		public boolean createPersonPojo(String fname, String lname, String password, String email);
		
		//R
		public PersonPojo readPersonPojoById(int PersonPojoId);
		
		
		public List<Person> readAllPersonPojo();
		
		//U
		public boolean updatePersonPojo(PersonPojo PersonPojo);
		
		
		//D
		public boolean deletePersonPojo(String email);
		
		//Validation for Login
		public boolean validateLogin(String email, String password);
		
		//Validation for Register
		public boolean validateRegister(String email);
		
		//validation for admin
		public boolean validateAdmin(String email, String password);

		
}
