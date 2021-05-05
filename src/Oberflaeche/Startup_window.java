package Oberflaeche;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;


/**
 * Haupt Fenster für das Programm.
 *
 * @author Colin Dohrmann.
 *         
 */

public class Startup_window extends JFrame{
	
	/**
	 * Variablen Definieren und erzeugen.
	 *
	 */
	private JPanel dataPanel = new JPanel();
	private MyButton btnNeu = new MyButton("Neue Gleichung");
	private MyButton btnLaden = new MyButton("Gleichung Laden");
	private JMenuBar menu = new JMenuBar();
	private JMenu opt = new JMenu("Optionen");
	private JMenuItem neuItem = new JMenuItem("Neue Gleichung");
	private JMenuItem ladenItem = new JMenuItem("Gleichung Laden");
	private JScrollPane scrollbar;
	private static Startup_window instance = null;
	
	
	/**
	 * Singleton Methode, überprüft ob schon ein Fenster erstellt wurde und gibt dieses zurück
	 *
	 * @return Instanz des Frames
	 */
	public static Startup_window getInstance() {
		if(instance == null) {
			instance = new Startup_window();
		}
		return instance;
	}
	
	
	
	/**
	 * Aufbauen des Startfensters und erzeugen des Inhaltes
	 *
	 */
	private Startup_window() {
		
		
		//Initialisiere Fenster
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,720);
		setTitle("Lösung einer Linearen Optimierung");
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		//Layout fuer das Panel festlegen
		this.dataPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		//Buttons einstellen und anordnen
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.8;
		gbc.weighty = 0.2;
		gbc.insets = new Insets(50,100,50,100);
		gbc.fill = GridBagConstraints.BOTH;
		
		this.dataPanel.add(this.btnNeu, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0.2;
		gbc.fill = GridBagConstraints.BOTH;
		this.dataPanel.add(this.btnLaden, gbc); 
		
		//Menu Initialisieren
	
				this.opt.add(this.neuItem);
				this.opt.add(this.ladenItem);
				this.menu.add(this.opt);
		
		//ScrollBar
		this.scrollbar = new JScrollPane(this.dataPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollbar.getViewport().setMinimumSize(new Dimension(400,400));
		this.scrollbar.getViewport().setPreferredSize(new Dimension(1280,720));
		
		
		//ActionListener fuer die Buttons
		
		this.neuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNeuClicked();
				
			}
		});		
		
		this.ladenItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLadenClicked();
				
			}
		});
				
		this.btnNeu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnNeuClicked();
			}
		});
		
		this.btnLaden.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLadenClicked();
			}

		});
		
		
		
		//Componenten zum Fenster hinzufuegen
		this.add(this.menu, BorderLayout.NORTH);
		this.add(this.scrollbar, BorderLayout.CENTER);
		this.setVisible(true);
		}
	
	//Button Clicked Event Neues Panel wird geladen und der Frame neu geladen
	private void btnNeuClicked() {
		GleichungEingabe newPanel = new GleichungEingabe();
		this.changePanel(newPanel);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private void btnLadenClicked() {
	GleichungLaden newPanel = new GleichungLaden();	
	this.changePanel(newPanel);
		
		
		
		SwingUtilities.updateComponentTreeUI(this);
		
	}
	
	
	/**
	 * Den Inhalt des "dataPanel" aendern um eine Neue Seite anzuzeigen
	 *
	 * @param data neues JPanel
	 */
	public void changePanel(JPanel data) {
		remove(this.dataPanel);
		this.dataPanel = data;
		this.scrollbar.getViewport().add(this.dataPanel);
		this.pack();
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	
	// Getter und Setter
	
	/**
	 * Getter für DataPanel
	 *
	 * @return dataPanel
	 */
		public JPanel getDataPanel() {
			return this.dataPanel;
		}
	
	
		/**
		 * Setter für DataPanel
		 *
		 * @param data neues DataPanel
		 */
		
		public void setDataPanel(JPanel data) {
			this.dataPanel = data;
		}

		/**
		 * Getter für das ScrollPane
		 *
		 * @return ScrollPane
		 */
		public JScrollPane getScrollbar() {
			
			return this.scrollbar;
		}
		
	
	

}
