/**
 * Classe contenant les éléments de type ressort
 * @author Alexandre
 * @version 12/2/15
 */

package geometrie;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import enemeration.Elem;
import enemeration.Position;

public class Ressort implements Elements{

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -5141774096593427516L;
	
	Path2D.Double ressort;
	private Carre carre, murG, murD, murH, murB;
	private Position position = Position.GAUCHE;
	
	private double constRessort = 5; // N/cm
	
	private double etirement = 0;
	private double longueur;
	private final double POURCENTAGE_ETIREMENT = 0.87;
	
	private int nbPointes;
	private final Color COULEUR = Color.BLACK;
	
	
	
	private boolean animation = false;
	private AffineTransform deplacement;
	
	private double forceGrav;
	private double forceRessort;
	private double acceleration;
	private double vitesse;
	
	private final double DISTANCE_COLLISION = 0.05;
	
	private Vecteur normalHaut;
	private Vecteur normalBas;
	private Vecteur normalDroite;
	private Vecteur normalGauche;
	

	private boolean afficheAccel = false;
	private boolean afficheVitesse = true;
	
	
	/**
	 * Constructeur du ressort
	 * @param posX La position en x du carre
	 * @param posY La position en y du carre
	 * @param murGauche Le mur de gauche
	 * @param murDroit Le mur de droite
	 * @param murHaut Le mur du haut
	 * @param murBas Le mur du bas
	 */
	public Ressort(double posX, double posY, Carre murGauche, Carre murDroit, Carre murHaut, Carre murBas) {
		this.carre = new Carre(posX, posY);
		this.deplacement = carre.getDeplacement();
		this.murG = murGauche;
		this.murD = murDroit;
		this.murH = murHaut;
		this.murB = murBas;
		normalHaut = new Vecteur(this.getX(), this.getY(), 0, 1);
		normalBas = new Vecteur(this.getX(), this.getY(), 0, -1);
		normalDroite = new Vecteur(this.getX(), this.getY(), 1, 0);
		normalGauche = new Vecteur(this.getX(), this.getY(), -1, 0);
		mettreAJour();
		creerRessort();
	}
	
