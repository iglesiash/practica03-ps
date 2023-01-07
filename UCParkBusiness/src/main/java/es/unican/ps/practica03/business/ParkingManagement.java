package es.unican.ps.practica03.business;

import java.util.Date;

import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IParkingDAO;
import es.unican.ps.practica03.persistence.IVehiclesDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ParkingManagement implements IParking {

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
		if (vehicle == null) {
			throw new InvalidOperation("There are no vehicles with the inserted number plate.");
		}

		return vehicle.getActiveParking();
	}

	@Override
	public void registerParking(Vehicle vehicle, int minutes) throws InvalidOperation {
		if (vehicle.getActiveParking() != null) {
			throw new InvalidOperation("This vehicle already has an active parking.");
		}

		if (minutes > 120 || minutes <= 0) {
			throw new InvalidOperation("The number of minutes exceeds the overall time limit of "
					+ "120 minutes. Please insert a valid ammount of minutes.");
		}

		if (minutes <= 0) {
			throw new InvalidOperation("The number of minutes is negative or zero. Please insert "
					+ "a valid ammount of minutes.");

		}

		Date now = new Date();
		Parking parking = new Parking(minutes, now, vehicle);

		if (!vehicle.addParking(parking)) {
			throw new InvalidOperation("The transaction could not be performed. Please check your"
					+ " current balance and then try again.");
		}
		parkingDao.addParking(parking);

	}

	@Override
	public Parking extendParkingTime(long parkingId, int minutes) throws InvalidOperation {
		Parking parking = parkingDao.getParking(parkingId);
		Vehicle vehicle = parking.getVehicle();
		int currentMinutes = vehicle.getActiveParking().getMinutes();

		if (minutes <= 0) {
			throw new InvalidOperation("The number of minutes is not valid.");
		}

		if (currentMinutes + minutes > 120) {
			throw new InvalidOperation("The inserted time exceeds the overall time limit "
					+ "of 120 minutes.");
		}

		if (!vehicle.editParking(minutes)) {
			throw new InvalidOperation("The transaction could not be performed. Please check your"
					+ " current balance and then try again.");
		}
		
		vehicle.getActiveParking().setMinutes(currentMinutes + minutes);

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
