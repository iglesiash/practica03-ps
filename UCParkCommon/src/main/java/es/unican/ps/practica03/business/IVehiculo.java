package es.unican.ps.practica03.business;

import es.unican.ps.practica03.common.*;

public interface IVehiculo {
	public Vehiculo registerVehiculo(Vehiculo vehicle);
	public Vehiculo deleteVehiculo(String matricula);
}
