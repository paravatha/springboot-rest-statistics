package org.springboot.stats.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.stats.domain.TransactionData;
import org.springboot.stats.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main Controller class
 * @author Prasad Paravatha 
 */
@RestController
public class MainController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	TransactionServiceImpl transactionService;
	
    /**
     * Save transactions
     * 
     * URI: /stats/transactions [POST]
     * @param TransactionData 
     * @return status
     */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public ResponseEntity saveTransaction(@RequestBody(required=true) TransactionData transactionData) throws Exception {
		LOGGER.debug(" in saveTransaction = " + transactionData.toString());
		
		transactionService.saveTransaction(transactionData);
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
    /**
     * Returns the statistics of transactions in the past 60 seconds
     * 
     * URI: /stats/statistics [GET]
     * @return Statistics
     */
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object getStatistics() throws Exception {
		LOGGER.debug(" in getStatistics = ");
		
		return ((transactionService.getStatistics().getCount() == 0) ? "No transactions in the past 60 seconds" : transactionService.getStatistics()) ;
		
	}

	
    /**
     * Get All transactions
     * 
     * URI: /transactions/list [GET]
     * @return List<TransactionData>
     */
	@RequestMapping(value = "/transactions/list", method = RequestMethod.GET)
	public List<TransactionData> getTransactions() throws Exception {
		
		return transactionService.getTransactions();
		
	}
	
	/**
	 * @return the transactionService
	 */
	public TransactionServiceImpl getTransactionService() {
		return transactionService;
	}

	/**
	 * @param transactionService the transactionService to set
	 */
	public void setTransactionService(TransactionServiceImpl transactionService) {
		this.transactionService = transactionService;
	}
}
