package Oberflaeche;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Dieser Dialog informiert den Nutzer �ber das gelingen, oder fehlschlagen der Speicherung der Werte.
 *
 * @author Colin Dohrmann.
 *         
 */
public class SaveDialog extends JDialog {

	
	private JLabel message;
	
	/**
	 * Konstruktor der Klasse, erzeugt den JDialog
	 *
	 */
	public SaveDialog() {
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	}
	
	/**
	 * erstellt basierend auf dem Parameter eine Nachricht �ber den Stand der Speicherung
	 *
	 * @param result Ob das Speichern erfolgreich war
	 */
	public void setResult(boolean result) {
		if(result) {
			this.message = new JLabel("Das Speichern war erfolgreich, \n"
					+ "sie k�nnen das Programm jetzt schlie�en");
			this.add(this.message);
		}
		else {
			this.message = new JLabel("Das Speichern war nicht erfolgreich, \n"
					+ "bitte versuchen sie es erneut");
			this.add(this.message);
			
		}
		SwingUtilities.updateComponentTreeUI(this);
	}

}
