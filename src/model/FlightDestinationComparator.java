package model;

import java.util.Comparator;

public class FlightDestinationComparator implements Comparator<Flight> {

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
	
	

}
