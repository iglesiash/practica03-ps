package es.unican.ps.practica03.business;

import es.unican.ps.practica03.model.Report;
import es.unican.ps.practica03.model.Vehicle;
import jakarta.ejb.Local;

@Local
public interface IReportLocal {
	public void reportParking(Vehicle vehicle, String description, double price);
	public void voidReport(Report report);
}
