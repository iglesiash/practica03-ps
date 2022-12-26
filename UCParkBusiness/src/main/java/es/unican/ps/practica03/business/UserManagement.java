package es.unican.ps.practica03.business;

import java.util.LinkedList;
import java.util.List;

import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.PaymentMethod;
import es.unican.ps.practica03.model.Report;
import es.unican.ps.practica03.model.User;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IUsersDAO;

public class UserManagement implements IAnonymousUser, IUser {

	private IUsersDAO usersDao;

	public UserManagement(IUsersDAO usersDao) {
		this.usersDao = usersDao;
	}

	@Override
	public List<Report> consultReports(String email) {
		return usersDao.getUser(email).getActiveReports();
	}

	@Override
	public List<Parking> consultParkingHistory(String email) {
		return usersDao.getUser(email).getParkingHistory();
	}

	@Override
	public List<Parking> consultCurrentParkingList(String email) throws OperacionNoValida {
		User user = usersDao.getUser(email);
		
		List<Parking> currentParking = new LinkedList<>();
		for (Vehicle vehicle: user.getVehicles()) {
			currentParking.add(vehicle.getActiveParking());
		}
		return currentParking;
	}

	@Override
	public void register(User user, PaymentMethod paymentMethod) throws OperacionNoValida {
		User searchedUser = usersDao.getUser(user.getEmail());
		if (searchedUser != null) {
			throw new OperacionNoValida("User already registered");
		}

		usersDao.addUser(user);
	}

}