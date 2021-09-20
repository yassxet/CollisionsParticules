/**
 * fenêtre qui affiche les options pour une corde
 * @author Yassine El Talhaoui
 * @version 01/04/2015
 */
package fenetressecondaires;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import evenement.ChangementOptionListener;
import geometrie.Corde;

import javax.swing.JLabel;
import javax.swing.JSpinner;

import java.awt.Font;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;


public class OptionCorde extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Corde element;
	private JSpinner spinMasseB2, spinMasseB1;
	private JSpinner spinCoef;



	private final EventListenerList LISTE_EVENEMENT=new EventListenerList();
	
	
	
	private JPanel pnlOptCorde;
	/**
	 * Create the frame.
	 */
	public OptionCorde(Corde elem) {
		this.element=elem;
		setTitle("Options pour le module plan inclin\u00E9/poulie");
		setBounds(100, 100, 261, 179);
		pnlOptCorde = new JPanel();
		pnlOptCorde.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlOptCorde);
		pnlOptCorde.setLayout(null);
		
		JLabel lblMasseBloc = new JLabel("Masse bloc 1:");
		lblMasseBloc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMasseBloc.setBounds(10, 11, 97, 23);
		pnlOptCorde.add(lblMasseBloc);
		
		spinMasseB1 = new JSpinner();
		spinMasseB1.setModel(new SpinnerNumberModel(1.0, 0.1, 5.0, 0.1));
		spinMasseB1.setValue(element.getMasseBlocVertical());
		spinMasseB1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				element.setMasseBlocVertical(((Double)spinMasseB1.getValue()));
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
					
			}
		});
		
		spinMasseB1.setBounds(10, 45, 71, 20);
		pnlOptCorde.add(spinMasseB1);

		
		JLabel lblMasseBloc_1 = new JLabel("Masse bloc 2:");
		lblMasseBloc_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMasseBloc_1.setBounds(140, 11, 116, 23);
		pnlOptCorde.add(lblMasseBloc_1);
		
		spinMasseB2 = new JSpinner();
		spinMasseB2.setModel(new SpinnerNumberModel(1.0, 0.1, 5.0, 0.1));
		spinMasseB2.setValue(element.getMasseBlocIncline());
		spinMasseB2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				element.setMasseBlocIncline((Double)spinMasseB2.getValue());
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
				
				
			}
		});
		spinMasseB2.setBounds(150, 45, 71, 20);
		pnlOptCorde.add(spinMasseB2);
		
		JLabel lblCoefficient = new JLabel("Coefficient de frottement:");
		lblCoefficient.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCoefficient.setBounds(10, 91, 211, 14);
		pnlOptCorde.add(lblCoefficient);
		
		spinCoef = new JSpinner();
		spinCoef.setModel(new SpinnerNumberModel(1.0, 0.0, 1.0, 0.1));
		spinCoef.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				element.setCoeficient((Double)spinCoef.getValue());
				
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
				
				
				
			}
		});
		spinCoef.setBounds(83, 116, 54, 20);
		spinCoef.setValue(element.getCoefFriction());
		pnlOptCorde.add(spinCoef);
		
	}
	
	
	/**
	 * Méthode permettant de mettre un objet à l'écoute d'un changement d'option
	 */
	public void addChangementOptionListener(ChangementOptionListener obj){
		LISTE_EVENEMENT.add(ChangementOptionListener.class, obj);
	}
}
