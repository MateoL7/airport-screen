package model;

public class Flight implements Comparable<Flight>{
	private String airline;
	private int hour;
	private int min;
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
	public Flight(String pAir, int pHour, int pMin, String pDest, int pGate, String pId, String pDate) {
		airline = pAir;
		hour = pHour;
		min = pMin;
		destination = pDest;
		gate = pGate;
		id = pId;
		date = pDate;
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
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}

	/** This method changes the value of the attribute with the value of the parameter
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/** This method returns the corresponding attribute
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/** This method changes the value of the attribute with the value of the parameter
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
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
	public String toString() {
		String report = " Time: " + hour + ":" + min;
		report += " Date: " + date;
		report += "Airline: " + airline;
		report += " Flight Number: " + id;
		report += " Destination: " + destination;
		report += " Gate: " + gate;
		return report;
		
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

}
