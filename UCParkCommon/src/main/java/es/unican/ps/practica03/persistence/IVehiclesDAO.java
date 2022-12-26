package es.unican.ps.practica03.persistence;

import java.util.List;

import es.unican.ps.practica03.model.Vehicle;

public interface IVehiclesDAO {
	
	/**
	 * Returns the list of vehicles.
	 * @return the list of vehicles
	 */
	public List<Vehicle> getVehicles();
	
	/**
	 * Returns the associated vehicle to a number plate.
	 * @param numberPlate the number plate associated to a vehicle
	 * @return the vehicle if it exists, null otherwise
	 */
	public Vehicle getVehicle(String numberPlate);
	
	/**
	 * Adds a vehicle.
	 * @param vehicle the vehicle to be added
	 * @return true if the vehicle could be added, false otherwise
	 */
	public boolean addVehicle(Vehicle vehicle);
	
	/**
	 * Modifies an existing vehicle.
	 * @param newVehicle the modified vehicle
	 * @return the modified vehicle if it exists, null otherwise
	 */
	public Vehicle modifyVehicle(Vehicle newVehicle);
	
	
	/**
	 * Deletes the associated vehicle to a number plate.
	 * @param numberPlate the vehicle's number plate.
	 * @return true if the vehicle could be deleted, false otherwise
	 */
	public boolean deleteVehicle(String numberPlate);
	
}
