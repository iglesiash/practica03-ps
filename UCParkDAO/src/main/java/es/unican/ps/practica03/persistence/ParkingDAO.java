package es.unican.ps.practica03.persistence;

import java.util.List;

import es.unican.ps.practica03.model.Parking;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ParkingDAO implements IParkingDAO {

	@PersistenceContext(unitName = "UCParkPU")
	private EntityManager em;

	public ParkingDAO() { }

	public ParkingDAO(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override 
	public List<Parking> getParkingList() {
		Query query = em.createQuery("select p from Parking p");
		return query.getResultList();
	}

	@Override
	public Parking getParking(long id) {
		Query query = em.createQuery("select p from Parking p where p.id = :id");
		query.setParameter("id", id);

		try {
			// Parking exists in the database
			return (Parking) query.getSingleResult();
		} catch (NoResultException e) {
			// Parking does not exist
			return null;
		}
	}

	@Override
	public boolean addParking(Parking parking) {
		try {
			// New parking is persisted in the database
			em.persist(parking);
			return true;
		} catch (EntityExistsException e) {
			//  Parking already exists in the database
			return false;
		} catch (IllegalArgumentException e) {
			// Argument is not a parking
			return false;
		}
	}

	@Override
	public Parking modifyParking(Parking newParking) {
		try {
			em.merge(newParking); // Updates the element in the database
			return newParking;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public boolean deleteParking(long id) {
		try {
			Parking parking = getParking(id);
			if (parking != null) { // Parking exists
				em.remove(id);
				return true;
			}
			else { // Parking doesn't exist
				return false;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
