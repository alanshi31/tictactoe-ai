package pieces;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {

	private Scanner scanner;
	private Player[][] board;
	private List<Cell> rootValues;
	
	public Board() {
		initializeBoard();
	}
	
	private void initializeBoard() {
		this.rootValues = new ArrayList<Cell>();
		this.scanner = new Scanner(System.in);
		this.board = new Player[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
	}
	
	public boolean isRunning() {
		if (hasWon(Player.COMPUTER) || hasWon(Player.USER)) return false;
		if (getEmptyCells().isEmpty()) return false;
		return true;
	}
	
	public boolean hasWon(Player player) {
		// checks diaganols
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
			return true;
		else if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
			return true;
		else
			for (int i = 0; i < Constants.BOARD_SIZE; i++) {
				// checks rows
				if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
					return true;
				// checks columns
				if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
					return true;
			}
		return false;
	}
	
	public List<Cell> getEmptyCells() {
		List<Cell> emptyCells = new ArrayList<Cell>();
		
		for (int i = 0; i < Constants.BOARD_SIZE; i++)
			for (int j = 0; j < Constants.BOARD_SIZE; j++)
				if (board[i][j] == Player.NONE)
					emptyCells.add(new Cell(i, j));
		
		return emptyCells;
	}
	
	public void move(Cell cell, Player player) {
		this.board[cell.getX()][cell.getY()] = player;
	}
	
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
	
	public void getInput() {
		System.out.println("User's move: ");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		Cell cell = new Cell(x, y);
		move(cell, Player.USER);
	}
	
	public void displayBoard() {
		System.out.println();
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			for (int j = 0; j < Constants.BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int returnMin(List<Integer> list) {
		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) < minValue)
				minValue = list.get(i);
				
		return minValue;
	}
	
	public int returnMax(List<Integer> list) {
		int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) > maxValue)
				maxValue = list.get(i);
				
		return maxValue;
	}
	
	public void runMinimax(int depth, Player player) {
		rootValues.clear();
		minimax(depth, player);
	}
	
	private void minimax(int depth, Player player) {
		
	}
	
	public void setupBoard() {
		for (int i = 0; i < Constants.BOARD_SIZE; i++)
			for (int j = 0; j < Constants.BOARD_SIZE; j++)
				board[i][j] = Player.NONE;
	}

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
