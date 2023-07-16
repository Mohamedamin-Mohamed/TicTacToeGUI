import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * Files: View.java          
 * Author: Mohamedamin Mohamed          
 * Contact mohamedamin204080@gmail.com  
 * Created 07/16/2023    
 * modified 07/16/2023               
 * Description:This class acts as an interface between the user and the application,
 * presenting information to the user and capturing their interactions, 
 * whilst delegating the processing of user actions to the controller.
 */
public class View {
	JButton[][] buttons = new JButton[3][3];
	private JLabel label;
	private Controller controller;
	private JFrame frame;
	private Model model;

	/**
	 * Constructs the View object.
	 * 
	 * @param controller The Controller object responsible for handling user actions.
	 * @param model      The Model object containing the game state.
	 */
	public View(Controller controller, Model model) {
		this.controller = controller;
		this.model = model;
		frame = new JFrame();
		frame.setTitle("TicTacToe GUI App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		label = new JLabel("", SwingConstants.CENTER);
		frame.add(label, BorderLayout.NORTH);

		JPanel panel = new JPanel(new GridLayout(3, 3));
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(controller);
				buttons[i][j].setActionCommand(i + "-" + j);
				panel.add(buttons[i][j]);
			}
		}
		frame.add(panel);

		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Updates the text on a button in the View.
	 * 
	 * @param row    The row index of the button.
	 * @param col    The column index of the button.
	 * @param symbol The symbol to be displayed on the button.
	 */
	public void updateButton(int row, int col, String symbol) {
		buttons[row][col].setText(symbol);
	}

	/**
	 * Updates the label in the View.
	 * 
	 * @param text The text to be displayed on the label.
	 */
	public void updateLabel(String text) {
		label.setText(text);
	}

	/**
	 * Displays a dialog indicating the winner of the game.
	 * 
	 * @param winner The winning player.
	 */
	public void showWinnerDialog(String winner) {
		label.setText(model.getCurrentPlayer() + " player won!");
		int response = JOptionPane.showConfirmDialog(null, winner + " player won. Do you want to play again?",
				"Winner", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			controller.resetGame();
		} else {
			frame.dispose();
		}
	}

	/**
	 * Displays a dialog indicating a draw in the game.
	 */
	public void showDrawDialog() {
		label.setText("Game draw!");
		int response = JOptionPane.showConfirmDialog(null, "No winner, game draw. Do you want to play again?",
				"Game Draw", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			controller.resetGame();
		} else {
			frame.dispose();
		}
	}

	/**
	 * Starts the game by displaying the View.
	 */
	public void startGame() {
		frame.setVisible(true);
	}

	/**
	 * Sets the Controller object responsible for handling user actions.
	 * 
	 * @param controller The Controller object.
	 */
	public void setController(Controller controller) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j].addActionListener(controller);
			}
		}
	}
}
