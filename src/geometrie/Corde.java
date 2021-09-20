/**
 * Classe contenant les éléments de type corde
 * @author Yassine El Talhaoui
 * @version 12/2/15
 */

package geometrie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import enemeration.Elem;

public class Corde implements Elements{

	
	private static final long serialVersionUID = -6373215968799321650L;

	

	//private boolean animation= false;
	//private boolean premier=false;

	

	//private Vecteur vitesse = new Vecteur(0,0,0,0);
	//private double deltaT;
	
	private double forceGravite;
	private double acceleration;
	private double longueurCorde= 15;
	private double basePlan=20;
	private double hautPlan=10;
	private double coefFriction= 0.3;
	private double vitesse;
	


	private Poulie poulie;
	private Triangle plan;
	private double posX, posY;

	private Carre blocVertical,blocIncline;
	private double longueurCorde2=7;
	private double angle=45;
	private double rayonPoulie=1;
	private double deplacementBlocIncline=10;
	private double deplacementBlocVertical=5;
	private double posAide=3.5;
	private double masseBlocV=0.5;
	private double masseBlocI=0.5;

	
	public Corde(double x, double y){
		this.posX=x;
		this.posY=y;
		
		
		
		plan= new Triangle (posX,posY);
		plan.setAngle(angle);
		plan.setCote1(basePlan);
		plan.setCote2(hautPlan);
		
		poulie= new Poulie(plan.getX()-11.5,plan.getY()+7);
		poulie.setRayon(rayonPoulie);
		
	
		
	
		
	}
	
	/**
	 * Methode qui dessine la corde
	 */
	public void dessiner(Graphics2D g2d, AffineTransform matMondeVersCompo) {
		
		//Color couleur=g2d.getColor();
		
		
		
		poulie.dessiner(g2d, matMondeVersCompo);
	
	
		//Dessin bloc vertical
		blocVertical=new Carre(poulie.getX()-poulie.getLargeur()/2,poulie.getY()-deplacementBlocVertical);
		blocVertical.setLargeur(2);
		blocVertical.setHauteur(3.5);
	
		
		//Dessin bloc incline sur le plan
		blocIncline=new Carre(poulie.getX()+rayonPoulie/2+longueurCorde2+deplacementBlocIncline,plan.getY()+longueurCorde2+posAide);
		blocIncline.setLargeur(3.5);
		blocIncline.setHauteur(3.5);
		blocIncline.setCouleur(Color.ORANGE);
		
		blocIncline.setMasse(masseBlocI);
		blocVertical.setMasse(masseBlocV);
	
		
		 
		int fontSize = 20;
		g2d.setFont(new Font("Arial", Font.PLAIN, fontSize));
		g2d.setColor(Color.black);
		g2d.drawString("Masse du bloc vertical:"+blocVertical.getMasse()+ "kg",100,100);
		g2d.drawString("Masse du bloc incline:"+blocIncline.getMasse()+"kg",100,120);
		g2d.drawString("Coefficient de frottement:"+getCoefFriction(),100,140);
		
		
	
		
		//Dessin du plan incliné
		plan.setCouleur(Color.black);
		plan.dessiner(g2d, matMondeVersCompo);
		
	
		//Dessin des cordes
		Line2D.Double cordeVert= new Line2D.Double(poulie.getX()-poulie.getLargeur()/2,poulie.getY(),poulie.getX()-poulie.getLargeur()/2,poulie.getY()-deplacementBlocVertical);
		Line2D.Double cordePlan= new Line2D.Double(poulie.getX(),poulie.getY()+poulie.getHauteur()/2,poulie.getX()+rayonPoulie/2+longueurCorde,plan.getY()+longueurCorde2);
		
		g2d.draw(matMondeVersCompo.createTransformedShape(cordeVert));
		
		AffineTransform matRot = new AffineTransform(matMondeVersCompo);
		
		matRot.rotate(Math.toRadians(-angle/2), poulie.getX(), poulie.getY());
		g2d.draw(matRot.createTransformedShape(cordePlan));
		
	
		g2d.setColor(Color.black);
		
		g2d.draw(matMondeVersCompo.createTransformedShape(cordeVert));
		blocVertical.dessiner(g2d, matMondeVersCompo);
	
		
		
		
		
		g2d.rotate(Math.toRadians(57.5/2.2),blocIncline.getX(),blocIncline.getY()-0.3);
		g2d.setColor(Color.blue);
		blocIncline.dessiner(g2d, matMondeVersCompo);
		
		
		
	 
	}
	


	
	public boolean contient(double posX, double posY) {
		return plan.contient(posX, posY);
	}

	public boolean contient(Elements elem) {
		return plan.contient(elem);
		
	}

	public Area getAire() {
		
		return plan.getAire();
	}

	public double getX() {
		
		return plan.getX();
	}

	
	
	/**
	 * Methode qui 
	 */
	public void setX(double posX) {
	plan.setX(posX);
		
	}
	/**
	 * Methode qui retourne la position Y du plan
	 */
	public double getY() {
		
		return plan.getY();
	}

	/**
	 * Methode qui permet de donner une position Y au plan 
	 */
	public void setY(double posY) {
	plan.setY(posY);
		
	}
	/**
	 * Methode qui donne la largeur du plan
	 */
	public double getLargeur() {
		
		return plan.getLargeur();
	}

