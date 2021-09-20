/**
 * Classe créant une matrice "Monde vers composant"
 * @author Alexandre Deneault
 * @version 19/2/15
 */

package outils;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import javax.swing.JPanel;

public class MatriceMondeVersComposant extends AffineTransform{
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 214509340904746923L;
	
	
	/**
	 * Crée la matrice "Monde vers composant"
	 * @param composant Le composant à partir duquel la matrice est créé
	 * @param largeurMonde La largeur souhaitée pour le composant (en cm)
	 */
	public MatriceMondeVersComposant(JPanel composant, double largeurMonde) {
		super();
		
		double pxlParUnite= composant.getWidth()/largeurMonde;
		double hauteur = composant.getHeight()/pxlParUnite;
		
		this.scale(pxlParUnite, -pxlParUnite);
		this.translate(0, -hauteur);
	}
	
	
	/**
	 * Methode qui deplace le centre du composant
	 * @param depX La distance en X
	 * @param depY La distance en Y
	 */
	public void deplacerCentre(double depX, double depY) {
		this.translate(depX, depY);
	}
	
	/**
	 * Méthode retournant la matrice "Composant vers Monde"
	 * @return La matrice inverse (retourne null si la matrice n'est pas inversible)
	 */
	public AffineTransform matComposantVersMonde(){
		try {
			return this.createInverse();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
			return null;
		}
	}
}
