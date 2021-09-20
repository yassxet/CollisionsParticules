/**
 * Fenêtre qui affiche les instructions complètes
 * @author Yassine El Talhaoui
 * @version 10/2/15
 */


package fenetressecondaires;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class InstructionsCompletes extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel paneauInstruction;

	
	public InstructionsCompletes() {
		setTitle("Instructions compl\u00E8tes");
		setBounds(100, 100, 958, 443);
		paneauInstruction = new JPanel();
		paneauInstruction.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(paneauInstruction);
		paneauInstruction.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 91, 922, 306);
		paneauInstruction.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Démarrer", null, panel, null);
		panel.setLayout(null);
		
		JTextPane txtpnJhgh = new JTextPane();
		txtpnJhgh.setEditable(false);
		txtpnJhgh.setBounds(0, 0, 917, 278);
		txtpnJhgh.setText("Notre application est un simulateur de collisions avec un module qui repr\u00E9sente un plan inclin\u00E9 munie d'une poulie. Pour bien comprendre le fonctionnement de notre application, voici les instructions \u00E0 suivre.\r\n\r\nTout d'abord,  il faut soit cr\u00E9er un nouveau projet ou en charger un d\u00E9ja existant. \r\n-Dans la page d'accueil, cliquer sur ''Charger un projet\" pour utiliser un projet d\u00E9ja existant ou cliquer sur \"Nouveau projet\" pour cr\u00E9er un nouveau projet.\r\n\r\nLa zone de test s'affiche.\r\n\r\n");
		panel.add(txtpnJhgh);
		
		JLabel lblSuivreLee = new JLabel("Onglet suivant ->\r\n");
		lblSuivreLee.setBounds(255, 277, 179, 27);
		panel.add(lblSuivreLee);
		lblSuivreLee.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Placer un objet", null, panel_1, null);
		panel_1.setLayout(null);
		
		JTextPane txtpnCestDansCette = new JTextPane();
		txtpnCestDansCette.setEditable(false);
		txtpnCestDansCette.setBounds(0, 0, 917, 294);
		panel_1.add(txtpnCestDansCette);
		txtpnCestDansCette.setText("C'est dans cette zone de test que l'on peut observer l'animation des diff\u00E9rents \u00E9l\u00E9ments disponible. Pour commencer, il faut placer ces \u00E9l\u00E9ments sur la zone de test:\r\n\r\n\r\n1.Cliquer une premi\u00E8re fois sur une des \u00E9l\u00E9ments disponible \u00E0 gauche ,soit un Cercle, un Carr\u00E9, un Triangle, un bloc immobile,un ressort et un plan inclin\u00E9 munit d'une poulie.\r\n\r\n2.Apr\u00E8s avoir cliqu\u00E9 sur un des objets, le panneau devient vert. Pour ensuite placer l'objet selectionn\u00E9, il faut cliquer sur la zone de test. L'objet apparait ensuite sur la zone de test\r\n\r\n3. Il est possible de d\u00E9placer un objet plac\u00E9 sur la zone de test. Pour ce faire:\r\n\t-Cliquer ,sans relacher le clic de la souris, et glisser l'objet \u00E0 la position d\u00E9sir\u00E9e sur la zone de test.\r\n\r\n4.Pour ajouter plus d'\u00E9l\u00E9ments, vous pouvez r\u00E9p\u00E9ter l'\u00E9tape 1 et 2 autant de fois d\u00E9sir\u00E9 (20 \u00E9l\u00E9ments maximum), sauf pour le plan inclin\u00E9 et la poulie qui font partie d'un module \u00E0 part.\r\n\r\n5.Comme le syst\u00E8me de plan inclin\u00E9 et de poulie font partie d'un syst\u00E8me \u00E0 part, il ne peut y avoir d'autre \u00E9l\u00E9ment sur la zone de test en pr\u00E9sence de celui-ci.");
		
		JTextPane txtpnXcfc = new JTextPane();
		txtpnXcfc.setEditable(false);
		tabbedPane.addTab("Modifier les options", null, txtpnXcfc, null);
		txtpnXcfc.setText("Avant de d\u00E9marrer l'animation, il est possible de modifier les param\u00E8tres d'un cercle, d'un carr\u00E9, d'un triangle des blocs dans le syst\u00E8me plan inclin\u00E9/poulie et d'un ressort.\r\n\r\nPour changer ces param\u00E8tres, apr\u00E8s avoir plac\u00E9 un objet sur la zone de test, il faut cliquer deux fois sur l'objet en question. Une fen\u00EAtre d'options, d\u00E9pendamment de l'objet, va s'afficher. \r\n\r\n\r\nR\u00E9gler les diff\u00E9rents param\u00E8tres disponibles aux valeurs d\u00E9sir\u00E9es. Vous pouvez en observer les effets instantan\u00E9ment sur la zone de test:\r\n\r\n-Pour un cercle, il est possible de modifier son rayon, sa masse ainsi que sa position en X et en Y\r\n\r\n-Pour un carr\u00E9, on peut modifier sa longueur, sa hauteur, sa masse ainsi que sa position en X et en Y.\r\n\r\n-Pour un triangle, on peut modifier un angle, les deux c\u00F4t\u00E9s du triangle, sa masse et sa position X et Y.\r\n\r\n-Pour un ressort, on peut modifier sa longueur, sa largeur, la constante de rappel , la masse ainsi que la postion en X et Y. On peut aussi indiquer sur quel mur le ressort est plac\u00E9.\r\n\r\nEnfin, pour ce qui est du syst\u00E8me qui comporte un plan inclin\u00E9 et une poulie, on peut modifier la masse des deux blocs suspendu.\r\n\r\nL'application est pr\u00EAte \u00E0 \u00EAtre anim\u00E9.\r\n\r\n\r\n\r\n\r\n\r\n");
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Animation", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTextPane txtpnGhjn = new JTextPane();
		txtpnGhjn.setEditable(false);
		txtpnGhjn.setText("Une fois que l'utilisateur a placer des \u00E9l\u00E9ments sur la zone de test et qu'il a modifier les param\u00E8tres des \u00E9l\u00E9ments (s'il le d\u00E9sire). L'application est pr\u00EAte a \u00EAtre anim\u00E9.\r\n\r\nPour d\u00E9marrer l'animation,cliquer sur le boutton \"D\u00E9marrer\" en bas a gauche. Les \u00E9l\u00E9ments pr\u00E9sents dans la zone de test se d\u00E9placent. On peut observer des collisions entres les objets ( sauf pour le syst\u00E8me plan inclin\u00E9/poulie)\r\n\r\nOn peut interrompre l'application:\r\n\t-Pendant l'animation, cliquer sur le bouton \"Pause\". Les objets sur la zone de test arr\u00EAtent de bouger.\r\n\r\nOn peut \u00E9galement arr\u00EAter l'application:\r\n\t-Pendant l'animation, cliquer sur la bouton \"Stop\". Les objets sur la zone de test arr\u00EAtent de bouger et retournent \u00E0 leurs positions de d\u00E9part.\r\n\r\nPendant l'animation, il est possible d'afficher les vitesses et les acc\u00E9l\u00E9rations sous forme de vecteurs:\r\n\t-Via le menu \"Options\", cocher  la case \"Afficher les vitesses\" / \"Afficher les acc\u00E9l\u00E9rations\".");
		txtpnGhjn.setBounds(0, 0, 917, 278);
		panel_2.add(txtpnGhjn);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Sauvegarder un projet", null, panel_3, null);
		panel_3.setLayout(null);
		
		JTextPane txtpnEnfinIlEst = new JTextPane();
		txtpnEnfinIlEst.setEditable(false);
		txtpnEnfinIlEst.setText("Enfin, il est possible de sauvegarder un projet pour le r\u00E9utiliser ult\u00E9rieurement.\r\nPour sauvegarder un projet en cours:\r\n\t-Via le menu \"Fichier\", cliquer sur \"Sauvegarder un projet\" et donner un nom \u00E0 votre projet.\r\n\r\nVotre projet est ensuite plac\u00E9 dans une liste et est pr\u00EAt \u00E0 \u00EAtre r\u00E9utiliser.\r\n\r\nPour ouvrir un projet sauvegard\u00E9:\r\n\t-Via le menu \"Fichier\", cliquer sur \"Charger un projet existant\", s\u00E9lectionner le projet d\u00E9sir\u00E9 et confirmer.La zone de test appara\u00EEt et le projet est pr\u00EAt \u00E0 \u00EAtre utilis\u00E9.\r\n\r\nIl est \u00E9galement possible de supprimer un projet:\r\n\t-Via le menu \"Fichier\", cliquer sur \"Supprimer un projet\", s\u00E9lectionner le projet d\u00E9sir\u00E9 et confimrer. Le projet est supprim\u00E9.\r\n\t");
		txtpnEnfinIlEst.setBounds(0, 0, 917, 278);
		panel_3.add(txtpnEnfinIlEst);
		
		JLabel lblInstructions = new JLabel("Instructions compl\u00E8tes :");
		lblInstructions.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblInstructions.setForeground(new Color(0, 0, 0));
		lblInstructions.setBounds(121, 24, 408, 56);
		paneauInstruction.add(lblInstructions);
	}
}
