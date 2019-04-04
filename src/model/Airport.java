package model;

import java.util.Arrays;

public class Airport {
	public final static int GATES = 7;
	private Flight[] flights;
	private String[] airNames;
	private String[] dates;
	private String[] destinations;
	private String[] ids;
	private int gates;

	public Airport() {
		gates = GATES;
		airNames = new String[] {"Avianca","Iberia","Viva Colombia", "Air France", "American Airlines", "Air Europa", 
					"Air Berlin", "British Airways", "Brussels Airlines", "SAS"};
		destinations = new String[] {"Aruba","Cancun","Bogota", "Paris", "Barcelona", "Madrid", 
				"Rome", "Florence", "San Andres", "Moscu"};
		dates = new String[] {"2015/03/15","2016/10/20","2018/12/11", "2019/11/10", "2019/11/12", "2019/10/10", 
				"2014/11/09", "2019/09/12", "2019/10/15", "2150/10/10"};
		ids = new String[] {"ABC","AZX","BWE", "ASW", "AWR", "POS", 
				"LHC", "TUY", "LSE", "KMW"};
	
		
	}

	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * @return the dates
	 */
	public String[] getDates() {
		return dates;
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

	//Creating a flight list randomly
	public void randomFlightList(int size) {
		flights = new Flight[size];
		for(int i = 0; i < flights.length; i++) {
			int rHour = (int) (Math.random()*12 + 1);
			int rMin = (int) Math.random()*59 + 1;
			int rGate = (int) Math.random()*7 + 1;
			String rDate = dates[(int) Math.random()*dates.length];
			String rDestination = destinations[(int) Math.random()*destinations.length];
			String rAirline = airNames[(int) Math.random()*airNames.length];
			String rId = ids[(int) Math.random()*ids.length] + (int) Math.random()*500+100;
			flights[i] = new Flight(rAirline, rHour, rMin, rDestination, rGate, rId, rDate);
		}
	}

	//Uses Comparable
	public void sortById() {
		Arrays.sort(flights);
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
	}

	//Bubble
	public void sortByTime() {
		Flight prevFlight;
		for(int i = 0; i < flights.length-1; i++) {
			for(int j = 0; j < flights.length-1; j++) {
				if(flights[j].getHour() >= flights[j+1].getHour()) {
					if(flights[j].getMin() > flights[j+1].getMin()) {
						prevFlight = flights[j];
						flights[j] = flights[j+1];
						flights[j+1] = prevFlight;
					}
				}
			}
		}
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
	}

	//Using Comparator
	public void sortByAirline() {
		Arrays.sort(flights, new FlightAirlineComparator());
	}
	public void sortByDestination() {
		Arrays.sort(flights, new FlightDestinationComparator());
	}

	/** This method generates a message with all of the information for each flight
	 * @return String with the message
	 */
	public String reportFlights() {
		String msg = "/n";
		for(int d = 0; d < flights.length; d++) {
			msg += "/n" + flights[d];
		}
		return msg;
	}
}

