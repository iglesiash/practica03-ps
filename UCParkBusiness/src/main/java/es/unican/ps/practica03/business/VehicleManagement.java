package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.User;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IUsersDAO;
import es.unican.ps.practica03.persistence.IVehiclesDAO;

public class VehicleManagement implements IVehicle {

	private IVehiclesDAO vehiclesDao;
	private IUsersDAO usersDao;

	public VehicleManagement(IVehiclesDAO vehiclesDao, IUsersDAO usersDao) {
		this.vehiclesDao = vehiclesDao;
		this.usersDao = usersDao;
	}

	@Override
	public boolean registerVehicle(Vehicle vehicle, User user) {
		if (vehiclesDao.addVehicle(vehicle)) {
			throw new OperacionNoValida("This vehicle has already been registered.");
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

}
