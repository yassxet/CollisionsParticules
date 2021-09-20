/**
 * Classe contenant les éléments de type poulie
 * @author Alexandre
 * @version 12/2/15
 */

package geometrie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import enemeration.Elem;

public class Poulie implements Elements{
	
	
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -2785900328511098689L;

	private Cercle cercle;
	private Color COULEUR_EXT = Color.GRAY;
	private Color COULEUR_INT = Color.BLACK;
	
	private double rayon = 1;
	
	private double posX, posY;
	private Ellipse2D.Double centre;
	
	private boolean animation = false;
	
	
	
	
	
	/**
	 * Constructeur de la classe Poulie
	 * @param posX La position en X du bloc
	 * @param posY La position en Y du bloc
	 */
	public Poulie(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
		cercle = new Cercle(posX, posY);
		cercle.setRayon(rayon);
		cercle.setCouleur(COULEUR_EXT);
	}
	
	/**
	 * Méthode permettant de dessiner la poulie
	 */
	public void dessiner(Graphics2D g2d, AffineTransform matMondeVersCompo) {
		Color couleurInit = g2d.getColor();
		
		cercle.dessiner(g2d, matMondeVersCompo);
		
		g2d.setColor(COULEUR_INT);
		centre = new Ellipse2D.Double(posX-rayon/2, posY-rayon/2, rayon, rayon);
		g2d.fill(matMondeVersCompo.createTransformedShape(centre));
		
		g2d.setColor(couleurInit);
	}
	
	/**
	 * Méthode booléenne retournant vrai si le point passé en paramètre est à l'intérieur de la poulie
	 * @param posX La position en X du point (en cm)
	 * @param posY La position en Y du point (en cm)
	 * @return VRAI s'il y a une collision
	 */
	public boolean contient(double posX, double posY) {
		return cercle.contient(posX, posY);
	}

	/**
	 * Méthode qui évalue si un Elements est superposé sur la poulie
	 * @param elem L'elements à tester
	 * @return VRAI s'il y a une collision
	 */
	public boolean contient(Elements elem) {
		return cercle.contient(elem);
	}

	/**
	 * Méthode qui retourne l'aire de la poulie
	 */
	public Area getAire() {
		return cercle.getAire();
	}
	
	/**
	 * Méthode qui ne fait rien (Les poulies ne bougent pas)
	 * @param deltaT L'intervalle de temps (en secondes)
	 * @param forceGrav La valeur de la force gravitationnelle
	 */
	public Elements avancerAnimation(double deltaT, double forceGrav) {
		//ne fait rien
		return this;
	}

	/**
	 * Methode qui reinitialise l'animation
	 */
	public void reinitialiserAnimation() {
		this.animation = false;
	}

	/**
	 * Méthode permettant de connaitre la position en x de la poulie
	 * @return posX La position en X
	 */
	public double getX() {
		return this.posX;
	}

	/**
	 * Méthode permettant de changer la position initiale de la poulie
	 * @param posX La nouvelle position en X
	 */
	public void setX(double posX) {
		if (!animation) {
			cercle.setX(posX);
			this.posX=posX;
		}
	}

	/**
	 * Méthode permettant de connaitre la position en y de la poulie
	 * @return posY La position en Y
	 */
	public double getY(){
		return this.posY;
	}

	
	/**
	 * Méthode permettant de changer la position initiale de la poulie (ne fonctionne pas si l'animation est en cours)
	 * @param posY La nouvelle position en Y
	 */
	public void setY(double posY){
		if (!animation) {
			cercle.setY(posY);
			this.posY=posY;
		}
	}

	/**
	 * Méthode retournant la largeur de la poulie
	 * @return 	La largeur de la poulie
	 */
	public double getLargeur() {
		return cercle.getLargeur();
	}

	/**
	 * Méthode retournant la hauteur de la poulie
	 * @return 	La hauteur de la poulie
	 */
	public double getHauteur() {
		return cercle.getHauteur();
	}

	/**
	 * Ne fait rien
	 * @param elements Liste d'elements
	 */
	public void collision(ArrayList<Elements> elements) {
		//ne fait rien
	}

	/**
	 * Indique si l'objet peut bouger lors des collisions
	 * @return FAUX
	 */
	public boolean getBougeCollision() {
		return false;
	}

	/**
	 * Methode ne faisant rien (Les poulies ne bougent pas lors de l'animation)
	 */
	public void setBougeCollision(boolean bouge) {
		//ne fait rien
	}

	/**
	 * Methode permettant de connaitre la masse de la poulie
	 * @return La masse de la poulie
	 */
	public double getMasse() {
		return cercle.getMasse();
	}

	/**
	 * Methode ne faisant rien (Les poulies ne bougent pas lors de l'animation)
	 * @return null
	 */
	public Vecteur getVitesse() {
		return null;
	}

	/**
	 * Reinitialise la couleur de la poulie
	 */
	public void reinitCouleur() {
		cercle.setCouleur(COULEUR_EXT);
	}

	/**
	 * Permet de changer la couleur de la poulie
	 * @param couleur La nouvelle couleur de la poulie
	 */
	public void setCouleur(Color couleur) {
		cercle.setCouleur(couleur);
	}

	/**
	 * Methode retournant le centre de masse de la poulie
	 * @return Le centre de masse
	 */
	public Point2D getCentre() {
		return cercle.getCentre();
	}

	/**
	 * Methode ne faisant rien (Les poulies ne bougent pas lors de l'animation)
	 */
	public void collision(Elements elements) {
		//ne fait rien
	}

	/**
	 * Methode ne faisant rien (Les poulies ne bougent pas lors de l'animation)
	 */
	public void collisionMultiple(ArrayList<Elements> liste) {
		//ne fait rien
	}

	/**
	 * Methode ne faisant rien (Les poulies ne bougent pas lors de l'animation)
	 */
	public void setVitesse(Vecteur nouvVitesse) {
		//ne fait rien
	}

	/**
	 * Methode qui retourne le vecteur unitaire normal
	 * @param elem L'element en collision
	 * @return Le vecteur normal
	 */
	public Vecteur getNormale(Elements elem) {
		return cercle.getNormale(elem);
	}

	/**
	 * Retourne le type d'element
	 */
	public Elem est() {
		return Elem.POULIE;
	}
	
	/**
	 * Methode permettant de changer le rayon de la poulie
	 * @param nouvRayon Le nouveau rayon
	 */
	public void setRayon(double nouvRayon) {
		this.rayon = nouvRayon;
		this.cercle.setRayon(nouvRayon);
	}

	/**
	 * Methode retournant le centre de masse de la poulie
	 * @return Le centre de masse
	 */
	public Point2D getCentreMasse() {
		return cercle.getCentreMasse();
	}

	/**
	 * Les poulies ne bougent pas
	 * @return Faux
	 */
	public boolean getAfficheVitesse() {
		return false;
	}

	/**
	 * Les poulies ne bougent pas
	 * @return Faux
	 */
	public boolean getAfficheAccel() {
		return false;
	}
	
	
}
