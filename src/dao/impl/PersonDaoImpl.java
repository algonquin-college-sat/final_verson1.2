package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountException;

import dao.PersonDao;
import entity.Person;
import entity.PersonPojo;
import tools.AlertDialog;
import tools.DbConnection;

public class PersonDaoImpl implements PersonDao {
	public static PersonPojo currentUser;

	@Override
	public boolean createPersonPojo(String fname, String lname, String password, String email) {
			String query = "insert into account(first_name,last_name,password,email)"
					+ "values(?,?,?,?)";
			Connection conn = null;
			PreparedStatement psmt = null;
			try {
				conn = DbConnection.getConnection();
				psmt= conn.prepareStatement(query);
				psmt.setString(1, fname);
				psmt.setString(2, lname);
				psmt.setString(3, password);
				psmt.setString(4, email);
				psmt.execute();
				return true;
				
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
	public PersonPojo readPersonPojoById(int PersonPojoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> readAllPersonPojo() {
		
		Connection conn = null;
		Statement stmt = null;
		List< Person> people = new ArrayList<>();
		
		try {
			conn = DbConnection.getConnection();
			System.out.println(conn);
			stmt = conn.createStatement();
			System.out.println(stmt);
			String selectSql= "select * from ticketdb.account";
			ResultSet rs = stmt.executeQuery(selectSql);
			
			while(rs.next()){
				Person newPerson = new Person();
                newPerson.setFirstName(rs.getString("first_name"));
                newPerson.setLastName(rs.getString("last_name"));
                newPerson.setPassword(rs.getString("password"));
                newPerson.setEmail(rs.getString("email"));
                newPerson.setAddress(rs.getString("address"));
                newPerson.setCard(rs.getString("cardno"));
                newPerson.setExp(rs.getString("expdate"));
                people.add(newPerson);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DbConnection.close(stmt, conn);
		}
		return people;
	}
		
	

	@Override
	public boolean updatePersonPojo(PersonPojo personPojo) {
		// TODO Auto-generated method stub
		String query = "update account set first_name = ?, last_name = ?, password = ?, "
				+ "address = ?, cardno = ?, expdate = ? where email = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DbConnection.getConnection();
			psmt= conn.prepareStatement(query);
			psmt.setString(1, personPojo.getFirst_Name());
			psmt.setString(2, personPojo.getLast_Name());
			psmt.setString(3, personPojo.getPassword());
			psmt.setString(4, personPojo.getAddress());
			psmt.setString(5, personPojo.getCardno());
			psmt.setString(6, personPojo.getExpdate());
			psmt.setString(7, personPojo.getEmail());
			psmt.execute();
			return true;
			
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
	public boolean deletePersonPojo(String email) {
		// TODO Auto-generated method stub
		String query = "delete from account where email = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, email);
			psmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			AlertDialog.showAlert("Error", "something went wrong, this person cannot be deleted");
			e.printStackTrace();
		}
		finally {
			DbConnection.close(psmt, conn);
		}
		return false;
	}

	@Override
	public boolean validateLogin(String email, String password) {
		// TODO Auto-generated method stub
		String query = "select * from account where email = ? and password = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, email);
			psmt.setString(2, password);
		    ResultSet rs = psmt.executeQuery();
		    if(rs.next()) {
		    	PersonPojo newPerson = new PersonPojo();
		    	newPerson.setId(rs.getInt("id"));
                newPerson.setFirst_Name(rs.getString("first_name"));
                newPerson.setLast_Name(rs.getString("last_name"));
                newPerson.setPassword(rs.getString("password"));
                newPerson.setEmail(rs.getString("email"));
                newPerson.setIsAdmin(rs.getInt("isAdmin"));
                newPerson.setIsCreator(rs.getInt("isCreator"));
                newPerson.setAddress(rs.getString("address"));
                newPerson.setCardno(rs.getString("cardno"));
                newPerson.setExpdate(rs.getString("expdate"));
                currentUser = newPerson;
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
		String query = "select * from account where email = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, email);
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
	public boolean validateAdmin(String email, String password) {
		String query = "select * from account where email = ? and password = ? and isAdmin = ?";
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(query);
			psmt.setString(1, email);
			psmt.setString(2, password);
			psmt.setInt(3, 1);
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
	

}
