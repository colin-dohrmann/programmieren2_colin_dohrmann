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
	
	//Nötig um zu validieren, dass das geladene Objekt das Gleiche wie das gespeicherte ist.
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Konstruktor für die Speicherung Attribute werden initialisiert
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
	 * Getter für die Liste mit Gleichungen
	 * @return Liste mit Gleichungen
	 */
	public List<Gleichung> getListe() {
		return this.liste;
	}

	/**
	 * Setter für die Liste der Gleichungen
	 * @param liste Liste die gespeichert werden soll
	 */
	public void setListe(List<Gleichung> liste) {
		this.liste = liste;
	}

	/**
	 * Getter für die Zielformel
	 * @return die Zielformel
	 */
	public Zielformel getFormel() {
		return this.formel;
	}

	/**
	 * Setter für die Zielformel
	 * @param formel die zu speichernde Formel
	 */
	public void setFormel(Zielformel formel) {
		this.formel = formel;
	}
	
}
