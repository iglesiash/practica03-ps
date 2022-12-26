package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.*;
public interface IParking {

	public Parking consultParking(String numberPlate);
	public void registerParking(Vehicle vehicle, int minutes);
	public Parking extendParkingTime(long parkingId, int minutes);
	public void finishParking(long parkingId);
}
