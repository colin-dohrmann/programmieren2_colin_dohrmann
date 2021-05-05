package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import Oberflaeche.Startup_window;

/**
 * Input und Output Klasse kümmert sich um das Speichern und Laden der Objekte
 * 
 *
 * @author Colin Dohrmann.
 *        
 */
public class InputOutput {

	private InputOutput() {
		
	}
	
	/**
	 * Speichert ein Objekt des Types Speicherung in einer Datei
	 *
	 * @param objekt das Objekt mit allen wichtigen Daten für die Speicherung
	 * @param frame JFrame des Aufrufers
	 * @return true /false Speicherung Erfolgreich / nicht erfolgreich
	 * 
	 */
	public static boolean SaveinFile(Speicherung objekt) {
		
		/*
		 * Speicherobjekt wird erzeugt. Ein Filechooser wird erzeugt, um den Speicherort zu wählen
		 * eine neue File wird erstellt. Die Auswahl wird ausgelesen und das Objekt wird in der
		 * neuen Datei gespeichert über ObjectOutputStream und FileOutputStream
		 * 
		 */
		
		Startup_window frame = Startup_window.getInstance();
		try {
		JFileChooser fs = new JFileChooser();
		File file = null;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Serializable files", "ser");
		
		fs.addChoosableFileFilter(filter);
		
		int returnVal = fs.showSaveDialog(frame);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			file =  fs.getSelectedFile();
		}
		else if(returnVal == JFileChooser.CANCEL_OPTION) { 
			
			return false; 
			
		}
		
		
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream writer = null;
		
			writer = new ObjectOutputStream(fos);
			writer.writeObject(objekt);
			
			
			
			
			//-----DEBUG-----
			System.out.println("-------------Speicherung----------------");
			System.out.println(objekt);
			//-----DEBUG END-----
			
			
			writer.close();
			
		} catch (Exception e) {
			//-----DEBUG-----
			System.out.println(e);
			System.out.println("Failure");
			//-----DEBUG END-----
			
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Lädt eingegebene Werte aus einer Datei.
	 *
	 * @param frame Parent Frame des Aufrufers
	 * @return Das Objekt welches Geladen urde
	 */
	public static Speicherung readFromFile() {
		
		/*
		 * Das gespeicherte Objekt wird über einen FileChooser lokalisiert und über 
		 * FileInputStream und ObjectInputStream geladen.
		 */
		Speicherung objekt = new Speicherung();
		Startup_window frame = Startup_window.getInstance();
		
		try {
			
			JFileChooser fs = new JFileChooser();
			File file = null;
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Serializable files", "ser");
			fs.addChoosableFileFilter(filter);
			
			int returnVal = fs.showOpenDialog(frame);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				file = fs.getSelectedFile();
			}
			else if(returnVal == JFileChooser.CANCEL_OPTION) {
				//Restart
				frame.dispose();
				Startup_window.getInstance();
				}
			
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream reader = new ObjectInputStream(fis);
			objekt = (Speicherung) reader.readObject();
			reader.close();
			
		} catch (Exception e) {
			
			System.out.println(e);
			System.out.println("Failure");
			frame.dispose();
			Startup_window.getInstance();
			
		}
		
		System.out.println("Success");
		System.out.println(objekt);
		
		return objekt;
		
	}
	
}
