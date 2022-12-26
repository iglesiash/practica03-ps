package es.unican.ps.practica03.business;

@SuppressWarnings("serial")
public class OperacionNoValida extends RuntimeException {
	public OperacionNoValida(String message) {
		super(message);
	}
}
