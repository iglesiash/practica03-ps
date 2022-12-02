package es.unican.ps.practica03.common;

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
	
}
