/* Starts a console-based game of tic tac toe with the user. The
 * computer uses the minimax algorithm used in artificial intelligence
 * to create a decision tree of all possible game outcomes. Using this
 * data, the computer chooses the best move every turn. Try and beat it!
 */
package client;

import logic.Game;

public class App {
	
	public static void main(String[] args) {
		new Game();
	}

}
