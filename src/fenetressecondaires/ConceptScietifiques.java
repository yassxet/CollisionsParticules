/**
 * Fenêtre qui affiche les concept scientifique
 * @author Yassine El Talhaoui
 * @version 10/2/15
 */
package fenetressecondaires;

import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConceptScietifiques extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; 
	private ImageIcon imgConcept1,imgConcept2;
	private JLabel lblImage;
	private JLabel lblImg;
	private JButton btnSuite;
	private JButton button;

	
	public ConceptScietifiques() {
		setTitle("Concepts Scientifiques");
		setBounds(0, 0, 750, 976);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSuite = new JButton("Suite");
		btnSuite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblImage.setVisible(false);
				lblImg.setVisible(true);
				btnSuite.setEnabled(false);
				button.setEnabled(true);
			}
		});
		
		button = new JButton("Précédent");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblImage.setVisible(true);
				lblImg.setVisible(false);
				
			}
		});
		button.setBounds(113, 859, 136, 23);
		contentPane.add(button);
		btnSuite.setBounds(612, 859, 89, 23);
		contentPane.add(btnSuite);
		
		JLabel lblTeneurScientifique = DefaultComponentFactory.getInstance().createTitle("Teneur scientifique:\r\n");
		lblTeneurScientifique.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTeneurScientifique.setBounds(22, 27, 229, 26);
		contentPane.add(lblTeneurScientifique);
		
		lblImage = new JLabel("\r\n");
		lblImage.setBounds(10, 64, 813, 716);
		
		contentPane.add(lblImage);
		
		
		URL urlImgConcept = getClass().getClassLoader().getResource("Concept1.png");
		imgConcept1 = new ImageIcon(urlImgConcept);
		
		URL urlImgConcept2 = getClass().getClassLoader().getResource("Concept2.png");
		imgConcept2 = new ImageIcon(urlImgConcept2);
		
		lblImage.setIcon(imgConcept1);
		
		lblImg = new JLabel("");
		lblImg.setBounds(10, 64, 813, 829);
		contentPane.add(lblImg);
		
		lblImg.setIcon(imgConcept2);
		lblImg.setVisible(false);
		

		
		
		
		
	}
}
