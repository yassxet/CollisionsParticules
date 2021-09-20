/**
 * Fenêtre qui affiche les options d'un ressort
 * @author Yassine El Talhaoui
 * @version 30/03/2015
 */


package fenetressecondaires;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import enemeration.Position;
import evenement.ChangementOptionListener;
import geometrie.Ressort;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class OptionsRessort extends JFrame {

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	private Ressort element;
	private JPanel pnlRessort;
	private JSpinner spinLong, spinLarg, spinMasse, spinX, spinY, spinCRappel;
	private final EventListenerList LISTE_EVENEMENT=new EventListenerList();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	


	
	 
	
	public OptionsRessort(Ressort elem) {
		
		
		this.element=elem;
		setTitle("Options d'un ressort");
		setBounds(100, 100, 450, 456);
		pnlRessort = new JPanel();
		pnlRessort.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlRessort);
		pnlRessort.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(16, 19, 381, 388);
		pnlRessort.add(panel);
		panel.setLayout(null);
		
		JLabel lblLongueur = new JLabel("Longueur: ");
		lblLongueur.setBounds(6, 17, 90, 14);
		panel.add(lblLongueur);
		lblLongueur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblLargeur = new JLabel("Largeur:");
		lblLargeur.setBounds(6, 56, 72, 14);
		panel.add(lblLargeur);
		lblLargeur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMasse = new JLabel("Masse:");
		lblMasse.setBounds(6, 92, 46, 14);
		panel.add(lblMasse);
		lblMasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPositionEnX = new JLabel("Position en X");
		lblPositionEnX.setBounds(6, 133, 90, 14);
		panel.add(lblPositionEnX);
		lblPositionEnX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPositionEnY = new JLabel("Position en Y:");
		lblPositionEnY.setBounds(6, 173, 90, 14);
		panel.add(lblPositionEnY);
		lblPositionEnY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		spinLong = new JSpinner();
		spinLong.setBounds(186, 16, 72, 20);
		panel.add(spinLong);
		spinLong.setModel(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
		
		
		spinLong.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				element.setLargeur((Double) spinLong.getValue());
	
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
					
				}
			
			}
		});
		spinLong.setValue(element.getHauteur());
	
		
		 spinLarg = new JSpinner();
		 spinLarg.setBounds(186, 55, 72, 20);
		 panel.add(spinLarg);
		 spinLarg.setModel(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
		 spinLarg.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent arg0) {
		 		
		 		element.setHauteur((Double) spinLarg.getValue());
			
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
				
		 		
		 	}
		 });
		 spinLarg.setValue(element.getHauteur());

		 
		 spinMasse = new JSpinner();
		 spinMasse.setBounds(186, 91, 72, 20);
		 panel.add(spinMasse);
		 spinMasse.setModel(new SpinnerNumberModel(1.0, 0.1, 5.0, 0.1));
		 spinMasse.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		
		 		element.setMasse((Double) spinMasse.getValue());
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
			 		
		 		}
		 	
		 		
		 	}
		 });
		 spinMasse.setValue(element.getMasse());
		 
		 spinX = new JSpinner();
		 spinX.setBounds(186, 132, 72, 20);
		 panel.add(spinX);
		 spinX.setModel(new SpinnerNumberModel(0.0, 0.0, 35, 0.1));
		 spinX.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		
		 		element.setX((Double) spinX.getValue());
		 		
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
		 		}
		 		
		 
		 		
		 	}
		 });
		
		 spinX.setValue(element.getX());
		 
		 spinY = new JSpinner();
		 spinY.setBounds(186, 172, 72, 20);
		 spinY.setModel(new SpinnerNumberModel(0.0, 0.0, 20, 0.1));
		 panel.add(spinY);
		 spinY.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		
		 		element.setY((Double) spinY.getValue());
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
		 		}
		 		
		 		
		 		
		 	}
		 });
		 spinY.setValue(element.getY());
		 
		 JLabel lblCentimtres = new JLabel("centim\u00E8tres");
		 lblCentimtres.setBounds(268, 17, 107, 14);
		 panel.add(lblCentimtres);
		 lblCentimtres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 
		 JLabel lblCenti = new JLabel("centim\u00E8tres");
		 lblCenti.setBounds(268, 58, 107, 14);
		 panel.add(lblCenti);
		 lblCenti.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 
		 JLabel lblGrammes = new JLabel("kg");
		 lblGrammes.setBounds(268, 94, 107, 17);
		 panel.add(lblGrammes);
		 lblGrammes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 
		 JLabel lblCent1 = new JLabel("centim\u00E8tres");
		 lblCent1.setBounds(268, 135, 107, 14);
		 panel.add(lblCent1);
		 lblCent1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 
		 JLabel lblCent = new JLabel("centim\u00E8tres");
		 lblCent.setBounds(268, 175, 107, 14);
		 panel.add(lblCent);
		 lblCent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 
		 JLabel lblCon = new JLabel("Constante de rappel:");
		 lblCon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		 lblCon.setBounds(6, 216, 149, 14);
		 panel.add(lblCon);

		 
		 spinCRappel = new JSpinner();
		 spinCRappel.setBounds(186, 215, 72, 20);
		 spinCRappel.setModel(new SpinnerNumberModel(1.0, 1.0, 15.0, 1.0));
		 panel.add(spinCRappel);
		 spinCRappel.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent arg0) {
		 	
		 		element.setConstanteRappel((Double) spinCRappel.getValue());

		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
		 		
		 	}
		 });
		 
		 spinCRappel.setValue(element.getConstanteRappel());
		 
		 
		 JLabel lblNm = new JLabel("N/cm");
		 lblNm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 lblNm.setBounds(268, 218, 46, 14);
		 panel.add(lblNm);
		 
		 JPanel panel_1 = new JPanel();
		 panel_1.setBorder(new TitledBorder(null, "Attaché au:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 panel_1.setBounds(10, 256, 365, 76);
		 panel.add(panel_1);
		 panel_1.setLayout(null);
		 
		 JRadioButton rdbtnMurDuHaut = new JRadioButton("Mur du Haut");
		 rdbtnMurDuHaut.addActionListener(new ActionListener() {
			 
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		element.setPosition(Position.HAUT);
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
			 		
		 		}
		 		
		 	}
		 });
		 buttonGroup.add(rdbtnMurDuHaut);
		 rdbtnMurDuHaut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 rdbtnMurDuHaut.setBounds(6, 16, 109, 23);
		 panel_1.add(rdbtnMurDuHaut);
		 
		 JRadioButton rdbtnMurDuBas = new JRadioButton("Mur du Bas");
		 rdbtnMurDuBas.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		element.setPosition(Position.BAS);
		 		
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
			 		
		 		}
		 	}
		 });
		 buttonGroup.add(rdbtnMurDuBas);
		 rdbtnMurDuBas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 rdbtnMurDuBas.setBounds(6, 46, 109, 23);
		 panel_1.add(rdbtnMurDuBas);
		 
		 JRadioButton rdbtnMurGauche = new JRadioButton("Mur \u00E0 gauche");
		 rdbtnMurGauche.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		element.setPosition(Position.GAUCHE);
		 		
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
			 		
		 		}
		 	}
		 });
		 buttonGroup.add(rdbtnMurGauche);
		 rdbtnMurGauche.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 rdbtnMurGauche.setBounds(146, 46, 109, 23);
		 panel_1.add(rdbtnMurGauche);
		 
		 JRadioButton rdbtnMurDroite = new JRadioButton("Mur \u00E0 droite");
		 rdbtnMurDroite.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		element.setPosition(Position.DROITE);
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
			 		
		 		}
		 	}
		 });
		 buttonGroup.add(rdbtnMurDroite);
		 rdbtnMurDroite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		 rdbtnMurDroite.setBounds(146, 16, 109, 23);
		 panel_1.add(rdbtnMurDroite);
		 
		Position pos= element.getPosition();
		
		switch(pos){
		
			
			case BAS:
				rdbtnMurDuBas.setSelected(true);
				break;	
				
			case HAUT:
				rdbtnMurDuHaut.setSelected(true);
				break;
					
			case GAUCHE:
				rdbtnMurGauche.setSelected(true);
				break;
				
			case DROITE:
				rdbtnMurDroite.setSelected(true);
				break;
				
				
				
				
				
				
		}
		 
		 JButton btnSupprimer = new JButton("Supprimer");
		 btnSupprimer.setBounds(121, 354, 119, 23);
		 panel.add(btnSupprimer);
		 btnSupprimer.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 	
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.supprimer(element);
		 	
		 			
		 		}
		 	
		 	}
		 });
	}
	
	/**
	 * Méthode permettant de mettre un objet à l'écoute d'un changement d'option
	 */
	public void addChangementOptionListener(ChangementOptionListener obj){
		LISTE_EVENEMENT.add(ChangementOptionListener.class, obj);
	}
}
