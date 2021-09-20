/**
 * Page principale de l'application
 * @author Alexandre Deneault
 * @version 9/2/15
 */

package aaplication;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import outils.FiltreBinaire;
import enemeration.Elem;
import evenement.ChangementElementSelectionne;
import fenetressecondaires.APropos;
import fenetressecondaires.ConceptScietifiques;
import fenetressecondaires.InstructionsCompletes;
import fenetressecondaires.JeuxDEssais;
import fenetressecondaires.Sources;
import geometrie.Elements;


public class App03SimCollisions extends JFrame {
	

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -7371269437958313755L;
	
	private JPanel contentPane;
	private JButton btnFermer;
	private ListeElements listeElements;
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenu mnOptions;
	private JMenu mnAide;
	private JMenuItem mntmNouveau;
	private JMenuItem mntmOuvrir;
	private JMenuItem mntmSupprimer;
	private JMenuItem mntmQuitter;
	private JMenuItem mntmSauvegarder;
	private JCheckBoxMenuItem chbxAcceleration;
	private JCheckBoxMenuItem chbxVitesse;
	private JMenu mnGravite;
	private JSlider sldGravite;
	private JMenuItem mntmJeuxEssais;
	private JMenuItem mntmInstructions;
	private JMenuItem mntmConcepts;
	private JMenuItem mntmSources;
	private JMenuItem mntmAPropos;
	private JLabel lblElements;
	private ZoneDeTest zoneDeTest;
	private JLabel lblZoneDeTest;
	private JButton btnPlay;
	private JButton btnPause;
	private JButton btnStop;
	
