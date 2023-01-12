package es.unican.ps.web;

import java.io.Serializable;

import es.unican.ps.practica03.business.IAnonymousUserRemote;
import es.unican.ps.practica03.model.Card;
import es.unican.ps.practica03.model.PaymentMethod;
import es.unican.ps.practica03.model.User;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class AnonymousBean implements Serializable {

	@EJB
	private IAnonymousUserRemote anonymousUserRemote;
	
	private String email;
	private String password;
	private User user;
	private String cardNumber;
	private String cvc;
	private String owner;

	/**
	 * Default constructor.
	 */
	public AnonymousBean() { }

	/**
	 * Returns the user.
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * @param user: the user to be set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the card number.
	 * @return the card number
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * Returns the user's email.
	 * @return the users's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email.
	 * @param email: the user's email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the user's password.
	 * @return the user's password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the user's password.
	 * @param password: the user's password to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Attempts a login for a user.
	 * @return the login page if the login fails, the index page otherwise
	 */
	public String login() {		
		return anonymousUserRemote.login(email, password) == null ? "login.xhtml" : "index.xhtml";
	}
	
	/**
	 * Attempts a sign up for a user.
	 * @return the login page if the sign up fails, the index page otherwise
	 */
	public String signUp() {
		user = new User(email, password);
		PaymentMethod card = new Card(cardNumber, cvc, owner);
		return anonymousUserRemote.register(user, card) != null ? "login.xhtml" : "index.xhtml";
	} 
}
