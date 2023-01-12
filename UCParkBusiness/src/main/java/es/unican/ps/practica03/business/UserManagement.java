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
public class UserManagement implements IAnonymousUserLocal, IAnonymousUserRemote, IUserRemote, IUserLocal  {

	@EJB
	private IUsersDAO usersDao;

	public UserManagement() { }

	@Override
	public List<Report> consultReports(String email) {
		List<Report> reports = new LinkedList<>();
		User user = usersDao.getUser(email);
		
		// Loops all the vehicles of the user and adds the reports to the list
		for (Vehicle vehicle: user.getVehicles()) {
			reports.addAll(vehicle.getCurrentReports());
		}
		return reports;
	}

	@Override
	public List<Parking> consultParkingHistory(String email) {
		return usersDao.getUser(email).getParkingHistory();
	}

	@Override
	public List<Parking> consultCurrentParkingList(String email) {
		User user = usersDao.getUser(email);
		List<Parking> currentParking = new LinkedList<>();

		// Loops all the vehicles of the user and adds the active parking to the list
		for (Vehicle vehicle: user.getVehicles()) {
			currentParking.add(vehicle.getActiveParking());
		}
		return currentParking;
	}

	@Override
	public User register(User user, PaymentMethod paymentMethod) {
		User searchedUser = usersDao.getUser(user.getEmail());

		// This user is already in the system
		if (searchedUser != null) {
			return null;
		}

		// User is added to the DAO
		user.addPaymentMethod(paymentMethod);
		usersDao.addUser(user);
		return user;
	}

	@Override
	public User login(String email, String password) {
		User user = usersDao.getUser(email);
		
		// The user does not exist or the password is incorrect
		if (user == null || !user.getPassword().equals(password)) {
			return null;
		}

		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {

		// Gets user from the DAO
		return usersDao.getUser(email);
	}

}