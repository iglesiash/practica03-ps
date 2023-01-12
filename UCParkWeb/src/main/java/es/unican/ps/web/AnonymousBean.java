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

	public AnonymousBean() { }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {		
		return anonymousUserRemote.login(email, password) == null ? "login.xhtml" : "index.xhtml";
	}
	
	public String signUp() {
		user = new User(email, password);
		PaymentMethod card = new Card(cardNumber, cvc, owner);
		return anonymousUserRemote.register(user, card) != null ? "login.xhtml" : "index.xhtml";
	} 
}
