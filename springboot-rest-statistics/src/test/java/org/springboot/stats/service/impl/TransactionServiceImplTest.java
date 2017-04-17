package org.springboot.stats.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springboot.stats.domain.Statistics;
import org.springboot.stats.domain.TransactionData;
import org.springboot.stats.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for TransactionService
 * @author Prasad Paravatha
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceImplTest {
	@InjectMocks
	private TransactionServiceImpl transactionService;
	
	@Autowired
	private TransactionRepository repository;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);	
		transactionService = new TransactionServiceImpl();
		transactionService.setRepository(repository);
	}

	/**
	 * Test method for {@link org.springboot.stats.service.impl.TransactionServiceImpl#saveTransaction(org.springboot.stats.domain.TransactionData)}.
	 * @throws Exception 
	 */
	@Test
	public void testSaveTransaction() throws Exception {
		repository.deleteAll();
		TransactionData transaction1 = new TransactionData();
		transaction1.setAmount(12.3);
		transaction1.setTimestamp(System.currentTimeMillis());
		transactionService.saveTransaction(transaction1);
		
		TransactionData transaction2 = new TransactionData();
		transaction2.setAmount(12.7);
		transaction2.setTimestamp(System.currentTimeMillis());
		transactionService.saveTransaction(transaction2);
	}

	/**
	 * Test method for {@link org.springboot.stats.service.impl.TransactionServiceImpl#getStatistics()}.
	 * @throws Exception 
	 */
	@Test
	public void testGetStatistics() throws Exception {
		Statistics statistics = transactionService.getStatistics();
		Assert.assertNotNull(statistics);
		Assert.assertEquals("Sum is correct", 25.0, statistics.getSum(),  0);
		Assert.assertEquals("Avg is correct", 12.5, statistics.getAvg(), 0);
		Assert.assertEquals("Count is correct", 2, statistics.getCount());
		Assert.assertEquals("Max is correct", 12.7, statistics.getMax(), 0);
		Assert.assertEquals("Min is correct", 12.3, statistics.getMin(), 0);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
}
