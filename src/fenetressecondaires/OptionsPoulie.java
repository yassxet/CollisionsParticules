/**
 * Fenêtre qui affiche les options d'une poulie
 * @author Yassine El Talhaoui
 * @version 02/03/2015
 */


package fenetressecondaires;

import evenement.ChangementOptionListener;
import geometrie.Poulie;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class OptionsPoulie extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Poulie poulie1;
	private JSpinner spinRayon;
	private JSpinner spinY,spinX;
	private double HAUTEUR_MONDE=20;
	
	
	
	
	private final EventListenerList LISTE_EVENEMENT=new EventListenerList();
	
	
	
	public OptionsPoulie(Poulie poulie) {
		this.poulie1=poulie;
		setTitle("Options d'un cercle immobile");
		
		setBounds(100, 100, 300, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRayon = new JLabel("Rayon:");
		lblRayon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRayon.setBounds(40, 23, 66, 14);
		contentPane.add(lblRayon);
		
		 spinRayon = new JSpinner();
		 spinRayon.setModel(new SpinnerNumberModel(1.0, 0.1, 5.0, 1.0));
		 spinRayon.setValue(poulie.getHauteur()/2);
		spinRayon.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
					
				}
				
				poulie1.setRayon((((Double) spinRayon.getValue())));
				
			}
		});
		spinRayon.setBounds(132, 22, 66, 20);
		contentPane.add(spinRayon);
		
		
		JLabel lblPosX = new JLabel("Position X:");
		lblPosX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosX.setBounds(40, 62, 77, 14);
		contentPane.add(lblPosX);
		
		spinX = new JSpinner();
		spinX.setModel(new SpinnerNumberModel(1.0, 0.1, 0.0, 1.0));
		spinX.setValue(poulie.getX());
		spinX.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				poulie1.setX((((Double) spinX.getValue())));
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
					
				}
				
			
				
				
			}
		});
		spinX.setBounds(132, 61, 66, 20);
		contentPane.add(spinX);
		
		JLabel lblPositionY = new JLabel("Position Y:");
		lblPositionY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPositionY.setBounds(42, 106, 92, 14);
		contentPane.add(lblPositionY);
		
		spinY = new JSpinner();
		spinY.setModel(new SpinnerNumberModel(1.0, 0.0, HAUTEUR_MONDE, 0.1));
		spinY.setValue(poulie.getY());
		spinY.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				poulie1.setY((Double)spinY.getValue());
				
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
					
				}
				poulie1.setY((((Double) spinY.getValue())));
				
				
				
			}
		});
		spinY.setBounds(132, 105, 66, 20);
		contentPane.add(spinY);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.supprimer(poulie1);
			
					
				}
				
				
			}
		});
		btnSupprimer.setBounds(97, 139, 89, 23);
		contentPane.add(btnSupprimer);
		
		JLabel lblCm = new JLabel("Cm");
		lblCm.setBounds(228, 25, 46, 14);
		contentPane.add(lblCm);
		
		JLabel label = new JLabel("Cm");
		label.setBounds(228, 64, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Cm");
		label_1.setBounds(228, 108, 46, 14);
		contentPane.add(label_1);
	}
	
	
	public void addChangementOptionListener(ChangementOptionListener obj){
		LISTE_EVENEMENT.add(ChangementOptionListener.class, obj);
	}
}
