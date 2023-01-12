package es.unican.ps.practica03.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.Vehicle;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ParkingDAOTest {
	// Mocks
	@Mock
	private EntityManager entityManagerMock;
	
	@Mock
	private Query queryMock;
	
	// SUT (System Under Test)
	private IParkingDAO sut;
	
	// Model attributes
	private Parking parking;
	private Vehicle vehicle;
	private List<Parking> parkingList;
	
	@BeforeEach
	public void setUp() {
		
		entityManagerMock = mock(EntityManager.class);
		queryMock = mock(Query.class);
		
		sut = new ParkingDAO(entityManagerMock);
		parkingList = new LinkedList<>();
		
		// Parking list for vehicle 1
		vehicle = new Vehicle("1111-AAA", "Peugeot", "205");
		
		parking = new Parking(60, Timestamp.valueOf(LocalDateTime.of(2022, 04, 01, 11, 58)), vehicle);
		parking.setId(1);
		parkingList.add(parking);
		
		parking = new Parking(85, Timestamp.valueOf(LocalDateTime.of(2022, 04, 24, 00, 49)), vehicle);
		parking.setId(2);
		parkingList.add(parking);
		
		parking = new Parking(90, Timestamp.valueOf(LocalDateTime.of(2022, 07, 04, 15, 51)), vehicle);
		parking.setId(3);
		parkingList.add(parking);
		
		// Parking list for vehicle 2
		vehicle = new Vehicle("2222-BBB", "Ford", "Focus");
		
		parking = new Parking(20, Timestamp.valueOf(LocalDateTime.of(2022, 07, 06, 14, 03)), vehicle);
		parking.setId(4);
		parkingList.add(parking);

		parking = new Parking(120, Timestamp.valueOf(LocalDateTime.of(2022, 07, 19, 14, 10)), vehicle);
		parking.setId(5);
		parkingList.add(parking);
		
		// Parking list for vehicle 3
		vehicle = new Vehicle("3333-CCC", "Citröen", "Xsara");
		
		parking = new Parking(10, Timestamp.valueOf(LocalDateTime.of(2022, 9, 05, 14, 24)), vehicle);
		parking.setId(6);
		parkingList.add(parking);

		parking = new Parking(24, Timestamp.valueOf(LocalDateTime.of(2022, 10, 03, 8, 35)), vehicle);
		parking.setId(7);
		parkingList.add(parking);

		parking = new Parking(5, Timestamp.valueOf(LocalDateTime.of(2022, 10, 31, 16, 37)), vehicle);
		parking.setId(8);
		parkingList.add(parking);
		
		// Parking list for vehicle 3
		vehicle = new Vehicle("4444-DDD", "Mini", "Cooper");
		
		parking = new Parking(5, Timestamp.valueOf(LocalDateTime.of(2022, 12, 24, 19, 26)), vehicle);
		parking.setId(9);
		parkingList.add(parking);

		parking = new Parking(45, Timestamp.valueOf(LocalDateTime.of(2023, 01, 01, 17, 32)), vehicle);
		parking.setId(10);
		parkingList.add(parking);
		
		// Mock behaviour
		when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
		when(queryMock.getResultList()).thenReturn(parkingList);
	}
	
	
	
	@Test
	public void getParkingListTest() {
		// UCD.1a
		assertEquals(parkingList, sut.getParkingList());
	}
	
	@Test
	public void getParkingTest() {
		
		// UCD.2a
		vehicle = new Vehicle("1111-AAA", "Peugeot", "205");
		parking = new Parking(60, Timestamp.valueOf(LocalDateTime.of(2022, 04, 01, 11, 58)), vehicle);
		parking.setId(1);
		when(queryMock.getSingleResult()).thenReturn(parking);
	
		assertEquals(parking, sut.getParking(1));
		
		// UCD.2b
		vehicle = new Vehicle("1111-AAA", "Peugeot", "205");
		parking = new Parking(60, Timestamp.valueOf(LocalDateTime.of(2022, 04, 01, 11, 58)), vehicle);
		parking.setId(11);
		when(queryMock.getSingleResult()).thenReturn(null);
	
		assertNull(sut.getParking(11));
	}
	
	@Test
	public void addParkingTest() {
		
		// UCD.3a
		vehicle = new Vehicle("1111-AAA", "Peugeot", "205");
		parking = new Parking(60, Timestamp.valueOf(LocalDateTime.of(2022, 04, 02, 11, 58)), vehicle);
		parking.setId(11);
		
		assertTrue(sut.addParking(parking));
		
		// UCD.3b
		doThrow(new EntityExistsException()).when(entityManagerMock).persist(parking);
		
		assertFalse(sut.addParking(parking));
	}
	
	@Test
	public void modifyParkingTest() {
		// UCD.4a
		vehicle = new Vehicle("1111-AAA", "Peugeot", "205");
		parking = new Parking(85, Timestamp.valueOf(LocalDateTime.of(2022, 04, 01, 11, 58)), vehicle);
		parking.setId(1);
		
		assertEquals(sut.modifyParking(parking), parking);
		
		// UCD.4b
		vehicle = new Vehicle("1111-AAA", "Peugeot", "205");
		parking = new Parking(85, Timestamp.valueOf(LocalDateTime.of(2022, 04, 01, 11, 58)), vehicle);
		parking.setId(11);
		doThrow(new IllegalArgumentException()).when(entityManagerMock).merge(parking);

		assertNull(sut.modifyParking(parking));
	}
	
	@Test
	public void deleteParkingTest() {
		
		// UCD.5a
		vehicle = new Vehicle("1111-AAA", "Peugeot", "205");
		parking = new Parking(85, Timestamp.valueOf(LocalDateTime.of(2022, 04, 01, 11, 58)), vehicle);
		parking.setId(1);
		when(queryMock.getSingleResult()).thenReturn(parking);
		
		assertTrue(sut.deleteParking(1));
		
		// UCD.5b
		vehicle = new Vehicle("1111-AAA", "Peugeot", "205");
		parking = new Parking(85, Timestamp.valueOf(LocalDateTime.of(2022, 04, 01, 11, 58)), vehicle);
		parking.setId(11);
		when(queryMock.getSingleResult()).thenReturn(null);
		
		assertFalse(sut.deleteParking(11));
	}
}
