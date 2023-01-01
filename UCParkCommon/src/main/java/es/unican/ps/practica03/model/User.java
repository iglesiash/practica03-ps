package es.unican.ps.practica03.model;

import java.util.LinkedList;
import java.util.List;

public class User {
	private String email;
	private String password;
	
	private List<Report> activeReports;
	private List<PaymentMethod> paymentMethods;
	private List<Vehicle> vehicles;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
		
		activeReports = new LinkedList<Report>();
		paymentMethods = new LinkedList<PaymentMethod>();
		vehicles = new LinkedList<Vehicle>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Report> getActiveReports() {
		return activeReports;
	}

	public void setActiveReports(List<Report> activeReports) {
		this.activeReports = activeReports;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public List<Parking> getParkingHistory() {
		List<Parking> parkingHistory = new LinkedList<>();
		
		for (Vehicle vehicle: vehicles) {
			parkingHistory.addAll(vehicle.getParkingHistory());
		}
		return parkingHistory;
	}
	
	public void addReport(Report report) {
		activeReports.add(report);
	}

	public void removeReport(Report report) {
		activeReports.remove(report);
		
	}

	public boolean addVehicle(Vehicle vehicle) {
		return vehicles.add(vehicle);
		
	}

	public boolean removeVehicle(Vehicle vehicle) {
		return vehicles.remove(vehicle);
		
	}
	
}
