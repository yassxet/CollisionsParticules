/**
 * Fenêtre qui affiche les options d'un cercle
 * @author Yassine El Talhaoui
 * @version 02/03/2015
 */

package fenetressecondaires;

import geometrie.Cercle;

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

import evenement.ChangementOptionListener;

public class OptionsCercle extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel pnlOptCercle;
	private Cercle cercle;
	private JSpinner spinRayon,	spinMasse,spinPosX, spinPosY;
	private final double LARGEUR_MONDE=35,HAUTEUR_MONDE=20;
	private final EventListenerList LISTE_EVENEMENT=new EventListenerList();
	
	
	public OptionsCercle(Cercle elem) {
		this.cercle=elem;

		setTitle("Options d'un cercle");
		setBounds(100, 100, 408, 263);
		pnlOptCercle = new JPanel();
		pnlOptCercle.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlOptCercle);
		pnlOptCercle.setLayout(null);
		
		JPanel panelOptCercle = new JPanel();
		panelOptCercle.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOptCercle.setBounds(16, 16, 350, 159);
		pnlOptCercle.add(panelOptCercle);
		panelOptCercle.setLayout(null);
		
		spinRayon = new JSpinner();
		spinRayon.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				cercle.setRayon((Double)spinRayon.getValue());
				
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
			}
		});
		spinRayon.setModel(new SpinnerNumberModel(1.0, 0.1, 5.0, 0.1));
		spinRayon.setBounds(181, 16, 73, 20);
		spinRayon.setValue(cercle.getRayon());
		panelOptCercle.add(spinRayon);
		
		JLabel lblRayonDunCercle = new JLabel("Rayon d'un Cercle:");
		lblRayonDunCercle.setBounds(6, 17, 152, 14);
		panelOptCercle.add(lblRayonDunCercle);
		lblRayonDunCercle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCm = new JLabel("Centim\u00E8tres");
		lblCm.setBounds(264, 19, 80, 14);
		panelOptCercle.add(lblCm);
		
		JLabel lblMasseDunCercle = new JLabel("Masse d'un Cercle:");
		lblMasseDunCercle.setBounds(6, 53, 152, 14);
		panelOptCercle.add(lblMasseDunCercle);
		lblMasseDunCercle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		spinMasse = new JSpinner();
		spinMasse.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				cercle.setMasse((Double)spinMasse.getValue());
				
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
			}
		});
		spinMasse.setModel(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
		spinMasse.setBounds(181, 52, 73, 20);
		spinMasse.setValue(cercle.getMasse());
		panelOptCercle.add(spinMasse);
		
		JLabel lblGrammes = new JLabel("Kg");
		lblGrammes.setBounds(264, 55, 73, 14);
		panelOptCercle.add(lblGrammes);
		
		JLabel lblPositionEnX = new JLabel("Position en X:");
		lblPositionEnX.setBounds(6, 90, 152, 14);
		panelOptCercle.add(lblPositionEnX);
		lblPositionEnX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPositionEnY = new JLabel("Position en Y:");
		lblPositionEnY.setBounds(6, 133, 152, 14);
		panelOptCercle.add(lblPositionEnY);
		lblPositionEnY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		spinPosX = new JSpinner();
		spinPosX.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				cercle.setX((Double)spinPosX.getValue());
				
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
			}
		});
		spinPosX.setModel(new SpinnerNumberModel(0.0, 0.0, LARGEUR_MONDE, 0.1));
		spinPosX.setBounds(181, 87, 73, 20);
		spinPosX.setValue(cercle.getX());
		panelOptCercle.add(spinPosX);
		
		spinPosY = new JSpinner();
		spinPosY.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				cercle.setY((Double)spinPosY.getValue());
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
			}
		});
		spinPosY.setModel(new SpinnerNumberModel(0.0, 0.0, HAUTEUR_MONDE, 0.1));
		spinPosY.setBounds(181, 132, 73, 20);
		spinPosY.setValue(cercle.getY());
		panelOptCercle.add(spinPosY);
		
		JLabel label = new JLabel("Centim\u00E8tres");
		label.setBounds(264, 92, 80, 14);
		panelOptCercle.add(label);
		
		JLabel label_1 = new JLabel("Centim\u00E8tres");
		label_1.setBounds(264, 135, 80, 14);
		panelOptCercle.add(label_1);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Méthode pour supprimer un objet 
				
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.supprimer(cercle);
				}
			
			}
		});
		btnSupprimer.setBounds(114, 191, 124, 23);
		pnlOptCercle.add(btnSupprimer);
	}
	
	
	/**
	 * Méthode permettant de mettre un objet à l'écoute d'un changement d'option
	 */
	public void addChangementOptionListener(ChangementOptionListener obj){
		LISTE_EVENEMENT.add(ChangementOptionListener.class, obj);
	}
	
}
