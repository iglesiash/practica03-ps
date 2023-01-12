package es.unican.ps.practica03.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import es.unican.ps.practica03.business.InvalidOperation;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class User implements Serializable {
	
	@Id private String email;
	private String password;

	@OneToMany @JoinColumn
	private List<PaymentMethod> paymentMethods;
	
	@OneToMany (mappedBy = "owner", fetch = FetchType.EAGER)
	private List<Vehicle> vehicles;
	
	@Transient
	private double balance;

	public User() { }
	public User(String email, String password) {
		this.email = email;
		this.password = password;
		paymentMethods = new LinkedList<PaymentMethod>();
		vehicles = new LinkedList<Vehicle>();
		
		balance = 10;
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

	public void setPaymentMethods(List<PaymentMethod> PaymentMethods) {
		this.paymentMethods = PaymentMethods;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	public boolean charge(double price) {
		if (balance == 0) {
			throw new InvalidOperation("0");
		}
		if (balance - price < 0) {
			return false;
		}
		balance -= price;
		return true;
	}
	
	public void addPaymentMethod(PaymentMethod PaymentMethod) {
		paymentMethods.add(PaymentMethod);
	}
}
