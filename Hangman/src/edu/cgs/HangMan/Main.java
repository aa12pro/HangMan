package edu.cgs.HangMan;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private String[] words = {"ΓΛΙΣΤΡΩ", "ΑΜΕΙΒΩ", "ΒΟΡΙΝΟΣ", "ΔΙΑΡΡΟΗ", "ΕΚΠΑΓΛΟΣ", "ΚΛΥΣΜΑ", "ΙΛΙΓΓΟΣ", "ΟΙΟΝΕΙ", "ΧΝΟΤΟ", "ΣΥΜΦΥΗΣ", "ΩΣΟΤΟΥ"};
	private String word;
	private JLabel[] label;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Α", "Β", "Γ", "Δ", "Ε", "Ζ", "Η", "Θ", "Ι", "Κ", "Λ", "Μ", "Ν", "Ξ", "Ο", "Π", "Ρ", "Σ", "Τ", "Υ", "Φ", "Χ", "Ψ", "Ω"}));
		comboBox.setBounds(316, 52, 89, 22);
		contentPane.add(comboBox);
		
		Random random = new Random();
		
		word = words[random.nextInt(11)];
		
		label = new JLabel[word.length()];
		
		JLabel HangMan = new JLabel();
		HangMan.setIcon(Constants.Default);
		HangMan.setBounds(12, 0, 271, 169);
		contentPane.add(HangMan);
		
		int x = 50;
		int y = 100;
		int height = 200;
		int width = 200;
		
		for (int i = 0; i < word.length(); i++) {
			label[i] = new JLabel();
			label[i].setText("_");
			label[i].setBounds(x, y, height, width);
			contentPane.add(label[i]);
			x+=10;
		}
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (word.contains((CharSequence) comboBox.getSelectedItem())) {
					for (int i = 0; i < word.length(); i++) {
						if (word.charAt(i) == comboBox.getSelectedItem().toString().charAt(0)) {
							label[i].setText((String) comboBox.getSelectedItem());
						}
					}
					boolean allFilled = true;
				    for (int i = 0; i < word.length(); i++) {
				        if (label[i].getText().equals("_")) {
				            allFilled = false;
				            break;
				        }
				    }
				    if (allFilled) {
				        JOptionPane.showMessageDialog(null, "You won! Congratulations!");
				        restart();
				    }
				} else {
					if (HangMan.getIcon().equals(Constants.Default)) {
						HangMan.setIcon(Constants.Stage_1);
					} else if (HangMan.getIcon().equals(Constants.Stage_1)) {
						HangMan.setIcon(Constants.Stage_2);
					} else if (HangMan.getIcon().equals(Constants.Stage_2)) {
						HangMan.setIcon(Constants.Stage_3);
					} else if (HangMan.getIcon().equals(Constants.Stage_3)) {
						HangMan.setIcon(Constants.Stage_4);
					} else if (HangMan.getIcon().equals(Constants.Stage_4)) {
						HangMan.setIcon(Constants.Stage_5);
					} else if (HangMan.getIcon().equals(Constants.Stage_5)) {
						HangMan.setIcon(Constants.Stage_6);
					} else if (HangMan.getIcon().equals(Constants.Stage_6)) {
						HangMan.setIcon(Constants.Stage_7);
					} else if (HangMan.getIcon().equals(Constants.Stage_7)) {
						HangMan.setIcon(Constants.Stage_8);
					} else {
						JOptionPane.showMessageDialog(null, "You lost! Better Luck Next Time!");
						restart();
					}
				}
				comboBox.removeItemAt(comboBox.getSelectedIndex());
			}
		});
		btnChoose.setBounds(316, 96, 89, 23);
		contentPane.add(btnChoose);
	}
	
	public void restart() {
		int restart = JOptionPane.showOptionDialog(this, "Select an option:", null, JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, Constants.RESTART_OPTIONS, -1);
		if (restart == 0) {
			this.dispose();
			new Main();
		} else {
			System.exit(0);
		}
	}
}
