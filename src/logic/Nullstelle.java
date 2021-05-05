package logic;

/**
 * H�lt die Werte X und Y f�r das anzeigen der Nullstellen.
 *
 * @author Colin Dohrmann.
 *         
 */
public class Nullstelle {

	private double x;
	private double y;
	private boolean Ecke;
	private int xpunkt, ypunkt;
	
	
	
	/**
	 * Gibt x an den Aufrufer zur�ck
	 * 
	 * 
	 */
	public double getX() {
		return this.x;
	}
	/**
	 * Setzt den �bergebenen Wert als X Komponente der Nullstelle
	 * 
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Gibt y an den Aufrufer zur�ck
	 * 
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * Setzt den �bergebenen Wert als Y Komponente der Nullstelle
	 * 
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * setzt fest, ob die Nullstelle eine Ecke ist oder nicht
	 *
	 * 
	 */
	public void setEcke(boolean Ecke) {
		this.Ecke = Ecke;
	}
	
	/**
	 * Gibt zur�ck ob die Nullstelle eine Ecke ist
	 *
	 *
	 */
	public boolean isEcke() {
		return this.Ecke;
	}
	/**
	 * Gibt die X Koordinate auf dem DrawPanel zur�ck
	 * 
	 */
	public int getXpunkt() {
		return xpunkt;
	}
	/**
	 * Setzt die Koordinate des X Punkts
	 * 
	 */
	public void setXpunkt(int xpunkt) {
		this.xpunkt = xpunkt;
	}
	/**
	 * Gibt die Y Koordinate auf dem DrawPanel zur�ck
	 * 
	 */
	public int getYpunkt() {
		return ypunkt;
	}
	/**
	 * Setzt die Koordinate des Y Punkts
	 * 
	 */
	public void setYpunkt(int ypunkt) {
		this.ypunkt = ypunkt;
	}
	
	
}
