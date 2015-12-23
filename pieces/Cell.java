/* The cell class encapsulates all relevant data for each individual
 * board cell. The x and y coordinates are relative to the 2D board
 * array and the minimax value is used by the computer to determine
 * whether a cell is the next best move to take.
 */

package pieces;

public class Cell {

	private int x;
	private int y;
	private int minimax;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMinimax() {
		return minimax;
	}

	public void setMinimax(int minimax) {
		this.minimax = minimax;
	}
	
	@Override
	public String toString() {
		return "(" + this.y + ", " + this.x + ")";
	}

}
