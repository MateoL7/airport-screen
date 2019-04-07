package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FlightTest {
	
	private Flight f;
	private Flight f2;
	
	private String airline;
	private String time;
	private String destination;
	private int gate;
	private String id;
	private String date;

	public void setupScenary1(){
		airline = "Avianca";
		time = "AM 10:30";
		destination = "Colombia";
		gate = 3;
		id = "ABC123";
		date = "2010/10/10";
	}
	
	public void setupScenary2() {
		f2 = new Flight("Avianca", "AM 10:30", "Colombia", 3, "ABC124", "2010/10/10");
		f = new Flight("Avianca", "AM 10:30", "Colombia", 3, "ABC123", "2010/10/10");
	}
	
	@Test
	public void testFlight() {
		setupScenary1();
		f = new Flight(airline, time, destination, gate, id, date);
		assertNotNull("The flight is null", f);
		assertTrue("Not the right attribute", f.getAirline().equalsIgnoreCase("Avianca"));
		assertTrue("Not the right attribute", f.getDestination().equalsIgnoreCase("AM 10:30"));
		assertTrue("Not the right attribute", f.getTime().equalsIgnoreCase("colombia"));
		assertTrue("Not the right attribute", f.getGate() == 3);
		assertTrue("Not the right attribute", f.getId().equalsIgnoreCase("ABC123"));
		assertTrue("Not the right attribute", f.getDate().equalsIgnoreCase("2010/10/10"));
	}
	@Test
	public void testCompareTo() {
		setupScenary2();
		
		int num = f.compareTo(f2);
		assertTrue("Not comparing correctly", num < 0);
	}

}
