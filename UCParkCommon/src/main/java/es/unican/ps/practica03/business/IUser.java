package es.unican.ps.practica03.business;

import java.util.List;

import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.Report;
import es.unican.ps.practica03.model.User;

public interface IUser {
	public List<Report> consultReports(String email);
	public List<Parking> consultCurrentParkingList(String email);
	public List<Parking> consultParkingHistory(String email);
	User getUserByEmail(String email);
}
