package es.unican.ps.web;

import java.io.Serializable;
import java.sql.Date;
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
	private String password;

	private User user;
	private String numberPlate;
	private int minutes;
	private List<Parking> parkingList;
	private List<Vehicle> userVehicles;
	private Date endingTime;
	
	public LoggedInBean() {}
	
	@PostConstruct
	public void init() {
		email = anonymousBean.getEmail();

		password = anonymousBean.getPassword();
		user = new User(email, password);
		
		parkingList = userManagementRemote.consultCurrentParkingList(email);
		userVehicles = user.getVehicles();
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
	
	public List<Parking> getParkingList() {
		return parkingList;
	}

	public void setParkingList(List<Parking> parkingList) {
		this.parkingList = parkingList;
	}
	
	public Date getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(Date endingTime) {
		this.endingTime = endingTime;
	}
	
	public String saveNewParking() {
		Vehicle vehicle = vehicleManagementRemote.getVehicleByNumberPlate(numberPlate);
		if (vehicle == null) { // Vehicle does not exist
			return "parking.xhtml";
		}
		
		if (!userVehicles.contains(vehicle)) { // Vehicle not owned by user
			return "parking.xhtml";
		}
		
		try {
			Parking parking = parkingManagementRemote.consultParking(numberPlate);
			if (parking == null) { // Vehicle not parked yet
				parkingManagementRemote.registerParking(vehicle, minutes);
			}
		} catch (InvalidOperation e) {
			return "parking.xhtml";
		}
		
		return "active_parking.xhtml";
	}
}
