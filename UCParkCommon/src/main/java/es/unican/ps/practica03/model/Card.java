package es.unican.ps.practica03.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Card extends PaymentMethod implements Serializable {
	private String number;
	private String cvc;
	private String owner;
	
	public Card() { }
	
	public Card(String number, String cvc, String owner) {
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
