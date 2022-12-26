package es.unican.ps.practica03.model;

public class Card extends PaymentMethod {
	private String numero;
	private String cvc;
	private String titular;
	
	public Card(String numero, String cvc, String titular) {
		this.numero = numero;
		this.cvc = cvc;
		this.titular = titular;
	}
}
