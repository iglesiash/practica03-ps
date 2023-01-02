package es.unican.ps.practica03.persistence;

import java.util.List;

import es.unican.ps.practica03.model.Parking;

public interface IParkingDAO {
	
	/**
	 * Returns the list of parking.
	 * @return the list of parking.
	 */
	public List<Parking> getParkings();
	
	/**
	 * Returns the parking associated to an id.
	 * @param id the parking's id
	 * @return the searched parking if it was found, null otherwise
	 */
	public Parking getParking(long id);
	
	
	/**
	 * Adds a Parking.
	 * @param Parking the parking to be added
	 * @return true if the parking could be added, false otherwise
	 */
	public boolean addParking(Parking parking);
	
	/**
	 * Modifies a parking.
	 * @param newParking the new parking
	 * @return the parking if it could be modified, null otherwise
	 */
	public Parking modifyParking(Parking newParking);
	
	/**
	 * Deletes a Parking.
	 * @param id the parking's id
	 * @return true if the parking could be deleted, false otherwise
	 */
	public boolean deleteParking(long id);

}
