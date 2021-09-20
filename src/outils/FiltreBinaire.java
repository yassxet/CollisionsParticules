/**
 * Filtre pour les fichiers binaires
 * @author Alexandre
 * @version 28/3/15
 */

package outils;

import java.io.File;
import java.io.FilenameFilter;

public class FiltreBinaire implements FilenameFilter {
	
	/**
	 * Methode qui determine si le fichier doit etre retourne
	 * @param fichier Le fichier
	 * @param nom Le nom du fichier
	 * @return Vrai si le nom du fichier termine par .bin
	 */
	public boolean accept(File fichier, String nom) {
		if (nom.endsWith(".bin"))
			return true;
		return false;
	}
	
}