	private ImageIcon imgBtnPlayActif;
	private ImageIcon imgBtnPlayInactif;
	private ImageIcon imgBtnPauseActif;
	private ImageIcon imgBtnPauseInactif;
	private ImageIcon imgBtnStopActif;
	private ImageIcon imgBtnStopInactif;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//cree la fenetre principale
					App03SimCollisions frame = new App03SimCollisions();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					
					//cree et affiche la page d'accueil
					PageAccueil fenetreAccueil = new PageAccueil(frame);
					fenetreAccueil.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Constructeur de la page principale
	 */
	public App03SimCollisions() {
		setTitle("Simulateur de collisions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1616, 876);
		
		
		//Lecture des images
		URL urlBtnPlayActif = getClass().getClassLoader().getResource("btnPlayActif.png");
		URL urlBtnPlayInactif = getClass().getClassLoader().getResource("btnPlayInactif.png");
		URL urlBtnPauseActif = getClass().getClassLoader().getResource("btnPauseActif.png");
		URL urlBtnPauseInactif = getClass().getClassLoader().getResource("btnPauseInactif.png");
		URL urlBtnStopActif = getClass().getClassLoader().getResource("btnStopActif.png");
		URL urlBtnStopInactif = getClass().getClassLoader().getResource("btnStopInactif.png");
		
		imgBtnPlayActif = new ImageIcon(urlBtnPlayActif);
		imgBtnPlayInactif = new ImageIcon(urlBtnPlayInactif);
		imgBtnPauseActif = new ImageIcon(urlBtnPauseActif);
		imgBtnPauseInactif = new ImageIcon(urlBtnPauseInactif);
		imgBtnStopActif = new ImageIcon(urlBtnStopActif);
		imgBtnStopInactif = new ImageIcon(urlBtnStopInactif);
		
		
		
		
		
		
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mntmNouveau = new JMenuItem("Nouveau Projet");
		mntmNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Vider la liste d'elements
				nouveau();
			}
		});
		mnFichier.add(mntmNouveau);
		
		mntmOuvrir = new JMenuItem("Ouvrir");
		mntmOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ecouteur charger un projet
				int reponse = JOptionPane.showConfirmDialog(null, "Si vous continuez, vous perdrez toute progression non sauvegardée.");
				if (reponse==0){
					ouvrir();
				}
				//fin
			}
		});
		mnFichier.add(mntmOuvrir);
		
		mntmSauvegarder = new JMenuItem("Sauvegarder");
		mntmSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Ecouteur Sauvegarder
				sauvegarder();
				//fin
			}
		});
		mnFichier.add(mntmSauvegarder);
		
		mntmSupprimer = new JMenuItem("Supprimer");
		mntmSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ecouteur supprimer
				supprimer();
				//fin
			}
		});
		mnFichier.add(mntmSupprimer);
		
		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//bouton fermer
				fermer();
			}
		});
		mnFichier.add(mntmQuitter);
		
		mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		chbxAcceleration = new JCheckBoxMenuItem("Afficher les acc\u00E9l\u00E9rations");
		chbxAcceleration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ecouteur affiche acceleration
				zoneDeTest.setAfficheAcceleration(chbxAcceleration.isSelected());
				//fin
			}
		});
		mnOptions.add(chbxAcceleration);
		
		chbxVitesse = new JCheckBoxMenuItem("Afficher les vitesses");
		chbxVitesse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ecouteur affiche vitesse
				zoneDeTest.setAfficheVitesse(chbxVitesse.isSelected());
				//fin
			}
		});
		mnOptions.add(chbxVitesse);
		
		mnGravite = new JMenu("Changer la gravit\u00E9");
		mnOptions.add(mnGravite);
		
		sldGravite = new JSlider();
		sldGravite.setPaintLabels(true);
		sldGravite.setMajorTickSpacing(10);
		sldGravite.setPaintTicks(true);
		sldGravite.setValue(98);
		sldGravite.setMinimum(10);
		sldGravite.setMaximum(150);
		Hashtable<Integer, JLabel> tableEtiquette = new Hashtable<Integer, JLabel>();
		for (int i=1; i<=15; i++) {
			tableEtiquette.put(i*10, new JLabel(""+i));
		}
		sldGravite.setLabelTable(tableEtiquette);
		sldGravite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//ecouteur changement gravité
				zoneDeTest.setForceGravitationnelle(sldGravite.getValue());
				//
			}
		});
		mnGravite.add(sldGravite);
		
		mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		mntmJeuxEssais = new JMenuItem("Jeux d'essais");
		mntmJeuxEssais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Affiche la fenetre Jeux d'essais
				JeuxDEssais jEssais = new JeuxDEssais();
				jEssais.setVisible(true);
			}
		});
		mnAide.add(mntmJeuxEssais);
		
		mntmInstructions = new JMenuItem("Instructions Compl\u00E8tes");
		mntmInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Affiche la fenetre d'instructions
				InstructionsCompletes instr = new InstructionsCompletes();
				instr.setVisible(true);
			}
		});
		mnAide.add(mntmInstructions);
		
		mntmConcepts = new JMenuItem("Concepts Scientifiques");
		mntmConcepts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Affiche la fenetre des concepts scientifiques
				ConceptScietifiques concept = new ConceptScietifiques();
				concept.setVisible(true);
			}
		});
		mnAide.add(mntmConcepts);
		
		mntmSources = new JMenuItem("Sources");
		mntmSources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Affiche la fenetre des sources
				Sources sources = new Sources();
				sources.setVisible(true);
			}
		});
		mnAide.add(mntmSources);
		
		mntmAPropos = new JMenuItem("\u00C0 Propos");
		mntmAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Affiche la fenetre A Propos
				APropos propos = new APropos();
				propos.setVisible(true);
			}
		});
		mnAide.add(mntmAPropos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//bouton Fermer
				fermer();
			}
		});
		btnFermer.setBounds(1333, 780, 217, 23);
		contentPane.add(btnFermer);
		
		listeElements = new ListeElements();
		listeElements.addChangementElementSelectionne(new ChangementElementSelectionne() {
			public void changementElement(Elem elementSelectionne) {
				//debut ecouteur
				zoneDeTest.changerElemSelectionne(elementSelectionne);
				//fin ecouteur
			}
		});
		listeElements.setBounds(50, 75, 225, 625);
		contentPane.add(listeElements);
		
		lblElements = new JLabel("\u00C9l\u00E9ments");
		lblElements.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblElements.setBounds(50, 24, 200, 40);
		contentPane.add(lblElements);
		
		
		
		zoneDeTest = new ZoneDeTest();
		zoneDeTest.setBounds(325, 77, 1225, 692);
		contentPane.add(zoneDeTest);
		
		lblZoneDeTest = new JLabel("Zone de Test");
		lblZoneDeTest.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblZoneDeTest.setBounds(325, 24, 307, 40);
		contentPane.add(lblZoneDeTest);
		
		btnPlay = new JButton("");
		btnPlay.setIcon(imgBtnPlayActif);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Écouteur du bouton démarrer
				demarrer();
			}
		});
		btnPlay.setBounds(50, 725, 68, 65);
		contentPane.add(btnPlay);
		
		btnPause = new JButton("");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Écouteur du bouton pause
				mettrePause();
			}
		});
		btnPause.setBounds(129, 725, 68, 65);
		btnPause.setEnabled(false);
		btnPause.setIcon(imgBtnPauseInactif);
		contentPane.add(btnPause);
		
		btnStop = new JButton("");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Écouteur du bouton stop
				arreter();
			}
		});
		btnStop.setBounds(207, 725, 68, 65);
		btnStop.setEnabled(false);
		btnStop.setIcon(imgBtnStopInactif);
		contentPane.add(btnStop);
		
		
	}
	
	
	
	
	/**
	 * Methode privée permettant de fermer l'application
	 */
	public void fermer(){
		int reponse = JOptionPane.showConfirmDialog(null, "Si vous continuez, vous perdrez toute progression non sauvegardée.");
		if (reponse==0){
			System.exit(0);
		}
	}
	
	
	/**
	 * Méthode vidant la liste d'éléments afin de débuter un nouveau projet
	 */
	public void nouveau(){
		int reponse = JOptionPane.showConfirmDialog(null, "Si vous continuez, vous perdrez toute progression non sauvegardée.");
		if (reponse==0){
			arreter();
			zoneDeTest.viderListe();
		}
	}
	
	
	/**
	 * Méthode servant a reutiliser une liste d'élément deja existante
	 * @param liste La liste d'élément a utiliser
	 */
	public void setListeElements(ArrayList<Elements> liste) { //Ouvrir un fichier
		zoneDeTest.setList(liste);
	}
	
	
	/**
	 * Méthode privé démarrant l'animation
	 */
	private void demarrer(){
		if (zoneDeTest.demarrer()){
			btnPlay.setEnabled(false);
			btnPlay.setIcon(imgBtnPlayInactif);
			btnPause.setEnabled(true);
			btnPause.setIcon(imgBtnPauseActif);
			btnStop.setEnabled(true);
			btnStop.setIcon(imgBtnStopActif);
			sldGravite.setEnabled(false);
		}
	}
	
	
	/**
	 * Méthode privé mettant l'animation en pause
	 */
	private void mettrePause(){
		zoneDeTest.pause();
		btnPlay.setEnabled(true);
		btnPlay.setIcon(imgBtnPlayActif);
		btnPause.setEnabled(false);
		btnPause.setIcon(imgBtnPauseInactif);
	}
	
	
	/**
	 * Méthode privé permettant d'arreter l'animation et de réinitialiser les composant
	 */
	private void arreter(){
		zoneDeTest.stop();
		btnPlay.setEnabled(true);
		btnPlay.setIcon(imgBtnPlayActif);
		btnPause.setEnabled(false);
		btnPause.setIcon(imgBtnPauseInactif);
		btnStop.setEnabled(false);
		btnStop.setIcon(imgBtnStopInactif);
		sldGravite.setEnabled(true);
	}
	
	
	
	
	
	/**
	 * Methode qui permet d'ecrire la position des elements dans un fichier
	 */
	private void sauvegarder() {
		String nom = null;
		File fichier = null;
		ObjectOutputStream ecrire = null;
		
		this.arreter();
		
		nom = JOptionPane.showInputDialog(null, "Veuillez nommer votre projet.");
		nom = nom+".bin";
		
		try {
			
			fichier = new File(nom);
			ecrire = null;
			
			//verifier le nom
			while (!nom.matches("\\w*.bin")||fichier.exists()){
				if(!nom.matches("\\w*.bin")) {
					nom = JOptionPane.showInputDialog(null, "Mauvais format. \nSeul les lettres et les chiffries sont acceptés");
					nom = nom+".bin";
				}
				if (fichier.exists()){
					nom = JOptionPane.showInputDialog(null, "Ce nom existe déjà, veuillez en choisir un autre.");
					nom = nom+".bin";
				}
				if (nom!=null) {
					fichier = new File(nom);
				}
			}
			
			if (nom.equals(".bin")||nom.equals("null.bin")) {
				throw new NullPointerException();
			}
			
			fichier = new File(nom);
		
			try {
				ecrire = new ObjectOutputStream(new FileOutputStream(fichier));
				ecrire.writeObject(zoneDeTest.getListe());
				JOptionPane.showMessageDialog(null, "Le fichier a bien été sauvegardé.");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Fichier introuvable.");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Problème à l'écriture.");
			} finally {
				try {
					ecrire.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,"Problème à la fermeture du fichier.");
				}
			}
		} catch (NullPointerException e) {
			//ne rien faire (annuler la sauvegarde)
		}
	}
	
	/**
	 * Methode qui permet d'ouvrir un fichier deja enregistre
	 * @return Vrai lorsque le fichier s'est bien ouvert
	 */
	@SuppressWarnings("unchecked")
	public boolean ouvrir(){
		this.arreter();
		
		ArrayList<Elements> liste = new ArrayList<Elements>();
		File fichier;
		ObjectInputStream lire = null;
		
		try {
			//Trouve les fichiers
			File[] listeFichier = trouveFichier();
			
			//Choisir le fichier a ouvrir
			JComboBox<String> cBoxFichier = new JComboBox<String>();
			for (int i=0; i<listeFichier.length; i++) {
				cBoxFichier.addItem(listeFichier[i].getName());
			}
			int option = JOptionPane.showOptionDialog(null, new Object[] {"Veuillez choisir le fichier à ouvrir:", cBoxFichier}, "Ouvrir un projet",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			
			if (option==0) {
				fichier = new File((String)cBoxFichier.getSelectedItem());
			} else {
				throw new NullPointerException(); //Ouverture annulee
			}
			
			try {
				lire = new ObjectInputStream(new FileInputStream(fichier));
				liste = ((ArrayList<Elements>) lire.readObject());
				
				chbxVitesse.setSelected(liste.get(0).getAfficheVitesse());
				chbxAcceleration.setSelected(liste.get(0).getAfficheAccel());
				if (liste.get(0).est() == Elem.CORDE) {
					listeElements.setSelecCorde();
				} else {
					listeElements.setSelecCercle();
				}
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Fichier introuvable.");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Problème à la lecture.");
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Classe inconnue.");
			} finally {
				try {
					lire.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,"Problème à la fermeture du fichier.");
				}
			}
			
			zoneDeTest.setList(liste);
			return true;
		} catch (NullPointerException e) {
			//Ne rien faire (annulation ou aucun fichier sauvegarde)
			return false;
		}
	}
	
	
	
	/**
	 * Methode qui permet de supprimer un fichier
	 */
	public void supprimer() {
		
		File fichier;
		
		try {
			//Trouve les fichiers
			File[] listeFichier = trouveFichier();
			
			//Choisir le fichier a supprimer
			JComboBox<String> cBoxFichier = new JComboBox<String>();
			for (int i=0; i<listeFichier.length; i++) {
				cBoxFichier.addItem(listeFichier[i].getName());
			}
			int option = JOptionPane.showOptionDialog(null, new Object[] {"Veuillez choisir le fichier à supprimer:", cBoxFichier}, "Supprimer un projet",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			
			if (option==0) {
				fichier = new File((String)cBoxFichier.getSelectedItem());
			} else {
				throw new NullPointerException(); //Ouverture annulee
			}
			fichier.delete();
			JOptionPane.showMessageDialog(null, "Le fichier a bien été supprimé.");
		} catch (NullPointerException e){
			//Ne rien faire (supression annulee ou aucun fichier trouve)
		}
	}
	
	
	
	
	/**
	 * methode qui retourne la liste des fichiers binaires
	 * @return la liste des fichiers
	 */
	private File[] trouveFichier() {
		String nomFichier = Paths.get("").toAbsolutePath().toString();
		File cherche = new File(nomFichier);
		
		File[] listeFichier;
		listeFichier = cherche.listFiles(new FiltreBinaire());
		
		if (listeFichier==null) {
			JOptionPane.showMessageDialog(null, "Il n'y a aucun fichier sauvegardé.");
			return null;
		} else {
			return listeFichier;
		}
	}
}