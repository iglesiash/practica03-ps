package es.unican.ps.practica03.model;

import java.util.Date;

public class Denuncia {
	private String id;
	private Date date;
	private double price;
	private String cause;
	private Vehiculo vehiculoDenunciado;
	private Usuario usuarioDenunciado;
	
	public Denuncia(String id, Date date, double price, String cause, Vehiculo vehicle,
			Usuario denunciado) {
		this.id = id;
		this.date = date;
		this.price = price;
		this.cause = cause;
		this.vehiculoDenunciado = vehicle;
		this.usuarioDenunciado = denunciado;
	}
	
	public void reportEstacionamiento(Vehiculo vehicle, String description, double price) {
		// TODO Auto-generated method stub
		
	}

	public void voidReport(Denuncia report) {
		// TODO Auto-generated method stub
		
	}

	
}
