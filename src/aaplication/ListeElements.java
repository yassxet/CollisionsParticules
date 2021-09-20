/**
 * Panneau affichant les different elements disponible pour la creation
 * @author Alexandre Deneault
 * @version 9/2/15
 */

package aaplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import outils.MatriceMondeVersComposant;
import enemeration.Elem;
import evenement.ChangementElementSelectionne;
import geometrie.Carre;
import geometrie.Cercle;
import geometrie.Corde;
import geometrie.Elements;
import geometrie.Poulie;
import geometrie.Ressort;
import geometrie.Triangle;

public class ListeElements extends JPanel {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -5613734158197761798L;
	
	private JPanel pnlCarre;
	private JPanel pnlCercle;
	private JPanel pnlTriangle;
	private JPanel pnlPoulie;
	private JPanel pnlCorde;
	private JPanel pnlRessort;
	
	private Color couleurSelection= new Color(100,200,80,150);
	private Elem selection;
	private JPanel pnlSelec;
	
	private Cercle cercle;
	private Carre carre;
	private Triangle triangle;
	private Poulie poulie;
	private Corde corde;
	private Ressort ressort;
	private Carre mur;
	
	private MatriceMondeVersComposant matMondeVersCompo;
	private final double LARGEUR = 7;
	double hauteur;

	private final EventListenerList LISTE_EVENEMENT=new EventListenerList();
	private final JPanel pnlFantome = new JPanel();
	private JPanel pnlSeparation;
	private JLabel lblCercle;
	private JLabel lblCarre;
	private JLabel lblTriangle;
	private JLabel lblCercleImmobile;
	private JLabel lblRessort;
	private JLabel lblSystmeDeCorde;
	
	
	
