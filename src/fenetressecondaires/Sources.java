/**
 * Fenêtre qui affiche les Sources
 * @author Yassine El Talhaoui
 * @version 10/2/15
 */

package fenetressecondaires;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Sources extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public Sources() {
		setTitle("Sources");
		setBounds(100, 100, 450, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnLienDeTlchargement = new JTextPane();
		txtpnLienDeTlchargement.setText("\r\n\r\nLien de t\u00E9l\u00E9chargement du son:\r\nhttp://www.freesfx.co.uk/soundeffects/impacts-crashes/?p=2");
		txtpnLienDeTlchargement.setBounds(10, 31, 414, 76);
		contentPane.add(txtpnLienDeTlchargement);
		
		JLabel lblSources = new JLabel("Sources");
		lblSources.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSources.setBounds(140, 0, 119, 29);
		contentPane.add(lblSources);
	}

}
