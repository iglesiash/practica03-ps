package es.unican.ps.practica03.common;

import java.util.Date;

public class Estacionamiento {
	private String idEstacionamiento;
	private double price;
	private int minutes;
	private Date startingTime;
	
	private Vehiculo vehiculo;
	
	public String getIdEstacionamiento() {
		return idEstacionamiento;
	}
	
	public void setIdEstacionamiento(String idEstacionamiento) {
		this.idEstacionamiento = idEstacionamiento;
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
		
}
