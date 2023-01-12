package es.unican.ps.practica03.business;

import java.util.Date;

import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IParkingDAO;
import es.unican.ps.practica03.persistence.IVehiclesDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ParkingManagement implements IParkingRemote, IParkingLocal {

	@EJB
	private IParkingDAO parkingDao;

	@EJB
	private IVehiclesDAO vehiclesDao;

	public ParkingManagement() { }

	public ParkingManagement(IParkingDAO parkingDao, IVehiclesDAO vehiclesDao) {
		this.parkingDao = parkingDao;
		this.vehiclesDao = vehiclesDao;
	}

	@Override
	public Parking consultParking(String numberPlate) throws InvalidOperation {
		Vehicle vehicle = vehiclesDao.getVehicle(numberPlate);

		// Vehicle does not exist
		if (vehicle == null) {
			throw new InvalidOperation("There are no vehicles with the inserted number plate.");
		}
		return vehicle.getActiveParking();
	}

	@Override
	public Parking registerParking(Vehicle vehicle, int minutes) throws InvalidOperation {
		Parking activeParking = vehicle.getActiveParking();

		// Vehicle does not have an active parking
		if (activeParking != null) {
			throw new InvalidOperation("This vehicle already has an active parking.");
		}

		// Minutes exceed 120
		if (minutes > 120) {
			throw new InvalidOperation("The number of minutes exceeds the overall time limit of "
					+ "120 minutes. Please insert a valid amount of minutes.");
		}

		// Negative minutes
		if (minutes <= 0) {
			throw new InvalidOperation("The number of minutes is negative or zero. Please insert "
					+ "a valid amount of minutes.");
		}

		Date now = new Date();
		Parking parking = new Parking(minutes, now, vehicle);

		// The parking could not be added to the vehicle
		if (!vehicle.addParking(parking)) {
			throw new InvalidOperation("The transaction could not be performed. Please check your"
					+ " current balance and then try again.");
		}

		// DAOs are updated
		vehiclesDao.modifyVehicle(vehicle);
		parkingDao.addParking(parking);
		return parking;
	}

	@Override
	public Parking extendParkingTime(long parkingId, int minutes) throws InvalidOperation {
		Parking parking = parkingDao.getParking(parkingId);
		Vehicle vehicle = parking.getVehicle();
		int currentMinutes = vehicle.getActiveParking().getMinutes();

		// Negative minutes
		if (minutes <= 0) {
			throw new InvalidOperation("The number of minutes is not valid.");
		}

		// Global time exceeds 120 minutes
		if (currentMinutes + minutes > 120) {
			throw new InvalidOperation("The inserted time exceeds the overall time limit "
					+ "of 120 minutes.");
		}

		// The parking could not be edited (presumably due to a transaction error)
		if (!vehicle.editParking(minutes)) {
			throw new InvalidOperation("The transaction could not be performed. Please check your"
					+ " current balance and then try again.");
		}

		// The time extension is added to the parking
		vehicle.getActiveParking().setMinutes(currentMinutes + minutes);

		// The DAO is updated
		return parkingDao.modifyParking(parking);
	}

	@Override
	public void finishParking(long parkingId) {
		// Deletes parking from vehicle
		Parking parking = parkingDao.getParking(parkingId);
		Vehicle vehicle = parking.getVehicle();

		vehicle.finishParking();
		vehiclesDao.modifyVehicle(vehicle);
	}
}
