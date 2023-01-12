package es.unican.ps.practica03.business;

import jakarta.ejb.ApplicationException;

@SuppressWarnings("serial")
@ApplicationException
public class InvalidOperation extends RuntimeException {
	public InvalidOperation(String message) {
		super(message);
	}
}
