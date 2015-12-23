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
