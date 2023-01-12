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
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
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

	/**
	 * Default constructor.
	 */
	public LoggedInBean() {}

	/**
	 * Initializes the bean.
	 */
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

	/**
	 * Returns the user's email.
	 * @return the user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email.
	 * @param email: the user's email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the user.
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * @param user: the user to be set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the vehicle's number plate.
	 * @return the vehicle's number plate
	 */
	public String getNumberPlate() {
		return numberPlate;
	}

	/**
	 * Sets the vehicle's number plate.
	 * @param numberPlate: the vehicle's number plate to be set
	 */
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	/**
	 * Returns the parking's minutes.
	 * @return the parking's minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Sets the parking's minutes.
	 * @param minutes: the parking's minutes to be set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * Returns the user's vehicles.
	 * @return the user's vehicles
	 */
	public List<Vehicle> getUserVehicles() {
		return userVehicles;
	}

	/**
	 * Sets the user's vehicles.
	 * @param userVehicles: the user's vehicles to be set
	 */
	public void setUserVehicles(List<Vehicle> userVehicles) {
		this.userVehicles = userVehicles;
	}

	/**
	 * Returns the parking's ending date as a string.
	 * @return the parking's ending date as a string
	 */
	public String getEndingDateString() {
		return endingDateString;
	}

	/**
	 * Sets the parking's ending date as a string.
	 * @param endingDateString: the parking's ending date as a string to be set
	 */
	public void setEndingDateString(String endingDateString) {
		this.endingDateString = endingDateString;
	}

	/**
	 * Adds a new parking to the system
	 * @return 	the page to be redirected to (active_parking.xhtml if the addition was successful,
	 * 			new_parking.xhtml otherwise)
	 */
	public String saveNewParking() {
		Vehicle vehicle = vehicleManagementRemote.getVehicleByNumberPlate(numberPlate);

		// Vehicle does not exist or it is not owned by the user
		if (vehicle == null || !user.getVehicles().contains(vehicle)) {
			return "new_parking.xhtml";
		}
		
		vehicle.setOwner(user);
		
		// Parking already active
		if (vehicle.isParkingActive()) {
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
