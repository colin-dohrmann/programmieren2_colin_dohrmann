package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse berechnet alle nötigen Punkte und Werte aus den Gleichungen und kann diese dem Aufrufer zurückgeben
 * 
 *
 * @author Colin Dohrmann.
 *         
 */
public class Berechnung {
	
	private int anzahlG;
	private double[][] geraden;
	List<Nullstelle> nullstellenListe;
	
	
	/**
	 * Diese Methode ruft die methode zur berechnung der Geraden auf, speichert die Werte in 
	 * einem Array ab und gibt dieses an die Methode zur berechnung der Nullstellen.
	 * 
	 * Diese Methode dient als Einstiegspunkt in die logik. Sie wird vom außen aufgerufen und "verteilt"
	 * die Aufgaben an dei restlichen Klassen.
	 * @param liste Eine liste mit Gleichungen vom Typ Gleichung
	 * @return Liste mit den berechneten Nullstellen
	 * 
	 */
	public List<Nullstelle> berechneListe(List<Gleichung> liste) {
		this.geraden = berechneGraden(liste);
		nullstellenListe = berechneNullstellen(this.geraden);
		
		return this.nullstellenListe;
	}
	
	
	/**
	 * Diese Methode berechnet aus einer Liste von Gleichungen die erforderlichen Geraden
	 * und gibt diese in Form eines zweidimensionalen Arrays zurück.
	 * Die Sichtbarkeit ist hier protected, da die Methode nicht alleine von außen aufrufbar sein soll,
	 * doch falls diese Klasse mal abgeleitet wird, kann diese Methode genutzt werden.
	 * private wäre hier aber ebenso möglich.
	 * 
	 * @param liste Eine liste mit Gleichungen vom Typ Gleichung
	 * @return Ein zweidimensionales Array mit den Werten der Geraden
	 */
	protected double[][] berechneGraden(List<Gleichung> liste) {
		
		this.anzahlG = liste.size() + 2;
		double[][] geraden = new double[this.anzahlG][3];
		
		/*
		 * verschieben der Werte aus der Gleichungsliste in ein zweidimensionales Array mit den Werten
		 * A,B und C.
		 */
	for(int i = 0; i < this.anzahlG - 2; i++) {
			Gleichung gl = liste.get(i);
			geraden[i][0] = gl.getX1();
			geraden[i][1] = gl.getX2();
			geraden[i][2] = gl.getC();	
		}
	
	//Nichtnegativitätsbedingungen werden angehangen

	geraden[this.anzahlG  - 2][0] = 1;
	geraden[this.anzahlG - 2][1] = 0;
	geraden[this.anzahlG - 2][2] = 0;
	
	geraden[this.anzahlG - 1][0] = 0;
	geraden[this.anzahlG - 1][1] = 1;
	geraden[this.anzahlG - 1][2] = 0;
	
	return geraden;
	}
	
	/**
	 * Diese Methode führt die Berechnung der Nullstellen der zuvor aufgestellten Geraden durch.
	 * Die Methode itteriert dafür durch das Array von Methoden und überträgt jede Gerade in ein
	 * Objekt des Typs Gerade. 
	 * Zurückgegeben wird eine Liste mit allen Nullstellen als Objekte des Typs Nullstelle
	 * 
	 * @param geraden Eine Liste mit den Werten der einzelnen Geraden
	 * @return 	Eine Liste mit allen ermittelten Nullstellen vom Typ Nullstelle
	 * 
	 */
	protected List<Nullstelle> berechneNullstellen(double[][] geraden) {
		
		List<Nullstelle> nullstellen = new ArrayList<Nullstelle>();
		
		/*
		 * Itteriert durch das Array und berechnet die Nullstellen zwischen zwei Gleichungen
		 * AX2 kann bei der Berechnung vernachlässigt werden, da es im folgendem immer 1 ist.
		 */
		
		for(int i = 0; i < this.anzahlG - 1; i++) {
			
			double nullX = 0, nullY = 0;
			boolean x1Set = false, y1Set = false;
			Gerade g1 = new Gerade(geraden[i][0], geraden[i][1], geraden[i][2]);
			
			if(geraden[i][1] != 0) {
				g1.setX1(geraden[i][0] / geraden[i][1]);	//x11 = x11 / x12
				g1.setC(geraden[i][2] / geraden[i][1]);		//c1 = c1 / x12
			}
			else {
				g1.setC(geraden[i][2] / geraden[i][0]);
				nullX = g1.getC();
				x1Set = true;
				g1.setEinsetzbarX1(false);
			}
			
			if(geraden[i][0] == 0) {
				g1.setC(geraden[i][2] / geraden[i][1]);
				nullY = g1.getC();
				y1Set = true;
				g1.setEinsetzbarX1(false);
			}

			
			
		/*
		 * Itteriert für jede Gleichung i durch alle anderen Gleichungen
		 */
			 
		  for(int e = i + 1; e < this.anzahlG ; e++) {
			  
			  boolean x2Set = false, y2Set = false;
			  double xErg, cErg;
			  Nullstelle punkt = new Nullstelle();
			  Gerade g2 = new Gerade(geraden[e][0], geraden[e][1], geraden[e][2]);
			  
			  
			  if(geraden[e][1] != 0) {	//X22 nicht 0
				  g2.setX1(geraden[e][0] / geraden[e][1]);
				  g2.setX2(1);
				  g2.setC(geraden[e][2] / geraden[e][1]);
				  
			  }
			  else {	//X22 = 0
				  g2.setX1(geraden[e][0]);
				  g2.setX2(0);
				  g2.setC(geraden[e][2] / g2.getX1());
				  nullX = g2.getC();
				  x2Set = true;
				  g2.setEinsetzbarX1(false);
			  }
			  
			  if(g2.getX1() == 0) { //X21 = 0
				  nullY = g2.getC();
				  y2Set = true;
				  g2.setEinsetzbarX1(false);
			  }
			  

			 if(!x1Set && !x2Set) {
				 xErg = g1.getX1() - g2.getX1();
				  cErg = g1.getC() - g2.getC();
						
				  if(xErg == 0 || cErg == 0) {
					nullX = 0;
				  }
				  else {
					  nullX = cErg / xErg;
				  }
			 }
			
			 if(!y1Set && !y2Set) {

				 if(g1.isEinsetzbarX1()) {
					 if(g1.getX1() * nullX != 0) {
							nullY = g1.getC() / (g1.getX1() * nullX);
					 }
					 else {
						 nullY = g1.getC();
					 }
				
				 }
				 else if(g2.isEinsetzbarX1()) {
					 if(g2.getX1() * nullX != 0) {
							nullY = g2.getC() / (g2.getX1() * nullX);
					 }
					 else {
						 nullY = g2.getC();
					 }
				 }
				 
				 
			 }
			 
			 punkt.setX(nullX);
			 punkt.setY(nullY);
			 nullstellen.add(punkt);
		  }
	
		}
		return nullstellen;
	}
	
	
	
