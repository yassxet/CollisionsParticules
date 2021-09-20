/**
 * Objet dessinable de forme triangulaire
 * @author Alexandre Deneault
 * @version 9/2/15
 */

package geometrie;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import enemeration.Elem;

public class Triangle extends Bloc{

	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -469556032051560670L;
	
	private double cote1=2, cote2=2;
	private final int ANGLE_INIT = 60;
	private double angle;
	private double hauteur;
	
	
	private Vecteur normalBas;
	private Vecteur normalGauche;
	private Vecteur normalDroite;
	
	private Vecteur normalHaut;
	private Vecteur normalGB;
	private Vecteur normalDB;
	
	private final Color COULEUR=Color.YELLOW;
	
	
	
	/**
	* Constructeur de la classe triangle
	 * @param posX La position initiale en x de l'objet (en cm)
	 * @param posY La position initiale en y de l'objet (en cm)
	 */
	public Triangle(double posX, double posY){
		super(posX, posY);
		angle = calculAngle(ANGLE_INIT);
		calculHauteur();
		setCouleur(COULEUR);
	}
	
	
	/**
	 * Méthode retournant la forme du bloc
	 * @return La forme du bloc
	 */
	@Override
	public Shape creerForme() {
		normalBas = new Vecteur(this.getX(), this.getY(), 0, -1);
		normalGauche = new Vecteur(this.getX(), this.getY(), -Math.cos(angle), Math.sin(angle));
		normalDroite = new Vecteur(this.getX(), this.getY(), Math.cos(angle), Math.sin(angle));
		
		normalHaut = new Vecteur(this.getX(), this.getY(), 0, 1);
		normalDB = new Vecteur(this.getX(), this.getY(), Math.cos(angle), -Math.sin(angle));
		normalGB = new Vecteur(this.getX(), this.getY(), -Math.cos(angle), -Math.sin(angle));
		
		Path2D.Double triangle=new Path2D.Double();
		triangle.moveTo(getX()+cote1/2, getY()-hauteur/2);
		triangle.lineTo(getX()-cote1/2, getY()-hauteur/2);
		triangle.lineTo(getX()-cote1/2-Math.cos(angle)*cote2, getY()+hauteur/2);
		triangle.closePath();
		return triangle;
	}
	
	
	/**
	 * Méthode permettant de changer un angle en radian
	 * @param degre L'angle en degré
	 * @return L'angle en radian
	 */
	private double calculAngle(double degre){
		return degre*2*Math.PI/180;
	}
	
	/**
	 * Calcule la hauteur du triangle
	 */
	private void calculHauteur(){
		hauteur = Math.sin(angle)*cote2;
	}
	
	
	/**
	 * Méthode permettant de changer la longueur du cote 1
	 * @param cote1 La nouvelle longueur du coté 1
	 */
	public void setCote1(double cote1) {
		this.cote1 = cote1;
		calculHauteur();
	}

	/**
	 * Méthode permettant de changer la longueur du cote 2
	 * @param cote2 La nouvelle longueur du coté 2
	 */
	public void setCote2(double cote2) {
		this.cote2 = cote2;
		calculHauteur();
	}
	
	/**
	 * Méthode permettant de changer l'angle du triangle
	 * @param angle La nouvelle mesure de l'angle en degré
	 */
	public void setAngle(double angle) {
		this.angle = calculAngle(angle);
		calculHauteur();
		
	}


	/**
	 * Méthode retournant la largeur du triangle
	 * @return 	La largeur du triangle
	 */
	public double getLargeur() {
		return cote1;
	}


	/**
	 * Méthode retournant la hauteur du triangle
	 * @return 	La hauteur du triangle
	 */
	public double getHauteur() {
		return hauteur;
	}
	
	
	/**
	 * Methode retournant le centre de masse du triangle
	 * @return Le centre de masse
	 */
	public Point2D getCentre(){
		return new Point2D.Double(getX(), getY());
	}
	
	
	/**
	 * Reinitialise la couleur du triangle
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
		
		normalBas.transformer(matRot);
		normalDroite.transformer(matRot);
		normalGauche.transformer(matRot);
		
		normalHaut.transformer(matRot);
		normalDB.transformer(matRot);
		normalGB.transformer(matRot);
		
		
		double bas = normalBas.produitScalaire(position);
		double droite = normalDroite.produitScalaire(position);
		double gauche = normalGauche.produitScalaire(position);
		
		double haut = normalHaut.produitScalaire(position)*3/4;
		double droiteBas = normalDB.produitScalaire(position)*3/4;
		double gaucheBas = normalGB.produitScalaire(position)*3/4;
		
		Vecteur normale;
		if(bas>droite&&bas>gauche&&bas>haut&&bas>droiteBas&&bas>gaucheBas) {
			normale = new Vecteur(normalBas);
		} else {
			if(gauche>droite&&gauche>haut&&gauche>droiteBas&&gauche>gaucheBas) {
				normale = new Vecteur(normalGauche);
			} else {
				if(droite>haut&&droite>droiteBas&&droite>gaucheBas) {
					normale = new Vecteur(normalDroite);
				} else {
					if(haut>droiteBas&&haut>gaucheBas) {
						normale = new Vecteur(normalHaut);
					} else {
						if(droiteBas>gaucheBas) {
							normale = new Vecteur(normalDB);
						} else {
							normale = new Vecteur(normalGB);
						}
					}
				}
			}
		}
		
		normalBas.transformer(matInv);
		normalDroite.transformer(matInv);
		normalGauche.transformer(matInv);
		
		normalHaut.transformer(matInv);
		normalDB.transformer(matInv);
		normalGB.transformer(matInv);
		
		return normale;
	}


	/**
	 * Retourne le type d'element
	 */
	public Elem est() {
		return Elem.TRIANGLE;
	}

	
}
