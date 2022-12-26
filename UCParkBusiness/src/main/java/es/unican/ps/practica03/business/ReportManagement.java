package es.unican.ps.practica03.business;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;

import es.unican.ps.practica03.model.Parking;
import es.unican.ps.practica03.model.Report;
import es.unican.ps.practica03.model.User;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IReportsDAO;
import es.unican.ps.practica03.persistence.IUsersDAO;
import es.unican.ps.practica03.persistence.IVehiclesDAO;

public class ReportManagement implements IReport {
	
	private IReportsDAO reportsDao;
	private IVehiclesDAO vehiclesDao;
	
	public ReportManagement(IReportsDAO reportsDao, IVehiclesDAO vehiclesDao) {
		this.reportsDao = reportsDao;
		this.vehiclesDao = vehiclesDao;
	}

	@Override
	public void reportParking(Vehicle vehicle, String description, double price) {
		if (vehicle.getActiveParking() == null) {
			Report report = new Report(new Date(), price, description, vehicle);

			vehicle.addReport(report);
			vehiclesDao.modifyVehicle(vehicle);
			reportsDao.addReport(report);
		}
	}

	@Override
	public void voidReport(Report report) {
		Vehicle vehicle = report.getReportedVehicle();
		vehicle.removeReport(report);
		reportsDao.deleteReport(report);
	}

}
