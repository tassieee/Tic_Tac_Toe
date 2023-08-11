

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TTT {

	public static void addComponentsToPane(Container pane) {
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JLabel title = new JLabel("Welcome to Tic Tac Toe");
		c.fill = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(title, c);

		JButton buttonStart = new JButton("Start Game");
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(buttonStart, c);

		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame gameFrame = new JFrame("Tic Tac Toe");
				gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				gameFrame.getContentPane().add(new TicTacToePanel());
				gameFrame.setSize(300, 300);
				gameFrame.setLocationRelativeTo(null);
				gameFrame.setVisible(true);
			}
		});

		JButton buttonHow = new JButton("How To Play");
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(buttonHow, c);
		buttonHow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame howToPlayFrame = new JFrame("How to Play");
				howToPlayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				howToPlayFrame.setLayout(new GridBagLayout());
				GridBagConstraints how = new GridBagConstraints();

				JLabel tutorial = new JLabel("To play, simply click on the buttons to mark your moves.");
				how.weighty = 0.7;
				how.insets = new Insets(10, 0, 0, 0);
				how.gridx = 0;
				how.gridy = 0;
				howToPlayFrame.add(tutorial, how);

				JLabel tutorial2 = new JLabel("If any player gets 3 moves in a row, column or diagonal, they win");
				how.gridx = 0;
				how.gridy = 1;
				howToPlayFrame.add(tutorial2, how);

				JButton buttonBack = new JButton("Back");
				how.weighty = 0.3;
				how.gridx = 0;
				how.gridy = 2;
				howToPlayFrame.add(buttonBack, how);
				buttonBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						howToPlayFrame.dispose();
					}
				});

				howToPlayFrame.setSize(640, 320);
				howToPlayFrame.setLocationRelativeTo(null);
				howToPlayFrame.setVisible(true);

			}

		});

		JButton buttonExit = new JButton("Exit");
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 3;
		pane.add(buttonExit, c);
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponentsToPane(frame.getContentPane());
		frame.setSize(640, 320);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		createAndShowGUI();
	}

	public static class TicTacToePanel extends JPanel {

		char currentPlayer = 'X';
		JButton[] buttons = new JButton[9];

		public TicTacToePanel() {
			setLayout(new GridLayout(3, 3));
			initializeButtons();
		}

		public void initializeButtons() {
			Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 70);
			Color fontColor = Color.WHITE;

			for (int i = 0; i < 9; i++) {
				buttons[i] = new JButton();
				buttons[i].setText("");
				buttons[i].setBackground(Color.DARK_GRAY);
				buttons[i].setFont(buttonFont);
				buttons[i].setForeground(fontColor);
				buttons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    JButton buttonClicked = (JButton) e.getSource();
					    if (isValidMove(buttonClicked)) {
					        buttonClicked.setText(String.valueOf(currentPlayer));

					        if (currentPlayer == 'X') {
					            currentPlayer = 'O';
					            buttonClicked.setBackground(Color.BLACK);
					        } else {
					            currentPlayer = 'X';
					            buttonClicked.setBackground(Color.PINK);
					        }

					        checkWin();
					        if (checkDraw()) {
					            JOptionPane.showMessageDialog(TicTacToePanel.this, "GAME DRAW!");
					            resetGame();
					        }
					    }
                                            else JOptionPane.showMessageDialog(TicTacToePanel.this, "Please choose an empty space to move.");
					}
				}

				
						);add(buttons[i]);
			}
	
}

		 private boolean isValidMove(JButton button) {
	            return button.getText().isEmpty();
		 }

		private void checkWin() {
			if (checkRow(0, 1, 2) || checkRow(3, 4, 5) || checkRow(6, 7, 8) || 
                            checkRow(0, 3, 6) || checkRow(1, 4, 7) || checkRow(2, 5, 8) || 
                            checkRow(0, 4, 8) || checkRow(2, 4, 6)) {

				if (currentPlayer == 'X') {
					currentPlayer = 'O';
				} else {
					currentPlayer = 'X';
				}

				JOptionPane.showMessageDialog(this, "Congratulation!! Player " + currentPlayer + " wins!");
				resetGame();
			}
		}

		private boolean checkRow(int a, int b, int c) {
			String checkA = buttons[a].getText();
			String checkB = buttons[b].getText();
			String checkC = buttons[c].getText();

			return !checkA.isEmpty() && checkA.equals(checkB) && checkB.equals(checkC);
		}

		private boolean checkDraw() {
			for (int i = 0; i < 9; i++) {
				if (buttons[i].getText().isEmpty()) {
					return false;
				}
			}
			return true;
		}

		private void resetGame() {
			currentPlayer = 'X';
			for (int i = 0; i < 9; i++) {
				buttons[i].setText("");
				buttons[i].setBackground(Color.DARK_GRAY);
			}
		}
	}
}