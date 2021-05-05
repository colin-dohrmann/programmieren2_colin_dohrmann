package logic;

import java.io.Serializable;
import java.util.List;

/**
 * Zu speicherndes Objekt welches beim speichern und laden genutzt wird
 *
 * @author Colin Dohrmann.
 *         
 */
public class Speicherung implements Serializable{

	private List<Gleichung> liste;
	private Zielformel formel;
	
	//N�tig um zu validieren, dass das geladene Objekt das Gleiche wie das gespeicherte ist.
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Konstruktor f�r die Speicherung Attribute werden initialisiert
	 *
	 * @param gleichungen Liste der Gleichungen aus der Eingabe
	 * @param formel Zielformel der Gleichungen
	 */
	public Speicherung(List<Gleichung> gleichungen, Zielformel formel) {
		this.liste = gleichungen;
		this.formel = formel;
	}
	
	/**
	 * Standard Konstruktor
	 *
	 */
	public Speicherung() {
		
	}

	/**
	 * Getter f�r die Liste mit Gleichungen
	 * @return Liste mit Gleichungen
	 */
	public List<Gleichung> getListe() {
		return this.liste;
	}

	/**
	 * Setter f�r die Liste der Gleichungen
	 * @param liste Liste die gespeichert werden soll
	 */
	public void setListe(List<Gleichung> liste) {
		this.liste = liste;
	}

	/**
	 * Getter f�r die Zielformel
	 * @return die Zielformel
	 */
	public Zielformel getFormel() {
		return this.formel;
	}

	/**
	 * Setter f�r die Zielformel
	 * @param formel die zu speichernde Formel
	 */
	public void setFormel(Zielformel formel) {
		this.formel = formel;
	}
	
}
