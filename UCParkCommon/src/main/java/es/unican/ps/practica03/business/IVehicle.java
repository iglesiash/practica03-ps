package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.*;

import jakarta.ejb.Remote;

@Remote
public interface IVehicle {
	public boolean registerVehicle(Vehicle vehicle, User user);
	public boolean deleteVehicle(Vehicle vehicle);
}
