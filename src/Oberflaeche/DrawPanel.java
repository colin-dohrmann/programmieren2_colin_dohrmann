package Oberflaeche;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import logic.Nullstelle;
import logic.Sortierung;

/**
 * Zeichnet das Koordinatensystem, rechnet die Punkte in Koordinaten um und zeichnet diese ein.
 *
 * @author Colin Dohrmann.
 *         
 */
public class DrawPanel extends JPanel {
	
	private List<Nullstelle> punkte;
	private List<Nullstelle> eckPunkte;
	private int maxX, maxY;
	private int abstand = 40;
	private DecimalFormat df;
	private Graphics2D g2;
	private boolean hLinie;
	private List<Nullstelle> hoehenlinien;
	
	/**
	 * Konstruktor der Klasse, initialisiert die Attribute der Klasse
	 *
	 * @param nullstellen Liste mit Nullstellen die gezeichent werden sollen
	 */
	public DrawPanel(List<Nullstelle> nullstellen) {
		this.punkte = nullstellen;
		this.df = new DecimalFormat("0.0");
		this.eckPunkte = addEcken();
		this.repaint();
		this.hLinie = false;
	}

	
	@Override
	public void paintComponent(Graphics g) {
		this.g2 = (Graphics2D) g;
		
		//Sklala grenzen berechnen
		this.maxX = getWidth();
		this.maxY = getHeight();
		this.maxX -= this.abstand;
		this.maxY -= this.abstand;
		
		//Skala berechnen
		double grossterX = 0f;
		double grossterY = 0f;
		
		while(this.maxX % 10 != 0 ) {
			this.maxX++;
		}
		while(this.maxY % 10 != 0) {
			this.maxY++;
		}
		
		//Koordinatensystem zeichnen
		this.g2.setColor(Color.BLACK);
		this.g2.drawLine( this.abstand, this.maxY, this.maxX, this.maxY);
		this.g2.drawLine(this.abstand, this.abstand, this.abstand, this.maxY);
		
		//Den größten Wert herausfinden um die Skala anzupassen
		for(int i = 0; i < this.punkte.size(); i++) {
			
			Nullstelle n = this.punkte.get(i);
			
			if(grossterX < n.getX()) {
				grossterX = n.getX();
			}
			
			if(grossterY < n.getY()) {
				grossterY = n.getY();
			}
			
		}
		
		
		// Beschriftung und Skala zeichnen
		for(int i = 1; i < 11; i++) {
			float lueckeX = (this.maxX / 10) * i;
			float lueckeY = (this.maxY / 10) * i;
			
			this.g2.draw(new Line2D.Double(lueckeX, this.maxY - 5, lueckeX, this.maxY + 5 ));
			
			//Überprüfen ob System noch für nächste Skala reicht
			if(this.maxY - lueckeY > this.abstand) {
				this.g2.draw(new Line2D.Double(this.abstand - 5, this.maxY - lueckeY, this.abstand + 5, this.maxY - lueckeY ));
			}
			else {
				this.g2.draw(new Line2D.Double(this.abstand - 5, this.abstand, this.abstand + 5, this.abstand ));
			}

			//Bezeichungen werden festgelegt
			String bezX = String.valueOf(this.df.format(grossterX * (i * 0.1f)));
			String bezY = String.valueOf(this.df.format(grossterY * (i * 0.1f)));
			
			this.g2.drawString(bezX,lueckeX - 10,this.maxY + 25);
			
			
			//Überprüfen ob System noch für nächste Skala reicht
			if(this.maxY - lueckeY > this.abstand) {
				this.g2.drawString(bezY,this.abstand - 35, this.maxY - lueckeY);
			}
			else {
				this.g2.drawString(bezY,this.abstand - 35, this.abstand);
			}
			
		}
		
		//Nullstellen in Punkte umwandeln und auf Ecken überprüfen
		boolean first = true;
		for(int i = 0; i < this.punkte.size() ; i++) {
			
			Nullstelle n = this.punkte.get(i);
			
			double wertX = n.getX() / grossterX;
			double wertY = n.getY() / grossterY;
			String bezX = "";
			String bezY = "";
			
			wertX *= this.maxX;
			wertY *= this.maxY;
			
			/*
			 * Anmerkung: Ich weiß das das explizite cast von double zu float zum Verlust von genauigkeit führt.
			 * Das sollte bei unserer Berechnung jedoch keine große Auswirkung haben und die Umrechnung von float
			 * zu int ist bedeutent einfacher, da ich auf den long verzichten will.
			 */
			int pX = Math.round((float) wertX);
			int pY = Math.round((float) wertY);
			
			pY = this.maxY - pY;
			
			bezX = String.valueOf(this.df.format(wertX));
			bezY = String.valueOf(this.df.format(wertY));
			String bez = String.valueOf(this.df.format(n.getX())) + " | " + String.valueOf(this.df.format(n.getY()));
			
			if(pX <= this.abstand) {
				pX = this.abstand;
			}
			if(pY <= this.abstand) {
				pY = this.abstand;
			}
			
			//Überprüfen ob Ecke -> dann Rot sonst Blau
			if(n.isEcke()) {
				this.g2.setColor(Color.RED);
			}
			else {
				this.g2.setColor(Color.BLUE);
			}
			this.g2.fillOval(pX,pY,5,5);
			this.g2.drawString(bez, pX + 10, pY);
			n.setXpunkt(pX);
			n.setYpunkt(pY);
			
		}
		
		//Zeichnet die Linien zwischen den Eckpunkten
		for(int i = 0; i < this.eckPunkte.size() - 1; i++) {
			Nullstelle n1 = this.eckPunkte.get(i);
			Nullstelle n2 = this.eckPunkte.get(i + 1);

			this.g2.draw(new Line2D.Double(n1.getXpunkt(),n1.getYpunkt(),n2.getXpunkt(),n2.getYpunkt()));
		}
		Nullstelle nLast = this.eckPunkte.get(this.eckPunkte.size() - 1);
 		Nullstelle nFirst = this.eckPunkte.get(0);
		this.g2.draw(new Line2D.Double(nLast.getXpunkt(),nLast.getYpunkt(),nFirst.getXpunkt(),nFirst.getYpunkt()));
		
		
		//Zeichnet die Höhenlinie wenn sie ausgewählt wurde und eine Zielfunktion definiert ist
		if(this.hLinie && this.hoehenlinien != null) {
			
			for(int i = 0; i < this.hoehenlinien.size() - 1; i = i+2) {
				
				
				Nullstelle n1 = this.hoehenlinien.get(i);
				Nullstelle n2 = this.hoehenlinien.get(i + 1);
				
				int x1 = this.abstand;
				double y1 = n1.getY() / grossterY;
				
				y1 *= this.maxY;
				y1 = this.maxY - y1;
				
				if(y1 < this.abstand) { y1 = this.abstand;  }
				
				n1.setXpunkt(x1);
				n1.setYpunkt(Math.round((float) y1));
				
				
				int y2 = this.maxY;
				double x2 = n2.getX() / grossterX;
				
				x2 *= this.maxX;
				
				if(x2 <= this.abstand) {x2 = this.abstand; }
				
				n2.setXpunkt(Math.round((float) x2));
				n2.setYpunkt(y2);
				
				this.g2.setColor(Color.BLACK);
				this.g2.drawLine(n1.getXpunkt(),n1.getYpunkt(),n2.getXpunkt(),n2.getYpunkt());
				
				
				
			}
		}
		
		
		
		
		
	}//End PaintComponent
	
