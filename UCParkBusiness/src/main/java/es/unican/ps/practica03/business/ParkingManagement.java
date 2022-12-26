package es.unican.ps.practica03.business;

import java.util.Date;

import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IParkingDAO;
import es.unican.ps.practica03.persistence.IVehiclesDAO;

public class ParkingManagement implements IParking {

	private IParkingDAO parkingDao;
	private IVehiclesDAO vehiclesDao;
	
	public ParkingManagement(IParkingDAO parkingDao, IVehiclesDAO vehiclesDao) {
		this.parkingDao	= parkingDao;
		this.vehiclesDao = vehiclesDao;
	}

	@Override
	public Parking consultParking(String numberPlate) {
		Vehicle vehicle = vehiclesDao.getVehicle(numberPlate);
		if (vehicle == null) {
			throw new OperacionNoValida("There are no vehicles with the inserted number plate.");
		}
		
		return vehicle.getActiveParking();
	}

	@Override
	public void registerParking(Vehicle vehicle, int minutes)  throws OperacionNoValida {
		if (vehicle.getActiveParking() != null) {
			throw new OperacionNoValida("This vehicle already has an active parking.");
		}
		
		if (minutes > 120 || minutes <= 0) {
			throw new OperacionNoValida("The number of minutes is negative or exceeds the overall "
					+ "time limit of 120 minutes.");
		}
		
		Date now = new Date();
		Parking parking = new Parking(minutes, now, vehicle);
		vehicle.addParking(parking);
	}

	@Override
	public Parking extendParkingTime(long parkingId, int minutes) {
		Parking parking = parkingDao.getParking(parkingId);
		Vehicle vehicle = parking.getVehicle();
		
		if (vehicle.getActiveParking().getMinutes() + minutes > 120) {
			throw new OperacionNoValida("The inserted time exceeds the overall time limit "
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
