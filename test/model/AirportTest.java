package model;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class AirportTest {

	private Airport ap;

	public void setupScenary1() {

	}

	public void setupScenary2() throws IOException {
		ap = new Airport(20);
	}

	public void setupScenary3() throws IOException {
		ap.randomFlightList(10);
	}

	@Test
	public void testTime() throws IOException {
		setupScenary2();
		double x = 100.0;
		String msg = ap.time(x);
		assertTrue("Not the right value", msg.equalsIgnoreCase("am"));
	}

	@Test
	public void testSortByGate() throws IOException {
		setupScenary2();
		setupScenary3();
		ap.sortByGate();
		Flight current = ap.getFirst();
		do {
			assertTrue("Not the right place", current.getGate() <= current.getNextF().getGate() );
			current = current.getNextF();
		} while(current.getNextF() != ap.getFirst());
	}
	@Test
	public void testSortByDate() throws IOException {
		setupScenary2();
		setupScenary3();
		ap.sortByDate();
		Flight current = ap.getFirst();
		do {
			assertTrue("Not the right place", current.getDate().compareTo(current.getNextF().getDate()) <= 0);
			current = current.getNextF();
		} while(current.getNextF() != ap.getFirst());
	}
	@Test
	public void testSortById() throws IOException {
		setupScenary2();
		setupScenary3();
		ap.sortById();
		Flight current = ap.getFirst();
		do {
			assertTrue("Not the right place", current.getId().compareTo(current.getNextF().getId()) <= 0);
			current = current.getNextF();
		} while(current.getNextF() != ap.getFirst());
	}
	@Test
	public void testSortByDestination() throws IOException {
		setupScenary2();
		setupScenary3();
		ap.sortByDestination();
		Flight current = ap.getFirst();
		do {
			assertTrue("Not the right place", current.getDestination().compareTo(current.getNextF().getDestination()) <= 0);
			current = current.getNextF();
		} while(current.getNextF() != ap.getFirst());
	}
	@Test
	public void testSortByAirline() throws IOException {
		setupScenary2();
		setupScenary3();
		ap.sortByAirline();
		Flight current = ap.getFirst();
		do {
			assertTrue("Not the right place", current.getAirline().compareTo(current.getNextF().getAirline()) <= 0);
			current = current.getNextF();
		} while(current.getNextF() != ap.getFirst());
	}
	@Test
	public void testSortByTime() throws IOException {
		setupScenary2();
		setupScenary3();
		ap.sortByTime();
		Flight current = ap.getFirst();
		do {
			assertTrue("Not the right place", current.getTime().compareTo(current.getNextF().getTime()) <= 0);
			current = current.getNextF();
		} while(current.getNextF() != ap.getFirst());
	}
	@Test
	public void testSearchFlightTime() throws IOException {
		setupScenary2();
		setupScenary3();
		String time = "AM 7:00";
		Flight x = ap.searchFlightTime(time);
		if(x != null) {
		assertTrue("Not working", x.getTime().equalsIgnoreCase(time));
		}
	}
	@Test
	public void testSearchFlightDate() throws IOException {
		setupScenary2();
		setupScenary3();
		String date = "2010/11/11";
		Flight x = ap.searchFlightTime(date);
		if(x != null) {
		assertTrue("Not working", x.getTime().equalsIgnoreCase(date));
		}
	}
	@Test
	public void testSearchFlightAirline() throws IOException {
		setupScenary2();
		setupScenary3();
		String search = "Avianca";
		Flight x = ap.searchFlightTime(search);
		if(x != null) {
		assertTrue("Not working", x.getTime().equalsIgnoreCase(search));
		}
	}
	@Test
	public void testSearchFlightId() throws IOException {
		setupScenary2();
		setupScenary3();
		String search = "ABC123";
		Flight x = ap.searchFlightTime(search);
		if(x != null) {
		assertTrue("Not working", x.getTime().equalsIgnoreCase(search));
		}
	}
	@Test
	public void testSearchFlightDestination() throws IOException {
		setupScenary2();
		setupScenary3();
		String search = "Aruba";
		Flight x = ap.searchFlightTime(search);
		if(x != null) {
		assertTrue("Not working", x.getTime().equalsIgnoreCase(search));
		}
	}
	@Test
	public void testSearchFlightGate() throws IOException {
		setupScenary2();
		setupScenary3();
		int gate = 1;
		Flight x = ap.searchFlightGate(gate);
		if(x != null) {
		assertTrue("Not working", x.getGate() == gate);
		}
	}

}
