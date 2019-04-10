package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Airport {
	public final static int GATES = 20;
	public final static String PATH_AIRLINES = "data\\airlines.txt";
	public final static String PATH_DESTINATIONS = "data\\destination.txt";
	private Flight[] flights;
	private SortedBy sorted;
	private String[] airNames;
	private String[] destinations;
	private int gates;
	private int shownFlights;
	private int currentPageFlights;


	public Airport(int size) throws IOException {
		randomFlightList(size);
		shownFlights = 25;
		currentPageFlights = 0;

	}

	/**
	 * @return the destinations
	 */
	public String[] getDestinations() {
		return destinations;
	}

	/**
	 * @return the airNames
	 */
	public String[] getAirNames() {
		return airNames;
	}

	/** This method returns the corresponding attribute 
	 * @return the flights
	 */
	public Flight[] getFlights() {
		return flights;
	}

	/** This method returns the corresponding attribute
	 * @return the gates
	 */
	public int getGates() {
		return gates;
	}

	/** This method changes the value of the attribute with the value of the parameter
	 * @param gates the gates to set
	 */
	public void setGates(int gates) {
		this.gates = gates;
	}

	public SortedBy getSorted() {
		return sorted;
	}

	public String[] loadInfo(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String[] info = br.readLine().split(",");
		br.close();
		return info;
	}

	//Creating a flight list randomly
	public void randomFlightList(int size) throws IOException {
		flights = new Flight[size];
		destinations = loadInfo(PATH_DESTINATIONS);
		airNames = loadInfo(PATH_AIRLINES);
		for(int i = 0; i < flights.length; i++) {
			int rHour = (int) (Math.floor(Math.random()*12 + 1));
			if(rHour == 12) {
				rHour -= 1;
			}
			int rMin = (int) Math.floor(Math.random()*59);
			int rYear = (int) Math.floor(Math.random()*(Flight.MAX_YEAR-Flight.MIN_YEAR) + Flight.MIN_YEAR);
			int rMonth = (int) Math.floor(Math.random()*12 + 1);
			int rDay = (int) Math.floor(Math.random()*28 + 1);
			String rDate = rYear + "/" + rMonth + "/" + rDay;
			if(rMonth < 10) {
				rDate = rYear + "/" +  "0" + rMonth + "/" + rDay;
			}
			if(rDay < 10) {
				rDate = rYear + "/" +  rMonth + "/" +  "0" + rDay;
			}
			if(rMonth < 10 && rDay < 10) {
				rDate = rYear + "/" + "0" + rMonth + "/" +  "0" + rDay;
			}

			String ap = time(Math.floor(Math.random()*200.0));
			String rTime = (ap + " " + rHour + ":" + rMin);
			if(rHour < 10) {
				rTime = (ap + " " + "0" + rHour + ":" + rMin);
			}
			if(rMin < 10) {
				rTime = (ap + " " + rHour + ":" +  "0" + rMin);
			}
			if(rHour < 10 && rMin < 10) {
				rTime = (ap + " " + "0" + rHour + ":" +  "0" + rMin);
			}
			char fletter = (char) (Math.floor(Math.random()*(Flight.MAX_CODE-Flight.MIN_CODE) + Flight.MIN_CODE));
			char sletter = (char) (Math.floor(Math.random()*(Flight.MAX_CODE-Flight.MIN_CODE) + Flight.MIN_CODE));
			char tletter = (char) (Math.floor(Math.random()*(Flight.MAX_CODE-Flight.MIN_CODE) + Flight.MIN_CODE));
			String rId = "" + fletter + sletter + tletter + (int)Math.floor(Math.random()*900+100);
			int rGate = (int)Math.floor(Math.random()*GATES + 1);
			String rDestination = destinations[(int) Math.floor( Math.random()*destinations.length)];
			String rAirline = airNames[(int) Math.floor(Math.random()*airNames.length)];
			flights[i] = new Flight(rAirline, rDestination, rTime, rGate, rId, rDate);
		}
	}

	//Uses Comparable
	public void sortById() {
		Arrays.sort(flights);
		sorted = SortedBy.ID;
	}

	//Selection
	public void sortByDate() {
		for(int I = 0; I < flights.length-1; I++) {
			int minPos = I;
			String minDate = flights[I].getDate();
			for(int J = I+1; J < flights.length; J++) {
				String compareDate = flights[J].getDate();
				if(compareDate.compareTo(minDate) < 0) {
					minPos = J;
					minDate = compareDate;
				}
			}
			Flight tempFlight = flights[minPos];
			flights[minPos] = flights[I];
			flights[I] = tempFlight;
		}
		sorted = SortedBy.DATE;
	}

	//Bubble
	public void sortByTime() {
		Flight prevFlight;
		for(int i = 0; i < flights.length-1; i++) {
			for(int j = 0; j < flights.length-1; j++) {
				if(flights[j].getTime().compareTo(flights[j+1].getTime())>0) {
					prevFlight = flights[j];
					flights[j] = flights[j+1];
					flights[j+1] = prevFlight;
				}
			}
		}
		sorted = SortedBy.TIME;
	}

	//Insertion
	public void sortByGate() {
		for (int i = 1; i < flights.length; i++) {
			Flight ini = flights[i]; 
			int j;
			for (j = i-1; j >= 0 && flights[j].getGate() > ini.getGate(); j--){
				flights[j+1] = flights[j];
			}
			flights[j+1] = ini;
		}
		sorted = SortedBy.GATE;
	}

	//Using Comparator
	public void sortByAirline() {
		Arrays.sort(flights, new FlightAirlineComparator());
		sorted = SortedBy.AIRLINE;
	}
	public void sortByDestination() {

		Comparator<Flight> FlightDestinationComparator = new Comparator<Flight>() {

			@Override
			public int compare(Flight f1, Flight f2) {
				int comparation;
				String des1 = f1.getDestination();
				String des2 = f2.getDestination();

				if(des1.compareTo(des2)>0) {
					comparation = 1;
				}else if(des1.compareTo(des2)<0) {
					comparation = -1;
				}else {
					comparation = 0;
				}

				return comparation;
			}

		};

		Arrays.sort(flights, FlightDestinationComparator);
		sorted = SortedBy.DESTINATION;
	}

	public String time(double d) {
		String msg = "PM";
		if(d >= 100.0) {
			msg = "AM";
		}
		return msg;
	}
	//Sequence Search
	public Flight searchFlightAirline(String air) {
		boolean stop = false;
		Flight gotIt = null;
		for(int i = 0; i < flights.length && !stop; i++) {
			if(flights[i].getAirline().equalsIgnoreCase(air)) {
				gotIt = flights[i];
				stop = true;
			}
		}
		return gotIt;
	}
	public Flight searchFlightTime(String time) {
		boolean stop = false;
		Flight gotIt = null;
		for(int i = 0; i < flights.length && !stop; i++) {
			if(flights[i].getTime().equalsIgnoreCase(time)) {
				gotIt = flights[i];
				stop = true;
			}
		}
		return gotIt;
	}
	public Flight searchFlightId(String id) {
		boolean stop = false;
		Flight gotIt = null;
		for(int i = 0; i < flights.length && !stop; i++) {
			if(flights[i].getId().equalsIgnoreCase(id)) {
				gotIt = flights[i];
				stop = true;
			}
		}
		return gotIt;
	}
	public Flight searchFlightDate(String date) {
		boolean stop = false;
		Flight gotIt = null;
		for(int i = 0; i < flights.length && !stop; i++) {
			if(flights[i].getDate().equalsIgnoreCase(date)) {
				gotIt = flights[i];
				stop = true;
			}
		}
		return gotIt;
	}
	public Flight searchFlightDestination(String des) {
		boolean stop = false;
		Flight gotIt = null;
		for(int i = 0; i < flights.length && !stop; i++) {
			if(flights[i].getDestination().equalsIgnoreCase(des)) {
				gotIt = flights[i];
				stop = true;
			}
		}
		return gotIt;
	}
	//Binary Search
	public Flight searchFlightGate(int gate) {
		Flight gotIt = null;
		boolean keep = true;
		int low = 0;
		int high = flights.length-1;
		while(low <= high && keep) {
			int mid = (low+high)/2;
			if(flights[mid].getGate() < gate) {
				low = mid + 1;
			}
			else if(flights[mid].getGate() > gate) {
				high = mid - 1;
			}
			else{
				gotIt = flights[mid];
				keep = false;
			}
		}
		return gotIt;
	}

	/**
	 * @return the currentPageFlights
	 */
	public int getCurrentPageFlights() {
		return currentPageFlights;
	}

	/**
	 * @param currentPageFlights the currentPageFlights to set
	 */
	public void setCurrentPageFlights(int currentPageFlights) {
		this.currentPageFlights = currentPageFlights;
	}
	/**
	 * @return the shownFlights
	 */
	public int getShownFlights() {
		return shownFlights;
	}
}

