
public class Position {
	private char row;
	private int column;
	
	public Position(char row, int column) {
		this.row = row;
		this.column = column;
	}
	public Position(int rowIndex, int columnIndex) {
		row = (char) ((char) rowIndex + 65);
		column = columnIndex + 1;
	}
	public char row() {
		return Character.toUpperCase(row);
	}
	public int column() {
		return column;
	}
	public String toString() {
		return row() + "-" + column();
	}
	public int rowIndex() {
		return ((int) row() - 65);
	}
	public int columnIndex() {
		return column() - 1;
	}
}
