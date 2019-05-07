package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Airport {
	public final static int GATES = 20;
	public final static String PATH_AIRLINES = "data\\airlines.txt";
	public final static String PATH_DESTINATIONS = "data\\destination.txt";
	//private Flight[] flights;
	private Flight first;
	private String[] airNames;
	private String[] destinations;
	private int gates;
	private int shownFlights;
	private int currentPageFlights;


	public Airport(int size) throws IOException {
		randomFlightList(size);
//		totalLength();

	}

	/**
	 * @return the first
	 */
	public Flight getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(Flight first) {
		this.first = first;
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


	public String[] loadInfo(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String[] info = br.readLine().split(",");
		br.close();
		return info;
	}

	//Creating a flight list randomly
	/** This method creates an especific amount of flights with random attributes 
	 * @param size the amount of flights to create
	 */
	public void randomFlightList(int size) throws IOException {
		//flights = new LinkedList<Flight>();
		int counter = 0;
		destinations = loadInfo(PATH_DESTINATIONS);
		airNames = loadInfo(PATH_AIRLINES);
		first = null;
		while(counter <= size) {
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
			addFlight(rAirline, rDestination, rTime, rGate, rId, rDate);
			counter++;
		}
	}

	public void addFlight(String rAirline, String rDestination, String rTime, int rGate, String rId, String rDate) {
		Flight f1 = new Flight(rAirline, rDestination, rTime, rGate, rId, rDate);
		if(first != null) {
			Flight last = first.getPrevF();
			last.setNextF(f1);
			first.setPrevF(f1);
			f1.setNextF(first);
			f1.setPrevF(last);
		} else {
			first = f1;
			first.setNextF(f1);
			first.setPrevF(f1);
		}
	}
	
//	public int totalLength() {
//		Flight current = first;
//		int sum = 0;
//		while(current.getNextF() != first) {
//			sum++;
//		}
//		return sum;
//	}

	//	//Uses Comparable
//	public void sortById() {
//	//		Arrays.sort(flights);
//		if(first != null) {
//			int counter = 0;
//			boolean changed = true;
//			
//			while(changed) {
//				changed = false; 
//				Flight current = first;
//				//Flight minF = first;
//				//while(current.getNextF() != minF)
//				while(counter <= totalLength()) {
//					Flight nextFlight = current.getNextF();
//					if(current.getId().compareTo(nextFlight.getId()) > 0) {
//					//	minF = nextFlight;
//						//Save the next and the prev flight or current
//						Flight currentsNext = current.getNextF();
//						Flight currentsPrev = current.getPrevF();
//						
//						//Exchange conections for current
//						current.setNextF(nextFlight.getNextF());
//						current.setPrevF(nextFlight.getPrevF());
//						
//						//Exchange conections for nextFlight
//						nextFlight.setNextF(currentsNext);
//						nextFlight.setPrevF(currentsPrev);
//						
//						//Condition worked
//						changed = true;
//					} else {
//						current = current.getNextF();
//					}
//				}
//			}
//			
//		}
//	//		sorted = SortedBy.ID;
//	}
	//
	//	//Selection
//		public void sortByDate() {
//	//		for(int I = 0; I < flights.length-1; I++) {
//	//			int minPos = I;
//	//			String minDate = flights[I].getDate();
//	//			for(int J = I+1; J < flights.length; J++) {
//	//				String compareDate = flights[J].getDate();
//	//				if(compareDate.compareTo(minDate) < 0) {
//	//					minPos = J;
//	//					minDate = compareDate;
//	//				}
//	//			}
//	//			Flight tempFlight = flights[minPos];
//	//			flights[minPos] = flights[I];
//	//			flights[I] = tempFlight;
//	//		}
//	//		sorted = SortedBy.DATE;
//			
//			
////			int counter = 0;
////			while(current.getNextF()!= first) {
////				counter++;
////			}
//			for(Flight current = first; current != first; current = current.getNextF()) {
//				Flight min = current;
//				String minDate = current.getDate();
//				//for(; times2 <= counter; times2++) {
//					if(current.getNextF().getDate().compareTo(minDate) < 0) {
//						min = current.getNextF();
//						minDate = current.getNextF().getDate();
//					}
//				//}
//				//Flight temp = min;
//				Flight nextMin = min.getNextF();
//				Flight prevMin = min.getPrevF();
//				
//				min.setNextF(first);
//				min.setPrevF(first.getPrevF());
//				
//				current.setNextF(nextMin);
//				current.setPrevF(prevMin);
//				
//			}
//		}
	//
		//Bubble
//		public void sortByTime() {
//			Flight prevFlight;
//			//Flight nextFlight;
//			Flight exchangeN;
//			
//			Flight current = first;
//			int counter = 0;
//			int times = 0;
//			while(current.getNextF() != first) {
//				counter++;
//			}
//			while(times <= counter) {
//				if(current.getTime().compareTo(current.getNextF().getTime()) > 0) {
//					times++;
//					exchangeN = current.getNextF();
//					prevFlight = current.getPrevF();
//					//nextFlight = current.getNextF();
//					//Flight next2Flight = current.getNextF().getNextF();
//					
//					current.setNextF(exchangeN);
//					current.setPrevF(exchangeN.getPrevF());
//					
//					exchangeN.setNextF(current);
//					exchangeN.setPrevF(prevFlight);
//					
//					current = current.getNextF();
//				}
//				times++;
//			}
//			sorted = SortedBy.TIME;
//		}
	//
	//	//Insertion
	//	public void sortByGate() {
	//		for (int i = 1; i < flights.length; i++) {
	//			Flight ini = flights[i]; 
	//			int j;
	//			for (j = i-1; j >= 0 && flights[j].getGate() > ini.getGate(); j--){
	//				flights[j+1] = flights[j];
	//			}
	//			flights[j+1] = ini;
	//		}
	//		sorted = SortedBy.GATE;
	//	}
	//
	//	//Using Comparator
	//	public void sortByAirline() {
	//		Arrays.sort(flights, new FlightAirlineComparator());
	//		sorted = SortedBy.AIRLINE;
	//	}
	//	public void sortByDestination() {
	//
	//		Comparator<Flight> FlightDestinationComparator = new Comparator<Flight>() {
	//
	//			@Override
	//			public int compare(Flight f1, Flight f2) {
	//				int comparation;
	//				String des1 = f1.getDestination();
	//				String des2 = f2.getDestination();
	//
	//				if(des1.compareTo(des2)>0) {
	//					comparation = 1;
	//				}else if(des1.compareTo(des2)<0) {
	//					comparation = -1;
	//				}else {
	//					comparation = 0;
	//				}
	//
	//				return comparation;
	//			}
	//
	//		};
	//
	//		Arrays.sort(flights, FlightDestinationComparator);
	//		sorted = SortedBy.DESTINATION;
	//	}

	public String time(double d) {
		String msg = "PM";
		if(d >= 100.0) {
			msg = "AM";
		}
		return msg;
	}
	//	Search
	public Flight searchFlightAirline(String air) {
		boolean stop = false;
		Flight current = first;
		Flight gotIt = null;
		while(current.getNextF() != first && !stop) {
			if(current.getAirline().equalsIgnoreCase(air)) {
				gotIt = current;
				stop = true;
			}
			current = current.getNextF();
		}

		return gotIt;
	}
	public Flight searchFlightTime(String time) {
		boolean stop = false;
		Flight current = first;
		Flight gotIt = null;
		while(current.getNextF() != first && !stop) {
			if(current.getTime().equalsIgnoreCase(time)) {
				gotIt = current;
				stop = true;
			}
			current = current.getNextF();
		}

		return gotIt;
	}
	public Flight searchFlightId(String id) {
		boolean stop = false;
		Flight current = first;
		Flight gotIt = null;
		while(current.getNextF() != first && !stop) {
			if(current.getId().equalsIgnoreCase(id)) {
				gotIt = current;
				stop = true;
			}
			current = current.getNextF();
		}

		return gotIt;
	}
	public Flight searchFlightDate(String date) {
		boolean stop = false;
		Flight current = first;
		Flight gotIt = null;
		while(current.getNextF() != first && !stop) {
			if(current.getDate().equalsIgnoreCase(date)) {
				gotIt = current;
				stop = true;
			}
			current = current.getNextF();
		}

		return gotIt;
	}
	public Flight searchFlightDestination(String des) {
		boolean stop = false;
		Flight current = first;
		Flight gotIt = null;
		while(current.getNextF() != first && !stop) {
			if(current.getDestination().equalsIgnoreCase(des)) {
				gotIt = current;
				stop = true;
			}
			current = current.getNextF();
		}

		return gotIt;
	}
	public Flight searchFlightGate(int gate) {
		boolean stop = false;
		Flight current = first;
		Flight gotIt = null;
		while(current.getNextF() != first && !stop) {
			if(current.getGate() == gate) {
				gotIt = current;
				stop = true;
			}
			current = current.getNextF();
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

