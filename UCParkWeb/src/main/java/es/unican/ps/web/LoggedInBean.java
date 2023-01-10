package es.unican.ps.web;

import java.util.List;

import es.unican.ps.practica03.business.IParkingRemote;
import es.unican.ps.practica03.business.IUserRemote;
import es.unican.ps.practica03.business.IVehicleRemote;
import es.unican.ps.practica03.business.InvalidOperation;
import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.Vehicle;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class LoggedInBean {
	
	@Inject
	private UserBean userBean;
	
	@EJB
	private IParkingRemote parkingManagement;
	
	@EJB
	private IVehicleRemote vehicleManagement;
	
	@EJB
	private IUserRemote userManagement;
	
	private String numberPlate;
	private int minutes;
	private List<Parking> parkingList;
	
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

	public LoggedInBean() {}
	
	@PostConstruct
	public void setUp() {
		String email = userBean.getEmail();
		this.setParkingList(userManagement.consultCurrentParkingList(email));
	}

	public List<Parking> getParkingList() {
		return parkingList;
	}

	public void setParkingList(List<Parking> parkingList) {
		this.parkingList = parkingList;
	}

	public String queries() {
		return "queries.xhtml";
	}
	
	public String parking() {
		return "parking.xhtml";
	}
	
	public String newParking() {
		return "new_parking.xhtml";
	}
	
	public String activeParking() {
		return "active_parking.xhtml";
	}
	
	public String saveNewParking() {
		Vehicle vehicle = vehicleManagement.getVehicleByNumberPlate(numberPlate);
		if (vehicle == null) {
			return "parking.xhtml";
		}
		
		try {
			Parking parking = parkingManagement.consultParking(numberPlate);
			if (parking == null) {
				parkingManagement.registerParking(vehicle, minutes);
			}
		} catch (InvalidOperation e) {
			return "parking.xhtml";
		}
		return "active_parking.xhtml";
	}
}
