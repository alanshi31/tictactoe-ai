/* The board class contains all of the game logic including initializing all
 * of the cells, checking if a player has won, and running the minimax algorithm
 * for the computer player. This class is meant to interact with the individual
 * game parts in the pieces package to build a functioning TicTacToe game.
 * 
 * Note, this class has a scanner member that handles user input and a method to
 * print the current board state to the console. However, the getInput() and 
 * printBoard() interact with a Game class in order to call these methods.
 */
package logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pieces.Cell;
import pieces.Constants;
import pieces.Player;

public class Board {

	private Scanner scanner;		// for user input
	private Player[][] board;		// to store the board state
	private List<Cell> rootValues;	// to store minimax root values

	public Board() {
		initializeBoard();
	}

	/** Initializes member fields */
	private void initializeBoard() {
		this.rootValues = new ArrayList<Cell>();
		this.scanner = new Scanner(System.in);
		this.board = new Player[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
	}

	/** Checks if the game is still running by determining a win or draw */
	public boolean isRunning() {
		if (hasWon(Player.COMPUTER) || hasWon(Player.USER)) return false;
		if (getEmptyCells().isEmpty()) return false;	// case: draw
		return true;
	}

	/** Checks if a player has won by checking all possible win patterns */
	public boolean hasWon(Player player) {
		// checks diaganols
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
			return true;
		else if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
			return true;
		else
			// checks rows and columns respectively
			for (int i = 0; i < Constants.BOARD_SIZE; i++) {
				if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
					return true;
				if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
					return true;
			}
		return false;
	}

	/** Iterates through the board and returns a list of the empty (available) cells */
	public List<Cell> getEmptyCells() {
		List<Cell> emptyCells = new ArrayList<Cell>();

		for (int i = 0; i < Constants.BOARD_SIZE; i++)
			for (int j = 0; j < Constants.BOARD_SIZE; j++)
				if (board[i][j] == Player.NONE)
					emptyCells.add(new Cell(i, j));

		return emptyCells;
	}

	/** Performs a move for a player */
	public void move(Cell cell, Player player) {
		this.board[cell.getX()][cell.getY()] = player;
	}

	/** Gets the best move the computer can take by searching the rootValues list */
	public Cell getBestMove() {
		int maxValue = Integer.MIN_VALUE;
		int bestIndex = Integer.MIN_VALUE;

		for (int i = 0; i < rootValues.size(); i++) {
			if (maxValue < rootValues.get(i).getMinimax()) {
				maxValue = rootValues.get(i).getMinimax();
				bestIndex = i; 
			}
		}
		return rootValues.get(bestIndex);
	}

	/** Gets input from the user for their next move */
	public void getInput() {
		System.out.print("User's move: (row col) ");
		while (true) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			Cell cell = new Cell(x, y);
			if (isValidMove(cell)) {
				move(cell, Player.USER);
				break;
			}
			else {
				System.out.print("Invalid move. Please choose another move: ");
			}
		}
	}
	
	/** Makes sure the move entered by the user is valid */
	private boolean isValidMove(Cell cell) {
		return board[cell.getX()][cell.getY()] == Player.NONE;
	}

	/** Prints the current board state to the console */
	public void displayBoard() {
		System.out.println();
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			for (int j = 0; j < Constants.BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	/** Simple linear search to find the minimum value */
	public int getMin(List<Integer> list) {
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) < minValue)
				minValue = list.get(i);

		return minValue;
	}

	/** Simple linear search to find the maximum value */
	public int getMax(List<Integer> list) {
		int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) > maxValue)
				maxValue = list.get(i);

		return maxValue;
	}

	/** Clears data from the last run and runs the minimax algorithm */
	public void runMinimax(int depth, Player player) {
		rootValues.clear();
		minimax(depth, player);
	}

	/** The minimax algorithm simulates all possible moves and their outcomes. 
	 *  This information is then used to determine how favorable a move is for
	 *  the computer. The favorability of all moves are stored in the rootValues
	 *  list, which is then used later by the computer to make the best possible
	 *  move.
	 */
	private int minimax(int depth, Player player) {
		if (hasWon(Player.COMPUTER)) return 1;
		if (hasWon(Player.USER)) return -1;
		List<Cell> availableCells = getEmptyCells();
		if (availableCells.isEmpty()) return 0;

		List<Integer> scores = new ArrayList<Integer>();

		// construct game tree by simulating moves through available (empty) cells
		for (int i = 0; i < availableCells.size(); i++) {
			Cell cell = availableCells.get(i);

			if (player == Player.COMPUTER) {	// need to check player options
				move(cell, Player.COMPUTER);
				int currentScore = minimax(depth + 1, Player.USER);	// minimizing step
				scores.add(currentScore);

				if (depth == 0) {
					cell.setMinimax(currentScore);
					rootValues.add(cell);
				}
			} else if (player == Player.USER) {	// need to check own options
				move(cell, Player.USER);
				scores.add(minimax(depth + 1, Player.COMPUTER));
			}

			board[cell.getX()][cell.getY()] = Player.NONE;	// reset cell after simulation
		}

		if (player == Player.COMPUTER) 
			return getMax(scores);
		else 
			return getMin(scores);
	}

	/** Iteratively initializes the board with all empty spaces */
	public void setupBoard() {
		for (int i = 0; i < Constants.BOARD_SIZE; i++)
			for (int j = 0; j < Constants.BOARD_SIZE; j++)
				board[i][j] = Player.NONE;
	}

	/* Begin getters and setters */

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public Player[][] getBoard() {
		return board;
	}

	public void setBoard(Player[][] board) {
		this.board = board;
	}

	public List<Cell> getRootValues() {
		return rootValues;
	}

	public void setRootValues(List<Cell> rootValues) {
		this.rootValues = rootValues;
	}
}