	/**
	 * Methode qui donne la hauteur du plan incliné
	 */
	public double getHauteur() {
		return plan.getHauteur();
	}

	/**
	 * Methode
	 */
	public Elements avancerAnimation(double deltaT, double forceGrav) {
		this.forceGravite=forceGrav;
		
		acceleration=(blocVertical.getMasse()*forceGravite-blocIncline.getMasse()*forceGravite*Math.sin(angle)-coefFriction*blocIncline.getMasse()*
					Math.cos(angle))/(blocVertical.getMasse()+blocIncline.getMasse());
			
		
		
		vitesse= acceleration*deltaT;
		deplacementBlocIncline-=vitesse;
		deplacementBlocVertical+=vitesse;
		
		longueurCorde-=vitesse;
		
	
		
		//Limites des blocs (approximatif)
		
		if(blocVertical.getY()+blocVertical.getHauteur()/2<4.2){
			deplacementBlocVertical=poulie.getY()-poulie.getHauteur()-0.25;
			acceleration=0;
			deplacementBlocIncline=4;
			longueurCorde2=7.1;
			longueurCorde=10.5;
			
		}else{
			
			if(blocVertical.getY()+blocVertical.getHauteur()/2>poulie.getY()-poulie.getHauteur()/2 && longueurCorde2>-9){
				deplacementBlocVertical=2.8;
				deplacementBlocIncline=12;
				vitesse=0;
				acceleration=0;
				longueurCorde=15;
		}

			
			
		
		}
		
		return this;
	}

	
	/**
	 * Methode N/A
	 */
	public void collision(Elements elements) {
		//ne fait rien
		
	}

	
	/**
	 * Methode qui réinitialise l'animation
	 */
	public void reinitialiserAnimation() {
		deplacementBlocVertical=5;
		deplacementBlocIncline=10;
		longueurCorde2=7;
		longueurCorde= 15;
	}

	

	/**
	 * Methode N/A
	 */
	public boolean getBougeCollision() {
	//ne fait rien
		return false;
	}

	
	/**
	 * Methode N/A
	 */
	public void setBougeCollision(boolean bouge) {
	// ne fait rien
		
	}
	
	

	
	/**
	 * Methode non utilisée
	 */
	public Vecteur getVitesse() {
	
			return null;
	}

	
	
	/**
	 * Methode non utilisée
	 */
	public Point2D getCentre() {
		
		// ne fait rien
		return 	plan.getCentre();
	}
	
	/**
	 * Methode non utilisée
	 */
	public Point2D getCentreMasse() {
		//ne fait rien
		return plan.getCentreMasse();
	}
	
	/**
	 * Methode qui permet de changer la couleur d'un element dans le module
	 */
	public void setCouleur(Color couleur) {
		blocIncline.setCouleur(couleur);
	}

	
	/**
	 * Methode qui permet de revenir à la couluer initiale
	 */
	public void reinitCouleur() {
		blocIncline.reinitCouleur();
	}

	
	/**
	 * Methode non utilisée
	 */
	public void collisionMultiple(ArrayList<Elements> liste) {
		//ne fait rien 
	}
	
	
	/**
	 * Methode non utilisée
	 */
	public void setVitesse(Vecteur nouvVitesse) {
		//ne fait rien
	}

	/**
	 * Methode qui retourne la normale du bloc Incline
	 * @param elem un element
	 * @return la normale 
	 */
	public Vecteur getNormale(Elements elem) {
		return blocIncline.getNormale(elem);
	}
	
	
	/**
	 * Methode qui determine quel est l'élément
	 * @return le type d'element
	 */
	public Elem est() {
		return Elem.CORDE;
	}

	
	/**
	 * Methode non utilise
	 * @param objetSelectionne
	 */
	public void ajouterElem(Bloc objetSelectionne) {
		//ne fait rien
	}

	/**
	 * Methode qui permet d'afficher le vecteur vitesse
	 */
	public boolean getAfficheVitesse() {
		return false;
	}
	/**
	 * Methode qui permet d'afficher le vecteur acceleration
	 */
	public boolean getAfficheAccel() {
		return false;
	}
	
	/**
	 * Methode qui permet de changer la masse du bloc verticale
	 * @param masse nouvelle masse du bloc
	 */
	public void setMasseBlocVertical(double masse){
		this.masseBlocV=masse;
	}
	
	
	/** 
	 * Methode qui permet de changer la masse du bloc verticale
	 * @param masse nouvelle masse du bloc
	 */
	public void setMasseBlocIncline(double masse){
		this.masseBlocI=masse;
	}
	
	/**
	 * Methode qui donne la masse d'un bloc
	 * @return masse
	 **/
	public double getMasseBlocVertical(){
		return blocVertical.getMasse();
	}
	
	
	/**
	 * Methode qui donne la masse d'un bloc
	 * @return masse
	 */
	public double getMasseBlocIncline(){
		return blocIncline.getMasse();
	}
	
	
	
	
	/**
	 * Methode non utilise
	 */
	public double getMasse() {
	//ne fait rien
		return 0;
	}
	/**
	 * Methode qui permet de modifier le coefficient de frottement
	 * @param coefficient de frottement
	 */
	public void setCoeficient(double coef){
		this.coefFriction=coef;
	}
	
	
	/**
	 * methode qui retourne le coefficient de frottement
	 * @return coefficient
	 */
	public double getCoefFriction(){
		return coefFriction;
	}
	
}
