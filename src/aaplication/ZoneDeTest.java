 /**
 * Panneau pour afficher les �l�ments et l'animation
 * @author Yassine El Talhaoui
 * @version 9/2/15
 */

package aaplication;

import enemeration.Elem;
import enemeration.Position;
import evenement.ChangementOptionListener;
import fenetressecondaires.OptionCorde;
import fenetressecondaires.OptionsCarre;
import fenetressecondaires.OptionsCercle;
import fenetressecondaires.OptionsPoulie;
import fenetressecondaires.OptionsRessort;
import fenetressecondaires.OptionsTriangle;
import geometrie.Bloc;
import geometrie.Carre;
import geometrie.Cercle;
import geometrie.Corde;
import geometrie.Elements;
import geometrie.Poulie;
import geometrie.Ressort;
import geometrie.Triangle;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import outils.MatriceMondeVersComposant;

public class ZoneDeTest extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Elements> listeElem;
	private boolean premiereFois=true;
	private MatriceMondeVersComposant matMC;
	private final double LARGEUR_DU_MONDE = 35; // en cm
	private Elem elemSelectionne=null;
	private boolean elementSelectionne = false;
	private double xPrecedent, yPrecedent;
	private Elements objetSelectionne;
	private final double LARGEUR_MUR= 0.5; //en cm
	private Thread proc=null;
	private final double TEMPS_SLEEP=1000/40; // en millisecondes
	private double deltaT=TEMPS_SLEEP/1000;
	private boolean animationEnCours= false;
	private boolean pause= false;
	private Carre blocsMurHaut,blocsMurBas,blocsMurDroite,blocsMurGauche;
	private double forceGravitationnelle=9.8;
	private  Elements present;
	private OptionsCercle optCercle;
	private OptionsCarre optCarre;
	private OptionsTriangle optTriangle;
	private OptionsPoulie optPoulie;
	private OptionsRessort optRessort;
	private boolean fenetreOuverte= false;
	private final double HAUTEUR_MONDE=19.77; //Calcul� avec la matriceMondeVersComposant
	private Elements nouveauPoulie;

	private Bloc nouveauTriangle, nouveauCarre,nouveauCercle;
	private Elements nouveauPlanPoulie;
	private AudioClip monclip;
	private boolean affichVitesse = false;
	private boolean affichAccel = false;

	private boolean son = true;
	private JFrame fenetreOpt=null;
	
	

	private ImageIcon imgBtnSonActif;
	private ImageIcon imgBtnSonInactif;
	private JToggleButton tglbtSon;
	private double NB_LIMITE=20;
	
	
	public ZoneDeTest() {
		
		setLayout(null);
		
		
		//Lecture des images
		URL urlBtnSonActif = getClass().getClassLoader().getResource("btnSonActif.png");
		URL urlBtnSonInactif = getClass().getClassLoader().getResource("btnSonInactif.png");
		
		imgBtnSonActif = new ImageIcon(urlBtnSonActif);
		imgBtnSonInactif = new ImageIcon(urlBtnSonInactif);
		
		
		tglbtSon = new JToggleButton("");
		tglbtSon.setBounds(1192, 669, 23, 23);
		tglbtSon.setIcon(imgBtnSonActif);
		this.add(tglbtSon);
		tglbtSon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ecouteur son
				if (tglbtSon.isSelected()) {
					tglbtSon.setIcon(imgBtnSonActif);
				} else {
					tglbtSon.setIcon(imgBtnSonInactif);
				}
				son(tglbtSon.isSelected());
				//fin
			}
		});
		
		
		
		
		URL urlFichier= getClass().getClassLoader().getResource("bruit3.wav" );
		monclip = Applet.newAudioClip(urlFichier);
		
		
		
		setPreferredSize(new Dimension(1225, 692));
		setBackground(Color.WHITE);
		listeElem= new ArrayList<Elements>();


		// Ecouteur de souris pour d�placer l'objet sur la zone de test
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if(elemSelectionne==Elem.CORDE){
					repaint();

				}else{
					limiteEtDrag(e);
					repaint();
				}
			

			}
		});

		// Ecouteur de souris qui place l'objet sur la zone de test ou le d�place s'il est d�ja pr�sent
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dessinPress(e);
				repaint();

			}// fin pressed


			// Ecouteur de souris pour placer l'objet sur la zone de test 
			@Override
			public void mouseReleased(MouseEvent arg0) {
				elementSelectionne=false;
			} // fin released

			//Ecouteur de souris qui affiche les fen�tres d'options des �l�ments
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				if(fenetreOpt!=null){
					fenetreOpt.setVisible(false);
					fenetreOpt=null;
				}
				afficherOptions(e);
				
				
			}
		});

	}

	/**
	 * M�thode qui d�ssine les objets sur la zone de test
	 */

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		if(premiereFois){
			matMC= new MatriceMondeVersComposant(this, LARGEUR_DU_MONDE);
		}

		//Dessin des murs

		blocsMurGauche= new Carre(LARGEUR_MUR/2,HAUTEUR_MONDE/2);
		blocsMurGauche.setHauteur(HAUTEUR_MONDE);
		blocsMurGauche.setLargeur(LARGEUR_MUR);
		blocsMurGauche.setCouleur(Color.black);
		blocsMurGauche.setBougeCollision(false);
		blocsMurGauche.dessiner(g2d, matMC);


		blocsMurDroite= new Carre(LARGEUR_DU_MONDE-LARGEUR_MUR/2,HAUTEUR_MONDE/2);
		blocsMurDroite.setHauteur(HAUTEUR_MONDE);
		blocsMurDroite.setLargeur(LARGEUR_MUR);
		blocsMurDroite.setCouleur(Color.black);
		blocsMurDroite.setBougeCollision(false);
		blocsMurDroite.dessiner(g2d, matMC);

		blocsMurBas= new Carre(LARGEUR_DU_MONDE/2,LARGEUR_MUR/2);
		blocsMurBas.setHauteur(LARGEUR_MUR);
		blocsMurBas.setLargeur(LARGEUR_DU_MONDE);
		blocsMurBas.setCouleur(Color.black);
		blocsMurBas.setBougeCollision(false);
		blocsMurBas.dessiner(g2d, matMC);

		blocsMurHaut= new Carre(LARGEUR_DU_MONDE/2,HAUTEUR_MONDE-LARGEUR_MUR/2);
		blocsMurHaut.setHauteur(LARGEUR_MUR);
		blocsMurHaut.setLargeur(LARGEUR_DU_MONDE);
		blocsMurHaut.setCouleur(Color.black);
		blocsMurHaut.setBougeCollision(false);
		blocsMurHaut.dessiner(g2d, matMC);


		// Note: � continuer Ca ne fonctionne pas si l'objet selectionn� est une
		// corde ou une poulie

		
		Iterator<Elements> parcours = listeElem.iterator();

		while (parcours.hasNext()) {
			present = (Elements) parcours.next();
			present.dessiner(g2d, matMC);


		}

	}


	/**
	 * M�thode qui g�re l'animation
	 */
	public void run() {

		while (animationEnCours&&!pause) { 
			
			Iterator<Elements> parcours = listeElem.iterator();
			
			while (parcours.hasNext()) {
				Elements present = (Elements) parcours.next();
				Elements testCollision = present.avancerAnimation(deltaT, forceGravitationnelle);
				ArrayList<Elements> liste = collision(testCollision);
				int taille = liste.size();
				switch (taille) {
					case 0:		
								break;
								
					case 1:		testCollision.collision(liste.get(0));
								if (son) {
									monclip.play();
								}
								break;
								
					default:	testCollision.collisionMultiple(liste);
								if (son) {
									monclip.play();
								}
				}
			}
			repaint();
			try {
				Thread.sleep((int)(TEMPS_SLEEP));
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}



	/**
	 * M�thode qui d�marrer l'animation
	 * @return vrai s'il n'y a pas de collision ( 2 objets dessin�s l'un sur l'autre) et retourne faux s'il y en a. 
	 */
	public boolean demarrer(){
		// ferme la fenetre d'option ouverte au d�marrage de l'Animation
		if(fenetreOpt!=null){
			fenetreOpt.setVisible(false);
			fenetreOpt=null;
		}
		
		boolean collision=false;
		if (!animationEnCours){
			Iterator<Elements> parcours = listeElem.iterator();
			while (parcours.hasNext()) {
				Elements present = (Elements) parcours.next();

				if (!collision(present).isEmpty()) {
					collision = true;
					present.setCouleur(Color.BLACK);
				}else{
					present.reinitCouleur();
				}

			}
		}
		if(collision==false){
			animationEnCours= true;
			pause= false;
			proc= new Thread(this);
			proc.start();
			return true;
		}


		JOptionPane.showMessageDialog(null, "Erreur: Des objets sont superpos�s", "Erreur", JOptionPane.ERROR_MESSAGE);
		return false;
	}


	/**
	 * M�thode qui arr�te l'animation
	 */
	public void stop(){
		animationEnCours=false;

		Iterator<Elements> parcours = listeElem.iterator();
		while (parcours.hasNext()) {
			Elements present = (Elements) parcours.next();
			present.reinitialiserAnimation();
		}

		repaint();
	}

	/**
	 * M�thode qui met l'animatione en pause
	 */
	public void pause(){
		pause=true;
	}

	/**
	 * Methode pour vider la liste
	 */
	public void viderListe() {
		listeElem.clear();
		repaint();

	}

	/**
	 * Methode pour passer la liste en param�tre
	 * @param list liste d'�l�ment � dessiner dans la zone de test
	 */

	public void setList(ArrayList<Elements> list) {
		listeElem = list;
		forceGravitationnelle = 9.8;
		repaint();
		affichAccel= listeElem.get(0).getAfficheAccel();
		affichVitesse= listeElem.get(0).getAfficheVitesse();
		setAfficheVitesse(affichVitesse);
		setAfficheAcceleration(affichAccel);

	}


	/**
	 * M�thode permettant de changer d'�l�ment dans la liste
	 * @param e Element
	 */
	public void changerElemSelectionne(Elem e) {
		
		if(elemSelectionne==Elem.CORDE && e!=Elem.CORDE){
			viderListe();
		}
		
		elemSelectionne = e;	
		if(elemSelectionne==Elem.CORDE){
			viderListe();
			
			nouveauPlanPoulie = new Corde(LARGEUR_DU_MONDE/2,HAUTEUR_MONDE/3.5);
			listeElem.add(nouveauPlanPoulie);
			
		}
	}

	/**
	 * M�thode qui transforme des pixels en cm ( Monde r�el)
	 * @param e Ecouteur de souris
	 * @return pointMondeReel
	 */
	public Point2D.Double transfoPixel (MouseEvent e){


		Point2D.Double pointPixel= new Point2D.Double(e.getX(), e.getY());
		Point2D.Double pointMondeReel= new Point2D.Double();
		try {
			matMC.inverseTransform(pointPixel, pointMondeReel);
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
		}
		return pointMondeReel;

	}

	/**
	 * M�thode priv� qui affiche la fen�tre d'option d'un cercle
	 */
	private void AfficherOptionCercle(Cercle cercle){
	
		optCercle=new OptionsCercle(cercle);
		optCercle.addChangementOptionListener(new ChangementOptionListener(){
			public void optionChangee() {
				repaint();
			}
	
			
	public void supprimer(Elements elem) {
				listeElem.remove(elem);
				repaint();
				optCercle.setVisible(false);
				
			}
		});
		optCercle.setVisible(true);
		fenetreOpt=optCercle;
		
	}
	
	/**
	 * M�thode priv� qui affiche la fen�tre d'option d'un carr�
	 */
	private void AfficherOptionCarre(Carre carre){
		optCarre=new OptionsCarre(carre);
		optCarre.addChangementOptionListener(new ChangementOptionListener(){
			public void optionChangee() {
				repaint();
			}
			
			public void supprimer(Elements elem) {
				listeElem.remove(elem);	
				repaint();
				optCarre.setVisible(false);
			}
		});
		optCarre.setVisible(true);
		fenetreOpt=optCarre;
		}

	
	
	/**
	 * M�thode priv� qui affiche la fen�tre d'option d'un cercle
	 */
	private void AfficherOptionRessort(Ressort ressort){
	
		optRessort=new OptionsRessort(ressort);
		optRessort.addChangementOptionListener(new ChangementOptionListener(){
			public void optionChangee() {
				repaint();
			}

			public void supprimer(Elements elem) {
			
				listeElem.remove(elem);
				repaint();
				optRessort.setVisible(false);
			}
		});
		optRessort.setVisible(true);
		fenetreOpt=optRessort;
	}
		

	/**
	 * M�thode priv� qui afficher la fen�tre d'option d'un triangle
	 */

	private void AfficherOptionTriangle(Triangle triangle){
	
		optTriangle=new OptionsTriangle(triangle);
		optTriangle.addChangementOptionListener(new ChangementOptionListener(){
			public void optionChangee() {
				repaint();
			}

			public void supprimer(Elements elem) {
				listeElem.remove(elem);
				repaint();
				optTriangle.setVisible(false);
			}	
		});
		
		optTriangle.setVisible(true);
		fenetreOpt=optTriangle;

	}
	
	/**
	 * M�thode priv� qui afficher la fen�tre d'option d'un triangle
	 */

	private void AfficherOptionPoulie(Poulie poulie){
	
		optPoulie=new OptionsPoulie(poulie);
		optPoulie.addChangementOptionListener(new ChangementOptionListener(){
			public void optionChangee() {
				repaint();
			}

			public void supprimer(Elements elem) {
				listeElem.remove(elem);
				repaint();
				optPoulie.setVisible(false);
			}	
		});
		
		optPoulie.setVisible(true);

	}
	
	
	
	
	
	/**
	 * M�thode priv� qui afficher la fen�tre d'option d'une corde
	 */
	private void AfficherOptionCorde(Corde corde){
		
		OptionCorde optCorde=new OptionCorde(corde);
		optCorde.addChangementOptionListener(new ChangementOptionListener(){
			public void optionChangee() {
				repaint();
			}

			public void supprimer(Elements elem) {
				listeElem.remove(elem);
				repaint();
				//optCorde.setVisible(false);
			}
		});
		optCorde.setVisible(true);
		fenetreOpt=optCorde;
		
	}


	/**
	 * M�thode priv� qui affiche les fen�tres d'options des �l�ments
	 * @param e �couteur de souris
	 */
	private void afficherOptions(MouseEvent e){

		Point2D.Double pointMondeReel =transfoPixel(e);
		Iterator<Elements> parcours = listeElem.iterator();

		while (parcours.hasNext()) {
			Elements present = (Elements) parcours.next();
			if (present.contient(pointMondeReel.getX(), pointMondeReel.getY())) {

				if(e.getClickCount()==2 && !animationEnCours&& !fenetreOuverte){

					switch (present.est()) {



					case CERCLE:
						AfficherOptionCercle((Cercle)present);
						break;
					
					case CARRE:
						AfficherOptionCarre((Carre)present);
						break;

					case TRIANGLE:
						AfficherOptionTriangle((Triangle) present);	
						
						break;
					case CORDE:
						AfficherOptionCorde((Corde)present);		
						break;
					case POULIE:
						AfficherOptionPoulie((Poulie)present);		
						break;
					case RESSORT:
						AfficherOptionRessort((Ressort)present);
						break;

						

					}

				
				}
			}
		}
	}


	/**
	 * Methode priv� qui dessine les �l�ments dans la zone de test
	 * @param e �couteur de souris
	 */
	private void dessinerElements(MouseEvent e){

		Point2D.Double pointMondeReel =transfoPixel(e);

		if(!animationEnCours)

			switch (elemSelectionne) {


			case CERCLE:

				nouveauCercle = new Cercle(pointMondeReel.getX(), pointMondeReel.getY());
				if (testMur(nouveauCercle)){
					listeElem.add(nouveauCercle);
					nouveauCercle.setAfficheAccel(affichAccel);
					nouveauCercle.setAfficheVitesse(affichVitesse);
					
				
				}
				break;

			case CARRE:

				nouveauCarre = new Carre(pointMondeReel.getX(), pointMondeReel.getY());
				if (testMur(nouveauCarre)){
					listeElem.add(nouveauCarre);
					
					nouveauCarre.setAfficheAccel(affichAccel);
					nouveauCarre.setAfficheVitesse(affichVitesse);
				}
				break;

			case TRIANGLE:


				nouveauTriangle = new Triangle(pointMondeReel.getX(), pointMondeReel.getY());
				if (testMur(nouveauTriangle)){
					listeElem.add(nouveauTriangle);
					nouveauTriangle.setAfficheAccel(affichAccel);
					nouveauTriangle.setAfficheVitesse(affichVitesse);
				}
				break;

			case CORDE: 
				break;

			case RESSORT:
				Ressort nouveauRessort = new Ressort(pointMondeReel.getX(), pointMondeReel.getY(), blocsMurGauche, blocsMurDroite, blocsMurHaut, blocsMurBas);
				nouveauRessort.setPosition(Position.HAUT);
				if (testMur(nouveauRessort)){
					listeElem.add(nouveauRessort);
					nouveauRessort.setAfficheAccel(affichAccel);
					nouveauRessort.setAfficheVitesse(affichVitesse);
				}
				break;

			case POULIE:
				nouveauPoulie = new Poulie(pointMondeReel.getX(), pointMondeReel.getY());
				if (testMur(nouveauPoulie)){
					listeElem.add(nouveauPoulie);	
				}
				break;
			
			
				

		}



	}
	/**
	 * M�thode qui test si l'objet � dessiner est sur un mur
	 * @param elem l'objet a dessiner
	 * @return faux si l'objet est sur un mur ou vrai si l'objet n'est pas sur un mur.
	 */

	private boolean testMur(Elements elem) {
		if(elem.getX()<LARGEUR_MUR+elem.getLargeur()/2){
			return false;
		}

		if(elem.getX()>LARGEUR_DU_MONDE-LARGEUR_MUR-elem.getLargeur()/2){
			return false;
		}


		if(elem.getY()<LARGEUR_MUR+elem.getHauteur()/2){
			return false;
		}

		if(elem.getY()>HAUTEUR_MONDE-LARGEUR_MUR-elem.getHauteur()/2){
			return false;
		}
		return true;
	}
	/**
	 * M�thode qui limite le d�placement des objets. Un �l�ment ne peut sortir de la zone de test
	 * @param e �couteur de souris
	 */
	private void limiteEtDrag(MouseEvent e){

		Point2D.Double pointMondeReel =transfoPixel(e);
		if(elementSelectionne){


			objetSelectionne.setX(objetSelectionne.getX()+pointMondeReel.x - xPrecedent);
			objetSelectionne.setY(objetSelectionne.getY()+pointMondeReel.y - yPrecedent);


			//D�limimation des objets(les objets ne peuvent aller plus loin que les murs)

			if(objetSelectionne.getX()<LARGEUR_MUR+objetSelectionne.getLargeur()/2){
				objetSelectionne.setX(LARGEUR_MUR+objetSelectionne.getLargeur()/2);
				elementSelectionne=false;
			}

			if(objetSelectionne.getX()>LARGEUR_DU_MONDE-(LARGEUR_MUR+objetSelectionne.getLargeur()/2)){
				objetSelectionne.setX(LARGEUR_DU_MONDE-(LARGEUR_MUR+objetSelectionne.getLargeur()/2));
				elementSelectionne=false;
			}


			if(objetSelectionne.getY()<LARGEUR_MUR+objetSelectionne.getHauteur()/2){
				objetSelectionne.setY(LARGEUR_MUR+objetSelectionne.getHauteur()/2);
				elementSelectionne=false;
			}

			if(objetSelectionne.getY()>HAUTEUR_MONDE-(LARGEUR_MUR+objetSelectionne.getHauteur()/2)){
				objetSelectionne.setY(HAUTEUR_MONDE-(LARGEUR_MUR+objetSelectionne.getHauteur()/2));
				elementSelectionne=false;

			}

			xPrecedent = pointMondeReel.getX();
			yPrecedent = pointMondeReel.getY();
		}
	}



	/**
	 * M�thode qui verifie s'il y a des collisions avec l'element passe en parametre
	 * @param elem L'element a verifier
	 * @return la liste des elements avec lesquels il y a collision
	 */
	private ArrayList<Elements> collision(Elements elem){
		Iterator<Elements> parcours = listeElem.iterator();
		Elements present;
		ArrayList<Elements> liste = new ArrayList<Elements>();
		
		
		while (parcours.hasNext()) {
			present = (Elements) parcours.next();
			if(elem.contient(present)&&elem!=present){
				liste.add(present);
			}
			if(!animationEnCours)
				repaint();
		}

		if(elem.contient(blocsMurHaut))
			liste.add(blocsMurHaut);
		if(elem.contient(blocsMurBas))
			liste.add(blocsMurBas);
		if(elem.contient(blocsMurDroite))
			liste.add(blocsMurDroite);
		if(elem.contient(blocsMurGauche))
			liste.add(blocsMurGauche);

		return liste;
	}

	/**
	 * Methode prive qui dessine l'objet sur la zone de test selon la position de la souris.
	 * @param e �couteur de souris 
	 */

	private void dessinPress(MouseEvent e){

		Point2D.Double pointMondeReel =transfoPixel(e);
		Iterator<Elements> parcours = listeElem.iterator();

		while (parcours.hasNext()&& elementSelectionne==false) {
			Elements present = (Elements) parcours.next();
			if (present.contient(pointMondeReel.getX(), pointMondeReel.getY())) {
				elementSelectionne=true;
				objetSelectionne = present;
				xPrecedent = pointMondeReel.x;
				yPrecedent = pointMondeReel.y;

			}
		}

		if (!elementSelectionne&&elemSelectionne!=null) {  
			if(listeElem.size()<NB_LIMITE){
				dessinerElements(e);
			}else{
				JOptionPane.showMessageDialog(null,"Vous avez atteint la limite du nombre d'objets permis dans la zone de test!","Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}


	/**
	 * Methode qui retourne la liste 	
	 * @return listeElem
	 */
	public ArrayList<Elements> getListe() {
		return listeElem;
	}

	/**
	 * Methode qui permet de modifier la gravit�
	 * @param nouvForce nouvelle gravit�
	 */
	public void setForceGravitationnelle(double nouvForce){
		forceGravitationnelle=nouvForce;
	}
	/**
	 * M�thode qui permet d'afficher les vecteurs vitesses
	 * @param afficheVitesse 
	 */
	public void setAfficheVitesse(boolean afficheVitesse) {
		affichVitesse=afficheVitesse;
		Iterator<Elements> it= listeElem.iterator();
		while(it.hasNext()){
			Elements present=it.next();
			if(present.est()==Elem.CARRE||present.est()==Elem.CERCLE||present.est()==Elem.TRIANGLE){
				Bloc bloc = (Bloc) present;
				bloc.setAfficheVitesse(afficheVitesse);
			} else {
				if (present.est()==Elem.RESSORT) {
					Ressort ressort = (Ressort) present;
					ressort.setAfficheVitesse(afficheVitesse);
				}
			}
		}

	}
	/**
	 * M�thode qui permet d'afficher les vecteurs acc�l�rations
	 * @param afficheAcceleration
	 */
	public void setAfficheAcceleration(boolean afficheAcceleration) {
		affichAccel=afficheAcceleration;
		Iterator<Elements> it= listeElem.iterator();
		while(it.hasNext()){
			Elements present=it.next();
			if(present.est()==Elem.CARRE||present.est()==Elem.CERCLE||present.est()==Elem.TRIANGLE){
				Bloc bloc = (Bloc) present;
				bloc.setAfficheAccel(afficheAcceleration);
			} else {
				if (present.est()==Elem.RESSORT)  {
					Ressort ressort = (Ressort) present;
					ressort.setAfficheAccel(afficheAcceleration);
				}
			}
			
		}


	}
	
	
	
	/**
	 * Methode qui permet d'activer ou de desactiver le son
	 * @param son Si le son est actif
	 */
	public void son(boolean son) {
		this.son  = son;
	}

}