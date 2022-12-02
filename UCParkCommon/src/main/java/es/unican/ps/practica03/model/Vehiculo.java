package es.unican.ps.practica03.model;

import java.util.LinkedList;
import java.util.List;

import es.unican.ps.practica03.business.IVehiculo;

public class Vehiculo {
	private String matricula;
	private String marca;
	private String modelo;
	private Estacionamiento estacionamientoEnVigor;
	private List<Denuncia> currentReports;
	
	public Vehiculo(String matricula, String marca, String modelo) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		
		estacionamientoEnVigor = null;
		currentReports = new LinkedList<Denuncia>();
	}
}