	/**
	 * Methode qui cree le ressort
	 */
	private void creerRessort() {
		ressort = new Path2D.Double();
		
		double longTot = this.longueur + etirement;
		double intervalle = longTot/(nbPointes+1);
		
		if (position == Position.GAUCHE) {
			ressort.moveTo(murG.getX()+murG.getLargeur()/2, carre.getY());
			ressort.lineTo(murG.getX()+murG.getLargeur()/2+intervalle/2, carre.getY());
			for (int i=0; i<nbPointes; i++){
				ressort.lineTo(murG.getX()+murG.getLargeur()/2+intervalle/2+intervalle*i+intervalle/4, carre.getY()+carre.getHauteur()/2);
				ressort.lineTo(murG.getX()+murG.getLargeur()/2+intervalle/2+intervalle*i+intervalle*3/4, carre.getY()-carre.getHauteur()/2);
				ressort.lineTo(murG.getX()+murG.getLargeur()/2+intervalle/2+intervalle*(i+1), carre.getY());
			}
			ressort.lineTo(murG.getX()+murG.getLargeur()/2+longTot, carre.getY());
		}
		if (position == Position.DROITE) {
			ressort.moveTo(murD.getX()-murD.getLargeur()/2, carre.getY());
			ressort.lineTo(murD.getX()-murD.getLargeur()/2-intervalle/2, carre.getY());
			for (int i=0; i<nbPointes; i++){
				ressort.lineTo(murD.getX()-murD.getLargeur()/2-intervalle/2-intervalle*i-intervalle/4, carre.getY()+carre.getHauteur()/2);
				ressort.lineTo(murD.getX()-murD.getLargeur()/2-intervalle/2-intervalle*i-intervalle*3/4, carre.getY()-carre.getHauteur()/2);
				ressort.lineTo(murD.getX()-murD.getLargeur()/2-intervalle/2-intervalle*(i+1), carre.getY());
			}
			ressort.lineTo(murD.getX()-murD.getLargeur()/2-longTot, carre.getY());
		}
		if (position == Position.HAUT) {
			ressort.moveTo(carre.getX(), murH.getY()-murH.getHauteur()/2);
			ressort.lineTo(carre.getX(), murH.getY()-murH.getHauteur()/2-intervalle/2);
			for (int i=0; i<nbPointes; i++){
				ressort.lineTo(carre.getX()+carre.getLargeur()/2, murH.getY()-murH.getHauteur()/2-intervalle/2-intervalle*i-intervalle/4);
				ressort.lineTo(carre.getX()-carre.getLargeur()/2, murH.getY()-murH.getHauteur()/2-intervalle/2-intervalle*i-intervalle*3/4);
				ressort.lineTo(carre.getX(), murH.getY()-murH.getHauteur()/2-intervalle/2-intervalle*(i+1));
			}
			ressort.lineTo(carre.getX(), murH.getY()-murH.getHauteur()/2-longTot);
		}
		if (position == Position.BAS) {
			ressort.moveTo(carre.getX(), murB.getY()+murB.getHauteur()/2);
			ressort.lineTo(carre.getX(), murB.getY()+murB.getHauteur()/2+intervalle/2);
			for (int i=0; i<nbPointes; i++){
				ressort.lineTo(carre.getX()+carre.getLargeur()/2, murB.getY()+murB.getHauteur()/2+intervalle/2+intervalle*i+intervalle/4);
				ressort.lineTo(carre.getX()-carre.getLargeur()/2, murB.getY()+murB.getHauteur()/2+intervalle/2+intervalle*i+intervalle*3/4);
				ressort.lineTo(carre.getX(), murB.getY()+murB.getHauteur()/2+intervalle/2+intervalle*(i+1));
			}
			ressort.lineTo(carre.getX(), murB.getY()+murB.getHauteur()/2+longTot);
		}
	}
	
	
	/**
	 * Méthode permettant de dessiner le ressort
	 * @param g2d Le contexte graphique
	 * @param matMondeVersCompo La matrice de transformation
	 */
	public void dessiner(Graphics2D g2d, AffineTransform matMondeVersCompo) {
		Color couleurInit = g2d.getColor();
		g2d.setColor(COULEUR);
		creerRessort();
		g2d.draw(matMondeVersCompo.createTransformedShape(ressort));
		
		if (true) {
			Vecteur vitesse = null;
			switch (position) {
				case GAUCHE: 	vitesse = new Vecteur(0,0,this.vitesse,0);
								break;
				case DROITE:	vitesse = new Vecteur(0,0,-this.vitesse,0);
								break;
				case HAUT:		vitesse = new Vecteur(0,0,0,-this.vitesse);
								break;
				case BAS:		vitesse = new Vecteur(0,0,0,this.vitesse);		
			}
			vitesse.setOrigX(carre.getCentreMasse().getX());
			vitesse.setOrigY(carre.getCentreMasse().getY());
			carre.setVitesse(vitesse);
			
		}
		
		if (animation && afficheAccel) {
			Vecteur acceleration = null;
			switch (position) {
				case GAUCHE: 	acceleration = new Vecteur(0,0,this.acceleration,0);
								break;
				case DROITE:	acceleration = new Vecteur(0,0,-this.acceleration,0);
								break;
				case HAUT:		acceleration = new Vecteur(0,0,0,-this.acceleration);
								break;
				case BAS:		acceleration = new Vecteur(0,0,0,this.acceleration);				
			}
			acceleration.setOrigX(carre.getCentreMasse().getX());
			acceleration.setOrigY(carre.getCentreMasse().getY());
			carre.setAcceleration(acceleration);
		}
		
		carre.dessiner(g2d, matMondeVersCompo);
		g2d.setColor(couleurInit);
	}

	/**
	 * Méthode booléenne retournant vrai si le point passé en paramètre est à l'intérieur du ressort
	 * @param posX La position en X du point (en cm)
	 * @param posY La position en Y du point (en cm)
	 * @return VRAI s'il y a une collision
	 */
	public boolean contient(double posX, double posY) {
		return getAire().contains(posX, posY);
	}

	
	/**
	 * Méthode qui évalue si un Elements est superposé sur le ressort
	 * @param elem L'elements à tester
	 * @return VRAI s'il y a une collision
	 */
	public boolean contient(Elements elem) {
		Area aireElem = elem.getAire();
		return contient(aireElem);
	}
	

	/**
	 * Méthode qui teste si une aire est superposé sur le ressort
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
	 * Méthode qui retourne l'aire du ressort
	 */
	public Area getAire() {
		Area aire = new Area(ressort.getBounds2D());
		aire.add(carre.getAire());
		return aire;
	}

