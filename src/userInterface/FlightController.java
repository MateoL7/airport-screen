package userInterface;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Airport;
import model.Flight;

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
    
    private ObservableList<Flight> oFlights;
    
    private Airport ap;
    
    public void initialize(){
    	ap = new Airport();
    	timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
    	dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    	airlineCol.setCellValueFactory(new PropertyValueFactory<>("airline"));
    	flightNumCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    	gateCol.setCellValueFactory(new PropertyValueFactory<>("gate"));
    	flightsTable.setItems(oFlights);
    }
    
    @FXML
    void createFlightList(ActionEvent event) {
    	int size = Integer.parseInt(JOptionPane.showInputDialog(""));
    	ap.randomFlightList(size);
    	updateList();
    	flightsTable.setItems(oFlights);
    	ap.reportFlights();
    	
    }
    
    @FXML
    void SortFlightNumber(ActionEvent event) {
    	ap.sortById();
    	ap.reportFlights();
    }

    @FXML
    void sortDate(ActionEvent event) {
    	ap.sortByDate();
    	ap.reportFlights();
    }
    
    @FXML
    void sortAirline(ActionEvent event) {
    	ap.sortByAirline();
    	ap.reportFlights();
    }

    @FXML
    void sortDestination(ActionEvent event) {
    	ap.sortByDestination();
    	ap.reportFlights();
    }

    @FXML
    void sortGate(ActionEvent event) {
    	oFlights.clear();
    	ap.sortByGate();
    	updateList();
    	ap.reportFlights();
    }
    
    public void updateList() {
    	
    	for(int i = 0; i < ap.getFlights().length; i++) {
    		oFlights.add(ap.getFlights()[i]);
    	}
    }

	public void setStage(Stage stage) {
		// TODO Auto-generated method stub
		
	}

}
