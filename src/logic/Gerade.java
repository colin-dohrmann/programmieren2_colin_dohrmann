package logic;

/**
 * Stellt ein Objekt bereit um eine Gerade zu speichern
 *
 * @author Colin Dohrmann.
 *         
 */
public class Gerade {

	private boolean einsetzbarX1 = true;
	private boolean einsetzbarX2 = true;
	private double x1, x2, c;
	
	/**
	 * Standard Konstruktor initialisiert die Attribute mit Standardwerten
	 *
	 */
	public Gerade() {
		this.x1 = 1;
		this.x2 = 1;
		this.c = 1;
	}
	
	/**
	 * Konstruktor 
	 *
	 * @param x1
	 * @param x2
	 * @param c
	 */
	public Gerade(double x1, double x2, double c) {
		this.x1 = x1;
		this.x2 = x2;
		this.c = c;
	}
	
	/**
	 * Konstruktor
	 *
	 * @param x1
	 * @param x2
	 * @param c
	 * @param einsetzbar1
	 * @param einsetzbar2
	 */
	public Gerade(double x1, double x2, double c, boolean einsetzbar1, boolean einsetzbar2) {
		this.x1 = x1;
		this.x2 = x2;
		this.c = c;
		this.einsetzbarX1 = einsetzbar1;
		this.einsetzbarX2 = einsetzbar2;
	}
	
	/*
	 * Getter und Setter für die Attribute
	 */
	
	//Getter
	public boolean isEinsetzbarX1() {
		return this.einsetzbarX1;
	}
	public boolean isEinsetzbarX2() {
		return this.einsetzbarX2;
	}
	public double getX1() {
		return this.x1;
	}
	public double getX2() {
		return this.x2;
	}
	public double getC() {
		return this.c;
	}
	
	//Setter
	public void setEinsetzbarX1(boolean einsetzbarX1) {
		this.einsetzbarX1 = einsetzbarX1;
	}
	public void setEinsetzbarX2(boolean einsetzbarX2) {
		this.einsetzbarX2 = einsetzbarX2;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public void setC(double c) {
		this.c = c;
	}

	
	
	
	
}
