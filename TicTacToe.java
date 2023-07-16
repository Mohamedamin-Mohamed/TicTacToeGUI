import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Tictactoe extends JFrame implements ActionListener{
	private JButton[][] buttons = new JButton[3][3];
	private JPanel panel;
	private JLabel label;
	private String player = "O";
	private int turn = 0;
	private boolean winner = false;

	public Tictactoe() {

		setTitle("TicTacToe GUI App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		label = new JLabel("O players starts");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.NORTH);
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length;j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setActionCommand(i + "-" + j);
				buttons[i][j].addActionListener(this);
				buttons[i][j].setOpaque(true);
			}
		}
		panel = new JPanel(new GridLayout(3,3));

		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length;j++) {
				panel.add(buttons[i][j]);
			}
		}

		add(panel);
		setSize(800,600);
		setLocationRelativeTo(null);
		setVisible(true);


	}
	@Override
	public void actionPerformed(ActionEvent event) {
		turn++;
		JButton button = (JButton)(event.getSource());
		String[] split = event.getActionCommand().split("-");
		int row = Integer.parseInt(split[0]);
		int col = Integer.parseInt(split[1]);
		button = buttons[row][col];
		button.setText(player);

		winner = checkWinner(buttons, row, col);
		if(winner) {
			label.setText(player + " player won!");
			int response = JOptionPane.showConfirmDialog(null, player + " player won. Do you want to play again", "Winner", JOptionPane.YES_NO_OPTION);
			if(response == JOptionPane.YES_OPTION) 
				resetBoard(buttons);
			else this.dispose();
		}
		else if(turn == 9) {
			label.setText("Game draw!");
			int response = JOptionPane.showConfirmDialog(null, "No winner, game draw, want to play again", "Game Draw!", JOptionPane.YES_NO_OPTION);
			if(response == JOptionPane.YES_OPTION) 
				resetBoard(buttons);
			else this.dispose();
		}
		player = player.equals("O")? "X" : "O";
		label.setText(player + "  player turn");
		label.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private boolean checkWinner(JButton[][] buttons, int row, int col) {
		String text = buttons[row][col].getText();
		//check horizontal
		if(buttons[row][0].getText().equals(text) && buttons[row][1].getText().equals(text) && buttons[row][2].getText().equals(text))
			return true;
		//check vertical
		if(buttons[0][col].getText().equals(text) && buttons[1][col].getText().equals(text) && buttons[2][col].getText().equals(text))
			return true;
		//check diagonal down right
		if(buttons[0][0].getText().equals(text) && buttons[1][1].getText().equals(text) && buttons[2][2].getText().equals(text))
			return true;
		//check diagonal up right
		if(buttons[2][0].getText().equals(text) && buttons[1][1].getText().equals(text) && buttons[0][2].getText().equals(text))
			return true;
		return false;
	}
	private void resetBoard(JButton[][] buttons) {
		turn = 0;
		for(int i = 0; i < buttons.length; i++) {
			for(int j = 0; j < buttons[i].length; j++) {
				buttons[i][j].setText("");
			}
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{;
		new Tictactoe();
		});
	}
}
