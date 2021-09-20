/**
 * Fenêtre A propos 
 * @author Yassine El Talhaoui
 * @version 10/2/15
 */

package fenetressecondaires;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

public class APropos extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public APropos() {
		setTitle("A propos");
		setBounds(100, 100, 450, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnVersionAlphaAlexandre = new JTextPane();
		txtpnVersionAlphaAlexandre.setEditable(false);
		txtpnVersionAlphaAlexandre.setText("Version FINALE\r\nAlexandre Deneault\r\nYassine El Talhaoui\r\n420-SCD-MA INT\u00C9GRATION DES APPRENTISSAGES EN SCIENCES, INFOS ET MATHS \r\nColl\u00E8ge de Maisonneuve\r\n2015");
		txtpnVersionAlphaAlexandre.setBounds(10, 11, 414, 114);
		contentPane.add(txtpnVersionAlphaAlexandre);
	}
}
