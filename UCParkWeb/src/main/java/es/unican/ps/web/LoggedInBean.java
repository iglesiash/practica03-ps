package es.unican.ps.web;

import java.io.Serializable;
import java.util.List;

import es.unican.ps.practica03.business.IParkingRemote;
import es.unican.ps.practica03.business.IUserRemote;
import es.unican.ps.practica03.business.IVehicleRemote;
import es.unican.ps.practica03.business.InvalidOperation;
import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.User;
import es.unican.ps.practica03.model.Vehicle;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class LoggedInBean implements Serializable {

	@Inject
	private AnonymousBean anonymousBean;

	@EJB
	private IParkingRemote parkingManagementRemote;

	@EJB
	private IVehicleRemote vehicleManagementRemote;

	@EJB
	private IUserRemote userManagementRemote;

	private String email;
	private User user;
	private String numberPlate;
	private int minutes;
	private List<Vehicle> userVehicles;
	private String endingDateString;

	public LoggedInBean() {}

	@PostConstruct
	public void init() {
		email = anonymousBean.getEmail();
		user = userManagementRemote.getUserByEmail(email);

		userVehicles = vehicleManagementRemote.getUserVehicles(email);
		user.setVehicles(userVehicles);

		// XXX This should NEVER be done, any money should be added by users themselves. 
		// XXX Only for testing purposes
		if (user.getBalance() == 0) {
			user.setBalance(10);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public List<Vehicle> getUserVehicles() {
		return userVehicles;
	}

	public void setUserVehicles(List<Vehicle> userVehicles) {
		this.userVehicles = userVehicles;
	}

	public String getEndingDateString() {
		return endingDateString;
	}

	public void setEndingDateString(String endingDateString) {
		this.endingDateString = endingDateString;
	}

	public String saveNewParking() {
		Vehicle vehicle = vehicleManagementRemote.getVehicleByNumberPlate(numberPlate);

		// Vehicle does not exist or it is not owned by the user
		if (vehicle == null || !user.getVehicles().contains(vehicle)) {
			return "new_parking.xhtml";
		}
		
		vehicle.setOwner(user);
		
		// Parking already active
		Parking consultedParking = parkingManagementRemote.consultParking(numberPlate);
		if (consultedParking != null && consultedParking.isParkingActive()) {
			return "new_parking.xhtml";
		}

		try {
			Parking parking = parkingManagementRemote.registerParking(vehicle, minutes);
			setEndingDateString(parking.convertEndingDateToString());
		} catch (InvalidOperation e) { // Any thrown error
			return "new_parking.xhtml";
		}

		return "active_parking.xhtml";
	} // saveNewParking
} // LoggedInBean
