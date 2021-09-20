/**
 * Fenêtre qui affiche les options d'un carré
 * @author Yassine El Talhaoui
 * @version 02/03/2015
 */



package fenetressecondaires;

import evenement.ChangementOptionListener;
import geometrie.Carre;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.border.TitledBorder;

public class OptionsCarre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlCarre;
	private JSpinner spinLong,spinMasse,spinX,spinY,spinLarg;
	private Carre element;

	
	private final EventListenerList LISTE_EVENEMENT=new EventListenerList();

	public OptionsCarre(Carre elem) {
		
		this.element=elem;
		setTitle("Options d'un bloc");
		setBounds(100, 100, 450, 339);
		pnlCarre = new JPanel();
		pnlCarre.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlCarre);
		pnlCarre.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(16, 19, 381, 199);
		pnlCarre.add(panel);
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
		spinLong.setValue(element.getHauteur());
		
		 spinLarg = new JSpinner();
		 spinLarg.setBounds(186, 55, 72, 20);
		 panel.add(spinLarg);
		 spinLarg.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent arg0) {
		 		
		 		element.setHauteur((Double) spinLarg.getValue());
			
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
				
		 		
		 	}
		 });
		 spinLarg.setModel(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
		 spinLarg.setValue(element.getHauteur());
		 
		 spinMasse = new JSpinner();
		 spinMasse.setBounds(186, 91, 72, 20);
		 panel.add(spinMasse);
		 spinMasse.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		element.setMasse((Double) spinMasse.getValue());
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
			 		
		 		}
		 	
		 		
		 	}
		 });
		 spinMasse.setModel(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
		 spinMasse.setValue(element.getMasse());
		 
		 spinX = new JSpinner();
		 spinX.setBounds(186, 132, 72, 20);
		 panel.add(spinX);
		 spinX.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		element.setX((Double) spinX.getValue());
		 		
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
		 		}
		 		
		 
		 		
		 	}
		 });
		 spinX.setModel(new SpinnerNumberModel(0.0, 0.0, 35, 0.1));
		 spinX.setValue(element.getX());
		 
		 spinY = new JSpinner();
		 spinY.setBounds(186, 172, 72, 20);
		 panel.add(spinY);
		 spinY.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		element.setY((Double) spinY.getValue());
		 
		 		for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
		 			ecout.optionChangee();
		 		}
		 		
		 		
		 		
		 	}
		 });
		 spinY.setModel(new SpinnerNumberModel(0.0, 0.0, 20.0, 0.1));
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
		spinLong.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				element.setLargeur((Double) spinLong.getValue());
	
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
					
				}
			
			}
		});
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.supprimer(element);
			
					
				}
			
			}
		});
		btnSupprimer.setBounds(167, 249, 119, 23);
		pnlCarre.add(btnSupprimer);
	}
	
	
	/**
	 * Méthode permettant de mettre un objet à l'écoute d'un changement d'option
	 */
	public void addChangementOptionListener(ChangementOptionListener obj){
		LISTE_EVENEMENT.add(ChangementOptionListener.class, obj);
	}
}
