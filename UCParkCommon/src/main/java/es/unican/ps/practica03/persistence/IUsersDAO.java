package es.unican.ps.practica03.persistence;

import java.util.List;

import es.unican.ps.practica03.model.User;

public interface IUsersDAO {
	
	/**
	 * Returns the list of users.
	 * @return the list of users
	 */
	public List<User> getUsers();
	
	/**
	 * Returns the user given an email.
	 * @param email the user's email
	 * @return the searched user if it was found, null otherwise
	 */
	public User getUser(String email);
	
	
	/**
	 * Adds a user.
	 * @param user the user to be added
	 * @return true if the user could be added, false otherwise
	 */
	public boolean addUser(User user);
	
	/**
	 * Modifies a user.
	 * @param newUser the new user
	 * @return the user if it could be modified, null otherwise
	 */
	public User modifyUser(User newUser);
	
	/**
	 * Deletes a user.
	 * @param email the user's email
	 * @return true if the user could be deleted, false otherwise
	 */
	public boolean deleteUser(String email);
	
}
