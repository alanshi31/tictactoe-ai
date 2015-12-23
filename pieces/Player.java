/* An enumerated type to simplify how both the user and computer
 * players interact with the game. Also provides a slightly easier
 * method to change the symbols associated with each type and keep
 * any printing methods independent of this definition (very modular).
 */

package pieces;

public enum Player {
	
	COMPUTER("X"), USER("O"), NONE("-");
	
	private final String text;
	
	private Player(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return this.text;
	}
}
