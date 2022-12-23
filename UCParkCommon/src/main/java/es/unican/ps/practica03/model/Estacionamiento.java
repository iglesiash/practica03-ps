package es.unican.ps.practica03.model;

import java.util.Date;

import es.unican.ps.practica03.business.IEstacionamiento;

public class Estacionamiento implements IEstacionamiento {
	private String idEstacionamiento;
	private double price;
	private int minutes;
	private Date startingTime;
	
	private Vehiculo vehiculo;
	
	public Estacionamiento(double price, int minutes, Date startingTime, Vehiculo vehicle) {
		this.price = price;
		this.minutes = minutes;
		this.startingTime = startingTime;
		this.vehiculo = vehicle;
	}
	

	public Estacionamiento consultEstacionamiento(String matricula) {
		// TODO Auto-generated method stub
		return null;
	}

	public void registerEstacionamiento(Vehiculo vehicle) {
		// TODO Auto-generated method stub
		
	}

	public Estacionamiento extendParkingTime(String idEstacionamiento, int minutes) {
		// TODO Auto-generated method stub
		return null;
	}

	public void finishEstacionamiento(String idEstacionamiento) {
		// TODO Auto-generated method stub
		
	}
}
