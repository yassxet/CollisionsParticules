/**
 * Fenêtre qui affiche les options d'un triangle
 * @author Yassine El Talhaoui
 * @version 02/03/2015
 */



package fenetressecondaires;

import geometrie.Triangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import evenement.ChangementOptionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OptionsTriangle extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel pnlOptTraingle;
	private Triangle triangle;
	private JSpinner spinCote,spinMasse,spinPosY,spinPosX;
	private final double HAUTEUR_MONDE=20;

	
	private final EventListenerList LISTE_EVENEMENT=new EventListenerList();

	public OptionsTriangle(Triangle elem) {
		
		this.triangle=elem;
		setTitle("Options d'un triangle");
		setBounds(100, 100, 450, 275);
		pnlOptTraingle = new JPanel();
		pnlOptTraingle.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlOptTraingle);
		pnlOptTraingle.setLayout(null);
		
		JPanel panelOptTriangle = new JPanel();
		panelOptTriangle.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOptTriangle.setBounds(29, 21, 367, 170);
		pnlOptTraingle.add(panelOptTriangle);
		panelOptTriangle.setLayout(null);
		
		JLabel lblCt = new JLabel("C\u00F4t\u00E9:");
		lblCt.setBounds(10, 14, 46, 24);
		panelOptTriangle.add(lblCt);
		lblCt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMasse = new JLabel("Masse:");
		lblMasse.setBounds(10, 52, 46, 24);
		panelOptTriangle.add(lblMasse);
		lblMasse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPositionEnX = new JLabel("Position en X:");
		lblPositionEnX.setBounds(10, 90, 83, 24);
		panelOptTriangle.add(lblPositionEnX);
		lblPositionEnX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPositionEnY = new JLabel("Position en Y:");
		lblPositionEnY.setBounds(10, 128, 108, 24);
		panelOptTriangle.add(lblPositionEnY);
		lblPositionEnY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		spinCote = new JSpinner();
		spinCote.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				triangle.setCote1((Double)spinCote.getValue());
				triangle.setCote2((Double)spinCote.getValue());
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
				
			}
		});
		spinCote.setModel(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
		spinCote.setBounds(190, 18, 83, 20);
		spinCote.setValue(elem.getLargeur());
		panelOptTriangle.add(spinCote);
		
		spinMasse = new JSpinner();
		spinMasse.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				triangle.setMasse((Double)spinMasse.getValue());
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
			}
		});
		spinMasse.setModel(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
		spinMasse.setBounds(190, 56, 83, 20);
		spinMasse.setValue(elem.getMasse());
		panelOptTriangle.add(spinMasse);
		
		spinPosX = new JSpinner();
		spinPosX.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				triangle.setX((Double)spinPosX.getValue());
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
		
			}
		});
		spinPosX.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.1));
		spinPosX.setBounds(190, 94, 83, 20);
		spinPosX.setValue(elem.getX());
		panelOptTriangle.add(spinPosX);
		
		spinPosY = new JSpinner();
		spinPosY.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				triangle.setY((Double)spinPosY.getValue());
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.optionChangee();
				}
				
			}
		});
		spinPosY.setModel(new SpinnerNumberModel(0.0, 0.0, HAUTEUR_MONDE, 0.1));
		spinPosY.setBounds(190, 132, 83, 20);
		spinPosY.setValue(elem.getY());
		panelOptTriangle.add(spinPosY);
		
		JLabel lblCentimtres = new JLabel("centim\u00E8tres");
		lblCentimtres.setBounds(283, 22, 72, 14);
		panelOptTriangle.add(lblCentimtres);
		lblCentimtres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblGrammes = new JLabel("kg\r\n");
		lblGrammes.setBounds(283, 58, 72, 17);
		panelOptTriangle.add(lblGrammes);
		lblGrammes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCentimtres_2 = new JLabel("centim\u00E8tres");
		lblCentimtres_2.setBounds(283, 97, 72, 14);
		panelOptTriangle.add(lblCentimtres_2);
		lblCentimtres_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblCenti = new JLabel("centim\u00E8tres");
		lblCenti.setBounds(283, 133, 72, 14);
		panelOptTriangle.add(lblCenti);
		lblCenti.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (ChangementOptionListener ecout:LISTE_EVENEMENT.getListeners(ChangementOptionListener.class)) {
					ecout.supprimer(triangle);
				}
				
			}
		});
		btnSupprimer.setBounds(146, 202, 142, 23);
		pnlOptTraingle.add(btnSupprimer);
	}
	
	
	
	
	/**
	 * Méthode permettant de mettre un objet à l'écoute d'un changement d'option
	 */
	public void addChangementOptionListener(ChangementOptionListener obj){
		LISTE_EVENEMENT.add(ChangementOptionListener.class, obj);
	}
}
