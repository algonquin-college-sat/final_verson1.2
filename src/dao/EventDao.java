package dao;

import java.util.List;

import entity.EventProperty;
import entity.Person;
import entity.PersonPojo;

public interface EventDao {
	
			//C
			public EventProperty createEvent(String title, String date, String creator, String price, String ava);
			
			//R
			public PersonPojo readPersonPojoById(int PersonPojoId);
			
			public List<EventProperty> readAllEvents();
			
			public List<EventProperty> getEventByCreator(String email);
			
			public String getAvaByEventId(int id);
			
			public List<EventProperty> readEventsByKeyWords(String keyWord);
			
			//U
			public void updateEventById(String ava, int id);
			
			//D
			public void deleteEvent(int id);
			
			//Validation for creator
			public boolean validateCreator(String creator);
			
			//Validation for Register
			public boolean validateRegister(String email);
			
			//validation for admin
			public boolean validateAdmin(String email, String password);
}
