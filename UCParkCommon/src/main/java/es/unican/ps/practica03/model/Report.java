package es.unican.ps.practica03.model;

import java.util.Date;

public class Report {
	private Date date;
	private double price;
	private String cause;
	private Vehicle reportedVehicle;
	
	public Report(Date date, double price, String cause, Vehicle vehicle) {
		this.date = date;
		this.price = price;
		this.cause = cause;
		this.reportedVehicle = vehicle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Vehicle getReportedVehicle() {
		return reportedVehicle;
	}

	public void setReportedVehicle(Vehicle reportedVehicle) {
		this.reportedVehicle = reportedVehicle;
	}
}
