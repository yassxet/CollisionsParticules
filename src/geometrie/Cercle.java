/**
 * Objet dessinable de forme circulaire
 * @author Alexandre Deneault
 * @version 9/2/15
 */

package geometrie;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import enemeration.Elem;

public class Cercle extends Bloc{
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 8942264833607004230L;
	
	private double rayon=1;
	
	private final Color COULEUR=Color.RED;
	

	
	
	/**
	 * Constructeur de la classe cercle
	 * @param posX La position initiale en x de l'objet (en cm)
	 * @param posY La position initiale en y de l'objet (en cm)
	 */
	public Cercle(double posX, double posY){
		super(posX, posY);
		setCouleur(COULEUR);
	}

	
	
	/**
	 * Méthode retournant la forme du bloc
	 * @return La forme du bloc
	 */
	@Override
	public Shape creerForme() {
		return new Ellipse2D.Double(getX()-rayon, getY()-rayon, rayon*2, rayon*2);
	}
	
	
	
	
	
	

	

	/**
	 * Méthode permettant de changer le rayon du cercle
	 * @param rayon Le nouveau rayon
	 */
	public void setRayon(double rayon) {
		this.rayon = rayon;
	}
	
	/**
	 * Méthode permettant de connaitre le rayon du cercle
	 * @return rayon Le rayon du cercle
	 */
	public double getRayon() {
		return this.rayon;
	}


	/**
	 * Méthode retournant la largeur du cercle
	 * @return 	La largeur du cercle
	 */
	public double getLargeur() {
		return rayon*2;
	}


	/**
	 * Méthode retournant la hauteur du cercle
	 * @return 	La hauteur du cercle
	 */
	public double getHauteur() {
		return rayon*2;
	}

	/**
	 * Methode retournant le centre de masse du cercle
	 * @return Le centre de masse
	 */
	public Point2D getCentre(){
		return new Point2D.Double(getX(), getY());
	}
	
	
	/**
	 * Reinitialise la couleur du cercle
	 */
	public void reinitCouleur() {
		setCouleur(COULEUR);
	}


	/**
	 * Methode qui retourne le vecteur unitaire normal
	 * @param elem L'element en collision
	 * @return Le vecteur normal
	 */
	public Vecteur getNormale(Elements elem) {
		Point2D centreElem = elem.getCentreMasse();
		Point2D centre = this.getCentreMasse();
		Vecteur normale = new Vecteur(0, 0, centreElem.getX()-centre.getX(), centreElem.getY()-centre.getY());
		normale = normale.produitParScalaire(1/normale.getNorme());
		return normale;
	}


	/**
	 * Retourne le type d'element
	 */
	public Elem est() {
		return Elem.CERCLE;
	}


	


	



}
