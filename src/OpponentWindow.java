import java.awt.Dimension;

import javax.swing.JFrame;

public class OpponentWindow implements BattleshipWindow {
	private JFrame frame;
	private BattleshipPanel panel;
	private BattleshipGame game;
	private ComputerBattleshipPlayer player;
//	private BattleshipPlayer opponent;
	private BattleshipGrid grid;
	private Ocean ocean;

	private PanelMouseListener mouseHandler;

	private boolean gameOver;

	private String playerName;
	private int turns;

	public OpponentWindow(ComputerBattleshipPlayer computer) {
		gameOver = false;

//		panel = new BattleshipPanel(); TODO: this
//		frame.setContentPane(panel);
		player = computer;
		playerName = player.playerName();
		frame = new JFrame("Battleship: " + playerName + "\'s Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocation(700, 20);
		frame.setResizable(false);
		game = new BattleshipGame(player);
		ocean = new Ocean();
		ocean.placeAllBoats();
		turns = 0;
		player.startGame();
		grid = player.getGrid();
//		turns = 0;
//		opponent.startGame();
//		opponent.initializeGrid();

//		frame.setVisible(true);
//		frame.pack();
	}

	public void createPanel() {
		mouseHandler = new PanelMouseListener(this);
		frame.addMouseListener(mouseHandler);
		frame.addMouseMotionListener(mouseHandler);
		panel = new BattleshipPanel(this);
		panel.setPreferredSize(new Dimension(600, 650 + BattleshipWindow.TOP_OFFSET));
		frame.setContentPane(panel);

		frame.setVisible(true);
		frame.pack();
	}

	public void turnInc() {
		turns++;
	}

	public BattleshipPanel getPanel() {
		return panel;
	}

	public BattleshipGame getGame() {
		return game;
	}

	public BattleshipGrid getGrid() {
		return grid;
	}

	public Ocean getOcean() {
		return ocean;
	}

	public int getTurns() {
		return turns;
	}

	public BattleshipPlayer getPlayer() {
		return player;
	}

	public PanelMouseListener getMouseHandler() {
		return mouseHandler;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setGameOver(boolean go) {
		gameOver = go;
	}

	@Override
	public boolean isGameOver() {
		return gameOver;
	}

//	public BattleshipPlayer getOpponent() {
//		return opponent;
//	}

}

/*
 * BattleshipGame(BattleshipPlayer player) { ocean = new Ocean(); turns = 0;
 * ocean.placeAllBoats(); this.player = player; this.player.startGame();
 * this.player.initializeGrid(); } public int play() { while(!ocean.allSunk() &&
 * turns < 100) { turns++; Position pos = player.shoot(); ocean.shootAt(pos);
 * player.updatePlayer(pos, ocean.hit(pos), ocean.boatInitial(pos),
 * ocean.boatName(pos), ocean.sunk(pos), ocean.allSunk(), turns >= 100, turns);
 * } return turns; }
 */
