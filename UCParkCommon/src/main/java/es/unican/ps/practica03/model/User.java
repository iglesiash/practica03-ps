package es.unican.ps.practica03.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Ucpark_user")
public class User implements Serializable {
	
	@Id private String email;
	private String password;

	@OneToMany @JoinColumn (name = "owner")
	private List<PaymentMethod> paymentMethods;
	
	@OneToMany @JoinColumn (name = "owner")
	private List<Vehicle> vehicles;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
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
	
	public boolean addVehicle(Vehicle vehicle) {
		return vehicles.add(vehicle);
		
	}

	public boolean removeVehicle(Vehicle vehicle) {
		return vehicles.remove(vehicle);
		
	}
	
}
