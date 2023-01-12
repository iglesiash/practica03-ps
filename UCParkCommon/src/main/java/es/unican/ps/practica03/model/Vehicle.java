package es.unican.ps.practica03.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Vehicle implements Serializable {

	private static final double PRICE_PER_MINUTE = 0.01; // 1 cent per minute

	@Id 
	private String numberPlate;
	private String brand;
	private String model;

	@Transient // Not persisted
	private Parking activeParking;

	@OneToMany
	private Stack<Parking> parkingHistory;

	@OneToMany
	private List<Report> currentReports;

	@ManyToOne @JoinColumn(name="owner_fk")
	private User owner;
	
	@Column(name = "active")
	private boolean isParkingActive;

	/**
	 * Default constructor.
	 */
	public Vehicle () {}

	/**
	 * Constructor.
	 * @param numberPlate: the vehicle's number plate
	 * @param brand: the vehicle's brand
	 * @param model: the vehicle's model
	 */
	public Vehicle(String numberPlate, String brand, String model) {
		this.numberPlate = numberPlate;
		this.brand = brand;
		this.model = model;

		activeParking = null;
		parkingHistory = new Stack<>();
		currentReports = new LinkedList<>();
		isParkingActive = false;
	}

	/**
	 * Returns the vehicle's number plate.
	 * @return the vehicle's number plate
	 */
	public String getNumberPlate() {
		return numberPlate;
	}

	/**
	 * Sets the vehicle's number plate.
	 * @param numberPlate: the vehicle's number plate
	 */
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	/**
	 * Returns the vehicle's brand.
	 * @return the vehicle's brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the vehicle's brand.
	 * @param brand: the vehicle's brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Returns the vehicle's model.
	 * @return the vehicle's model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the vehicle's model.
	 * @param model: the vehicle's model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Returns the vehicle's active parking.
	 * @return the vehicle's active parking
	 */
	public Parking getActiveParking() {
		return activeParking;
	}

	/**
	 * Sets the vehicle's active parking.
	 * @param activeParking: the vehicle's active parking
	 */
	public void setActiveParking(Parking activeParking) {
		this.activeParking = activeParking;
	}

	/**
	 * Returns the vehicle's owner.
	 * @return the vehicle's owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * Sets the vehicle's owner.
	 * @param owner: the vehicle's owner
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * Returns the vehicle's parking history.
	 * @return the vehicle's parking history
	 */
	public Stack<Parking> getParkingHistory() {
		return parkingHistory;
	}

	/**
	 * Sets the vehicle's parking history.
	 * @param parkingHistory: the vehicle's parking history
	 */
	public void setParkingHistory(Stack<Parking> parkingHistory) {
		this.parkingHistory = parkingHistory;
	}

	/**
	 * Returns the vehicle's current reports.
	 * @return the vehicle's current reports
	 */
	public List<Report> getCurrentReports() {
		return currentReports;
	}

	/**
	 * Sets the vehicle's current reports.
	 * @param currentReports: the vehicle's current reports
	 */
	public void setCurrentReports(List<Report> currentReports) {
		this.currentReports = currentReports;
	}

	/**
	 * Returns the vehicle's parking status.
	 * @return the vehicle's parking status
	 */
	public boolean isParkingActive() {
		return isParkingActive;
	}

	/**
	 * Sets the vehicle's parking status.
	 * @param isParkingActive: the vehicle's parking status
	 */
	public boolean addParking(Parking parking) {
		double price = parking.getMinutes() * PRICE_PER_MINUTE;
		
		// If the owner can be charged, the parking can be set
		if (owner.charge(price)) {
			parking.setPrice(price);
			activeParking = parking;
			isParkingActive = true;
			return true;
		}
		return false;
	}

	/**
	 * Finishes the vehicle's active parking.
	 * @param parking: the vehicle's active parking
	 */
	public void finishParking() {
		parkingHistory.add(activeParking);
		activeParking = null;
	}

	/**
	 * Adds a report to the vehicle's current reports.
	 * @param report: the report to be added
	 * @return true if the report was added, false otherwise
	 */
	public boolean addReport(Report report) {
		return currentReports.add(report);
	}

	/**
	 * Removes a report from the vehicle's current reports.
	 * @param report: the report to be removed
	 * @return true if the report was removed, false otherwise
	 */
	public boolean removeReport(Report report) {
		return currentReports.remove(report);
	}

	/**
	 * Edits the vehicle's active parking.
	 * @param minutes: the minutes to be added to the active parking
	 * @return true if the parking was edited, false otherwise
	 */
	public boolean editParking(int minutes) {
		int currentMinutes = activeParking.getMinutes();
		activeParking.setMinutes(minutes + currentMinutes);

		return owner.charge(minutes * PRICE_PER_MINUTE); // Only charges the new minutes
	}


	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof Vehicle)) {
			return false;
		}

		Vehicle vehicle = (Vehicle) o;
		return vehicle.getNumberPlate().equals(numberPlate);
	}
}