	/**
	 * Überprüft ob Nullstellen als Ecken definiert wurden und schreibt diese in eine Liste.
	 * Sanach sortiert ein Algorythmus die Ecken nach der größe um später die Geraden zu zeichnen
	 *
	 * @return Liste mit allen eckpunkten sortiert nach Größe x und y
	 */
	public List<Nullstelle> addEcken() {
		List<Nullstelle> ecken = new ArrayList<Nullstelle>();
		for(int i = 0; i < this.punkte.size(); i++) {
			
			Nullstelle n = this.punkte.get(i);
			
			if(n.isEcke()) {
				ecken.add(n);
			}	
		}
		
		//Sortierung wird gestartet
		Sortierung sort = new Sortierung();
		 ecken = sort.sortNullen(ecken);
		 
		 
		 
		 
		 
		 //-----------DEBUG------------------
		 
		 //Debugging für die Konsole
		System.out.println("-------------- Hier beginnt die Sortierung ------------------------------");
		
		for(int i = 0; i < ecken.size(); i++) {
			Nullstelle n = ecken.get(i);
			System.out.println(n.getX() + " | " + n.getY());
		}
		
		
		
		return ecken;
	}
	
	/**
	 * Gibt die gezeicheneten Eckpunkte zurück
	 *
	 * @return eckPunkte Liste der Eckpunkte
	 */
	public List<Nullstelle> getEcken() {
		return this.eckPunkte;
	}
	
	/**
	 * Übergibt die Liste der Höhenlinien an die Klasse
	 *
	 * @param Liste der zu zeichnenden Linien
	 */
	public void setHLinie(List<Nullstelle> hoehenlinien) {
		this.hoehenlinien = hoehenlinien;
		this.hLinie = true;
	}
	
}

/*
 * Anmerkung zu dieser Klasse:
 * Es wäre sicherlich möglich diese Berechnungen in eine andere Klasse auszulagern. Da die Berechnungen
 * aber sehr nah an der Anzeige sind und direkt der Anzeige der Daten dienen, habe ich mich dazu
 * entschieden dies in einer Klasse zu machen, die eigentlich nur einen Teil der Oberfläche darstellt.
 * 
 * 
 */