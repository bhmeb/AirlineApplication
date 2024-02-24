package com.airline.AirlineApplicationDemo;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
class AirlineApplicationDemoApplicationTests extends TestCase {

	@InjectMocks
	AirlineApplicationDemoApplication application;

	@Test
	void contextLoads() {
	}

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	public static TestSuite suit(){
		return new TestSuite(AirlineApplicationDemoApplicationTests.class);
	}

	public void testApp(){
		assertTrue(true);
	}
}
