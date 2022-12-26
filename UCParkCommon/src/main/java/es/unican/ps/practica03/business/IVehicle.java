package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.*;

public interface IVehicle {
	public boolean registerVehicle(Vehicle vehicle, User user);
	public boolean deleteVehicle(Vehicle vehicle);
}
