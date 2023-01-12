package es.unican.ps.practica03.persistence;

import java.util.List;

import es.unican.ps.practica03.model.Report;
import jakarta.ejb.Remote;

@Remote
public interface IReportsDAO {

	
	/**
	 * Returns the list of report.
	 * @return the list of report.
	 */
	public List<Report> getReports();
	
	/**
	 * Returns the report associated to an id.
	 * @param id the report's id
	 * @return the searched report if it was found, null otherwise
	 */
	public Report getReport(long id);
	
	
	/**
	 * Adds a report.
	 * @param Report the report to be added
	 * @return true if the report could be added, false otherwise
	 */
	public boolean addReport(Report Report);
	
	/**
	 * Modifies a report.
	 * @param newReport the new report
	 * @return the report if it could be modified, null otherwise
	 */
	public Report modifyReport(Report newReport);
	
	/**
	 * Deletes the report associated to an id.
	 * @param id the report's id
	 * @return true if the report could be deleted, false otherwise
	 */
	public boolean deleteReport(long id);
	
	/**
	 * Deletes a specific report
	 * @param report the report
	 * @return true if the report could be deleted, false otherwise
	 */
	public boolean deleteReport(Report report);
}
