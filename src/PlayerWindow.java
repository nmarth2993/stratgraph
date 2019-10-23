import java.awt.Dimension;

import javax.swing.JFrame;

public class PlayerWindow implements BattleshipWindow {

	private JFrame frame;
	private BattleshipPanel panel;
	private BattleshipGame game;
	private BattleshipPlayer player;
//	private ComputerBattleshipPlayer opponent;
	private BattleshipGrid grid;
	private Ocean ocean;

	private boolean gameOver;

	private PanelMouseListener mouseHandler;

	private String playerName;
	private int turns;

	public PlayerWindow(BattleshipPlayer human) {
		gameOver = false;

//		panel = new BattleshipPanel(); TODO: this
//		frame.setContentPane(panel);
		player = human;
		game = new BattleshipGame(player);
		ocean = new Ocean();
		// TODO: allow users to place boats
		turns = 0;
		ocean.placeAllBoats();
		player.startGame(); // this calls initGrid
//		player.initializeGrid(); //(redundant) (redundant)
		playerName = player.playerName();
		frame = new JFrame("Battleship: " + playerName + "\'s Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocation(0, 20);
		frame.setResizable(false);
		grid = player.getGrid();
//		frame.setVisible(true);
//		frame.pack();
	}

	public void createPanel() {
		mouseHandler = new PanelMouseListener(this);
		frame.addMouseListener(mouseHandler);
		frame.addMouseMotionListener(mouseHandler);
		panel = new BattleshipPanel(this);
//		panel.addMouseListener(l);
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

	public Ocean getOcean() {
		return ocean;
	}

	public String getPlayerName() {
		return playerName;
	}

	public BattleshipPlayer getPlayer() {
		return player;
	}

	public PanelMouseListener getMouseHandler() {
		return mouseHandler;
	}

	@Override
	public BattleshipGrid getGrid() {
		return grid;
	}

	@Override
	public int getTurns() {
		return turns;
	}

	public void setGameOver(boolean go) {
		gameOver = go;
	}

	public boolean isGameOver() {
		return gameOver;
	}

//	public ComputerBattleshipPlayer getOpponent() {
//		return opponent;
//	}
}
