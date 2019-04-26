//package model;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.IOException;
//
//import org.junit.jupiter.api.Test;
//
//class AirportTest {
//
//	private Airport ap;
//
//	public void setupScenary1() {
//
//	}
//
//	public void setupScenary2() throws IOException {
//		ap = new Airport(20);
//	}
//
//	public void setupScenary3() throws IOException {
//		ap.randomFlightList(10);
//	}
//
//	@Test
//	public void testTime() throws IOException {
//		setupScenary2();
//		double x = 100.0;
//		String msg = ap.time(x);
//		assertTrue("Not the right value", msg.equalsIgnoreCase("am"));
//	}
//
//	@Test
//	public void testSortByGate() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		ap.sortByGate();
//		for(int i = 0; i < ap.getFlights().length-1; i++) {
//			assertTrue("Not the right place", ap.getFlights()[i].getGate() <= ap.getFlights()[i+1].getGate());
//		}
//	}
//	@Test
//	public void testSortByDate() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		ap.sortByDate();
//		for(int i = 0; i < ap.getFlights().length-1; i++) {
//			assertTrue("Not the right place", ap.getFlights()[i].getDate().compareTo(ap.getFlights()[i+1].getDate()) <= 0);
//		}
//	}
//	@Test
//	public void testSortById() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		ap.sortById();
//		for(int i = 0; i < ap.getFlights().length-1; i++) {
//			assertTrue("Not the right place", ap.getFlights()[i].getId().compareTo(ap.getFlights()[i+1].getId()) <= 0);
//		}
//	}
//	@Test
//	public void testSortByDestination() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		ap.sortByDestination();
//		for(int i = 0; i < ap.getFlights().length-1; i++) {
//			assertTrue("Not the right place", ap.getFlights()[i].getDestination().compareTo(ap.getFlights()[i+1].getDestination()) <= 0);
//		}
//	}
//	@Test
//	public void testSortByAirline() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		ap.sortByAirline();
//		for(int i = 0; i < ap.getFlights().length-1; i++) {
//			assertTrue("Not the right place", ap.getFlights()[i].getAirline().compareTo(ap.getFlights()[i+1].getAirline()) <= 0);
//		}
//	}
//	@Test
//	public void testSortByTime() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		ap.sortByTime();
//		for(int i = 0; i < ap.getFlights().length-1; i++) {
//			assertTrue("Not the right place", ap.getFlights()[i].getTime().compareTo(ap.getFlights()[i+1].getTime()) <= 0);
//		}
//	}
//	@Test
//	public void testSearchFlightTime() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		String time = "AM 7:00";
//		Flight x = ap.searchFlightTime(time);
//		if(x != null) {
//		assertTrue("Not working", x.getTime().equalsIgnoreCase(time));
//		}
//	}
//	@Test
//	public void testSearchFlightDate() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		String date = "2010/11/11";
//		Flight x = ap.searchFlightTime(date);
//		if(x != null) {
//		assertTrue("Not working", x.getTime().equalsIgnoreCase(date));
//		}
//	}
//	@Test
//	public void testSearchFlightAirline() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		String search = "Avianca";
//		Flight x = ap.searchFlightTime(search);
//		if(x != null) {
//		assertTrue("Not working", x.getTime().equalsIgnoreCase(search));
//		}
//	}
//	@Test
//	public void testSearchFlightId() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		String search = "ABC123";
//		Flight x = ap.searchFlightTime(search);
//		if(x != null) {
//		assertTrue("Not working", x.getTime().equalsIgnoreCase(search));
//		}
//	}
//	@Test
//	public void testSearchFlightDestination() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		String search = "Aruba";
//		Flight x = ap.searchFlightTime(search);
//		if(x != null) {
//		assertTrue("Not working", x.getTime().equalsIgnoreCase(search));
//		}
//	}
//	@Test
//	public void testSearchFlightGate() throws IOException {
//		setupScenary2();
//		setupScenary3();
//		int gate = 1;
//		Flight x = ap.searchFlightGate(gate);
//		if(x != null) {
//		assertTrue("Not working", x.getGate() == gate);
//		}
//	}
//
//}
