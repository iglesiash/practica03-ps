package es.unican.ps.practica03.business;

import java.util.Date;
import es.unican.ps.practica03.model.Report;
import es.unican.ps.practica03.model.Vehicle;
import es.unican.ps.practica03.persistence.IReportsDAO;
import es.unican.ps.practica03.persistence.IVehiclesDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ReportManagement implements IReportLocal, IReportRemote {
	
	@EJB
	private IReportsDAO reportsDao;
	
	@EJB
	private IVehiclesDAO vehiclesDao;
	
	public ReportManagement() { }

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
