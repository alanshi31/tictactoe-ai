/* The Game class contains all of the logic to create the game and run
 * the game loop. It is responsbile for interacting with the Board class
 * in order to update moves for both the player and the computer. Most of
 * the heavy algorithmic lifting is done by the Board class.
 */

package logic;

import java.io.IOException;
import java.util.Random;

import pieces.Cell;
import pieces.Constants;
import pieces.Player;

public class Game {

	private Board board;
	private Random random;

	public Game() {
		initializeGame();
		displayBoard();
		makeFirstMove();
		playGame();
		checkResult();
	}

	/** Initialize member fields */
	private void initializeGame() {
		this.board = new Board();
		this.board.setupBoard();
		this.random = new Random();
	}

	/** Calls board's display method */
	private void displayBoard() {
		this.board.displayBoard();
	}

	/** Decides whether the player wants to go first or the computer */
	private void makeFirstMove() {
		System.out.print("Who starts? 1 - User | 2 - Computer ");
		try {
			int choice = board.getScanner().nextInt();
			if (choice == 1) {
				return;
			} else if (choice == 2) {	// generate random first move for computer
				Cell cell = new Cell(random.nextInt(Constants.BOARD_SIZE), random.nextInt(Constants.BOARD_SIZE));
				this.board.move(cell, Player.COMPUTER);
				displayBoard();
			} else {
				throw new IOException();
			}
		} catch (IOException e) {	// just for fun
			System.out.println("YOU HAD ONE JOB");
		}
	}
	
	/** Contains actual game loop logic */
	private void playGame() {
		while (this.board.isRunning()) {	// game loop
			// player move
			this.board.getInput();
			displayBoard();
			System.out.println();
			if (!this.board.isRunning()) break;
			
			// computer move
			this.board.runMinimax(0, Player.COMPUTER);
			System.out.println("Evaluating options...");
			for (Cell cell : this.board.getRootValues())
				System.out.println("Cell: " + cell + " Minimax: " + cell.getMinimax());
			Cell bestCell = board.getBestMove();
			this.board.move(bestCell, Player.COMPUTER);
			
			System.out.println();
			System.out.println("Computer's move: (" + bestCell.getY() + ", " + bestCell.getX() + ")");
			displayBoard();
			System.out.println();
		}
	}

	/** Checks what the final status of the game was when the loop exited */
	private void checkResult() {
		if (board.hasWon(Player.COMPUTER))
			System.out.println("Computer wins!");
		else if (board.hasWon(Player.USER))
			System.out.println("You win!");
		else
			System.out.println("Draw!");
	}
}
