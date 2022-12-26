package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.*;

public interface IReport {
	public void reportParking(Vehicle vehicle, String description, double price);
	public void voidReport(Report report);
}
