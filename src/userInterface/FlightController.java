package userInterface;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Airport;
import model.Flight;
import model.SortedBy;

public class FlightController {

	@FXML
	private Pane screen;

	@FXML
	private TableView<Flight> flightsTable;

	@FXML
	private TableColumn<Flight, String> timeCol;

	@FXML
	private TableColumn<Flight, String> dateCol;

	@FXML
	private TableColumn<Flight, String> airlineCol;

	@FXML
	private TableColumn<Flight, String> flightNumCol;

	@FXML
	private TableColumn<Flight, String> destinationCol;

	@FXML
	private TableColumn<Flight, String> gateCol;

	@FXML
	private Label message;

	@FXML
	private TextField searchField;

	private ObservableList<Flight> oFlights;

	private Airport ap;

	private Long time;
	private Long before;
	private Long after;

	public void initialize() throws IOException{
		ap = new Airport(20);
		ObservableList<Flight> oFlights = FXCollections.observableArrayList();
		updateList();
		timeCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		airlineCol.setCellValueFactory(new PropertyValueFactory<>("airline"));
		destinationCol.setCellValueFactory(new PropertyValueFactory<>("time"));
		flightNumCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		gateCol.setCellValueFactory(new PropertyValueFactory<>("gate"));
		flightsTable.setItems(oFlights);
	}

	@FXML
	public void createFlightList(ActionEvent event) throws IOException {
		try {
			int size = Integer.parseInt(searchField.getText());
			ap.randomFlightList(size);
			updateList();
			flightsTable.setItems(oFlights);
		}catch(NumberFormatException e) {
			message.setText("Please type the amount of flights");
		}
	}

	@FXML
	public void SortFlightNumber(ActionEvent event) {
		before = (System.currentTimeMillis());
		ap.sortById();
		after = (System.currentTimeMillis());
		time = (after-before);
		System.out.println("Time to sort by Flight number:" + time + " miliseconds");
		flightsTable.setItems(updateList());
	}

	@FXML
	public void sortDate(ActionEvent event) {
		before = (System.currentTimeMillis());
		ap.sortByDate();
		after = (System.currentTimeMillis());
		time = (after-before);
		System.out.println("Time to sort by Date:" + time + " miliseconds");
		flightsTable.setItems(updateList());
	}

	@FXML
	public void sortAirline(ActionEvent event) {
		before = (System.currentTimeMillis());
		ap.sortByAirline();
		after = (System.currentTimeMillis());
		time = (after-before);
		System.out.println("Time to sort by Airline:" + time + " miliseconds");
		flightsTable.setItems(updateList());
	}

	@FXML
	public void sortDestination(ActionEvent event) {
		before = (System.currentTimeMillis());
		ap.sortByDestination();
		after = (System.currentTimeMillis());
		time = (after-before);
		System.out.println("Time to sort by Time(Hour and Minutes):" + time + " miliseconds");
		flightsTable.setItems(updateList());
	}

	@FXML
	public void sortGate(ActionEvent event) {
		before = (System.currentTimeMillis());
		ap.sortByGate();
		after = (System.currentTimeMillis());
		time = (after-before);
		System.out.println("Time to sort by Gate:" + time + " miliseconds");
		flightsTable.setItems(updateList());
	}

	@FXML
	public void sortTime(ActionEvent event) {
		before = (System.currentTimeMillis());
		ap.sortByTime();
		after = (System.currentTimeMillis());
		time = (after-before);
		System.out.println("Time to sort by Destination: " + time + " miliseconds");
		flightsTable.setItems(updateList());
	}

	public ObservableList<Flight> updateList() {
		Flight[] correct = ap.getFlights();
		List<Flight> list = Arrays.asList(correct);
		return oFlights = FXCollections.observableArrayList(list);
	}

	@FXML
	public void searchAirline(ActionEvent event) {
		Flight show1 = ap.searchFlightAirline(searchField.getText());
		if(show1 == null) {
			message.setText("No flight found with that criteria.");
		}
		else {
			oFlights.clear();
			oFlights.add(show1);
			flightsTable.setItems(oFlights);
		}

	}

	@FXML
	public void searchDate(ActionEvent event) {
		Flight show1 = ap.searchFlightDate(searchField.getText());
		if(show1 == null) {
			message.setText("No flight found with that criteria.");
		}
		else {
			oFlights.clear();
			oFlights.add(show1);
			flightsTable.setItems(oFlights);
		}
	}

	@FXML
	public void searchDestination(ActionEvent event) {
		Flight show1 = ap.searchFlightDestination(searchField.getText());
		if(show1 == null) {
			message.setText("No flight found with that criteria.");
		}
		else {
			oFlights.clear();
			oFlights.add(show1);
			flightsTable.setItems(oFlights);
		}
	}

	@FXML
	public void searchGate(ActionEvent event) {
		Flight show1 = null; 
		if(ap.getSorted() == SortedBy.GATE) {
			show1 = ap.searchFlightGate((Integer.parseInt(searchField.getText())));
			if(show1 != null) {
				oFlights.clear();
				oFlights.add(show1);
				flightsTable.setItems(oFlights);
			}
			else {
				message.setText("No flight found with that criteria.");
			}
		}
		else{
			message.setText("First sort the list by gate, then try again.");
		}

	}

	@FXML
	public void searchId(ActionEvent event) {
		Flight show1 = ap.searchFlightId(searchField.getText());
		if(show1 == null) {
			message.setText("No flight found with that criteria.");
		}
		else {
			oFlights.clear();
			oFlights.add(show1);
			flightsTable.setItems(oFlights);
		}
	}

	@FXML
	public void searchTime(ActionEvent event) {
		Flight show1 = ap.searchFlightTime(searchField.getText());
		if(show1 == null) {
			message.setText("No flight found with that criteria.");
		}
		else {
			oFlights.clear();
			oFlights.add(show1);
			flightsTable.setItems(oFlights);
		}
	}

	public void setStage(Stage stage) {
		// TODO Auto-generated method stub

	}

}
