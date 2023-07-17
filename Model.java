/*
 * Files: Model.java          
 * Author: Mohamedamin Mohamed          
 * Contact mohamedamin204080@gmail.com  
 * Created 07/16/2023    
 * modified 07/16/2023               
 * Description:This class encapsulates the core functionality and logic of the game
 */
public class Model {

	private String[][] board = new String[3][3];
	private String currentPlayer = "O";
	private int turnCount = 0;

	public Model() {
		createUI();
	}

	/**
	 * Initializes the game board and sets the initial player.
	 */
	public void createUI() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = "";
			}
		}
		currentPlayer = "O";
		turnCount = 0;
	}

	/**
	 * Returns the current player symbol.
	 *
	 * @return The current player symbol.
	 */
	public String getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Plays a move at the specified position on the board.
	 *
	 * @param row The row index of the move.
	 * @param col The column index of the move.
	 */
	public void playMove(int row, int col) {
		if (board[row][col].isEmpty()) {
			board[row][col] = currentPlayer;
			turnCount++;
		}
	}

	/**
	 * Checks if the current player has won the game after making a move.
	 *
	 * @param row The row index of the move.
	 * @param col The column index of the move.
	 * @return True if the current player has won, false otherwise.
	 */
	public boolean checkWinner(int row, int col) {
		String symbol = board[row][col];
		// Check horizontal
		if (board[row][0].equals(symbol) && board[row][1].equals(symbol) && board[row][2].equals(symbol))
			return true;
		// Check vertical
		if (board[0][col].equals(symbol) && board[1][col].equals(symbol) && board[2][col].equals(symbol))
			return true;
		// Check diagonal down right
		if (board[0][0].equals(symbol) && board[1][1].equals(symbol) && board[2][2].equals(symbol))
			return true;
		// Check diagonal up right
		if (board[2][0].equals(symbol) && board[1][1].equals(symbol) && board[0][2].equals(symbol))
			return true;
		return false;
	}

	/**
	 * Checks if the game board is full, indicating a draw.
	 *
	 * @return True if the game board is full, false otherwise.
	 */
	public boolean isBoardFull() {
		return turnCount == 9;
	}

	/**
	 * Returns the game board.
	 *
	 * @return The game board.
	 */
	public String[][] getBoard() {
		return board;
	}
	
    /**
	 * Returns the game board.
	 *
	 * @param currentPlayer symbol of currently player
	 */
	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
		
	}
}
