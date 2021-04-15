package dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import dao.TransactionDao;
import entity.EventProperty;
import entity.Transaction;
import tools.AlertDialog;
import tools.DbConnection;

public class TransactionDaoImpl implements TransactionDao {



	@Override
	public List<Transaction> readAllTrans() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		List<Transaction> trans = new ArrayList<>();
		String selectSql= "select * from ticketdb.transinfo"; 
		
		try {
			conn = DbConnection.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(selectSql);
			while(rs.next()){
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setEventName(rs.getString("event"));
                t.setCardno(rs.getString("cardNO"));
                t.setCredit(rs.getBigDecimal("credit"));
                t.setAccount_email(rs.getString("account_email"));
                t.setCreated(rs.getLong("created_date"));
                trans.add(t);
			}
		} catch (Exception e) {
			System.out.println("problem during getTrans");
			e.printStackTrace();
		}
		finally {
			DbConnection.close(stmt, conn);
		}
		return trans;
	}

	@Override
	public List<Transaction> readTransByEmail(String email) {
		Connection conn = null;
		PreparedStatement psmt = null;
		List<Transaction> trans = new ArrayList<>();
		String selectSql= "select * from ticketdb.transinfo where account_email = ? "; 
		
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(selectSql);
			psmt.setString(1, email);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()){
				Transaction t = new Transaction();
				t.setId(rs.getInt("id"));
				t.setEventName(rs.getString("event"));
                t.setCardno(rs.getString("cardNO"));
                t.setCredit(rs.getBigDecimal("credit"));
                t.setAccount_email(rs.getString("account_email"));
                t.setCreated(rs.getLong("created_date"));
                trans.add(t);
			}
			
		} catch (Exception e) {
			System.out.println("problem during getTrans");
			e.printStackTrace();
		}
		finally {
			DbConnection.close(psmt, conn);
		}
		return trans;
	}



	@Override
	public boolean createTrans(String event, String cardNO, String credit, String account_email, Instant instant) {
			String query = "insert into transinfo(event,cardNO,credit,account_email,created_date)"
					+ "values(?,?,?,?,?)";
			Connection conn = null;
			PreparedStatement psmt = null;
			
			try {
				conn = DbConnection.getConnection();
				psmt = conn.prepareStatement(query);
				psmt.setString(1, event);
				psmt.setString(2, cardNO);
				psmt.setBigDecimal(3, new BigDecimal(credit));
				psmt.setString(4, account_email);
				psmt.setLong(5, instant.toEpochMilli());
				psmt.execute();
				return true;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				AlertDialog.showAlert("Error", "sth went wrong in transaction");
				e.printStackTrace();
			}
			
			finally {
				DbConnection.close(psmt,conn);
			}
		  return false;
	}

	@Override
	public boolean deleteTranById(int id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String deleSql= "delete from ticketdb.transinfo where id = ? "; 
		
		try {
			conn = DbConnection.getConnection();
			psmt = conn.prepareStatement(deleSql);
			psmt.setInt(1, id);
			psmt.execute();
			return true;
		} catch (Exception e) {
			System.out.println("problem during delete trans");
			AlertDialog.showAlert("Error","could not delete");
			e.printStackTrace();
		}
		finally {
			DbConnection.close(psmt, conn);
		}
		return false;
	}

}
