/**
 * Classe qui dessine les vecteurs representant les vitesses et les accelerations
 * @author Yassine El Talhaoui 
 * @version 9/2/15
 */

package geometrie;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;


public class Vecteur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double origX;
	private double origY;
	private double x,y; //coordon�e X et Y d'arriv�e du vecteur

	
	public Vecteur(double origX, double origY, double X, double Y) {
		this.origX = origX;
		this.origY = origY;
		this.x = X;
		this.y=Y;
		creerShapesSousJacentes();
	}//fin constructeur
	
	public Vecteur(Vecteur vec) {
		this.origX = vec.getOrigX();
		this.origY = vec.getOrigY();
		this.x = vec.getX();
		this.y=vec.getY();
		creerShapesSousJacentes();
	}
	
	

	/**
	 * Methode prive qui cree les shapes
	 */
	private void creerShapesSousJacentes() {
		new Line2D.Double(origX, origY, origX+getNorme(), origY);
	}

	/**
	 * M�thode priv� qui dessine des vecteurs (corps et les trait de t�te)
	 * @param g2d Graphics2D
	 * @param matCM la matrice composant vers monde
	 */
	public void dessiner(Graphics2D g2d, AffineTransform matCM) {
		//sauvegarde des transformations courantes
		//AffineTransform matRot= new AffineTransform();
			
		Line2D.Double corps= new Line2D.Double(origX,origY,origX+x,origY+y);
	//	traitTete = new Line2D.Double(origX+x,origY+y,origX+x*0.75,origY+y*0.75);
		
		
		
		Stroke strokeInit = g2d.getStroke();
		g2d.setStroke(new BasicStroke(3));
		g2d.draw(matCM.createTransformedShape(corps));
		g2d.setStroke(strokeInit);
	
	}
	
	/**
	 * M�thode pour d�finir la longueur du vecteur
	 * @param x= longueur du vecteur
	 */
	public void setLongeur(double x){
		creerShapesSousJacentes();
	}
	   
	/**
	 * M�thode pour changer la coordon�e Y
	 * @param y coordon�e d'arriv� Y
	 */
	public void setY(double y){
			this.y=y;
	}
	
	/**
	 * M�thode pour changer la coordon�e X
	 * @param x coordon�e d'arriv� X
	 */
	public void setX(double x){
		this.x=x;
	}
	
	/**
	 * M�thode pour changer l'orientation du vecteur
	 * @param orientation nouvel angle en degr�es
	 */
	public void setOrientation(double orientation){
		orientation=Math.toRadians(orientation);
		double norme = getNorme();
		x=Math.cos(orientation)*norme;
		y=Math.sin(orientation)*norme;	
		
	}
	/**
	 * M�thode pour changer la norme du vecteur
	 * @param norme nouvelle norme du vecteur
	 */
	public void setNorme(double norme){
		double orientation=Math.toRadians(getOrientation());
		x=Math.cos(orientation)*norme;
		y=Math.sin(orientation)*norme;		
	}
	/**
	 * M�thode pour conna�tre X
	 * @return X coordon�e d'arriv�e X
	 */
	public double getX(){
		return x;
		
	}
	
	/**
	 * M�thode pour conna�tre Y
	 * @return Y coordon�e d'arriv�e Y
	 */
	public double getY(){
		return y;
	}


	/**
	 * M�thode pour conna�tre la norme du vecteur
	 * @return norme du vecteur
	 */
	public double getNorme(){
		return Math.sqrt((x*x)+(y*y));
	}
	/**
	 * M�thode pour conna�tre l'orientation du vecteur
	 * @return l'orientation du vecteur en degres
	 */
	public double getOrientation(){
		double orientation = Math.toDegrees(Math.atan(y/x));
		if (x<0) {
			orientation += 180;
		} else {
			if (y<0) {
				orientation += 360;
			}
		}
		return orientation;
		
	}

	/**
	 * M�thode qui additionne 2 vecteurs
	 * @param v2 vecteur a aditionner
	 */
	public void additionVecteur(Vecteur v2){
		x=x+v2.getX();
		y=y+v2.getY();
		
	}
	/**
	 * M�thode qui multiplie un vecteur avec un scalaire
	 * @param coef qui multiplie le vecteur
	 * @return vec vecteur apr�s la multiplication
	 */
	 public Vecteur produitParScalaire(double coef){
		 Vecteur vec= new Vecteur(origX,origY,x*coef,y*coef);
		 return vec;
		 
	 }
	
	 /**
	  * M�thode qui fait le cosinus de l'angle entre 2 vecteurs 
	  * @param vec vecteur a multipli�
	  * @return le cosinus de l'angle
	  */
	 public double cosAngle(Vecteur vec){
		 return Math.cos(Math.toRadians(Math.abs(this.getOrientation()-vec.getOrientation()))); 
		 
	 }
	 
	  /**
	  * M�thode qui fait le retourne le sinus de l'angle entre 2 vecteurs 
	  * @param vec vecteur a multipli�
	  * @return le sinus de l'angle
	  */
	 public double sinAngle(Vecteur vec){
		return Math.sin(Math.toRadians(Math.abs(this.getOrientation()-vec.getOrientation()))); 
			 
	}
		 
	 /**
	  * Methode qui inverse un vecteur
	  * @return un vecteur
	  */
	public Vecteur inverseVec(){
		return new Vecteur(x, y, origX, origY);
	}
	
	/**
	 * Methode qui fais le produit scalaire entre 1 vecteur et un scalaire
	 * @param vec un vecteur
	 * @return produit scalaire
	 */
	public double produitScalaire(Vecteur vec) {
		return x*vec.getX()+y*vec.getY();
	}
	
	/**
	 * Methode qui fais le produit vectoriel entre
	 * @param vec un vecteur
	 * @return produit vectoriel coordone en z du vecteur resultant
	 */
	
	
	public double produitVectoriel(Vecteur vec){
		return this.x*vec.getY()-this.y*vec.getX();	
		
	}
	/**
	 * Methode qui applique une transformation sur un vecteur
	 * @param mat une matrice affine
	 */
	public void transformer(AffineTransform mat){
		Point2D origine = new Point2D.Double(origX, origY);
		Point2D nouvOrigine = new Point2D.Double();
		Point2D tete = new Point2D.Double(x,y);
		Point2D nouvTete = new Point2D.Double();
		mat.deltaTransform(origine, nouvOrigine);
		mat.deltaTransform(tete, nouvTete);
		
		origX = nouvOrigine.getX();
		origY = nouvOrigine.getY();
		x = nouvTete.getX();
		y = nouvTete.getY();
	}
	
	/**
	 * Methode qui donne l'origine X du vecteur
	 * @return origX l'origine X
	 */
	public double getOrigX() {
		return origX;
	}

	/**
	 * Methode qui donne l'origine Y du vecteur
	 * @return origiY l'origine Y
	 */
	public double getOrigY() {
		return origY;
	}
	
	
	/**
	 * Methode qui permet de changer l'origine en X
	 * @param nouvOrigX l'origine X
	 */
	public void setOrigX(double nouvOrigX) {
		origX = nouvOrigX;
	}
	/**
	 * Methode qui permet de changer l'origine en Y
	 * @param nouvOrigY l'origine Y
	 */
	public void setOrigY(double nouvOrigY) {
		origY = nouvOrigY;
	}
	
	
	/**
	 * Methode qui compare 2 vecteurs
	 * @param vec Le vecteur a comparer
	 * @return Vrai si le vecteur est semblable
	 */
	public boolean egale(Vecteur vec) {
		if (this.x==vec.getX() && this.y==vec.getY()) {
			return true;
		} else {
			return false;
		}
	}

}//fin classe