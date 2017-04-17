package org.springboot.stats.repository;
import java.util.List;

import org.springboot.stats.domain.TransactionData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * In memory repository with custom queries
 * @author Prasad Paravatha
 */
@Repository
public interface TransactionRepository extends CrudRepository<TransactionData, Long>{
	
	@Query("select transaction from TransactionData transaction where transaction.timestamp >= ?1")
	public List<TransactionData> findTransactions(long timeframe);

}
