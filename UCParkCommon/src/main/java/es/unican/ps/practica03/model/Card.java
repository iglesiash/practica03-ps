package es.unican.ps.practica03.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Card extends PaymentMethod implements Serializable {
	@Id @GeneratedValue
	private int id;
	private String number;
	private String cvc;
	private String owner;	
	
	@ManyToOne
	private User user;
	
	/**
	 * Default constructor
	 */
	public Card() {
		super();
	}
	
	/**
	 * Constructor
	 * @param number: the card number
	 * @param cvc: the card cvc
	 * @param owner: the card owner
	 */
	public Card(String number, String cvc, String owner) {
		super();
		this.number = number;
		this.cvc = cvc;
		this.owner = owner;
	}

	/**
	 * Returns the card number
	 * @return the card number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Returns the card cvc
	 * @return the card cvc
	 */
	public String getCvc() {
		return cvc;
	}

	/**
	 * Returns the card owner
	 * @return the card owner
	 */
	public String getOwner() {
		return owner;
	}
}
