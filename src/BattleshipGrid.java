
public class BattleshipGrid {
	private char[][] grid = new char[10][10];
	
	public BattleshipGrid() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				grid[i][j] = '.';
			}
		}
	}
	public void shotAt(Position pos, boolean hit, char initial) {
		if (!hit) {
			grid[pos.rowIndex()][pos.columnIndex()] = 'X';
			return;
		}
		grid[pos.rowIndex()][pos.columnIndex()] = initial;
		
	}
	public boolean hit(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] != '.' && grid[pos.rowIndex()][pos.columnIndex()] != 'X') {
			return true;
		}
		return false;
	}
	public boolean miss(Position pos) {
		//XXX: possible?:
//		return !hit(pos);
		if (grid[pos.rowIndex()][pos.columnIndex()] == 'X') {
			return true;
		}
		return false;
	}
	public boolean empty(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] == '.') {
			return true;
		}
		return false;
	}
	public char boatInitial(Position pos) {
		if (hit(pos)) {
			return grid[pos.rowIndex()][pos.columnIndex()];
		}
		return ' ';
	}
}
