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

	@SuppressWarnings("unchecked")
	@Override 
	public List<Parking> getParkings() {
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
		Parking parking = getParking(id);
		if (parking == null) {
			return false;
		}
		try {
			Query query = em.createQuery("delete from Parking p where p.id = :id");
			query.setParameter("id", id);
			query.executeUpdate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
