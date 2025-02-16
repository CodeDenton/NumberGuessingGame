import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NumberGuessingGame extends JFrame {

	// Main method
	public static void main(String[] args) {
		new NumberGuessingGame();
	}

	int numAnswer;
	boolean gameWon = false;
	public static int count = 0;

	JButton answerButton = new JButton("GUESS");
	JTextField answerBox = new JTextField();
	JLabel text = new JLabel("Guess the number between 1 and 100!");
	JLabel gameWonText = new JLabel("tester");
	JButton quitButton = new JButton("Quit");
	JButton resetButton = new JButton("Reset");

	NumberGuessingGame() {
		this.setSize(500, 500);
		this.setResizable(false);
		this.setTitle("Number Guessing Game");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLayout(null); // Disable layout manager for absolute positioning
//        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 200));
		this.getContentPane().setBackground(Color.decode("#9C89B8"));
		startGame();

		// Text
		text.setFont(new Font("Helvetica", Font.BOLD, 20)); // Set font and size
		text.setForeground(Color.BLACK); // Set text color
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setBounds(50, 80, 400, 100);

		add(text);

		// gameWonText
		gameWonText.setFont(new Font("Helvetica", Font.BOLD, 50)); // Set font and size
		gameWonText.setForeground(Color.BLACK); // Set text color
		gameWonText.setHorizontalAlignment(SwingConstants.CENTER);
		gameWonText.setBounds(50, 30, 400, 100);

		add(gameWonText);

		// Answer Textbox
		answerBox.setHorizontalAlignment(JTextField.CENTER); // Center text inside the field
		answerBox.setBackground(Color.LIGHT_GRAY);
		answerBox.setForeground(Color.BLACK);
		answerBox.setBounds(100, 150, 300, 100);
		answerBox.setFont(new Font("Arial", Font.BOLD, 40));
		answerBox.setBackground(Color.decode("#FCEFEF"));

		add(answerBox);

		// Reset Button
		resetButton.setBounds(150, 325, 200, 60);
		resetButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		// Set color
		resetButton.setBackground(Color.decode("#FCEFEF"));
		resetButton.setForeground(Color.BLACK);
		resetButton.setOpaque(true);
		resetButton.setContentAreaFilled(true);
		resetButton.setBorderPainted(true);
		resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// hover effect
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				resetButton.setBackground(Color.decode("#549F93")); // hover color
			}

			// reset color to original
			@Override
			public void mouseExited(MouseEvent e) {
				resetButton.setBackground(Color.decode("#FCEFEF"));
			}
		});

		// action listener
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});

		add(resetButton);

		// Quit Button
		quitButton.setBounds(150, 380, 200, 60);
		quitButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		quitButton.setBackground(Color.decode("#FCEFEF"));

		// Set color
		quitButton.setBackground(Color.decode("#FCEFEF"));
		quitButton.setForeground(Color.BLACK);
		quitButton.setOpaque(true);
		quitButton.setContentAreaFilled(true);
		quitButton.setBorderPainted(true);
		quitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// hover effect
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setBackground(Color.decode("#549F93")); // hover color
			}

			// reset color to original
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setBackground(Color.decode("#FCEFEF"));
			}
		});

		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add(quitButton);

		// Answer Button
		answerButton.setBounds(150, 250, 200, 80);
		answerButton.setFont(new Font("Helvetica", Font.BOLD, 30));

		// Set color
		answerButton.setBackground(Color.decode("#FCEFEF"));
		answerButton.setForeground(Color.BLACK);
		answerButton.setOpaque(true);
		answerButton.setContentAreaFilled(true);
		answerButton.setBorderPainted(true);
		answerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// hover effect
		answerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				answerButton.setBackground(Color.decode("#549F93")); // hover color
			}

			// reset color to original
			@Override
			public void mouseExited(MouseEvent e) {
				answerButton.setBackground(Color.decode("#FCEFEF"));
			}
		});

		answerButton.addActionListener(new ActionListener() {
			int userGuessInt;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameWon) {
					startGame();
					return;
				}
				String userGuess = answerBox.getText();
				try {
					userGuessInt = Integer.parseInt(userGuess);
				} catch (NumberFormatException d) {
					answerBox.setText("");
					text.setText("Invalid answer, try again");
				}
				if (userGuessInt < 1 || userGuessInt > 100) {
					answerBox.setText("");
					text.setText("Invalid answer, try again:");
				} else if (userGuessInt > numAnswer) {
					answerBox.setText("");
					text.setText("Little lower, try again");
					count++;
				} else if (userGuessInt < numAnswer) {
					answerBox.setText("");
					text.setText("Little higher, try again");
					count++;
				} else if (userGuessInt == numAnswer) {
					count++;
					answerBox.setText("");
					if (count == 1) {
						text.setText("COMPLETED IN " + count + " TRY!");
					} else {
						text.setText("Completed in " + count + " tries");
					}
					gameWonText.setText("CORRECT");
					answerButton.setText("Play again");
					gameWon = true;
					answerBox.setEditable(false);

				}
			}
		});
		add(answerButton);

		this.setVisible(true);

	}

	void startGame() {
		numAnswer = (int) (Math.random() * 100) + 1;
		System.out.println(numAnswer);
		gameWonText.setText("");
		count = 0;
		gameWon = false;
		answerButton.setText("Guess");
		answerBox.setText("");
		text.setText("Guess the number between 1 and 100!");
		answerBox.setEditable(true);
	}
}
