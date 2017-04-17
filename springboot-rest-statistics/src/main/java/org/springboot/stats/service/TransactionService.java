package org.springboot.stats.service;

import java.util.List;

import org.springboot.stats.domain.Statistics;
import org.springboot.stats.domain.TransactionData;
/**
 * Interface for Transaction service operations
 * @author Prasad Paravatha
 */
public interface TransactionService {
	
	/*
	 * This method saves the Transaction 
	 * @param Transaction
	 */
	public void saveTransaction(TransactionData transactionData) throws Exception;
	
	/*
	 * This gets the Statistics
	 * @return Statistics
	 */
	public Statistics getStatistics() throws Exception;
	
	/*
	 * This gets all the transactions
	 * @return Statistics
	 */
	public List<TransactionData> getTransactions() throws Exception;	
	
}
