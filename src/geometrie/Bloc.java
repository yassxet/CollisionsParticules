/**
 * Classe permettant de controler le dessin et l'animation des cercles, carres et triangles
 * @author Alexandre Deneault
 * @version 26/2/15
 */

package geometrie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import enemeration.Elem;

public abstract class Bloc implements Elements{
	
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -6546255010155526352L;

	private double inertie;


	private Shape bloc, blocDeplacement, blocTransfo;
	
	
	private Color couleur;
	
	
	private double posX, posY;
	private double masse=0.5;
	private Point2D centreMasse;
	
	
	private boolean animation=false;
	private AffineTransform deplacement=new AffineTransform();
	private AffineTransform rotation = new AffineTransform();
	
	private boolean bougeCollision=true;
	
	private double depX, depY;
	private final double DISTANCE_COLLISION = 0.02;
	
	private Vecteur forceGrav;
	
	private Vecteur acceleration = new Vecteur(0,0,0,0);
	private Vecteur vitesse = new Vecteur(0,0,0,0);
	private double deltaT;
	

	private double vitesseAng = 0;
	private double deplacementAng = 0;
	private final double LIMITE_ENERGIE_ANG = 0.6;
	
	private boolean afficheVitesse = false;
	private boolean afficheAccel = false;
	
	
	private boolean attacheCorde = false;
	
	/**
	 * Constructeur de la classe Bloc
	 * @param posX La position en X du bloc
	 * @param posY La position en Y du bloc
	 */
	public Bloc(double posX, double posY){
		this.posX=posX;
		this.posY=posY;
		bloc = creerForme();
		blocDeplacement = bloc;
		centreMasse = getCentre();
		
		this.inertie = this.masse*5;
	}
	
	public abstract Shape creerForme();
	
	/**
	 * Méthode permettant de dessiner le bloc
	 */
	public void dessiner(Graphics2D g2d, AffineTransform matMondeVersCompo) {
		Color couleurInit=g2d.getColor();
		
		if (!animation) {
			bloc = creerForme();
			blocDeplacement = bloc;
		}
		
		g2d.setColor(Color.BLACK);
		if (animation && afficheVitesse) {
			vitesse.setOrigX(centreMasse.getX());
			vitesse.setOrigY(centreMasse.getY());
			vitesse.dessiner(g2d, matMondeVersCompo);
		}
		if (animation && afficheAccel) {
			acceleration.setOrigX(centreMasse.getX());
			acceleration.setOrigY(centreMasse.getY());
			acceleration.dessiner(g2d, matMondeVersCompo);
		}
		
		g2d.setColor(couleur);
		blocTransfo = matMondeVersCompo.createTransformedShape(blocDeplacement);
		g2d.fill(blocTransfo);
		
		g2d.setColor(couleurInit);
	}

	
	
	
	
	
	
	/**
	 * Méthode booléenne retournant vrai si le point passé en paramètre est à l'intérieur du bloc
	 * @param posX La position en X du point (en cm)
	 * @param posY La position en Y du point (en cm)
	 * @return VRAI s'il y a une collision
	 */
	public boolean contient(double posX, double posY) {
		return blocDeplacement.contains(posX, posY);
	}

	/**
	 * Méthode qui évalue si un Elements est superposé sur le bloc
	 * @param elem L'elements à tester
	 * @return VRAI s'il y a une collision
	 */
	public boolean contient(Elements elem) {
		Area aireElem = elem.getAire();
		return contient(aireElem);
	}
	
