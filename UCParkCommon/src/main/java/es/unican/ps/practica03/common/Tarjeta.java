package es.unican.ps.practica03.common;

public class Tarjeta extends MedioPago {
	private String numero;
	private String cvc;
	private String titular;
	
	public Tarjeta(String numero, String cvc, String titular) {
		this.numero = numero;
		this.cvc = cvc;
		this.titular = titular;
	}
}
