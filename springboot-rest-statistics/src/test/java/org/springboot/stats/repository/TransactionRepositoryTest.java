package org.springboot.stats.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.stats.domain.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test class for In Memory database
 * @author Prasad Paravatha
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionRepositoryTest {
	
	@Autowired
	private TransactionRepository repository;
	
	private TransactionData transactionData;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception { 
		transactionData = new TransactionData();
		long currentTimeinMillis = System.currentTimeMillis();
		transactionData.setAmount(12.3);
		transactionData.setTimestamp(currentTimeinMillis);
	}

	/**
	 * Test method for {@link org.springframework.data.repository.CrudRepository#save(S)}.
	 */
	@Test
	public void testSaveS() {
		repository.save(transactionData);
	}
	
	/**
	 * Test method for {@link org.springboot.stats.repository.TransactionRepository#findTransactions()}.
	 */
	@Test
	public void testFindTransactions() {
		long currentTimeinMillis = System.currentTimeMillis();

		List<TransactionData> transactions = repository.findTransactions(currentTimeinMillis - 60000);
		Assert.assertEquals("Amount is correct", transactionData.getAmount(),  transactions.get(0).getAmount(), 0);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
}
