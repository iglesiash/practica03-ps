package es.unican.ps.business;	

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.ps.practica03.business.InvalidOperation;
import es.unican.ps.practica03.business.ParkingManagement;
import es.unican.ps.practica03.model.User;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IParkingDAO;
import es.unican.ps.practica03.persistence.IVehiclesDAO;

public class ParkingManagementTest {
	
	private ParkingManagement sut;
	private IVehiclesDAO vehiclesDaoMock;
	private IParkingDAO parkingDaoMock;
	private String numberPlate;
	private String brand;
	private String model;
	private Vehicle vehicle;
	private int minutes;
	private User user;
	
	@BeforeEach
	public void setUp() {
		vehiclesDaoMock = mock(IVehiclesDAO.class);
		parkingDaoMock = mock(IParkingDAO.class);
		sut = new ParkingManagement(parkingDaoMock, vehiclesDaoMock);
		user = new User("hgi834@alumnos.unican.es", "hector");
		user.setBalance(10);
	}
	

	@Test
	public void registerParkingTest() {
		// UGE.2a
		numberPlate = "1111-AAA";
		model = "Peugeot";
		brand = "205";
		vehicle = new Vehicle(numberPlate, brand, model);
		vehicle.setOwner(user);
		
		minutes = 30;
		sut.registerParking(vehicle, minutes);
		
		assertNotNull(vehicle.getActiveParking());
		assertEquals(10 - minutes * 0.01, vehicle.getOwner().getBalance());
		
		// UGE.2b
		minutes = 91;
		
		assertThrows(InvalidOperation.class, () -> sut.registerParking(vehicle, minutes));
		
		// UGE.2c
		numberPlate = "2222-BBB";
		model = "Ford";
		brand = "Focus";
		vehicle = new Vehicle(numberPlate, brand, model);
		vehicle.setOwner(user);
		
		minutes = 140;
		assertThrows(InvalidOperation.class, () -> sut.registerParking(vehicle, minutes));
		assertNull(vehicle.getActiveParking());
		
		
		// UGE.2d
		minutes = -50;
		assertThrows(InvalidOperation.class, () -> sut.registerParking(vehicle, minutes));
		assertNull(vehicle.getActiveParking());
		
		// UGE.2d
		minutes = -50;
		assertThrows(InvalidOperation.class, () -> sut.registerParking(vehicle, minutes));
		assertNull(vehicle.getActiveParking());
		
		// UGE.2e
		vehicle.getOwner().setBalance(0);
		assertThrows(InvalidOperation.class, () -> sut.registerParking(vehicle, minutes));
		assertNull(vehicle.getActiveParking());
	}
}
