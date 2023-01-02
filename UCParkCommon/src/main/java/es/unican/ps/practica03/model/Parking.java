package es.unican.ps.practica03.model;

import java.util.Date;

public class Parking {
	private double price;
	private int minutes;
	private Date startingTime;
	
	private Vehicle vehicle;
	
	public Parking(int minutes, Date startingTime, Vehicle vehicle) {
		this.minutes = minutes;
		this.startingTime = startingTime;
		this.vehicle = vehicle;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public Date getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	
}
