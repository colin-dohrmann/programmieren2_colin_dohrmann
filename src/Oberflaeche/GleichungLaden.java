package Oberflaeche;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.*;


/**
 * Panel zum Prüfen der geladenen Ungleichungen und starten des Ladevorgangs
 *
 * @author Colin Dohrmann.
 *        
 */
public class GleichungLaden extends JPanel {

	private List<Gleichung> liste;
	private Zielformel formel;
	private Startup_window frame;
	
	/**
	 * Konstruktor für das Laden Panel ruft die Ladefunktion auf und speichert die geladenen Werte.
	 *
	 * @param frame Jframe des Aufrufers
	 */
	public GleichungLaden() {
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BoxLayout(this, 1));
		this.frame = Startup_window.getInstance();
		
		Speicherung speicher = InputOutput.readFromFile();
		
		this.liste = speicher.getListe();
		this.formel = speicher.getFormel();
		
		
		addGleichungen();
		
		
		MyButton btnWeiter = new MyButton("Weiter");
		btnWeiter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				weiter();
				
			}
		});
		
		
		add(btnWeiter);
	}
	
	/**
	 * Fügt sie geladenen Gleichungen und die Zielformel zum Panel hinzu
	 *
	 */
	private void addGleichungen() {
		
		//Wenn eine Formel eingegeben wurde, zeige dies an
		if(this.formel != null) {
			
			JLabel zielfunktionlbl = new JLabel("Zielfunktion:    " + this.formel.toString());
			this.add(Box.createRigidArea(new Dimension(0,10)));
			zielfunktionlbl.setAlignmentX(CENTER_ALIGNMENT);
			this.add(zielfunktionlbl);
		}
		
		//Itteriert durch die Liste an Gleichungen und zeigt diese auf dem Panel an
		for(int i = 0; i < this.liste.size(); i++) {
			this.add(Box.createRigidArea(new Dimension(0,10)));
			int e = i+1;
			Gleichung g = this.liste.get(i);
			JLabel glbl = new JLabel("Gleichung " + e +"    " + g.toString());
			glbl.setAlignmentX(CENTER_ALIGNMENT);
			this.add(glbl);
		}
		
	}
	
	/*Ein neues Panel wird erzeugt, mit den Daten aus dem Ladevvorgang
	 * 
	 */
	private void weiter() {
		AnzeigePanel newPanel = new AnzeigePanel(this.liste,this.formel);
		this.frame.changePanel(newPanel);
	}
	
	
}
