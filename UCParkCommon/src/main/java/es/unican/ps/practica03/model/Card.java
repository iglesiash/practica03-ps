package es.unican.ps.practica03.model;

public class Card extends PaymentMethod {
	private String number;
	private String cvc;
	private String owner;
	
	public Card(String number, String cvc, String owner) {
		this.number = number;
		this.cvc = cvc;
		this.owner = owner;
	}
}