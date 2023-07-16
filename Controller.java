import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Files: Controller.java          
 * Author: Mohamedamin Mohamed          
 * Contact mohamedamin204080@gmail.com  
 * Created 07/16/2023                   
 * Modified: 07/16/2023                 
 * Description:This class is responsible for handling user interactions 
 * and updating the model accordingly.             
 */  
public class Controller implements ActionListener {
    private Model model;
    private View view;

    /**
     * Constructs a Controller object with the specified model.
     * 
     * @param model the model object
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * Sets the view for the controller.
     * 
     * @param view the view object
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Starts the game by updating the label with the current player.
     */
    public void startGame() {
        view.updateLabel(model.getCurrentPlayer() + " player starts");
    }

    /**
     * Resets the game by resetting the model and updating the label and buttons on the view.
     */
    public void resetGame() {
        model.createUI();
        view.updateLabel(model.getCurrentPlayer() + " player starts");

        for (int i = 0; i < view.buttons.length; i++) {
            for (int j = 0; j < view.buttons[i].length; j++) {
                view.updateButton(i, j, "");
            }
        }
    }

    /**
     * Handles button click events. Plays the move in the model, updates the button on the view,
     * and checks for a winner or a draw.
     * 
     * @param event the ActionEvent object representing the button click
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String[] split = event.getActionCommand().split("-");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);

        model.playMove(row, col);
        view.updateButton(row, col, model.getCurrentPlayer());

        if (model.checkWinner(row, col)) {
            view.showWinnerDialog(model.getCurrentPlayer());
        } else if (model.isBoardFull()) {
            view.showDrawDialog();
        } else {
            switchPlayer();
        }
    }

    /**
     * Switches to the next player by updating the current player in the model and the label on the view.
     */
    private void switchPlayer() {
    	String currentPlayer = (model.getCurrentPlayer().equals("O")) ? "X" : "O";
        model.setCurrentPlayer(currentPlayer);
        view.updateLabel(model.getCurrentPlayer() + " player's turn");
    }
}
