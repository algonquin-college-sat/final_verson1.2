package dao.impl;

import java.util.List;

import dao.EventDao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.PersonDao;
import entity.*;
import tools.AlertDialog;
import tools.DbConnection;

public class EventDaoImpl implements EventDao {

	@Override
	public EventProperty createEvent(String title, String date, String creator, String price, String ava) {
		// TODO Auto-generated method stub
		String query = "insert into event(title,event_date,creator,price,available)"
				+ "values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement psmt = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		EventProperty eventProperty = new EventProperty();
		try {
			Instant ins = sf.parse(date).toInstant();
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, title);
			psmt.setLong(2, ins.toEpochMilli());
			psmt.setString(3, creator);
			psmt.setBigDecimal(4, new BigDecimal(price));
			psmt.setInt(5, Integer.parseInt(ava));
			psmt.execute();
			ResultSet generatedKeys = psmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				eventProperty.setEventId(id);
				eventProperty.setEventName(title);
				eventProperty.setEventDate(ins.toString());
				eventProperty.setEventCreater(creator);
				eventProperty.setEventPrice(new BigDecimal(price).toString());
				eventProperty.setEventAvailable(ava);
				return eventProperty;
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AlertDialog.showAlert("Error", "please input valid format of every field");
			e.printStackTrace();
		}
		
		finally {
			DbConnection.close(psmt,conn);
		}
	  return null;
	}

	@Override
	public PersonPojo readPersonPojoById(int PersonPojoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventProperty> readAllEvents() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		List<EventProperty> eventProperty = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			stmt = conn.createStatement();
			String selectSql= "select * from ticketdb.event";
			ResultSet rs = stmt.executeQuery(selectSql);
			
			while(rs.next()){
				EventProperty ep = new EventProperty();
				ep.setEventId(rs.getInt("id"));
                ep.setEventName(rs.getString("title"));
                ep.setEventDate(Instant.ofEpochMilli(rs.getLong("event_date")).toString());
                ep.setEventCreater(rs.getString("creator"));
                ep.setEventPrice(rs.getBigDecimal("price").toString());
                ep.setEventAvailable(Integer.toString(rs.getInt("available")));
                ep.setEventDesp(rs.getString("desp"));
                eventProperty.add(ep);
			}
			
		} catch (Exception e) {
			System.out.println("problem during getEvents");
			e.printStackTrace();
		}
		finally {
			DbConnection.close(stmt, conn);
		}
		return eventProperty;
	}


	@Override
	public void deleteEvent(int id) {
		// TODO Auto-generated method stub
		String query = "delete from event where id = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DbConnection.close(psmt, conn);
		}
		
		
	}

	@Override
	public boolean validateCreator(String creator) {
		String query = "select * from account where email = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, creator);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DbConnection.close(psmt, conn);
		}
		return false;
	}

	@Override
	public boolean validateRegister(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateAdmin(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EventProperty> getEventByCreator(String email) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		List<EventProperty> eventProperty = new ArrayList<>();
		String selectSql= "select * from ticketdb.event where creator = ?";
		
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(selectSql);
			psmt.setString(1, email);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				EventProperty ep = new EventProperty();
				ep.setEventId(rs.getInt("id"));
                ep.setEventName(rs.getString("title"));
                ep.setEventDate(Instant.ofEpochMilli(rs.getLong("event_date")).toString());
                ep.setEventCreater(rs.getString("creator"));
                ep.setEventPrice(rs.getBigDecimal("price").toString());
                ep.setEventAvailable(Integer.toString(rs.getInt("available")));
                eventProperty.add(ep);
			}
			
		} catch (Exception e) {
			System.out.println("problem during getEvents");
			e.printStackTrace();
		}
		finally {
			DbConnection.close(psmt, conn);
		}
		return eventProperty;
	}

	@Override
	public String getAvaByEventId(int id) {
		String query = "select event.available from event where id = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		String avaString = "";
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				avaString = rs.getString("available");
			}
			
		} catch (Exception e) {
			System.out.println("problem during getEvents");
			e.printStackTrace();
		}
		finally {
			DbConnection.close(psmt, conn);
		}
		
		return avaString;
	}

	
	@Override
	public List<EventProperty> readEventsByKeyWords(String keyWord) {
		Connection conn = null;
		PreparedStatement psmt = null;
		List<EventProperty> eventProperty = new ArrayList<>();
		String selectSql= "select * from ticketdb.event where event.title like ?";		
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(selectSql);
			psmt.setString(1, "%"+keyWord+"%");
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				EventProperty ep = new EventProperty();
				ep.setEventId(rs.getInt("id"));
                ep.setEventName(rs.getString("title"));
                ep.setEventDate(Instant.ofEpochMilli(rs.getLong("event_date")).toString());
                ep.setEventCreater(rs.getString("creator"));
                ep.setEventPrice(rs.getBigDecimal("price").toString());
                ep.setEventAvailable(Integer.toString(rs.getInt("available")));
                System.out.println(ep.toString());
                eventProperty.add(ep);
			}
			
		} catch (Exception e) {
			System.out.println("problem during getEvents");
			e.printStackTrace();
		}
		finally {
			DbConnection.close(psmt, conn);
		}
		return eventProperty;
	}

	@Override
	public void updateEventById(String ava, int id) {
		String query = "update event set available = ? where id = ?;";
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DbConnection.getConnection();
			psmt= conn.prepareStatement(query);
			psmt.setString(1, ava);
			psmt.setInt(2, id);
			psmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DbConnection.close(psmt, conn);
		}
		
	}
	
	
	}


