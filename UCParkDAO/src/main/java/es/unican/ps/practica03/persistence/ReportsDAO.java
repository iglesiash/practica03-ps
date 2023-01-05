package es.unican.ps.practica03.persistence;
import java.util.List;

import es.unican.ps.practica03.model.Report;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ReportsDAO implements IReportsDAO {

	@PersistenceContext(unitName = "UCParkPU")
	private EntityManager em;
	
	public ReportsDAO() { }
	
	public ReportsDAO(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override 
	public List<Report> getReports() {
		Query query = em.createQuery("select r from Report r");
		return query.getResultList();
	}

	@Override
	public Report getReport(long id) {
		Query query = em.createQuery("select r from Report r where r.id = :id");
		query.setParameter("id", id);

		try {
			// Report exists in the database
			return (Report) query.getSingleResult();
		} catch (NoResultException e) {
			// Report does not exist
			return null;
		}
	}

	@Override
	public boolean addReport(Report report) {
		try {
			// New report is persisted in the database
			em.persist(report);
			return true;
		} catch (EntityExistsException e) {
			//  Report already exists in the database
			return false;
		}
	}

	@Override
	public Report modifyReport(Report newReport) {
		try {
			em.merge(newReport); // Updates the element in the database
			return newReport;
		} catch (IllegalArgumentException e) { // The report does not exist
			return null;
		}
	}

	@Override
	public boolean deleteReport(long id) {
		try {
			Report parking = getReport(id);
			if (parking != null) { // Report exists
				em.remove(id);
				return true;
			}
			else { // Report doesn't exist
				return false;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean deleteReport(Report report) {
		return deleteReport(report.getId());
	}

}