	/**
	 * Méthode qui teste si une aire est superposé sur le bloc
	 * @param aire L'aire a tester
	 * @return VRAI s'il y a une collision
	 */
	public boolean contient(Area aire) {
		Area objet = this.getAire();
		
		aire.intersect(objet);
		if (aire.isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Méthode qui retourne l'aire du bloc
	 */
	public Area getAire() {
		return new Area(blocDeplacement);
	}
	
	
	
	
	
	
	
	/**
	 * Méthode qui réinitialise la position du bloc
	 */
	public void reinitialiserAnimation() {
		animation = false;
		deplacement =new AffineTransform();
		rotation = new AffineTransform();
		vitesse = new Vecteur(0, 0, 0, 0);
		vitesseAng = 0;
		deplacementAng = 0;
		centreMasse = getCentre();
		
	}
	
	

	/**
	 * Méthode permettant de faire avancer le bloc d'un pas
	 * @param deltaT L'intervalle de temps (en secondes)
	 * @param forceGrav La valeur du champs gravitationnel
	 */
	public Bloc avancerAnimation(double deltaT, double forceGrav) {
		if (!attacheCorde) {
			
			if (!animation) {
				animation = true;
				rotation = new AffineTransform();
			}
			
			this.forceGrav = new Vecteur(0, 0, 0, -forceGrav*masse);
			this.deltaT = deltaT;
			this.acceleration = this.forceGrav.produitParScalaire(1/masse);	
				
			vitesse.additionVecteur(acceleration.produitParScalaire(this.deltaT));
			
			depX = vitesse.produitParScalaire(this.deltaT).getX();
			depY = vitesse.produitParScalaire(this.deltaT).getY();
		
			
			deplacer(depX, depY, vitesseAng*this.deltaT);
		}
		
		return this;
	}
	
	/**
	 * Méthode qui gere les collisions
	 * @param element L'elements avec lequel il y a une collison
	 */
	public void collision(Elements element){
		//Pour rotations
		Area aire= new Area(this.getAire());
		aire.intersect(element.getAire());

		//annule le deplacement
		deplacer(-depX, -depY, -vitesseAng*this.deltaT);
		vitesse.additionVecteur(acceleration.produitParScalaire(this.deltaT).inverseVec());
		
		double difDeltaT = 0;
		double deltaTDiv = deltaT;
		//Pour coller les elements
		do {
			depX=depX/2;
			depY=depY/2;
			deltaTDiv = deltaTDiv/2;
			difDeltaT += deltaTDiv;

			deplacer(depX, depY, vitesseAng*deltaTDiv);

			if (this.contient(element)) {
				//Pour rotations
				aire= new Area(this.getAire());
				aire.intersect(element.getAire());
				deplacer(-depX, -depY, -vitesseAng*deltaTDiv);
				difDeltaT -= deltaTDiv;
			}
		} while(!(Math.abs(depX)<DISTANCE_COLLISION && Math.abs(depY)<DISTANCE_COLLISION));
		
		vitesse.additionVecteur(acceleration.produitParScalaire(difDeltaT));
		
		

		//Pour les rotations
		Rectangle rect = aire.getBounds();
		double posColX = rect.getCenterX();
		double posColY = rect.getCenterY();
		Point2D centre = this.getCentreMasse();
		Vecteur distanceCol = new Vecteur(0,0, posColX-centre.getX(), posColY-centre.getY());

		Vecteur forceColElem;
		if (!element.getBougeCollision()){
			forceColElem = this.getVitesse().produitParScalaire(this.getMasse()/deltaT);
		} else {
			forceColElem = element.getVitesse().produitParScalaire(element.getMasse()/deltaT);
		}
		Vecteur forceCol = this.getVitesse().produitParScalaire(this.getMasse()/deltaT);

		
		
	
		//Trouve la normale
		Vecteur normale;
		if (element.est()==Elem.CARRE || element.est()==Elem.RESSORT) {
			normale = element.getNormale(this);
		} else {
			if (this.est()==Elem.CARRE){
				normale = this.getNormale(element);
			} else {
				if (this.est()==Elem.TRIANGLE) {
					normale = this.getNormale(element);
				} else {
					if (element.est()==Elem.TRIANGLE) {
						normale = element.getNormale(this);
					} else {
						normale = this.getNormale(element);
					}
				}
			}
		}

		Vecteur tangente = new Vecteur(normale.getOrigX(), normale.getOrigY(), -normale.getY(), normale.getX());
		
		
		//Corrige l'orientation de la force
		forceCol.setOrientation(normale.getOrientation());
		forceColElem.setOrientation(normale.getOrientation());


		
		//Changer les vitesses
		if(element.getBougeCollision()){
			
			double vitesseNormale = normale.produitScalaire(this.vitesse);
			double vitesseTangente = tangente.produitScalaire(this.vitesse);
			double vitesseNormaleElem = normale.produitScalaire(element.getVitesse());
			double vitesseTangenteElem = tangente.produitScalaire(element.getVitesse());
			
			double vitesseNormaleFin = calculVitesse(vitesseNormale, vitesseNormaleElem, this.getMasse(), element.getMasse());
			double vitesseNormaleFinElem = calculVitesse(vitesseNormaleElem, vitesseNormale, element.getMasse(), this.getMasse());
			
			Vecteur vitesseFin = normale.produitParScalaire(vitesseNormaleFin);
			vitesseFin.additionVecteur(tangente.produitParScalaire(vitesseTangente));
			Vecteur vitesseFinElem = normale.produitParScalaire(vitesseNormaleFinElem);
			vitesseFinElem.additionVecteur(tangente.produitParScalaire(vitesseTangenteElem));
			
			this.vitesse = vitesseFin;
			element.setVitesse(vitesseFinElem);
			
			
			
			//Change la vitesse de rotation du deuxieme bloc
			Bloc elem = (Bloc)element;
			Point2D centreElem = elem.getCentreMasse();
			Vecteur distanceColElem = new Vecteur(0,0, posColX-centreElem.getX(), posColY-centreElem.getY());
			
			
			double enerRot = elem.getInertie()*Math.pow(Math.abs(elem.getVitesseAng()), 2);
			double enerCin = elem.getMasse()*Math.pow(elem.getVitesse().getNorme(), 2);
			double enerTot = enerRot + enerCin;
			
			
			double accAngElem = distanceColElem.produitVectoriel(forceCol) / elem.getInertie();
			elem.setVitesseAng(elem.getVitesseAng()+accAngElem*deltaT);
			
			enerRot = elem.getInertie()*Math.pow(elem.getVitesseAng(), 2);
			if (enerRot>enerTot*LIMITE_ENERGIE_ANG) {
				enerRot = enerTot*LIMITE_ENERGIE_ANG;
				elem.setVitesseAng(Math.sqrt(enerRot/elem.getInertie())*elem.getVitesseAng()/Math.abs(elem.getVitesseAng()));
			}
			
			enerCin = enerTot - enerRot;
			if (enerCin<0)
				enerCin=0;
			double nouvVitesse = Math.sqrt(enerCin/elem.getMasse());
			elem.getVitesse().setNorme(nouvVitesse);
			
			
			
		} else {
			if (element.est() == Elem.RESSORT || element.est() == Elem.CORDE) {
				element.collision(this);
			} else {
				double vitesseNormale = normale.produitScalaire(this.vitesse);
				double vitesseTangente = tangente.produitScalaire(this.vitesse);
				
				Vecteur vitesseFin = normale.produitParScalaire(-1*vitesseNormale);
				vitesseFin.additionVecteur(tangente.produitParScalaire(vitesseTangente));
				
				this.vitesse = vitesseFin;
				
			}
		}
		
		//Change la vitesse de rotation de ce bloc
		
		double enerRot = this.getInertie()*Math.pow(this.getVitesseAng(), 2);
		double enerCin = this.getMasse()*Math.pow(this.vitesse.getNorme(), 2);
		double enerTot = enerRot + enerCin;
		
		double accAng = distanceCol.produitVectoriel(forceColElem) / this.inertie;
		this.vitesseAng += accAng*deltaT;
		
		enerRot = this.getInertie()*Math.pow(this.getVitesseAng(), 2);
		if (enerRot>enerTot*LIMITE_ENERGIE_ANG) {
			enerRot = enerTot*LIMITE_ENERGIE_ANG;
			this.vitesseAng =  Math.sqrt(enerRot/this.inertie)*vitesseAng/Math.abs(vitesseAng);
		}
		
		enerCin = enerTot - enerRot;
		if (enerCin<0)
			enerCin=0;
		double nouvVitesse = Math.sqrt(enerCin/this.getMasse());
		this.vitesse.setNorme(nouvVitesse);
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Methode prive retournant la vitesse finale du premier objet.
	 * @param vitesseInit1 La vitesse initiale du premier objet
	 * @param vitesseInit2 La vitesse initiale du deuxieme objet
	 * @param masse1 La masse du premier objet
	 * @param masse2 La masse du deuxieme objet
	 * @return La nouvelle vitesse
	 */
	public static double calculVitesse(double vitesseInit1, double vitesseInit2, double masse1, double masse2){
		return (vitesseInit1*(masse1-masse2)+2*masse2*vitesseInit2)/(masse1+masse2);
	}
	
	/**
	 * Methode qui gere les collisions lorsqu'il y en a plusieurs
	 */
	public void collisionMultiple(ArrayList<Elements> liste){
		
		double reculX=0, reculY=0;
		Elements elemCol;
		
		//Trouver celui qui a cause la premiere collision
		do {
			Iterator<Elements> itChercher = liste.iterator();
			
			deplacer(-depX*DISTANCE_COLLISION, -depY*DISTANCE_COLLISION, 0);
			reculX += depX*DISTANCE_COLLISION;
			reculY += depY*DISTANCE_COLLISION;
			int index = -1;
			while(itChercher.hasNext()){
				Elements present = itChercher.next();
				if (!this.contient(present))
					index = liste.indexOf(present);
			}
			if (index!=-1 && liste.size()>1)
				liste.remove(index);
		} while (liste.size()!=1);
		elemCol = liste.get(0);
		
		//annuler les deplacements
		deplacer(reculX, reculY, 0);
		
		//traitement de la collision
		this.collision(elemCol);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Methode privee qui deplace le bloc
	 * @param distX La distance en X
	 * @param distY La distance en Y
	 * @param rotation La rotation du bloc
	 */
	private void deplacer(double distX, double distY, double rotation){
		
		
		getDeplacement().translate(distX, distY);
		centreMasse = getDeplacement().transform(getCentre(), null);
		
		deplacementAng += rotation;
		this.rotation = new AffineTransform();
		this.rotation.rotate(deplacementAng, centreMasse.getX(), centreMasse.getY());
		
		
		blocDeplacement = getDeplacement().createTransformedShape(bloc);
		blocDeplacement = this.rotation.createTransformedShape(blocDeplacement);
		
	}
	
	

	

	/**
	 * Méthode permettant de connaitre la position en x du bloc
	 * @return posX La position en X
	 */
	public double getX(){
		return this.posX;
	}

	/**
	 * Méthode permettant de changer la position initiale du bloc (ne fonctionne pas si l'animation est en cours)
	 * @param posX La nouvelle position en X
	 */
	public void setX(double posX){
		if (!animation) {
			this.posX=posX;
		}
	}

	/**
	 * Méthode permettant de connaitre la position en y du bloc
	 * @return posY La position en Y
	 */
	public double getY(){
		return this.posY;
	}

	/**
	 * Méthode permettant de changer la position initiale du bloc (ne fonctionne pas si l'animation est en cours)
	 * @param posY La nouvelle position en Y
	 */
	public void setY(double posY){
		if (!animation) {
			this.posY=posY;
		}
	}

	

	
	
	
	
	/**
	 * Méthode permettant de changer la masse du bloc
	 * @param masse La nouvelle masse du bloc
	 */
	public void setMasse(double masse) {
		this.masse = masse;
		this.inertie = this.masse*5;
	}
	
	/**
	 * Methode permettant de connaitre la masse du bloc
	 * @return La masse du bloc
	 */
	public double getMasse() {
		return this.masse;
	}
	
	
	/**
	 * Permet de changer la couleur du bloc
	 * @param couleur La nouvelle couleur du bloc
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Méthode retournant la couleur du bloc
	 * @return La couleur du bloc
	 */
	public Color getCouleur() {
		return couleur;
	}
	
	/**
	 * Indique si l'objet peut bouger lors des collisions
	 * @return VRAI si l'objet bouge lors des collisions
	 */
	public boolean getBougeCollision(){
		return bougeCollision;
	}
	
	/**
	 * Change le comportement de l'element lors des collisions
	 * @param bouge Si l'objet peut bouger lorsqu'il y a collision
	 */
	public void setBougeCollision(boolean bouge){
		this.bougeCollision=bouge;
	}
	
	/**
	 * Retourne le vecteur vitesse de l'element
	 */
	public Vecteur getVitesse() {
		return this.vitesse;
	}
	
	/**
	 * Change la vitesse du bloc
	 * @param nouvVitesse La nouvlle vitesse du bloc
	 */
	public void setVitesse(Vecteur nouvVitesse){
		this.vitesse = nouvVitesse;
	}
	
	/**
	 * Methode permettant de changer l'accelertation d'un bloc
	 * @param acceleration La nouvelle acceleration du bloc
	 */
	public void setAcceleration(Vecteur acceleration) {
		this.acceleration = acceleration;
	}
	
	/**
	 * Methode retournant la vitesse angulaire
	 * @return la vitesse angulaire du bloc (en radians)
	 */
	public double getVitesseAng(){
		return vitesseAng;
	}
	
	/**
	 * Permet de changer la vitesse angulaire du bloc
	 * @param nouvVit La nouvelle vitesse (en radians)
	 */
	public void setVitesseAng(double nouvVit){
		this.vitesseAng = nouvVit;
	}
	
	/**
	 * Methode retournant la matrice de deplacement
	 * @return La matrice de deplacement
	 */
	public AffineTransform getDeplacement() {
		return deplacement;
	}
	
	/**
	 * Methode permettant de modifier la matrice de deplacement
	 * @param nouvDep La nouvelle matrice de peplacement
	 */
	public void setDeplacement(AffineTransform nouvDep) {
		this.deplacement = nouvDep;
		this.deplacer(0, 0, 0);
	}
	
	/**
	 * Methode retournant la matrice de rotation
	 * @return La matrice de rotation
	 */
	public AffineTransform getRotation() {
		return this.rotation;
	}
	
	/**
	 * Methode permettant de modifier la matrice de rotation
	 * @param nouvRot La nouvelle matrice de rotation
	 */
	public void setRotation (AffineTransform nouvRot) {
		this.rotation = nouvRot;
		this.deplacer(0, 0, 0);
	}
	
	/**
	 * Retourne le centre de masse (deplace) du bloc
	 * @return Le centre de masse
	 */
	public Point2D getCentreMasse() {
		return this.centreMasse;
	}
	
	/**
	 * Methode qui permet de faire dessiner le vecteur de vitesse
	 * @param afficheVitesse Vrai si le vecteur de vitesse doit se dessiner
	 */
	public void setAfficheVitesse(boolean afficheVitesse) {
		this.afficheVitesse = afficheVitesse;
	}
	
	/**
	 * Methode retournant vrai si le vecteur de vitesse s'affiche
	 * @return Vrai si le vecteur de vitesse s'affiche
	 */
	public boolean getAfficheVitesse() {
		return this.afficheVitesse;
	}
	
	/**
	 * Methode qui permet de faire dessiner le vecteur d'acceleration
	 * @param afficheAccel Vrai si le vecteur d'acceleration doit se dessiner
	 */
	public void setAfficheAccel(boolean afficheAccel) {
		this.afficheAccel = afficheAccel;
	}
	
	/**
	 * Methode retournant vrai si le vecteur d'acceleration s'affiche
	 * @return Vrai si le vecteur d'acceleration s'affiche
	 */
	public boolean getAfficheAccel() {
		return this.afficheAccel;
	}
	
	
	
	
	/**
	 * Methode permettant de controler les parametres de l'animation (Pour les ressorts)
	 * @param animationEnCours Vrai si l'animation est en cours
	 */
	public void setAnimation(boolean animationEnCours) {
		this.animation = animationEnCours;
	}
	
	/**
	 * Methode permettant d'attacher un bloc a une corde
	 * @param attache Si le bloc est attache
	 */
	public void attacheCorde(boolean attache) {
		attacheCorde = attache;
	}
	
	/**
	 * Methode retournant le moment d'inertie
	 * @return La valeur du moment d'inertie
	 */
	public double getInertie() {
		return inertie;
	}
	
}