package es.unican.ps.practica03.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Transient;

@Entity
public class Parking {
	
	@Id @GeneratedValue 
	private long id;
	
	private double price;
	private int minutes;
	
	@Column(name = "startTime")
	private Date startingTime;
	
	@Transient // Not persisted (just for the timer)
	private Date finishingTime;
	
	@JoinColumn(name="vehicle_fk")
	private Vehicle vehicle;

	/**
	 * Default constructor
	 */
	public Parking() {}
	
	/**
	 * Constructor
	 * @param minutes The minutes of the parking
	 * @param startingTime The starting time of the parking 
	 * @param vehicle The parked vehicle
	 */
	public Parking(int minutes, Date startingTime, Vehicle vehicle) {
		this.minutes = minutes;
		this.startingTime = startingTime;
		this.vehicle = vehicle;
		this.finishingTime = calculateFinishingTime(startingTime, minutes);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns the minutes of the parking
	 * @return The minutes of the parking
	 */
	public int getMinutes() {
		return minutes;
	}
	
	/**
	 * Sets the minutes of the parking
	 * @param minutes The minutes to be set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * Returns the price of the parking
	 * @return The price of the parking
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the parking
	 * @param price The price to be set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Returns the starting time of the parking
	 * @return The starting time of the parking
	 */
	public Date getStartingTime() {
		return startingTime;
	}

	/**
	 * Sets the starting time of the parking
	 * @param startingTime The starting time to be set
	 */
	public void setStartingTime(Date startingTime) {
		this.startingTime = startingTime;
	}

	/**
	 * Returns the finishing time of the parking
	 * @return The finishing time of the parking
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * Sets the vehicle of the parking
	 * @param vehicle The vehicle to be set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public Date getFinishingTime() {
		return finishingTime;
	}

	public void setFinishingTime(Date finishingTime) {
		this.finishingTime = finishingTime;
	}

	/**
	 * Returns the finishing time of the parking given a time and the amount of minutes to be
	 * added.
	 * @param startingTime: the starting time of the parking
	 * @param minutes: the minutes of the parking
	 * @return The finishing time of the parking
	 */
	public Date calculateFinishingTime(Date startingTime, int minutes) {
		Date finishingTime = startingTime;
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(finishingTime);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
	
	public String convertEndingDateToString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return formatter.format(finishingTime);
	}
}
