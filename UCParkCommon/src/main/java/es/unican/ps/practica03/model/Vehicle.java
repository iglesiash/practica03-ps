package es.unican.ps.practica03.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Vehicle {
	private String numberPlate;
	private String brand;
	private String model;
	private Parking activeParking;
	private Stack<Parking> parkingHistory;
	private List<Report> currentReports;
	private User owner;
	
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

	public void addParking(Parking parking) {
		activeParking = parking;	
	}

	public boolean addReport(Report report) {
		return currentReports.add(report);
	}

	public boolean removeReport(Report report) {
		return currentReports.remove(report);
	}

	public void finishParking() {
		parkingHistory.add(activeParking);
		activeParking = null;
	}
}
