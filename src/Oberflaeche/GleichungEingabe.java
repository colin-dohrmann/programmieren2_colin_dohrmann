package Oberflaeche;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import logic.Gleichung;
import logic.Zielformel;
import javax.swing.Timer;


/**
 * Panel zum Eingeben von Gleichungen.
 * Gleichungen werden auf Grundlage des Ergebnisses von AnzahlEingabe dynamisch erzeugt
 * und am Ende gespeichert und von dem nächsten Panel weiterverarbeitet.
 *  
 *
 * @author Colin Dohrmann.
 *        
 */
public class GleichungEingabe extends JPanel implements ActionListener{

	private Startup_window parent;
	private int anzahl = 2;
	private BoxLayout layout;
	private MyButton btnSave;
	private List<Box> liste;
	private List<Gleichung> glListe;
	private Zielformel formel;
	private JTextField zfunktionX;
	private JTextField zfunktionY;
	
	/**
	 * Konstruktor für das Eingabe Panel. Erstellt das Oanel und füllt den Inhalt
	 *
	 * @param frame JFrame der Anwendung
	 */
	public GleichungEingabe() {
		
		this.parent = Startup_window.getInstance();
		AnzahlEingabe ae = new AnzahlEingabe(this);
		ae.setVisible(true);
		this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(this.layout);
		this.formel = new Zielformel();
		
		this.liste = new ArrayList<Box>();
		this.glListe = new ArrayList<Gleichung>();
		this.btnSave = new MyButton("Speichern");
		this.btnSave.addActionListener(this);
		
		JLabel lblX = new JLabel(" X ");
		JLabel lblY = new JLabel(" Y ");
		JLabel zfunktionlbl = new JLabel("Zielfunktion:   ");
		this.zfunktionX = new JTextField(5);
		this.zfunktionX.setMaximumSize(new Dimension(75,30));
		this.zfunktionY = new JTextField(5);
		this.zfunktionY.setMaximumSize(new Dimension(75,30));
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(zfunktionlbl);
		box.add(this.zfunktionX);
		box.add(lblX);
		box.add(this.zfunktionY);
		box.add(lblY);
		add(box);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		setBackground(Color.GREEN);
		
		addFields(this.anzahl);
		
		add(this.btnSave);

		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.btnSave)) {
			saveInputs();
		}
		
		
	}
	
	/**
	 * setzt sie Anzahl der Gleichungen
	 *
	 * @param anz 
	 */
	public void setAnzahl(int anz) {
		this.anzahl = anz;
	}
		private void addFields(int anzahl) {
		
			/* 
			 * Itteriert durch die vorher angegebene Anzahl und erstellt eine neue Box mit Feldern
			*	Fügt diese dem Panel hinzu
			*/
			
		for(int i = 0; i < anzahl; i++) {
			int l = i+1;
			String name = "Gleichung" + l + ":\t";
			JLabel lbl = new JLabel(name);
			JLabel lblPlus = new JLabel("  X1   +   ");
			JLabel lblx2 = new JLabel("  X2   ");
			JTextField txtField1 = new JTextField(5);
			JTextField txtField2 = new JTextField(5);
			JTextField txtField3 = new JTextField(5);
			JRadioButton rbtnk = new JRadioButton("<=");
			JRadioButton rbtng = new JRadioButton(">=");
			ButtonGroup btngrp = new ButtonGroup();
			btngrp.add(rbtnk);
			btngrp.add(rbtng);
			lbl.setPreferredSize(new Dimension(100,10));
			txtField1.setMaximumSize(new Dimension(75,30));
			txtField2.setMaximumSize(new Dimension(75,30));
			txtField3.setMaximumSize(new Dimension(75,30));
			Box box = new Box(BoxLayout.X_AXIS);
			box.add(Box.createRigidArea(new Dimension(10,0)));
			box.add(lbl);
			box.add(txtField1);
			box.add(lblPlus);
			box.add(txtField2);
			box.add(lblx2);
			box.add(rbtnk);
			box.add(rbtng);
			box.add(txtField3);
			this.add(box);
			this.add(Box.createRigidArea(new Dimension(0, 10)));		
			this.liste.add(box);
			
		}
	}
	//Saved die Inputs
	private void saveInputs() {
		boolean b = true;
		
		try {
			String s1 = this.zfunktionX.getText();
			String s2 = this.zfunktionY.getText();
			double d1 = Double.parseDouble(s1);
			double d2 = Double.parseDouble(s2);
			this.formel.setX(d1); 
			this.formel.setY(d2);
			this.formel.setEnabled(true);
		} catch (Exception e) {
			this.formel.setEnabled(false);
		}
	
	
		
			
		
		
		//Fügt jede Eingabe einer Liste hinzu und überprüft diese auf richtigkeit danach wird die Gleichung gespeichert
		for(int i = 0;i < this.liste.size();i++) {
		
		Box box = this.liste.get(i);
		JRadioButton rb1 = (JRadioButton) box.getComponent(6);
		JRadioButton rb2 = (JRadioButton) box.getComponent(7);
		JTextField t1 = (JTextField) box.getComponent(2);
		JTextField t2 = (JTextField) box.getComponent(4);
		JTextField t3 = (JTextField) box.getComponent(8);
		
		boolean b1 = true;
		
		if(rb1.isSelected()) {
			b1 = true;
			}
		else if(rb2.isSelected()) {
			b1 = false;
			}
		else {
			b = false;
			}
		Gleichung g = new Gleichung();
		b = g.save(this,t1,t2,t3,b1);
		
		if(!b) {
			this.btnSave.setText("Fehler bitte korrigieren");
			this.setBackground(Color.RED);
			
			return;
			}
		else {
			this.btnSave.setText("Gespeichert");
			this.glListe.add(g);
			}
		}
		
		nextPanel(this.parent);
	}
	
	//nächstes Panel wird erzeugt
	private void nextPanel(Startup_window frame) {
		
		JPanel data = frame.getDataPanel();
		frame.remove(data);
		data = new AnzeigePanel(this.glListe,this.formel);
		frame.getScrollbar().getViewport().add(data);
		frame.pack();
		SwingUtilities.updateComponentTreeUI(frame);
	}
	
	
	
	
	
	

}
