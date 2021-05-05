package Oberflaeche;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import logic.*;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


/**
 * Diese Klasse zeigt die vorher eingegebenen Daten in einem Koordinatensystem an und zeigt alle Infos zum Ergebnis
 * Außerdem wird von hier die Berechnung der Nullstellen und Punkte gestartet.
 *
 * @author Colin Dohrmann.
 *         
 */
public class AnzeigePanel extends JPanel {
	private DrawPanel centerPanel;
	private Startup_window parent;
	private List<Gleichung> liste;
	private Zielformel formel;
	private MyButton btnHLinie, btnSave;
	private JPanel leftPanel;
	private JTextField txtFieldHLinie;
	private Berechnung ber1;
	private List<Nullstelle> nullPunkte;

	/**
	 * Erzeugt des AnzeigePanel, initialisiert die Attribute und baut die Panel auf.
	 * @param frame Der Parent Frame in dem das Panel angezeigt wird.
	 * @param liste eine Liste mit Gleichungen die verarbeitet werden sollen.
	 * @param formel Die Zielformel
	 */
	public AnzeigePanel(List<Gleichung> liste, Zielformel formel) {

		this.liste = liste;
		setLayout(new BorderLayout(0, 0));
		this.parent = Startup_window.getInstance();
		this.formel = formel;
		
		this.ber1 = new Berechnung();
		this.parent.setResizable(false);
			
		this.leftPanel = new JPanel();
		this.leftPanel.setLayout(new BoxLayout(this.leftPanel,BoxLayout.PAGE_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(this.leftPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
												ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.setMinimumSize(new Dimension(2000,700));
		add(scrollPane, BorderLayout.WEST);
		
		Box boxbtns = new Box(BoxLayout.X_AXIS);
		
		JLabel lblHLinie = new JLabel("Höhenlinie:  ");
		this.txtFieldHLinie = new JTextField(5);
		this.txtFieldHLinie.setMaximumSize(new Dimension(100,20));
		
		Box boxhlinie = new Box(BoxLayout.X_AXIS);
		
		this.btnSave = new MyButton("in Datei Speichern");
		this.btnHLinie = new MyButton("Höhenlinie einzeichnen");
		
		this.btnHLinie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				addHohenlinie();
			}
		});
		this.btnSave.addActionListener(new ActionListener() {
		
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				saveData();
			}
		});
		
		//Boxen werden gebaut
		boxhlinie.add(lblHLinie);
		boxhlinie.add(this.txtFieldHLinie);
		
		boxbtns.add(this.btnHLinie);
		boxbtns.add(this.btnSave);
		
		//Komponenten werden zum Panel hinzugefügt
		this.leftPanel.add(boxbtns);
		this.leftPanel.add(boxhlinie);
		addPoints();
	}
	
	
	/**
	 * Ruft die Berechnung der Nullstellen auf und fügt die Elemente des linken Panels hinzu.
	 *
	 */
	public void addPoints() {
		
		this.nullPunkte = this.ber1.berechneListe(this.liste);
		
		this.ber1.berechneEcken(this.liste);
		
		this.centerPanel = new DrawPanel(this.nullPunkte);
		this.centerPanel.repaint();
		
		//updatet die Komponenten
		SwingUtilities.updateComponentTreeUI(this.parent);
		
		//Wenn eine Formel angegeben ist wird das Maximum berechnet und ausgegeben
		if(this.formel.isEnabled() == true) {
			List<Nullstelle> ecken = this.centerPanel.getEcken();
			Nullstelle maximum = this.ber1.berechneMaximum(ecken,this.formel);
			
			JLabel maxlbl = new JLabel("Maximum:      " + String.valueOf(maximum.getX()) + "  |  " + String.valueOf(maximum.getY()));
			this.leftPanel.add(maxlbl);
		}
	
		SwingUtilities.updateComponentTreeUI(this.parent);
		add(this.centerPanel, BorderLayout.CENTER);
		
		
		
		
		
		/* -------------DEBUG---------------------
		 * Itteriert durch die Liste mit Nullpunkten und schreibt diese in die Konsole
		 * Wichtig zum Debuggen. Für den User uninterressant.
		*/
		for(int i = 0; i < this.nullPunkte.size(); i++) {
			double d = this.nullPunkte.get(i).getX();
			double dd = this.nullPunkte.get(i).getY();
			System.out.println(d + " | " + dd);
			
			String s = String.valueOf(d);
			String ss = String.valueOf(dd);
			int e = i+1;
			JLabel lbl1 = new JLabel();
			lbl1.setText("Nullstelle " + e + ":   " + s + " | " + ss);
			this.leftPanel.add(lbl1);
			repaint();
		}
	}
	
	
	
	/**
	 * fügt dem DrawPanel eine höhenlinie hinzu
	 *
	 */
	public void addHohenlinie() {
		
		boolean fehler = false;
		double d = 0;
		
		try {
			d = Double.parseDouble(this.txtFieldHLinie.getText());
		} catch (Exception e) {
			fehler = true;
		}
		
		
		if(this.formel == null || fehler) {
			this.btnHLinie.setText("Keine Zielformel definiert");
			return;
		}
		
		List<Nullstelle> n = this.ber1.berechneHoehe(this.formel,d);
		
		this.centerPanel.setHLinie(n);
		repaint();
		
		SwingUtilities.updateComponentTreeUI(this.parent);
	}
	
	
	
	/**
	 * startet die Speicherung der Anfangsdaten um sie später zu laden.
	 * Außerdem Fehlerbehandlung
	 *
	 */
	public void saveData() {
		
		Speicherung saveFile = new Speicherung(this.liste, this.formel);
		boolean saved = InputOutput.SaveinFile(saveFile);
		SaveDialog savedDial = new SaveDialog();
		savedDial.setResult(saved);
		savedDial.setVisible(true);
		
	
	}

}
