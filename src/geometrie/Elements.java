/**
 * Classe dont les éléments heritent
 * @author Alexandre Deneault
 * @version 9/2/15
 */

package geometrie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import enemeration.Elem;

public interface Elements extends Serializable{
	

	public void dessiner(Graphics2D g2d, AffineTransform matMondeVersCompo);
	
	public boolean contient(double posX, double posY);
	
	public boolean contient(Elements elem);
	
	public Area getAire();
	
	public double getX();
	
	public void setX(double posX);
	
	public double getY();
	
	public void setY(double posY);
	
	public double getLargeur();
	
	public double getHauteur();
	
	public Elements avancerAnimation(double deltaT,  double forceGrav);
	
	public void collision(Elements elements);
	
	public void reinitialiserAnimation();

	public boolean getBougeCollision();
	
	public void setBougeCollision(boolean bouge);

	public double getMasse();

	public Vecteur getVitesse();

	public Point2D getCentre();
	
	public Point2D getCentreMasse();
	
	public void setCouleur(Color couleur);
	
	public void reinitCouleur();

	public void collisionMultiple(ArrayList<Elements> liste);

	public void setVitesse(Vecteur nouvVitesse);
	
	public Vecteur getNormale(Elements elem);
	
	public Elem est();
	
	public boolean getAfficheVitesse();
	
	public boolean getAfficheAccel();
}
