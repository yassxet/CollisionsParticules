 /**
 * Fenêtre qui affiche les Jeux d'essais
 * @author Yassine 
 * @version 10/2/15
 */

package fenetressecondaires;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class JeuxDEssais extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel pnlJeux;

	
	public JeuxDEssais() {
		setTitle("Jeux d'essais");
		setBounds(100, 100, 517, 741);
		pnlJeux = new JPanel();
		pnlJeux.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlJeux);
		pnlJeux.setLayout(null);
		
		JLabel lblJeuxDessais = new JLabel("Jeux d'essais:");
		lblJeuxDessais.setAutoscrolls(true);
		lblJeuxDessais.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblJeuxDessais.setBounds(25, 11, 197, 62);
		pnlJeux.add(lblJeuxDessais);
		
		JTextPane txtpnVbvc = new JTextPane();
		txtpnVbvc.setAutoscrolls(false);
		txtpnVbvc.setEditable(false);
		txtpnVbvc.setText("1-Cliquer sur le bouton ''Nouveau projet'' de la page principale.\r\n\t-La zone de test apparait\r\n2-Cliquer sur le plan inclin\u00E9 noir\r\n\t-Une fen\u00EAtre d'avertissement apparait. Confirmer.\r\n3-Un plan inclin\u00E9 avec une poulie et deux blocs apparaissent sur la zone de test\r\n4-Faire un double cliquer sur la plan\r\n\t-Un fen\u00EAtre d'options apparait\r\n5-Modifier la masse du bloc 1 \u00E0 1,4kg\r\n6- Cliquer sur play\r\n\t-Les deux blocs bougent\r\n7-Cliquer sur le boutton stop pour revenir aux positions initiales\r\n8- Ensuite, cliquer sur le cercle rouge.\r\n\t-Une fen\u00EAtre d'avertissement apparait. Confimer.\r\n8-Placer une dizaine de cercle dans la zone de test.\r\n9-Appuyer sur le bouton \"D\u00E9marrer\"\r\n\t-Observer les collisions et les d\u00E9placements des objets.\r\n\t-Il y a un son lors des collisions. (Pour le d\u00E9sactiver, appuyer sur le petit bouton en bas \u00E0 droite)\r\n4-Appuyer sur le menu \"Fichier\" puis cliquez sur \"Sauvegarder \".\r\n5-Entrer le nom \"bille\".\r\n6-Appuyer sur le menu \"Fichier\" puis cliquez sur \"Nouveau Projet\".\r\n4-Appuyer sur le menu \"Fichier\" puis cliquez sur \"Ouvrir \".\r\n5-Selectionner le projet \"billeModifies\".\r\n6-Appuyer sur le bouton \"D\u00E9marrer\"\r\n\t-Observer les collisions et les d\u00E9placements des objets.\r\n7-Appuyer sur le menu \"Fichier\" puis cliquez sur \"Ouvrir \".\r\n8-Selectionner le projet \"rotation\".\r\n9-Appuyer sur le bouton \"D\u00E9marrer\"\r\n\t-Observer les d\u00E9placements et les rotations des objets. \r\n10-Appuyer sur le menu \"Options\" puis cliquez sur \"Afficher les vitesses\".\r\n11-Desactiver l'affichage des vitesses.\r\n12-Appuyer sur le menu \"Fichier\" puis cliquez sur \"Ouvrir \".\r\n13-Selectionner le projet \"ressortDroit\".\r\n14-Appuyer sur le bouton \"D\u00E9marrer\"\r\n\t-Observer les collisions et les d\u00E9placements des objets.\r\n\t-Le cercle du bas est 10x plus lourd que le deuxi\u00E8me.\r\n\t-Les vitesses s'affichent parceque le projet \u00E0 \u00E9t\u00E9 sauvegard\u00E9 avec les vitesses affich\u00E9es.\r\n15--Appuyer sur le menu \"Fichier\" puis cliquez sur \"Ouvrir \".\r\n16-Selectionner le projet \"ressortBas\".\r\n17-Appuyer sur le bouton \"D\u00E9marrer\"\r\n\t-Observer les collisions et les d\u00E9placements des objets.\r\n18-Double-cliquer sur le cercle.\r\n19-Mettre la masse \u00E0 4kg.\r\n20-Appuyer sur le bouton \"D\u00E9marrer\"\r\n\t-Observer les collisions et les d\u00E9placements des objets.\r\n21-Quitter l'application en appuyant sur le bouton \"Fermer\" en bas \u00E0 droite.");
		txtpnVbvc.setBounds(10, 68, 478, 624);
		pnlJeux.add(txtpnVbvc);
	}
}