	/**
	 * Constructeur de la classe ListeElements
	 */
	public ListeElements() {
		this.setPreferredSize(new Dimension(250, 600));
		this.setBackground(Color.WHITE);
		
		cercle = new Cercle(0, 0);
		carre = new Carre(0, 0);
		triangle = new Triangle(0, 0);
		poulie = new Poulie(0, 0);
		corde = new Corde(0, 0);
		mur = new Carre(-3, 0);
		ressort = new Ressort(1.5, 0, mur,mur,mur,mur);
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 28, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		
		pnlCercle = new JPanel() {
			/**
			 * Serial Version ID
			 */
			private static final long serialVersionUID = -6145640152291047194L;

			//Redéfinition de la méthode paintComponent du panneau Cercle
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				dessiner(g, cercle);
			}
		};
		pnlCercle.setBackground(Color.WHITE);
		//Écouteur de clic sur le panneau Cercle
		pnlCercle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clic(e,Elem.CERCLE);
			}
		});
		pnlCercle.setLayout(null);
		GridBagConstraints gbc_pnlCercle = new GridBagConstraints();
		gbc_pnlCercle.fill = GridBagConstraints.BOTH;
		gbc_pnlCercle.insets = new Insets(0, 0, 5, 0);
		gbc_pnlCercle.gridx = 0;
		gbc_pnlCercle.gridy = 0;
		add(pnlCercle, gbc_pnlCercle);
		
		lblCercle = new JLabel("Cercle");
		lblCercle.setBounds(10, 0, 46, 14);
		pnlCercle.add(lblCercle);
		
		pnlFantome.setBounds(0, 0, 225, 100);
		hauteur = LARGEUR*pnlFantome.getHeight()/pnlFantome.getWidth();
		matMondeVersCompo = new MatriceMondeVersComposant(pnlFantome, LARGEUR);
		matMondeVersCompo.deplacerCentre(LARGEUR/2, hauteur/2);
		
		
		
		pnlCarre = new JPanel() {
			/**
			 * Serial Version ID
			 */
			private static final long serialVersionUID = 3397557794454312996L;

			//Redéfinition de la méthode paintComponent du panneau Carre
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				dessiner(g, carre);
			}
		};
		pnlCarre.setBackground(Color.WHITE);
		//Écouteur de clic sur le panneau carré
		pnlCarre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clic(e,Elem.CARRE);
			}
		});
		pnlCarre.setLayout(null);
		GridBagConstraints gbc_pnlCarre = new GridBagConstraints();
		gbc_pnlCarre.fill = GridBagConstraints.BOTH;
		gbc_pnlCarre.insets = new Insets(0, 0, 5, 0);
		gbc_pnlCarre.gridx = 0;
		gbc_pnlCarre.gridy = 1;
		add(pnlCarre, gbc_pnlCarre);
		
		lblCarre = new JLabel("Carre");
		lblCarre.setBounds(10, 0, 46, 14);
		pnlCarre.add(lblCarre);
		
		
		
		pnlTriangle = new JPanel() {
			/**
			 * Serial Version ID
			 */
			private static final long serialVersionUID = -5200149072624007091L;

			//Redéfinition de la méthode paintComponent Triangle
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				dessiner(g, triangle);
			}
		};
		pnlTriangle.setBackground(Color.WHITE);
		//Écouteur de clic sur le panneau triangle
		pnlTriangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clic(e,Elem.TRIANGLE);
			}
		});
		pnlTriangle.setLayout(null);
		GridBagConstraints gbc_pnlTriangle = new GridBagConstraints();
		gbc_pnlTriangle.insets = new Insets(0, 0, 5, 0);
		gbc_pnlTriangle.fill = GridBagConstraints.BOTH;
		gbc_pnlTriangle.gridx = 0;
		gbc_pnlTriangle.gridy = 2;
		add(pnlTriangle, gbc_pnlTriangle);
		
		lblTriangle = new JLabel("Triangle");
		lblTriangle.setBounds(10, 0, 46, 14);
		pnlTriangle.add(lblTriangle);
		
		
		
		pnlPoulie = new JPanel() {	
			/**
			 * Serial Version ID
			 */
			private static final long serialVersionUID = -8502285062020365615L;

			//Redéfinition de la méthode paintComponent Poulie
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				dessiner(g, poulie);
			}
		};
		pnlPoulie.setBackground(Color.WHITE);
		//Écouteur de clic sur le panneau poulie
		pnlPoulie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clic(e,Elem.POULIE);
			}
		});
		pnlPoulie.setLayout(null);
		GridBagConstraints gbc_pnlPoulie = new GridBagConstraints();
		gbc_pnlPoulie.insets = new Insets(0, 0, 5, 0);
		gbc_pnlPoulie.fill = GridBagConstraints.BOTH;
		gbc_pnlPoulie.gridx = 0;
		gbc_pnlPoulie.gridy = 3;
		add(pnlPoulie, gbc_pnlPoulie);
		
		lblCercleImmobile = new JLabel("Cercle Immobile");
		lblCercleImmobile.setBounds(10, 0, 165, 14);
		pnlPoulie.add(lblCercleImmobile);

		
		
		pnlCorde = new JPanel() {	
			/**
			 * Serial Version ID
			 */
			private static final long serialVersionUID = 3442318274136831615L;

			//Redéfinition de la méthode paintComponent Corde
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				dessiner(g, corde);
			}
		};
		pnlCorde.setBackground(Color.WHITE);
		//Écouteur de clic sur le panneau corde
		pnlCorde.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clic(e,Elem.CORDE);
			}
		});
		
				
				
				pnlRessort = new JPanel() {
					/**
					 * Serial Version ID
					 */
					private static final long serialVersionUID = 663886775797548275L;
		
					//Redéfinition de la méthode paintComponent Ressort
					@Override
					public void paintComponent(Graphics g) {
						super.paintComponent(g);
						dessiner(g, ressort);
					}
				};
				pnlRessort.setBackground(Color.WHITE);
				//Écouteur de clic sur le panneau ressort
				pnlRessort.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						clic(e,Elem.RESSORT);
					}
				});
				pnlRessort.setLayout(null);
				GridBagConstraints gbc_pnlRessort = new GridBagConstraints();
				gbc_pnlRessort.insets = new Insets(0, 0, 5, 0);
				gbc_pnlRessort.fill = GridBagConstraints.BOTH;
				gbc_pnlRessort.gridx = 0;
				gbc_pnlRessort.gridy = 4;
				add(pnlRessort, gbc_pnlRessort);
				
				lblRessort = new JLabel("Ressort");
				lblRessort.setBounds(10, 0, 46, 14);
				pnlRessort.add(lblRessort);
		
		pnlSeparation = new JPanel();
		pnlSeparation.setBackground(Color.DARK_GRAY);
		pnlSeparation.setLayout(null);
		GridBagConstraints gbc_pnlSeparation = new GridBagConstraints();
		gbc_pnlSeparation.insets = new Insets(0, 0, 5, 0);
		gbc_pnlSeparation.fill = GridBagConstraints.BOTH;
		gbc_pnlSeparation.gridx = 0;
		gbc_pnlSeparation.gridy = 5;
		add(pnlSeparation, gbc_pnlSeparation);
		
		lblSystmeDeCorde = new JLabel("Syst\u00E8me de corde");
		lblSystmeDeCorde.setForeground(Color.WHITE);
		lblSystmeDeCorde.setBounds(10, 5, 168, 14);
		pnlSeparation.add(lblSystmeDeCorde);
		pnlCorde.setLayout(null);
		GridBagConstraints gbc_pnlCorde = new GridBagConstraints();
		gbc_pnlCorde.fill = GridBagConstraints.BOTH;
		gbc_pnlCorde.gridx = 0;
		gbc_pnlCorde.gridy = 6;
		add(pnlCorde, gbc_pnlCorde);
	}
	
	
	/**
	 * Méthode qui change l'éléments qui est sélectionné et lève l'évenement
	 * @param e L'évenement qui a été généré
	 * @param elem Le type d'élément qui est sélectionné
	 */
	private void clic(MouseEvent e, Elem elem) {
		if (pnlSelec!=e.getSource()){
			int reponse = 0;
			if (elem == Elem.CORDE || selection == Elem.CORDE) {
				reponse = JOptionPane.showConfirmDialog(null, "Les systèmes de cordes et les autres éléments ne sont pas compatible. \nSi vous continuez, vous perdrez toute progression non sauvegardée. Voulez-vous continuer?");
			}
			if (reponse==0){
				if (selection!=null) {
					pnlSelec.setBackground(Color.WHITE);
				}
				pnlSelec=(JPanel) e.getSource();
				pnlSelec.setBackground(couleurSelection);
				selection=elem;
				
				for (ChangementElementSelectionne ecout:LISTE_EVENEMENT.getListeners(ChangementElementSelectionne.class)) {
					ecout.changementElement(selection);
				}
			}	
		}
	}
	
	
	/**
	 * Méthode qui dessine le contenu des panneaux de sélection
	 * @param g Le contexte graphique du panneau
	 * @param element L'élément à dessiner dans le panneau
	 */
	private void dessiner(Graphics g, Elements element) {
		Graphics2D g2d = (Graphics2D) g;
		element.dessiner(g2d, matMondeVersCompo);
		
	}
	
	/**
	 * Methode qui selectionne la corde dans la liste
	 */
	public void setSelecCorde() {
		if (selection!=null) {
			pnlSelec.setBackground(Color.WHITE);
		}
		pnlSelec = pnlCorde;
		pnlSelec.setBackground(couleurSelection);
		selection = Elem.CORDE;
		for (ChangementElementSelectionne ecout:LISTE_EVENEMENT.getListeners(ChangementElementSelectionne.class)) {
			ecout.changementElement(selection);
		}
	}
	
	
	/**
	 * Méthode permettant de mettre un objet à l'écoute d'un changement d'élément selectionné
	 * @param obj L'objet qui doit etre a l'écoute du changement d'élément sellectionné
	 */
	public void addChangementElementSelectionne(ChangementElementSelectionne obj){
		LISTE_EVENEMENT.add(ChangementElementSelectionne.class, obj);
	}


	/**
	 * Methode qui selectionne le cercle dans la liste
	 */
	public void setSelecCercle() {
		if (selection!=null) {
			pnlSelec.setBackground(Color.WHITE);
		}
		pnlSelec = pnlCercle;
		pnlSelec.setBackground(couleurSelection);
		selection = Elem.CERCLE;
		for (ChangementElementSelectionne ecout:LISTE_EVENEMENT.getListeners(ChangementElementSelectionne.class)) {
			ecout.changementElement(selection);
		}
	}
}