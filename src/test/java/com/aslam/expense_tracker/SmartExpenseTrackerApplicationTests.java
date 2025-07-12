package com.aslam.expense_tracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SmartExpenseTrackerApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Context loaded successfully.");
	}

}
