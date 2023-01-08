package es.unican.ps.practica03.business;

import java.util.List;

import es.unican.ps.practica03.model.User;
import es.unican.ps.practica03.model.Vehicle;

public interface IVehicle {
	public boolean registerVehicle(Vehicle vehicle, User user);
	public boolean deleteVehicle(Vehicle vehicle);
	public Vehicle getVehicleByNumberPlate(String numberPlate);
	public List<Vehicle> getAllVehicles();
}