	/**
	 * berechnet ob es sich bei den Nullstellen um Ecken des Polygons handelt
	 *
	 * @param gleichungen Liste der Gleichungen für die Berechnung
	 */
	public void berechneEcken(List<Gleichung> gleichungen) {
		for(int i = 0; i < this.nullstellenListe.size(); i++) {
			boolean istEcke = true;
			Nullstelle n = this.nullstellenListe.get(i);
			
			for(int e = 0; e < gleichungen.size(); e++) {
				
				Gleichung g = gleichungen.get(e);
				
				double ergebnis = g.getX1() * n.getX() + g.getX2() * n.getY();
				
				//Wenn true dann kleiner gleich
				if(g.getGleich()) {
							if(ergebnis > g.getC()) {
								istEcke = false;
							}
				}
				
				//größer gleich
				else {
					if(ergebnis < g.getC()) {
						istEcke = false;
					}
				}
			}
			
			if(istEcke) {
				n.setEcke(true);
			}
			else {
				n.setEcke(false);
			}
			
		}
	}
	
	
	/**
	 * Berechnet das Maximum aus den Ecken des Polygons und der Zielformel
	 *
	 * @param ecken Liste der Ecken
	 * @param formel Zielformel
	 * @return Nullstelle mit der maximalen Menge
	 */
	public Nullstelle berechneMaximum(List<Nullstelle> ecken, Zielformel formel) {
		
		
		Nullstelle nMax = new Nullstelle();
		double max = 0;
		
		//Itteriert durch die Liste ecken
		for(int i = 0; i < ecken.size(); i++) {
			
			Nullstelle n = ecken.get(i);
			
			double x = n.getX();
			double y = n.getY();
			
			double erg = x * formel.getX() + y * formel.getY();
			
			
			//Wenn das ergebnis größer ist als das bisherige maximum dann ist das ergebnis das neue Maximum
			if(erg > max) {
				max = erg;
				nMax = n;
			}
			//Sonderfall könnte noch weiter bearbeitet werden
			else if(erg == max) {
			System.out.println("------------Maximum überprüfung----------");
			System.out.println("Doppeltes Maximum ");
			}
		}
		
		return nMax;
	}

/**
 * Errechnet die Werte der Höhenlinien und speichert diese in einer Liste.
 *
 * @param formel die Zielformel für die höhenlinien
 * @param eingabe Eingabe des Ergebnisses der Höhenlinie
 * @return Eine Liste mit den Hoehenlinien anfang und ende
 */
public List<Nullstelle> berechneHoehe(Zielformel formel,double eingabe) {
		
		List<Nullstelle> list = new ArrayList<Nullstelle>();
		
			double x1 = 0;
			double y1 = eingabe / formel.getY();
			
			double x2 = eingabe / formel.getX();
			double y2 = 0;
		
			//anfang und ende der Höhenlinie
			Nullstelle anfang = new Nullstelle(); 
			Nullstelle ende = new Nullstelle();
			
			//Anfangskoordinaten
			anfang.setX(x1);
			anfang.setY(y1);
			
			//Endkoordinaten
			ende.setX(x2);
			ende.setY(y2);
			
			//Zur liste hinzufügen
			list.add(anfang);
			list.add(ende);
			
			
			return list;
		
	}



}

	
