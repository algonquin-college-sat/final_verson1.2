package tools;
import java.sql.*;

public class DbConnection {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = 
	"jdbc:mysql://localhost:3306/ticketdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT";
	static final String USER = "cst8334";
	static final String PASS = "8334";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}catch(SQLException e) {
			System.out.println("failed to execute sql");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Statement pst,Connection con){
		if(pst!=null){
			try{
				pst.close();
			}catch(SQLException c){}
		}
		if(con!=null){
			try{
				con.close();
			}catch(SQLException c){}
		}
	}
	
	public static void close(ResultSet rs, Statement pst, Connection con){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException c){}
		}
		
		if(pst!=null){
			try{
				pst.close();
			}catch(SQLException c){}
		}
		if(con!=null){
			try{
				con.close();
			}catch(SQLException c){}
		}
	}

}
