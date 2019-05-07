package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;


public class Airport {
	public final static int GATES = 20;
	public final static String PATH_AIRLINES = "data\\airlines.txt";
	public final static String PATH_DESTINATIONS = "data\\destination.txt";
	private Flight first;
	private String[] airNames;
	private String[] destinations;
	private int gates;
	private int shownFlights;


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
	/** This method adds a new flight to the linked list
	 * 
	 */
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

	/** This method sorts the linked list by the flights Id
	 * 
	 */
	public void sortById() {
		if(first != null) {

			boolean changed = true;
			while(changed) {
				Flight current = first;
				changed = false;
				while(current.getNextF() != first) {
					Flight nextFlight = current.getNextF();

					if(current.compareTo(nextFlight)>0) {

						current.getPrevF().setNextF(nextFlight);
						nextFlight.getNextF().setPrevF(current);

						current.setNextF(nextFlight.getNextF());
						nextFlight.setPrevF(current.getPrevF());
						current.setPrevF(nextFlight);
						nextFlight.setNextF(current);


						if(current == first) {
							first = nextFlight;
						}

						changed = true;

					}else{
						current = current.getNextF();
					}
				}				
			}
		}

	}
	/** This method sorts the linked list by the flights Date
	 * 
	 */
	public void sortByDate() {
		if(first != null) {

			boolean changed = true;
			while(changed) {
				Flight current = first;
				changed = false;
				while(current.getNextF() != first) {
					Flight nextFlight = current.getNextF();

					if(current.getDate().compareTo(nextFlight.getDate())>0) {

						current.getPrevF().setNextF(nextFlight);
						nextFlight.getNextF().setPrevF(current);

						current.setNextF(nextFlight.getNextF());
						nextFlight.setPrevF(current.getPrevF());
						current.setPrevF(nextFlight);
						nextFlight.setNextF(current);


						if(current == first) {
							first = nextFlight;
						}

						changed = true;

					}else{
						current = current.getNextF();
					}
				}				
			}
		}
	}
	/** This method sorts the linked list by the flights Time
	 * 
	 */
	public void sortByTime() {
		if(first != null) {

			boolean changed = true;
			while(changed) {
				Flight current = first;
				changed = false;
				while(current.getNextF() != first) {
					Flight nextFlight = current.getNextF();

					if(current.getTime().compareTo(nextFlight.getTime())>0) {

						current.getPrevF().setNextF(nextFlight);
						nextFlight.getNextF().setPrevF(current);

						current.setNextF(nextFlight.getNextF());
						nextFlight.setPrevF(current.getPrevF());
						current.setPrevF(nextFlight);
						nextFlight.setNextF(current);


						if(current == first) {
							first = nextFlight;
						}

						changed = true;

					}else{
						current = current.getNextF();
					}
				}				
			}
		}
	}
	/** This method sorts the linked list by the flights Gate
	 * 
	 */
	public void sortByGate() {
		if(first != null) {

			boolean changed = true;
			while(changed) {
				Flight current = first;
				changed = false;
				while(current.getNextF() != first) {
					Flight nextFlight = current.getNextF();

					if(current.getGate() > nextFlight.getGate()) {

						current.getPrevF().setNextF(nextFlight);
						nextFlight.getNextF().setPrevF(current);

						current.setNextF(nextFlight.getNextF());
						nextFlight.setPrevF(current.getPrevF());
						current.setPrevF(nextFlight);
						nextFlight.setNextF(current);


						if(current == first) {
							first = nextFlight;
						}

						changed = true;

					}else{
						current = current.getNextF();
					}
				}				
			}
		}
	}
	/** This method sorts the linked list by the flights Airline
	 * 
	 */
	public void sortByAirline() {
		FlightAirlineComparator fac = new FlightAirlineComparator();
		if(first != null) {

			boolean changed = true;
			while(changed) {
				Flight current = first;
				changed = false;
				while(current.getNextF() != first) {
					Flight nextFlight = current.getNextF();

					if(fac.compare(current, nextFlight)>0) {

						current.getPrevF().setNextF(nextFlight);
						nextFlight.getNextF().setPrevF(current);

						current.setNextF(nextFlight.getNextF());
						nextFlight.setPrevF(current.getPrevF());
						current.setPrevF(nextFlight);
						nextFlight.setNextF(current);


						if(current == first) {
							first = nextFlight;
						}

						changed = true;

					}else{
						current = current.getNextF();
					}
				}				
			}
		}
	}
	/** This method sorts the linked list by the flights Destination
	 * 
	 */
	public void sortByDestination() {

		//Anonymous class
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

		if(first != null) {

			boolean changed = true;
			while(changed) {
				Flight current = first;
				changed = false;
				while(current.getNextF() != first) {
					Flight nextFlight = current.getNextF();

					if(FlightDestinationComparator.compare(current, nextFlight)>0) {

						current.getPrevF().setNextF(nextFlight);
						nextFlight.getNextF().setPrevF(current);

						current.setNextF(nextFlight.getNextF());
						nextFlight.setPrevF(current.getPrevF());
						current.setPrevF(nextFlight);
						nextFlight.setNextF(current);


						if(current == first) {
							first = nextFlight;
						}

						changed = true;

					}else{
						current = current.getNextF();
					}
				}				
			}
		}

	}

	public String time(double d) {
		String msg = "PM";
		if(d >= 100.0) {
			msg = "AM";
		}
		return msg;
	}
	/** This method searches for an specific flight that contains the specific Airline
	 * 
	 */
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
	/** This method searches for an specific flight that contains the specific Time
	 * 
	 */
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
	/** This method searches for an specific flight that contains the specific Id
	 * 
	 */
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
	/** This method searches for an specific flight that contains the specific Date
	 * 
	 */
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
	/** This method searches for an specific flight that contains the specific Destination
	 * 
	 */
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
	/** This method searches for an specific flight that contains the specific Gate
	 * 
	 */
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
	 * @return the shownFlights
	 */
	public int getShownFlights() {
		return shownFlights;
	}
}

