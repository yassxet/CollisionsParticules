/**
 * Objet dessinable de forme carre
 * @author Alexandre Deneault
 * @version 9/2/15
 */

package geometrie;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import enemeration.Elem;

public class Carre extends Bloc{
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -6823659448193254432L;
	
	private double hauteur=2, largeur=2;
	private Vecteur normaleHaut;
	private Vecteur normalBas;
	private Vecteur normalDroite;
	private Vecteur normalGauche;
	private Vecteur normalHD;
	private Vecteur normalDB;
	private Vecteur normalBG;
	private Vecteur normalGH;

	
	private final Color COULEUR=Color.BLUE;
	  

	
	
	/**
	 * Constructeur de la classe carre
	 * @param posX La position initiale en x de l'objet (en cm)
	 * @param posY La position initiale en y de l'objet (en cm)
	 */
	public Carre(double posX, double posY){
		super(posX, posY);
		setCouleur(COULEUR);
	}
	
	
	/**
	 * Méthode retournant la forme du bloc
	 * @return La forme du bloc
	 */
	@Override
	public Shape creerForme() {
		normaleHaut = new Vecteur(this.getX(), this.getY(), 0, 1);
		normalBas = new Vecteur(this.getX(), this.getY(), 0, -1);
		normalDroite = new Vecteur(this.getX(), this.getY(), 1, 0);
		normalGauche = new Vecteur(this.getX(), this.getY(), -1, 0);
		
		normalHD = new Vecteur(this.getX(), this.getY(), this.getHauteur(), this.getLargeur());
		normalHD.setNorme(1);
		normalDB = new Vecteur(this.getX(), this.getY(), this.getHauteur(), -this.getLargeur());
		normalDB.setNorme(1);
		normalBG  = new Vecteur(this.getX(), this.getY(), -this.getHauteur(), -this.getLargeur());
		normalBG.setNorme(1);
		normalGH = new Vecteur(this.getX(), this.getY(), -this.getHauteur(), this.getLargeur());
		normalGH.setNorme(1);
		return new Rectangle2D.Double(getX()-largeur/2.0, getY()-hauteur/2.0, largeur, hauteur);
	}
	
	
	/**
	 * Méthode permettant de changer la largeur du carre
	 * @param largeur La nouvelle largeur
	 */
	public void setLargeur(double largeur) {
		this.largeur=largeur;
	}
	
	/**
	 * Méthode permettant de changer la hauteur du carre
	 * @param hauteur La nouvelle hauteur
	 */
	public void setHauteur(double hauteur) {
		this.hauteur=hauteur;
	}

	/**
	 * Méthode retournant la largeur du carre
	 * @return 	La largeur du carre
	 */
	public double getLargeur() {
		return largeur;
	}

	/**
	 * Méthode retournant la hauteur du carre
	 * @return 	La hauteur du carre
	 */
	public double getHauteur() {
		return hauteur;
	}
	
	/**
	 * Methode retournant le centre de masse du carre
	 * @return Le centre de masse
	 */
	public Point2D getCentre(){
		return new Point2D.Double(getX(), getY());
	}

	/**
	 * Reinitialise la couleur du carre
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
		Vecteur position = new Vecteur(0, 0, centreElem.getX()-centre.getX(), centreElem.getY()-centre.getY());
		
		AffineTransform matRot = this.getRotation();
		AffineTransform matInv = new AffineTransform();
		try {
			matInv = matRot.createInverse();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		
		normaleHaut.transformer(matRot);
		normalBas.transformer(matRot);
		normalDroite.transformer(matRot);
		normalGauche.transformer(matRot);
		
		normalHD.transformer(matRot);
		normalDB.transformer(matRot);
		normalBG.transformer(matRot);
		normalGH.transformer(matRot);
		
		
		double haut = normaleHaut.produitScalaire(position)/hauteur;
		double bas = normalBas.produitScalaire(position)/hauteur;
		double droite = normalDroite.produitScalaire(position)/largeur;
		double gauche = normalGauche.produitScalaire(position)/largeur;
		
		double hautDroite = normalHD.produitScalaire(position)/(hauteur*largeur/2)*3/4;
		double droiteBas = normalDB.produitScalaire(position)/(hauteur*largeur/2)*3/4;
		double basGauche = normalBG.produitScalaire(position)/(hauteur*largeur/2)*3/4;
		double gaucheHaut = normalGH.produitScalaire(position)/(hauteur*largeur/2)*3/4;
		
		
		Vecteur normale;
		if(haut>bas&&haut>droite&&haut>gauche&&haut>hautDroite&&haut>droiteBas&&haut>basGauche&&haut>gaucheHaut){
			normale = new Vecteur(normaleHaut);
		} else {
			if(bas>droite&&bas>gauche&&bas>hautDroite&&bas>droiteBas&&bas>basGauche&&bas>gaucheHaut) {
				normale = new Vecteur(normalBas);
			} else {
				if(gauche>droite&&gauche>hautDroite&&gauche>droiteBas&&gauche>basGauche&&gauche>gaucheHaut) {
					normale = new Vecteur(normalGauche);
				} else {
					if (droite>hautDroite&&droite>droiteBas&&droite>basGauche&&droite>gaucheHaut) {
						normale = new Vecteur(normalDroite);
					} else {
						if (hautDroite>droiteBas&&hautDroite>basGauche&&hautDroite>gaucheHaut) {
							normale = new Vecteur(normalHD);
						} else {
							if (droiteBas>basGauche&&droiteBas>gaucheHaut) {
								normale = new Vecteur(normalDB);
							} else {
								if (basGauche>gaucheHaut) {
									normale = new Vecteur(normalBG);
								} else {
									normale = new Vecteur(normalGH);
								}
							}
						}
					}
				}
			}
		}
		
		
		normaleHaut.transformer(matInv);
		normalBas.transformer(matInv);
		normalDroite.transformer(matInv);
		normalGauche.transformer(matInv);
		
		normalHD.transformer(matInv);
		normalDB.transformer(matInv);
		normalBG.transformer(matInv);
		normalGH.transformer(matInv);
		
		return normale;
	}
	
	/**
	 * Retourne le type d'element
	 */
	public Elem est() {
		return Elem.CARRE;
	}
	
		
}