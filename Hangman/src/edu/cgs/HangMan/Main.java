package edu.cgs.HangMan;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;


public class Main extends JFrame {
	
	private JPanel contentPane;
	//Σύνολο των λέξεων
	private String[] words = {"ΓΛΙΣΤΡΩ", "ΑΜΕΙΒΩ", "ΒΟΡΙΝΟΣ", "ΔΙΑΡΡΟΗ", "ΕΚΠΑΓΛΟΣ", "ΚΛΥΣΜΑ", "ΙΛΙΓΓΟΣ", "ΟΙΟΝΕΙ",
			"ΧΝΟΤΟ", "ΣΥΜΦΥΗΣ", "ΩΣΟΤΟΥ", "ΑΛΦΑΒΗΤΟ", "ΜΑΘΗΜΑΤΙΚΑ", "ΕΞΥΠΝΟΣ", "ΠΡΟΓΡΑΜΜΑΤΙΣΜΟΣ", "ΚΛΗΡΟΝΟΜΙΑ", "ΑΡΙΣΤΕΙΑ", 
			"ΠΑΡΑΔΕΙΓΜΑ", "ΣΥΝΕΡΓΑΣΙΑ", "ΕΠΙΤΥΧΙΑ", "ΚΑΛΛΙΤΕΧΝΙΑ", "ΔΗΜΙΟΥΡΓΙΚΟΤΗΤΑ", "ΕΥΦΥΙΑ", "ΑΝΑΚΑΛΥΨΗ", "ΠΕΙΘΩ", "ΑΝΔΡΕΙΑ",
			"ΑΙΣΙΟΔΟΞΙΑ", "ΕΠΙΜΟΝΗ", "ΠΕΙΡΑΜΑΤΙΣΜΟΣ", "ΦΙΛΟΔΟΞΙΑ", "ΑΝΑΠΤΥΞΗ", "ΑΝΤΟΧΗ", "ΑΥΤΟΠΕΠΟΙΘΗΣΗ", "ΑΞΙΟΠΡΕΠΕΙΑ", "ΚΑΙΝΟΤΟΜΙΑ"};
	private String word;
	//Κενά για την κάθε λέξη που μετά γίνονται τα γράμματα
	private JLabel[] spaces;
	//Σύνολο των γραμμάτων
	private JComboBox<String> letters;
	//Εικόνες του κρεμασμένου
	private JLabel HangedMan;
	private JLabel[] label;
	private JComboBox<String> comboBox;
	private JLabel HangMan;
	
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

	//Ότι φαίνεται στο παράθυρο που ανοίγει
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(26, 95, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		letters = new JComboBox();
		letters.setModel(new DefaultComboBoxModel(new String[] {"Α", "Β", "Γ", "Δ", "Ε", "Ζ", "Η", "Θ", "Ι", "Κ", "Λ", "Μ", "Ν", "Ξ", "Ο", "Π", "Ρ", "Σ", "Τ", "Υ", "Φ", "Χ", "Ψ", "Ω"}));
		letters.setBounds(491, 51, 73, 34);
		contentPane.add(letters);
		

		Random random = new Random();
		
		//Διαλέγει την λέξη αφού παίρνει έναν τυχαίο αριθμό από το 0 μέχρι το άθροισμα των λέξεων σε αυτή τη λίστα "words"
		word = words[random.nextInt(words.length)];
		
		spaces = new JLabel[word.length()];
		
		HangedMan = new JLabel();
		//Βάζει την εικόνα της κρεμάλας
		HangedMan.setIcon(Constants.Default);
		//Βάζει τις διαστάσεις και τη θέση της εικόνας
		HangedMan.setBounds(12, 12, 269, 390);
		contentPane.add(HangedMan);
		
		int x = 298, y = 73, height = 300, width = 400;
		
		for (int i = 0; i < word.length(); i++) {
			spaces[i] = new JLabel();
			spaces[i].setText("_");
			spaces[i].setFont(new Font("Georgia", Font.PLAIN, 30));
			spaces[i].setBounds(x, y, height, width);
			contentPane.add(spaces[i]);
			x+=30;
		}
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (word.contains((CharSequence) letters.getSelectedItem())) {
					for (int i = 0; i < word.length(); i++) {
						if (word.charAt(i) == letters.getSelectedItem().toString().charAt(0)) {
							spaces[i].setText((String) letters.getSelectedItem());
						}
					}
					boolean allFilled = true;
				    for (int i = 0; i < word.length(); i++) {
				        if (spaces[i].getText().equals("_")) {
				            allFilled = false;
				            break;
				        }
				    }
				    //Αν όλα τα κενά έχουν συμπληροθεί ο παίκτης νίκησε
				    if (allFilled) {
				        JOptionPane.showMessageDialog(null, "You won! Congratulations!");
				        restart();
				    }
				} else {
					//Αλλάζει τις εικόνες ανάλογα με το στάδιο που βρίσκονται
					if (HangedMan.getIcon().equals(Constants.Default)) {
						HangedMan.setIcon(Constants.Stage_1);
					} else if (HangedMan.getIcon().equals(Constants.Stage_1)) {
						HangedMan.setIcon(Constants.Stage_2);
					} else if (HangedMan.getIcon().equals(Constants.Stage_2)) {
						HangedMan.setIcon(Constants.Stage_3);
					} else if (HangedMan.getIcon().equals(Constants.Stage_3)) {
						HangedMan.setIcon(Constants.Stage_4);
					} else if (HangedMan.getIcon().equals(Constants.Stage_4)) {
						HangedMan.setIcon(Constants.Stage_5);
					} else if (HangedMan.getIcon().equals(Constants.Stage_5)) {
						HangedMan.setIcon(Constants.Stage_6);
					} else if (HangedMan.getIcon().equals(Constants.Stage_6)) {
						HangedMan.setIcon(Constants.Stage_7);
					} else if (HangedMan.getIcon().equals(Constants.Stage_7)) {
						HangedMan.setIcon(Constants.Stage_8);
					} else {
						//Άμα τελιώσουν οι φάσεις της εικόνας ο παίκτης χάνει
						JOptionPane.showMessageDialog(null, "You lost! Better Luck Next Time!");
						restart();
					}
				}
				//Βγάζει από το σύνολο των γραμμάτων το γράμμα που έχει ήδη επιλεχτεί
				letters.removeItemAt(letters.getSelectedIndex());
			}
		});
		btnChoose.setBounds(491, 102, 89, 23);
		contentPane.add(btnChoose);
	}
	
	//Είτε κλείνει και τελιώνει το παιχνίδι ή το ξανααρχίζει
	public void restart() {
		int restart = JOptionPane.showOptionDialog(this, "Select an option:", null, JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, Constants.RESTART_OPTIONS, -1);
		if (restart == 0) {
			this.dispose();
			new Main().setVisible(true);
		} else {
			System.exit(0);
		}
	}
}