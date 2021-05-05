package logic;

import java.io.Serializable;

/**
 * Stellt ein Objekt zur bereit, um die Zielformel zu speichern.
 * Es werden die x und y gespeichert.
 *
 * @author Colin Dohrmann.
 *        
 */
public class Zielformel implements Serializable{

	private double x;
	private double y;
	private boolean enabled;
	private static final long serialVersionUID = 2L;
	
	/**
	 * Konstruktor f�r die Klasse, setzt die Standardwerte f�r x und y und enabled die Zielformel
	 *
	 */
	public Zielformel() {
		this.setEnabled(false);
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Getter f�r x
	 * @return x
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Setter f�r x
	 * @param X wird als x gesetzt
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Getter f�r y
	 * @return y
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Setter f�r y
	 * @param Y wird als y gesetzt
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Getter f�r enabled
	 * @return enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Setter f�r enabled
	 * @param enabled der zu setzende Zustand
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public String toString() {
		String s = this.x + " * X1  " + this.y + " * Y";
		
		
		return s;
		
	}
}
