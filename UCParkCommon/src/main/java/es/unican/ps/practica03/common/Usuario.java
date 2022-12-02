package es.unican.ps.practica03.common;

import java.util.LinkedList;
import java.util.List;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Denuncia> getDenunciasEnVigor() {
		return denunciasEnVigor;
	}
	
	public List<MedioPago> getMetodosPago() {
		return metodosPago;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}
}
