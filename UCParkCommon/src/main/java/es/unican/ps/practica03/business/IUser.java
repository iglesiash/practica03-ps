package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.*;

import java.util.List;

public interface IUser {
	public List<Report> consultReports(String email);
	public List<Parking> consultCurrentParkingList(String email);
	public List<Parking> consultParkingHistory(String email);
}
