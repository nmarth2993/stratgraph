
public interface BattleshipWindow {

	public final static int TOP_OFFSET = 20;

	public BattleshipGame getGame();

	public BattleshipGrid getGrid();

	public BattleshipPlayer getPlayer();

	public Ocean getOcean();

	public BattleshipPanel getPanel();

	public PanelMouseListener getMouseHandler();

	public String getPlayerName();

	public void turnInc();

	public int getTurns();

	public void createPanel();

	public void setGameOver(boolean go);

	public boolean isGameOver();
}
