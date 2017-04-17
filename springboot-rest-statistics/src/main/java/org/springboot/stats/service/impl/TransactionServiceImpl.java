package org.springboot.stats.service.impl;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.stats.domain.Statistics;
import org.springboot.stats.domain.TransactionData;
import org.springboot.stats.repository.TransactionRepository;
import org.springboot.stats.service.TransactionService;
import org.springframework.stereotype.Service;

/**
 * Implementation for TransactionService operations
 * @author Prasad Paravatha
 */
@Service
public class TransactionServiceImpl implements TransactionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

	@Resource
	TransactionRepository repository;
	
	@Override
	public void saveTransaction(TransactionData transactionData) throws Exception {
		LOGGER.debug("in saveTransaction : ");
		repository.save(transactionData);
		
	}

	@Override
	public Statistics getStatistics() throws Exception {
		LOGGER.debug("in getStatistics : ");
		
		long currentTimeinMillis = System.currentTimeMillis();
		List<TransactionData> transactions = repository.findTransactions(currentTimeinMillis - 60000);
		
		DoubleSummaryStatistics summaryStatistics = transactions.stream()
			     .collect(Collectors.summarizingDouble(TransactionData::getAmount));
		
		Statistics statistics = new Statistics();
		statistics.setSum(summaryStatistics.getSum());
		statistics.setAvg(summaryStatistics.getAverage());
		statistics.setCount(summaryStatistics.getCount());
		if (Double.isInfinite(summaryStatistics.getMax()))
			statistics.setMax(0.0);
		else
			statistics.setMax(summaryStatistics.getMax());
		if (Double.isInfinite(summaryStatistics.getMin()))
			statistics.setMin(0.0);
		else
			statistics.setMin(summaryStatistics.getMin());
		
		return statistics;
	}
	
	@Override
	public List<TransactionData> getTransactions() throws Exception {
		return (List<TransactionData>) repository.findAll();
	}
	
	/**
	 * @return the repository
	 */
	public TransactionRepository getRepository() {
		return repository;
	}
	/**
	 * @param repository the repository to set
	 */
	public void setRepository(TransactionRepository repository) {
		this.repository = repository;
	}
}