	/**
	 * Méthode permettant de faire avancer le ressort d'un pas
	 * @param deltaT L'intervalle de temps (en secondes)
	 * @param forceGrav La valeur du champs gravitationnel
	 */
	public Elements avancerAnimation(double deltaT, double forceGrav) {
		if (!animation) {
			this.animation = true;
			this.carre.setAnimation(animation);
			this.vitesse = 0;
		}
		//trouve l'acceleration et la vitesse
		this.forceGrav = carre.getMasse()*forceGrav;
		this.forceRessort = -constRessort * etirement;
		
		if (position == Position.HAUT) {
			this.acceleration = (this.forceRessort + this.forceGrav)/ this.carre.getMasse();
		} else {
			if (position == Position.BAS) {
				this.acceleration = (this.forceRessort - this.forceGrav)/ this.carre.getMasse();
			} else {
				this.acceleration = this.forceRessort/this.carre.getMasse();
			}
		}
		this.vitesse += this.acceleration*deltaT;
		this.etirement += this.vitesse*deltaT;
		if (Math.abs(etirement) >= longueur*POURCENTAGE_ETIREMENT) {
			this.etirement = this.longueur*POURCENTAGE_ETIREMENT *(this.etirement/Math.abs(this.etirement));
			vitesse = 0;
		}
		
		
		this.deplacement = new AffineTransform();
		switch (position) {
			case GAUCHE: 	this.deplacement.translate(this.etirement, 0);
							break;
			case DROITE:	this.deplacement.translate(-this.etirement, 0);
							break;
			case HAUT:		this.deplacement.translate(0, -this.etirement);
							break;
			case BAS:		this.deplacement.translate(0, this.etirement);				
		}
		carre.setDeplacement(deplacement);
		return this;
	}

	/**
	 * Méthode qui réinitialise la position du bloc
	 */
	public void reinitialiserAnimation() {
		animation = false;
		carre.setAnimation(false);
		vitesse = 0;
		etirement = 0;
		this.deplacement = new AffineTransform();
		carre.setDeplacement(deplacement);
	}

	/**
	 * Méthode permettant de connaitre la position en x du carre
	 * @return La position en X du carre
	 */
	public double getX() {
		return carre.getX();
	}

	/**
	 * Méthode permettant de changer la position initiale du carre (ne fonctionne pas si l'animation est en cours)
	 * @param posX La nouvelle position en X
	 */
	public void setX(double posX) {
		carre.setX(posX);
		mettreAJour();
	}

	/**
	 * Méthode permettant de connaitre la position en y du carre
	 * @return La position en Y
	 */
	public double getY() {
		return carre.getY();
	}

	/**
	 * Méthode permettant de changer la position initiale du carre (ne fonctionne pas si l'animation est en cours)
	 * @param posY La nouvelle position en Y
	 */
	public void setY(double posY) {
		carre.setY(posY);
		mettreAJour();
	}

	/**
	 * Méthode retournant la largeur du carre
	 * @return 	La largeur du carre
	 */
	public double getLargeur() {		
		return carre.getLargeur();
	}
	
	/**
	 * Méthode permettant de changer la largeur du carre
	 * @param largeur La nouvelle largeur
	 */
	public void setLargeur(double largeur) {
		this.carre.setLargeur(largeur);
		this.mettreAJour();
	}

	/**
	 * Méthode retournant la hauteur du carre
	 * @return 	La hauteur du carre
	 */
	public double getHauteur() {
		return carre.getHauteur();
	}
	
	/**
	 * Méthode permettant de changer la hauteur du carre
	 * @param hauteur La nouvelle hauteur
	 */
	public void setHauteur(double hauteur) {
		this.carre.setHauteur(hauteur);
		this.mettreAJour();
	}

	/**
	 * Indique si l'objet peut bouger lors des collisions
	 * @return FAUX
	 */
	public boolean getBougeCollision() {
		return false;
	}

	/**
	 * Methode qui ne fait rien (On ne peut pas changer cette donne pour les ressorts)
	 */
	public void setBougeCollision(boolean bouge) {
		//ne fait rien
	}

	/**
	 * Methode permettant de connaitre la masse du carre
	 * @return La masse du carre
	 */
	public double getMasse() {
		return carre.getMasse();
	}
	
	/**
	 * Méthode permettant de changer la masse du carre
	 * @param masse La nouvelle masse du carre
	 */
	public void setMasse(double masse) {
		this.carre.setMasse(masse);
	}

