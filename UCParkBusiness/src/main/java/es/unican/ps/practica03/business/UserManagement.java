package es.unican.ps.practica03.business;

import java.util.LinkedList;
import java.util.List;

import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.PaymentMethod;
import es.unican.ps.practica03.model.Report;
import es.unican.ps.practica03.model.User;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IUsersDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UserManagement implements IAnonymousUser, IUser {
	
	@EJB
	private IUsersDAO usersDao;

	public UserManagement() { }

	@Override
	public List<Report> consultReports(String email) {
		return usersDao.getUser(email).getActiveReports();
	}

	@Override
	public List<Parking> consultParkingHistory(String email) {
		return usersDao.getUser(email).getParkingHistory();
	}

	@Override
	public List<Parking> consultCurrentParkingList(String email) throws InvalidOperation {
		User user = usersDao.getUser(email);
		
		List<Parking> currentParking = new LinkedList<>();
		for (Vehicle vehicle: user.getVehicles()) {
			currentParking.add(vehicle.getActiveParking());
		}
		return currentParking;
	}

	@Override
	public void register(User user, PaymentMethod paymentMethod) throws InvalidOperation {
		User searchedUser = usersDao.getUser(user.getEmail());
		if (searchedUser != null) {
			throw new InvalidOperation("User already registered");
		}

		usersDao.addUser(user);
	}

}