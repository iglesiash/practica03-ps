package es.unican.ps.practica03.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class Parking {
	@Id @GeneratedValue 
	private long id;
	
	private double price;
	private int minutes;
	
	@Column(name = "startTime")
	private Date startingTime;
	
	@OneToOne @JoinColumn(name="id")
	private Vehicle vehicle;

	public Parking() {}
	
	public Parking(int minutes, Date startingTime, Vehicle vehicle) {
		this.setMinutes(minutes);
		this.startingTime = startingTime;
		this.vehicle = vehicle;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
