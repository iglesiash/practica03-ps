package es.unican.ps.practica03.persistence;

import java.util.List;

import es.unican.ps.practica03.model.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class UsersDAO implements IUsersDAO {

	@PersistenceContext(unitName = "UCParkPU")
	private EntityManager em;

	public UsersDAO() { }

	public UsersDAO(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override 
	public List<User> getUsers() {
		Query query = em.createQuery("select u from User u");
		return query.getResultList();
	}

	@Override
	public User getUser(String email) {
		Query query = em.createQuery("select u from User u where u.email = :email");
		query.setParameter("email", email);

		try {
			// User exists in the database
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			// User does not exist
			return null;
		}
	}

	@Override
	public boolean addUser(User user) {
		try {
			// New user is persisted in the database
			em.persist(user);
			return true;
		} catch (EntityExistsException e) {
			//  User already exists in the database
			return false;
		}
	}

	@Override
	public User modifyUser(User newUser) {
		try {
			em.merge(newUser); // Updates the element in the database
			return newUser;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public boolean deleteUser(String email) {
		try {
			User user = getUser(email);
			if (user != null) { // User exists
				em.remove(email);
				return true;
			}
			else { // User doesn't exist
				return false;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
