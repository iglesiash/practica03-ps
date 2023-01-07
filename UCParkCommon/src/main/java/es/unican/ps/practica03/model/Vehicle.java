package es.unican.ps.practica03.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
	
	@Id private String numberPlate;
	private String brand;
	private String model;
	
	@Transient // Not persisted
	private Parking activeParking;
	
	@OneToMany @JoinColumn(name="owner")
	private Stack<Parking> parkingHistory;
	
	@OneToMany @JoinColumn(name="vehicle")
	private List<Report> currentReports;
	
	@ManyToOne @JoinColumn(name="email")
	private User owner;
	
	public Vehicle () {}
	
	public Vehicle(String matricula, String marca, String modelo) {
		this.numberPlate = matricula;
		this.brand = marca;
		this.model = modelo;
		
		activeParking = null;
		parkingHistory = new Stack<>();
		currentReports = new LinkedList<>();
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Parking getActiveParking() {
		return activeParking;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setActiveParking(Parking activeParking) {
		this.activeParking = activeParking;
	}

	public Stack<Parking> getParkingHistory() {
		return parkingHistory;
	}

	public void setParkingHistory(Stack<Parking> parkingHistory) {
		this.parkingHistory = parkingHistory;
	}

	public List<Report> getCurrentReports() {
		return currentReports;
	}

	public void setCurrentReports(List<Report> currentReports) {
		this.currentReports = currentReports;
	}

	public boolean addParking(Parking parking) {
		activeParking = parking;
		activeParking.setPrice(parking.getMinutes() * PRICE_PER_MINUTE);
		return owner.charge(parking.getPrice());
	}

	public void finishParking() {
		parkingHistory.add(activeParking);
		activeParking = null;
	}

	public boolean addReport(Report report) {
		return currentReports.add(report);
	}

	public boolean removeReport(Report report) {
		return currentReports.remove(report);
	}

	public boolean editParking(int minutes) {
		int currentMinutes = activeParking.getMinutes();
		activeParking.setMinutes(minutes + currentMinutes);

		return owner.charge(minutes * PRICE_PER_MINUTE); // Only charges the new minutes
	}
}
