import javax.swing.SwingUtilities;

/*
 * Files: TicTacToeApp.java          
 * Author: Mohamedamin Mohamed          
 * Contact mohamedamin204080@gmail.com  
 * Created 07/10/2023                   
 * Modified: 07/16/2023              
 * Description:This class sets up the necessary components and establishes the connections between them to create a functioning App
 */
public class TicTacToeApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Model model = new Model();
			Controller controller = new Controller(model);
			View view = new View(controller, model);//need model so as to get current player(to be used when showing the winner dialog

			controller.setView(view);
			controller.startGame();
			view.startGame();
		});
	}
}