	/**
	 * Retourne le vecteur vitesse de l'element
	 */
	public Vecteur getVitesse() {
		Vecteur vitesse = null;
		switch (position) {
			case GAUCHE: 	vitesse = new Vecteur(0,0, this.vitesse, 0);
			case DROITE:	vitesse = new Vecteur(0,0, -this.vitesse, 0);
			case HAUT:		vitesse = new Vecteur(0,0, 0, -this.vitesse);
			case BAS:		vitesse = new Vecteur(0,0, 0, this.vitesse);
			
		}
		return vitesse;
	}

	/**
	 * Reinitialise la couleur du carre
	 */
	public void reinitCouleur() {
		this.carre.reinitCouleur();
	}

	/**
	 * Permet de changer la couleur du carre
	 * @param couleur La nouvelle couleur du carre
	 */
	public void setCouleur(Color couleur) {
		this.carre.setCouleur(couleur);
	}

	/**
	 * Methode retournant le centre de masse du carre
	 * @return Le centre de masse
	 */
	public Point2D getCentre() {
		return carre.getCentre();
	}
	
	/**
	 * Retourne le centre de masse (deplace) du carre
	 * @return Le centre de masse
	 */
	public Point2D getCentreMasse() {
		return carre.getCentreMasse();
	}
	
	/**
	 * Methode retournant la position du mur auquel le ressort est attache
	 * @return La position du mur
	 */
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Methode permettant de changer le mur auquel le ressort est attache
	 * @param nouvPos La position du mur
	 */
	public void setPosition(Position nouvPos) {
		this.position = nouvPos;
		this.mettreAJour();
	}

	/**
	 * Méthode qui gere les collisions
	 * @param elements L'elements avec lequel il y a une collison
	 */
	public void collision(Elements elements) {
		
		//Pour coller les elements
		while (this.contient(elements)) {
			etirement -= DISTANCE_COLLISION;
			this.deplacement = new AffineTransform();
			switch (position) {
				case GAUCHE: 	this.deplacement.translate(this.etirement, 0);
								break;
				case DROITE:	this.deplacement.translate(-this.etirement, 0);
								break;
				case HAUT:		this.deplacement.translate(0, -this.etirement);
								break;
				case BAS:		this.deplacement.translate(0, this.etirement);
			}
			carre.setDeplacement(deplacement);
		}
		
		
		//Trouver la normale
		Vecteur normale = this.getNormale(elements);
		
		
		if (elements.getBougeCollision()) {

			//Change les vitesses
			if ((position==Position.HAUT&&(normale.egale(normalBas))) || (position==Position.BAS&&(normale.egale(normalHaut)))) {
				double vitesseTangenteElem = elements.getVitesse().getX();
				double vitesseNormaleElem = elements.getVitesse().getY();

				double vitesseNormaleFinElem = Bloc.calculVitesse(vitesseNormaleElem, this.getVitesse().getY(), elements.getMasse(), this.getMasse());
				double vitesseFinNormale = Bloc.calculVitesse(this.getVitesse().getY(), vitesseNormaleElem, this.getMasse(), elements.getMasse());

				Vecteur vitesseFinElem = new Vecteur(0,0, vitesseTangenteElem, vitesseNormaleFinElem);
				Vecteur vitesseFin = new Vecteur(0,0,0, vitesseFinNormale);

				elements.setVitesse(vitesseFinElem);
				this.setVitesse(vitesseFin);
			} else {
				if ((position==Position.GAUCHE&&(normale.egale(normalDroite))) || (position==Position.DROITE&&(normale.egale(normalGauche)))) {
					double vitesseTangenteElem = elements.getVitesse().getY();
					double vitesseNormaleElem = elements.getVitesse().getX();

					double vitesseNormaleFinElem = Bloc.calculVitesse(vitesseNormaleElem, this.getVitesse().getX(), elements.getMasse(), this.getMasse());
					double vitesseFinNormale = Bloc.calculVitesse(this.getVitesse().getX(), vitesseNormaleElem, this.getMasse(), elements.getMasse());

					Vecteur vitesseFinElem = new Vecteur(0,0, vitesseNormaleFinElem, vitesseTangenteElem);
					Vecteur vitesseFin = new Vecteur(0,0,0, vitesseFinNormale);

					elements.setVitesse(vitesseFinElem);
					this.setVitesse(vitesseFin);
				} else {
					if (position==Position.HAUT || position==Position.BAS) {
						elements.getVitesse().setX(-1*elements.getVitesse().getX());
					} else {
						elements.getVitesse().setY(-1*elements.getVitesse().getY());
					}
				}
			}
		} else {
			this.vitesse = -this.vitesse;
		}

	}

