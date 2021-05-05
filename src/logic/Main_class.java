package logic;

import Oberflaeche.Startup_window;

/**
 * Main Klasse, hier startet die Software
 *
 * @author Colin Dohrmann.
 *        
 */
public class Main_class {

	/**
	 * Main Funktion der Software startet das Haupt Fenster des Programmes
	 *
	 * @param args Startargumente für die Java VM
	 */
	public static void main(String[] args) {
		
		Startup_window.getInstance();
		
		//----------DEBUG------------
		//Die Main-Methode ist beendet.
		System.out.println("Finisch");
	}

}
