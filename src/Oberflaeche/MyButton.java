package Oberflaeche;

import java.awt.Color;
import javax.swing.JButton;

/**
 * Einfache Buttons mit eigenem Aussehen 
 *
 * @author Colin Dohrmann.
 *         
 */
public class MyButton extends JButton{
	
	/**
	 * Standard Konstruktor
	 *
	 */
	public MyButton() {
		setBackground(Color.BLUE);
		setForeground(Color.BLACK);
	}
	
	
	/**
	 * Konstruktor setzt den Text des Buttons beim initialisieren
	 *
	 * @param name Text des Buttons
	 */
	public MyButton(String name) {
		setBackground(Color.BLUE);
		setForeground(Color.BLACK);
		setText(name);
	}
}
