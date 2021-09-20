/**
 * Page d'accueil de l'application
 * @author Yassine El Talhaoui
 * @version 9/2/15
 */

package aaplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fenetressecondaires.Aide;

public class PageAccueil extends JFrame {

	
	private static final long serialVersionUID = 7554462380689739206L;
	private JPanel panneauAccueil;
	private App03SimCollisions pagePrincip;
	private ImageIcon imgFondAccueil;



	
	
	
	

	public PageAccueil(App03SimCollisions pagePrincip) {
		
		URL urlFondAccueil = getClass().getClassLoader().getResource("fondAccueil.png");
		imgFondAccueil = new ImageIcon(urlFondAccueil);
		
		
	
		
		
		
		
		
		setTitle("Simulateur de collisions");
		this.pagePrincip = pagePrincip;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 125, 1111, 657);
		panneauAccueil = new JPanel();
		panneauAccueil.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panneauAccueil);
		panneauAccueil.setLayout(null);
		
		JButton btnNouvProjet = new JButton("Nouveau Projet");
		btnNouvProjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Demarre un nouveau projet
				AfficherPagePrincipal();
				setVisible(false);
			}
		});
		btnNouvProjet.setBounds(47, 462, 221, 23);
		panneauAccueil.add(btnNouvProjet);
		
		JButton btnChargerProjet = new JButton("Charger un Projet existant");
		btnChargerProjet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Fenêtre pour charger un projet existant s'affiche
				
				AfficherChargerProjet();	
			}
		});
		btnChargerProjet.setBounds(47, 496, 221, 23);
		panneauAccueil.add(btnChargerProjet);
		
		JButton btnEffacerProj = new JButton("Effacer un projet");
		btnEffacerProj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Fenêtre pour effacer un projet s'affiche
				
				AfficherEffacerProjet();
			}
		});
		btnEffacerProj.setBounds(47, 530, 221, 23);
		panneauAccueil.add(btnEffacerProj);
		
		JButton btnFermerApp = new JButton("Fermer l'application");
		btnFermerApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ferme l'application
				System.exit(0);
			}
		});
		btnFermerApp.setBounds(47, 564, 221, 23);
		panneauAccueil.add(btnFermerApp);
		
		JButton btnNewButton = new JButton("Aide");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AfficherAide();
			}
		});
		btnNewButton.setBounds(47, 428, 221, 23);
		panneauAccueil.add(btnNewButton);
		
		JLabel lblSimM = new JLabel("Simulateur de collisions");
		lblSimM.setFont(new Font("Candara", Font.BOLD, 50));
		lblSimM.setForeground(new Color(0, 0, 0));
		lblSimM.setBounds(47, 40, 613, 144);
		panneauAccueil.add(lblSimM);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 1105, 624);
		panneauAccueil.add(label);
		label.setIcon(imgFondAccueil);
		
		
	}
	
	
	//Méthodes privés pour afficher les fenêtres secondaires
	
	/**
	 * Méthode privé qui affiche la fenêtre de l'aide
	 */
	private void AfficherAide(){
		Aide aide=new Aide();
		aide.setVisible(true);
		
	}
	
	/**
	 * Méthode privé qui affiche la fenêtre pour charger un projet
	 */
	private void AfficherChargerProjet(){
		boolean reussi = pagePrincip.ouvrir();
		if (reussi) {
			AfficherPagePrincipal();
		}
		
	}
	
	/**
	 * Méthode privé qui affiche la fenêtre pour effacer un projet
	 */
	private void AfficherEffacerProjet(){
		pagePrincip.supprimer();
		
	}
	
/**
 * Méthode privé qui affiche la fenêtre principlae
 */
	private void AfficherPagePrincipal(){
		pagePrincip.setVisible(true);
		this.setVisible(false);
		
	}
	
}
