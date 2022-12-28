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

	@Override
	public Parking consultParking(String numberPlate) {
		Vehicle vehicle = vehiclesDao.getVehicle(numberPlate);
		if (vehicle == null) {
			throw new InvalidOperation("There are no vehicles with the inserted number plate.");
		}
		
		return vehicle.getActiveParking();
	}

	@Override
	public void registerParking(Vehicle vehicle, int minutes)  throws InvalidOperation {
		if (vehicle.getActiveParking() != null) {
			throw new InvalidOperation("This vehicle already has an active parking.");
		}
		
		if (minutes > 120 || minutes <= 0) {
			throw new InvalidOperation("The number of minutes is negative, zero or exceeds the"
					+ "overall time limit of 120 minutes.");
		}
		
		Date now = new Date();
		Parking parking = new Parking(minutes, now, vehicle);
		vehicle.addParking(parking);
	}

	@Override
	public Parking extendParkingTime(long parkingId, int minutes) {
		Parking parking = parkingDao.getParking(parkingId);
		Vehicle vehicle = parking.getVehicle();
		
		if (minutes <= 0) {
			throw new InvalidOperation("The number of minutes is not valid.");
		}
		
		if (vehicle.getActiveParking().getMinutes() + minutes > 120) {
			throw new InvalidOperation("The inserted time exceeds the overall time limit "
					+ "of 120 minutes.");
		}
		
		parking.setMinutes(minutes + vehicle.getActiveParking().getMinutes());
		return parkingDao.modifyParking(parking);
	}

	@Override
	public void finishParking(long parkingId) {
		// Deletes parking from vehicle
		Parking parking = parkingDao.getParking(parkingId);
		Vehicle vehicle = parking.getVehicle();
		vehicle.finishParking();

		// Deletes parking from DAO
		parkingDao.deleteParking(parkingId);
		
	}
	
}
