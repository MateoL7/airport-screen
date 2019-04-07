package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class AirportTest {
	
	private Airport ap;

	public void setupScenary1() {
		
	}
	
	public void setupScenary2() throws IOException {
		ap = new Airport(20);
	}
	
	@Test
	public void testTime() throws IOException {
		setupScenary2();
		double x = 100.0;
		String msg = ap.time(x);
		assertTrue("Not the right value", msg.equalsIgnoreCase("am"));
	}

}
