package logic;

import java.util.List;

/**
 * Einfacher Sortieralgorithmus um die Ecken nach Koordinaten zu sortieren
 *
 * @author Colin Dohrmann.
 *         
 */
public class Sortierung {
	
	/**
	 * Sortiert die Liste nach den Koordinaten
	 * 
	 * 
	 * 
	 * @param ecken  Liste mit Eckpunkten (unsortiert)
	 * @return List<Nullstelle> ecken Liste mit Eckpunkten (sortiert)
	 */
	
	public List<Nullstelle> sortNullen(List<Nullstelle> ecken) {
		
		for(int i = 0; i < ecken.size() - 1; i++) {
			Nullstelle erster = ecken.get(i);
			Nullstelle zweiter = ecken.get(i + 1);
			Nullstelle z;
			
			if(erster.getX() <= zweiter.getX()) {
				continue;
			}
			z = erster;
			ecken.set(i, zweiter);
			ecken.set(i + 1, z);
			sortNullen(ecken);
			}
		
		for(int i = 0; i < ecken.size() - 1; i++) {
			Nullstelle erster = ecken.get(i);
			Nullstelle zweiter = ecken.get(i + 1);
			Nullstelle z;
			
			if(erster.getY() <= zweiter.getY() || erster.getX() != zweiter.getX()) {
				continue;
			}
			z = erster;
			ecken.set(i, zweiter);
			ecken.set(i + 1, z);
			sortNullen(ecken);
			}
		
		
		
		return ecken;
	}
}