	/**
	 * Methode qui gere les collisions lorsqu'il y en a plusieurs
	 */
	public void collisionMultiple(ArrayList<Elements> liste) {
		Elements elemCol;
		
		//Trouver celui qui a cause la premiere collision
		do {
			Iterator<Elements> itChercher = liste.iterator();
			
			etirement -= DISTANCE_COLLISION;
			
			this.deplacement = new AffineTransform();
			switch (position) {
				case GAUCHE: 	this.deplacement.translate(this.etirement, 0);
								break;
				case DROITE:	this.deplacement.translate(-this.etirement, 0);
								break;
				case HAUT:		this.deplacement.translate(0, -this.etirement);
								break;
				case BAS:		this.deplacement.translate(0, this.etirement);
			}
			carre.setDeplacement(deplacement);
			
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
		
		//traitement de la collision
		this.collision(elemCol);
	}

	/**
	 * Change la vitesse du systeme bloc-ressort
	 * @param nouvVitesse La nouvlle vitesse du bloc
	 */
	public void setVitesse(Vecteur nouvVitesse) {
		this.vitesse = -nouvVitesse.getNorme();
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
		
		
		double haut = normalHaut.produitScalaire(position)/this.getHauteur();
		double bas = normalBas.produitScalaire(position)/this.getHauteur();
		double droite = normalDroite.produitScalaire(position)/this.getLargeur();
		double gauche = normalGauche.produitScalaire(position)/this.getLargeur();
		
		Vecteur normale;
		if(haut>bas&&haut>droite&&haut>gauche){
			normale = new Vecteur(normalHaut);
		} else {
			if(bas>droite&&bas>gauche) {
				normale = new Vecteur(normalBas);
			} else {
				if(gauche>droite) {
					normale = new Vecteur(normalGauche);
				} else {
					normale = new Vecteur(normalDroite);
				}
			}
		}
		
		return normale;
	}

	/**
	 * Retourne le type d'element
	 */
	public Elem est() {
		return Elem.RESSORT;
	}

	
	
	/**
	 * Methode permettant de mettre les donné du ressort a jour.
	 */
	public void mettreAJour() {
		switch (position) {
			case GAUCHE: 	this.longueur = Math.abs(this.carre.getX()-this.murG.getX())-this.carre.getLargeur()/2-this.murG.getLargeur()/2;
							break;
			case DROITE:	this.longueur = Math.abs(this.murD.getX()-this.carre.getX())-this.carre.getLargeur()/2-this.murD.getLargeur()/2;
							break;
			case HAUT:		this.longueur = Math.abs(this.murH.getY()-this.carre.getY())-this.carre.getHauteur()/2-this.murH.getHauteur()/2;
							break;
			case BAS:		this.longueur = Math.abs(this.carre.getY()-this.murB.getY())-this.carre.getHauteur()/2-this.murB.getHauteur()/2;
		}
		this.nbPointes = (int) ((constRessort/2)*longueur);
	}

	
	/**
	 * Methode qui permet de faire dessiner le vecteur de vitesse
	 * @param afficheVitesse Vrai si le vecteur de vitesse doit se dessiner
	 */
	public void setAfficheVitesse(boolean afficheVitesse) {
		this.afficheVitesse = afficheVitesse;
		carre.setAfficheVitesse(afficheVitesse);
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
		carre.setAfficheAccel(afficheAccel);
	}
	
	/**
	 * Methode retournant vrai si le vecteur d'acceleration s'affiche
	 * @return Vrai si le vecteur d'acceleration s'affiche
	 */
	public boolean getAfficheAccel() {
		return this.afficheAccel;
	}
	
	/**
	 * Methode permettant de changer la constante de rappel du ressort
	 * @param nouvConst La nouvelle constante de rappel
	 */
	public void setConstanteRappel(double nouvConst) {
		this.constRessort = nouvConst;
		mettreAJour();
	}
	
	/**
	 * Methode permettant de connaitre la constante de rappel du ressort
	 * @return La constante de rappel
	 */
	public double getConstanteRappel() {
		return this.constRessort;
	}
	
	
}