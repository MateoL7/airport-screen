package model;

public class Flight implements Comparable<Flight>{
	public final static int MAX_YEAR = 2020;
	public final static int MIN_YEAR = 2010;
	public final static int MAX_CODE = 90;
	public final static int MIN_CODE = 65;
	private String airline;
	private String time;
	private String destination;
	private int gate;
	private String id;
	private String date; //		Year/Month/Day
	
	/**
	 * @param pAir
	 * @param pHour
	 * @param pMin
	 * @param pDest
	 * @param pGate
	 * @param pId
	 * @param pDate
	 */
	public Flight(String pAir, String pDest, String pTime, int pGate, String pId, String pDate) {
		airline = pAir;
		destination = pDest;
		time = pTime;
		gate = pGate;
		id = pId;
		date = pDate;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/** This method returns the corresponding attribute
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/** This method changes the value of the attribute with the value of the parameter
	 * @param airline the airline to set
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}

	/** This method returns the corresponding attribute
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/** This method changes the value of the attribute with the value of the parameter
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/** This method returns the corresponding attribute
	 * @return the gate
	 */
	public int getGate() {
		return gate;
	}

	/** This method changes the value of the attribute with the value of the parameter
	 * @param gate the gate to set
	 */
	public void setGate(int gate) {
		this.gate = gate;
	}

	/** This method returns the corresponding attribute
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/** This method changes the value of the attribute with the value of the parameter
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	/** This method returns the corresponding attribute
	 * @return the date
	 */
	public String getDate() {
		return date;
	}



	/** This method changes the value of the attribute with the value of the parameter
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int compareTo(Flight other) {
		int comparation = 0;
		if(id.compareTo(other.id) > 0) {
			comparation = 1;
		}
		else if(id.compareTo(other.id) < 0){
			comparation = -1;
		}
		return comparation;
	}
	
	@Override
	public String toString() {
		return "" + airline + 
		time + 
		 destination +
		 gate +
		 id + 
		 date;
	}
}
