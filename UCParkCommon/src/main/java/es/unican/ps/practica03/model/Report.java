package es.unican.ps.practica03.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@SuppressWarnings("serial")
@Entity
public class Report implements Serializable {
	
	@Id @GeneratedValue
	private long id;
	private Date date;
	private double price;
	private String cause;
	
	@JoinColumn(name = "vehicle_fk")
	private Vehicle reportedVehicle;
	
	/**
	 * Default constructor.
	 */
	public Report() {}
	
	/**
	 * Constructor.
	 * @param date: the date of the report
	 * @param price: the price of the report
	 * @param cause: the cause of the report
	 * @param vehicle: the reported vehicle
	 */
	public Report(Date date, double price, String cause, Vehicle vehicle) {
		this.date = date;
		this.price = price;
		this.cause = cause;
		this.reportedVehicle = vehicle;

	}

	/**
	 * Returns the id of the report.
	 * @return the id of the report
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id of the report.
	 * @param id: the id to be set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns the date of the report.
	 * @return the date of the report
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date of the report.
	 * @param date: the date to be set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Returns the price of the report.
	 * @return the price of the report
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the report.
	 * @param price: the price to be set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Returns the cause of the report.
	 * @return the cause of the report
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * Sets the cause of the report.
	 * @param cause: the cause to be set
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * Returns the reported vehicle.
	 * @return the reported vehicle
	 */
	public Vehicle getReportedVehicle() {
		return reportedVehicle;
	}

	/**
	 * Sets the reported vehicle.
	 * @param reportedVehicle: the reported vehicle to be set
	 */
	public void setReportedVehicle(Vehicle reportedVehicle) {
		this.reportedVehicle = reportedVehicle;
	}
}
