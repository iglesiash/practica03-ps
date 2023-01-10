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
	
	public Card() {
		super();
	}
	
	public Card(String number, String cvc, String owner) {
		super();
		this.number = number;
		this.cvc = cvc;
		this.owner = owner;
	}

	public String getNumber() {
		return number;
	}

	public String getCvc() {
		return cvc;
	}

	public String getOwner() {
		return owner;
	}
}
