package es.unican.ps.practica03.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity @Table(name = "Payment")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PaymentMethod implements Serializable {
	@Id @GeneratedValue
	private long id;
	
	/**
	 * Default constructor.
	 */
	public PaymentMethod () {}	
}
