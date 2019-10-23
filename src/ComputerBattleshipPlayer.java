import java.util.Random;

public class ComputerBattleshipPlayer extends BattleshipPlayer {
	public void updatePlayer(Position pos, boolean hit, char initial, String boatName, boolean sunk, boolean gameOver, boolean tooManyTurns, int turns) {
		updateGrid(pos, hit, initial);
	}
	public String playerName() {
		return "Computer Player";
	}
	public void startGame() {
		initializeGrid();
	}
	public Position shoot() {
		Random r = new Random();
		for (;;) {
			Position pos = new Position(r.nextInt(10), r.nextInt(10));
			if (getGrid().empty(pos)) {
				return pos;
			}
		}
	}
}
