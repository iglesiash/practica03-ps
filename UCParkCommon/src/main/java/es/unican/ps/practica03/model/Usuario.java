package es.unican.ps.practica03.model;

import java.util.LinkedList;
import java.util.List;

import es.unican.ps.practica03.business.Denuncia;
import es.unican.ps.practica03.business.Estacionamiento;
import es.unican.ps.practica03.business.Vehiculo;

public class Usuario {
	private String email;
	private String password;
	
	private List<Denuncia> denunciasEnVigor;
	private List<MedioPago> metodosPago;
	private List<Vehiculo> vehiculos;

	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
		
		denunciasEnVigor = new LinkedList<Denuncia>();
		metodosPago = new LinkedList<MedioPago>();
		vehiculos = new LinkedList<Vehiculo>();
	}

	public List<Denuncia> consultReports() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Estacionamiento> consultEstacionamientos() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Vehiculo> consultVehicles() {
		// TODO Auto-generated method stub
		return null;
	}
}
