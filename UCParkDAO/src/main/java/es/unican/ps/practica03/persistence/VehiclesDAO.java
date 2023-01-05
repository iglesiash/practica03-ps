package es.unican.ps.practica03.persistence;

import java.util.List;

import es.unican.ps.practica03.model.Vehicle;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class VehiclesDAO implements IVehiclesDAO {

	@PersistenceContext(unitName = "UCParkPU")
	private EntityManager em;

	public VehiclesDAO() { }

	public VehiclesDAO(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override 
	public List<Vehicle> getVehicles() {
		Query query = em.createQuery("select v from Vehicle v");
		return query.getResultList();
	}

	@Override
	public Vehicle getVehicle(String numberPlate) {
		Query query = em.createQuery("select v from Vehicle v where v.numberPlate = :numberPlate");
		query.setParameter("numberPlate", numberPlate);

		try {
			// Vehicle exists in the database
			return (Vehicle) query.getSingleResult();
		} catch (NoResultException e) {
			// Vehicle does not exist
			return null;
		}
	}

	@Override
	public boolean addVehicle(Vehicle vehicle) {
		try {
			// New vehicle is persisted in the database
			em.persist(vehicle);
			return true;
		} catch (EntityExistsException e) {
			//  Vehicle already exists in the database
			return false;
		}
	}

	@Override
	public Vehicle modifyVehicle(Vehicle newVehicle) {
		try {
			em.merge(newVehicle); // Updates the element in the database
			return newVehicle;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public boolean deleteVehicle(String numberPlate) {
		try {
			Vehicle vehicle = getVehicle(numberPlate);
			if (vehicle != null) { // Vehicle exists
				em.remove(numberPlate);
				return true;
			}
			else { // Vehicle doesn't exist
				return false;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
