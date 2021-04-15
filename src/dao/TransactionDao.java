package dao;

import java.time.Instant;
import java.util.List;

import entity.Transaction;

public interface TransactionDao {
	
	public boolean createTrans(String event, String cardNO, String credit, String account_email, Instant instant);
	
	public List<Transaction> readAllTrans();
	
	public List<Transaction> readTransByEmail(String email);
	
	public boolean deleteTranById(int id);
	
	

}
