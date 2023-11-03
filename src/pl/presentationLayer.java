package pl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import dal.PoemDal;
import TransferObject.PoemTO;

import java.util.StringTokenizer;
import java.util.ArrayList;

public class presentationLayer {
	private JFrame frame;
	private JTextField titleField;
	private JTextField verseNumberField;
	private JTextField misra1Field;

	private JButton rootsButton = new JButton("Roots Button");
	private ArrayList<String> tokensMisra1 = new ArrayList<>();
	private JPanel tokenPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

	public presentationLayer() {
		frame = new JFrame("Arabic Poem Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setLayout(new GridLayout(5, 2));

		JLabel titleLabel = new JLabel("Title:");
		titleField = new JTextField();
		JLabel verseNumberLabel = new JLabel("Verse Number:");
		verseNumberField = new JTextField();
		JLabel misra1Label = new JLabel("Misra 1:");
		misra1Field = new JTextField();

		JButton insertButton = new JButton("Insert Poem");
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertPoem();
			}
		});

		JButton TokenButton = new JButton("Tokenize Verses");
		TokenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tokenizeVerses();
			}
		});

		rootsButton.setEnabled(false);
		
		frame.add(titleLabel);
		frame.add(titleField);
		frame.add(verseNumberLabel);
		frame.add(verseNumberField);
		frame.add(misra1Label);
		frame.add(misra1Field);

		frame.add(insertButton);
		frame.add(TokenButton);
		frame.add(tokenPanel);
		frame.add(rootsButton);

		frame.setVisible(true);
	}

	private void insertPoem() {
		String title = titleField.getText();
		int verseNumber = Integer.parseInt(verseNumberField.getText());
		String misra1 = misra1Field.getText();

		PoemTO poem = new PoemTO();
		poem.setTitle(title);
		poem.setVerseNumber(verseNumber);
		poem.setMisra1(misra1);

		PoemDal poemDAO = new PoemDal();

		try {
			poemDAO.savePoem(poem);
			JOptionPane.showMessageDialog(frame, "Poem inserted successfully.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			clearFields();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error occurred while inserting poem.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void tokenizeVerses() {
		String misra1 = misra1Field.getText();

		tokensMisra1.clear(); // Clear previous tokens

		StringTokenizer tokenizer1 = new StringTokenizer(misra1, " ");

		while (tokenizer1.hasMoreTokens()) {
			String token = tokenizer1.nextToken();
			tokensMisra1.add(token);
		}

		refreshTokenButtons();
		//enableRootsButton();
	}

	private void refreshTokenButtons() {
		// Remove existing buttons
		tokenPanel.removeAll();

		// Create buttons for misra1 tokens
		for (String token : tokensMisra1) {
			JButton tokenButton = new JButton(token);
			tokenButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Handle button click for token
					// You can add your custom logic here
					enableRootsButton();
				}
			});
			tokenPanel.add(tokenButton);
		}

		frame.revalidate();
		frame.repaint();
	}

	 private void enableRootsButton() {
	        if (tokensMisra1.size() > 0) {
	            rootsButton.setEnabled(true);
	        }
	    }
	 
	private void clearFields() {
		titleField.setText("");
		verseNumberField.setText("");
		misra1Field.setText("");

		tokensMisra1.clear();

		tokenPanel.removeAll();
		frame.revalidate();
		frame.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new presentationLayer());
	}
}
