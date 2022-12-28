package es.unican.ps.practica03.business;

@SuppressWarnings("serial")
public class InvalidOperation extends RuntimeException {
	public InvalidOperation(String message) {
		super(message);
	}
}
