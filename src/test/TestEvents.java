package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import entity.EventProperty;
import tools.DbConnection;

public class TestEvents {

	public static void main(String[]args){
	
		Connection conn = null;
		Statement stmt = null;
		List<EventProperty> eventProperty = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			stmt = conn.createStatement();
			String selectSql= "select * from ticketdb.event ";
			ResultSet rs = stmt.executeQuery(selectSql);
			while(rs.next()){
				EventProperty ep = new EventProperty();
                ep.setEventName(rs.getString("title"));
                ep.setEventDate(Instant.ofEpochMilli(rs.getLong("event_date")).toString());
                ep.setEventCreater(rs.getString("creator"));
                ep.setEventPrice(rs.getBigDecimal("price").toString());
                ep.setEventAvailable(Integer.toString(rs.getInt("available")));
                eventProperty.add(ep);
                System.out.println("start");
                System.out.println(ep.getEventDate());
			}
			
		} catch (Exception e) {
			System.out.println("problem during getEvents");
			e.printStackTrace();
		}
		finally {
			DbConnection.close(stmt, conn);
		}
		
	}
	
}
