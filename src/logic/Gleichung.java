package logic;

import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Stellt ein Objekt für eine Gleichung bereit und speichert die Bestandteile der Gleichung
 *
 * @author Colin Dohrmann.
 *         
 */
public class Gleichung implements Serializable{
	
	private boolean kleiner;
	private double x1,x2,c;
	private static final long serialVersionUID = 3L;
	
	/**
	 * Speichert die Werte der Gleichung ab und überprüft diese auf die korrekte Form
	 *
	 * @param pnl	Content Panel
	 * @param x1	TextField x1
	 * @param x2	TextField x2
	 * @param c1	TextField Ergebnis c
	 * @param kleiner	ist die Gleichung kleiner Gleich
	 * 	
	 * @return Boolean Antwort ob das Speichern erfolgreich war
	 */
	public boolean save(JPanel pnl, JTextField x1, JTextField x2, JTextField c1, boolean kleiner) {
		int a;
		int b;
		int c;
		try {
			a = Integer.parseInt(x1.getText().trim());
			b = Integer.parseInt(x2.getText().trim());
			c = Integer.parseInt(c1.getText().trim());
		} catch (Exception e) {
			System.out.println("Fehler Falsche Eingabe");
			return false;
		}
		
		this.x1 = a;
		this.x2 = b;
		this.c = c;
		this.kleiner = kleiner;
		return true;
		
	}
	
	
	//Getter Methoden für die Attribute
	
/**
 * Getter für das Attribut X1
 *
 * @return X1
 */
	
public double getX1() {
	return this.x1;
}

/**
 * Getter für das Attribut X2
 *
 * @return X2
 */
public double getX2() {
	return this.x2;
}

/**
 * Getter für das Attribut C
 *
 * @return Ergebnis der Gleichung
 */
public double getC() {
	return this.c;
}

/**
 * Getter für die Abfrage ob die Ungleichung größer oder kleiner ist
 *
 * @return ob Kleiner ausgewählt wurde
 */
public boolean getGleich() {
	return this.kleiner;
}


//Setter methoden für die Attribute

/**
 * Setzt das Attribut X1 
 *
 * @param x1
 */
public void setX1(double x1) {
	this.x1 = x1;
}

/**
 * Setzt das Attribut X2
 *
 * @param x2
 */
public void setX2(double x2) {
	this.x2 = x2;
}

/**
 * Setzt das Attribut C
 *
 * @param c
 */
public void setC(double c) {
	this.c = c;
}

/**
 * Setzt die kleiner größer komponente
 *
 * @param kleiner ist die Gleichung kleiner=
 */
public void setGleich(boolean kleiner) {
	this.kleiner = kleiner;
}

@Override
public String toString() {
	String s = "";
	if(this.kleiner) {
		 s = this.x1 + " * X1  " + this.x2 + " * X2  <=  " + this.c;
	}
	else {
		s = this.x1 + " * X1  " + this.x2 + " * X2  >=  " + this.c;
	}
	
	
	return s;
}
	
}

	
