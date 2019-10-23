public class BattleshipGame {
	// creates an ocean, places all boats
	private int turns;
	private Ocean ocean;
	private BattleshipPlayer player;

	BattleshipGame(BattleshipPlayer player) {
		ocean = new Ocean();
		turns = 0;
		ocean.placeAllBoats();
		this.player = player;
		this.player.startGame();
		this.player.initializeGrid();
	}

	public int play() {
		while (!ocean.allSunk() && turns < 100) {
			turns++;
			Position pos = player.shoot();
			ocean.shootAt(pos);
			player.updatePlayer(pos, ocean.hit(pos), ocean.boatInitial(pos), ocean.boatName(pos), ocean.sunk(pos),
					ocean.allSunk(), turns >= 100, turns);
		}
		return turns;
	}

}
