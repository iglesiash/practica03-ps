package es.unican.ps.practica03.business;

import java.util.List;

import es.unican.ps.practica03.model.User;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IUsersDAO;
import es.unican.ps.practica03.persistence.IVehiclesDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class VehicleManagement implements IVehicle {
	
	@EJB
	private IVehiclesDAO vehiclesDao;
	
	@EJB
	private IUsersDAO usersDao;

	public VehicleManagement() { }

	@Override
	public boolean registerVehicle(Vehicle vehicle, User user) throws InvalidOperation {
		if (vehiclesDao.addVehicle(vehicle)) {
			throw new InvalidOperation("This vehicle has already been registered.");
		}
		return user.addVehicle(vehicle);
	}

	@Override
	public boolean deleteVehicle(Vehicle vehicle) {
		User owner = vehicle.getOwner();
		owner.removeVehicle(vehicle);
		usersDao.modifyUser(owner);

		return vehiclesDao.deleteVehicle(vehicle.getNumberPlate());
	}
	
	@Override
	public Vehicle getVehicleByNumberPlate(String numberPlate) {
		return vehiclesDao.getVehicle(numberPlate);
	}

	@Override
	public List<Vehicle> getAllVehicles() {
		return vehiclesDao.getVehicles();
	}

}
