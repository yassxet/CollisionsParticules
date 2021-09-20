
/**
 * Fenêtre  Aide qui regroupe plusieurs fenêtres secondaires
 * @author Yassine El Talhaoui
 * @version 10/2/15
 */
package fenetressecondaires;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Aide extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Aide() {
		setTitle("Aide");
		setBounds(100, 100, 300, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		
		JButton btnJeuxDessais = new JButton("Jeux d'essais");
		btnJeuxDessais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ouvre la fenêtre de Jeux d'essais
				AfficherJeuxEssais();
				fermer();
			}
		});
		btnJeuxDessais.setBounds(30, 31, 230, 25);
		contentPane.add(btnJeuxDessais);
		
		JButton btnNewButton = new JButton("Instructions compl\u00E8tes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ouvre la fenêtre des Instructions complètes
				AfficherInstructions();
				fermer();
			}
		});
		btnNewButton.setBounds(30, 87, 230, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Concepts scientifiques");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ouvre la fenêtre des concept scinetifiques
				AfficherConceptSci();
				fermer();
			}
		});
		btnNewButton_1.setBounds(30, 143, 230, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sources");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ouvre la fenêtre des sources
				AfficherSources();
				fermer();
			}
		});
		btnNewButton_2.setBounds(30, 199, 230, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("A propos");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ouvre la fenêtre A propos
				AfficherApropos();
				fermer();
			}
		});
		btnNewButton_3.setBounds(30, 255, 230, 25);
		contentPane.add(btnNewButton_3);
	}

	/**
	 * Methode prive qui affiche la fenêtre de jeu d'essai
	 */
	
	private void AfficherJeuxEssais(){
		JeuxDEssais jEssai=new JeuxDEssais();
		jEssai.setVisible(true);
		
	}
	/**
	 * Methode prive qui affiche la fenêtre des concept scientifique
	 */
	
	private void AfficherConceptSci(){
		ConceptScietifiques concept= new ConceptScietifiques();
		concept.setVisible(true);
		
	}
	
	
	/**
	 * Methode prive qui affiche la fenêtre a propos
	 */
	private void AfficherApropos(){
		APropos ap= new APropos();
		ap.setVisible(true);
		
	}
	
	
	/**
	 * Methode prive qui affiche la fenêtre des sources
	 */
	
	private void AfficherSources(){
		Sources sources= new Sources();
		sources.setVisible(true);
		
	}
	
	/**
	 * Methode prive qui affiche la fenêtre des instructions
	 */
	private void AfficherInstructions(){
		InstructionsCompletes inst=new InstructionsCompletes();
		inst.setVisible(true);
		
	}
	/**
	 * Methode prive qui ferme la fenêtre d'aide 
	 */
	private void fermer(){
		this.dispose();
	}
	
	
	
}
