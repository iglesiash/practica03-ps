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
	
	@Transient // Not persisted
	private double balance;

	/**
	 * Default constructor.
	 */
	public User() { }

	/**
	 * Constructor.
	 * @param email: the user email
	 * @param password: the user password
	 */
	public User(String email, String password) {
		this.email = email;
		this.password = password;
		paymentMethods = new LinkedList<PaymentMethod>();
		vehicles = new LinkedList<Vehicle>();
		
		balance = 10;
	}

	/**
	 * Returns the user email.
	 * @return the user email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user email.
	 * @param email: the user email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the user's password.
	 * @return the user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 * @param password: the user's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the user's payment methods.
	 * @return the user's payment methods
	 */
	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	/*
	 * Sets the user's payment methods.
	 * @param paymentMethods: the user's payment methods
	 */
	public void setPaymentMethods(List<PaymentMethod> PaymentMethods) {
		this.paymentMethods = PaymentMethods;
	}

	/**
	 * Returns the user's vehicles.
	 * @return the user's vehicles
	 */
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * Sets the user's vehicles.
	 * @param vehicles: the user's vehicles
	 */
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	/**
	 * Returns the user's balance.
	 * @return the user's balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Sets the user's balance.
	 * @param balance: the user's balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Returns the user's parking history.
	 * @return the user's parking history
	 */
	public List<Parking> getParkingHistory() {
		List<Parking> parkingHistory = new LinkedList<>();
		
		// Loops all vehicles and adds their parking history to a list
		for (Vehicle vehicle: vehicles) {
			parkingHistory.addAll(vehicle.getParkingHistory());
		}
		return parkingHistory;
	}
	
	/**
	 * Adds a vehicle to the user's vehicles list.
	 * @param vehicle: the vehicle to add
	 * @return true if the vehicle was added, false otherwise
	 */
	public boolean addVehicle(Vehicle vehicle) {
		return vehicles.add(vehicle);
	}


	/**
	 * Removes a vehicle from the user's vehicles list.
	 * @param vehicle: the vehicle to remove
	 * @return true if the vehicle was removed, false otherwise
	 */
	public boolean removeVehicle(Vehicle vehicle) {
		return vehicles.remove(vehicle);	
	}

	/**
	 * Charges a price from the user's balance.
	 * @param price: the price to be charged
	 * @return true if the price was charged, false otherwise
	 */
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
	
	/**
	 * Adds a payment method to the user's payment methods list.
	 * @param PaymentMethod: the payment method to be added
	 */
	public void addPaymentMethod(PaymentMethod PaymentMethod) {
		paymentMethods.add(PaymentMethod);
	}
}
