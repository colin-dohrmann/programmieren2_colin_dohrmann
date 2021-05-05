package Oberflaeche;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Diese Klasse erstellt einen Dialog in dem der Nutzer die Anzahl an Ungleichungen angeben kann
 *
 * @author Colin Dohrmann.
 *         
 */
public class AnzahlEingabe extends JDialog implements ActionListener{

	JLabel lbl;
	JTextField glInput;
	JButton btn;
	GridBagConstraints gbc;
	int anzahl = 2;
	GleichungEingabe gl;
	
	/**
	 * Konstruktor für den Dialog. Baut das Fenster auf 
	 *
	 * @param gle Panel für die Eingabe der Daten
	 */
	public AnzahlEingabe(GleichungEingabe gle) {
		this.gl = gle;
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.weightx = 0.5;
		this.gbc.weighty = 0.3;
		 this.lbl = new JLabel("Wie viele Ungleichungen soll es geben?");
		 this.glInput = new JTextField(5);
		 this.btn = new JButton("Speichern");
		add(this.lbl,this.gbc);
		
		this.gbc.gridy = 1;
		add(this.glInput,this.gbc);
		
		this.gbc.gridy = 2;
		add(this.btn,this.gbc);
		
		this.btn.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.gl.setAnzahl(Integer.parseInt(this.glInput.getText()));
		this.dispose();
	}
	
	
	
}
