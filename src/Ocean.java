import java.util.Random;

public class Ocean {
	private Boat[] fleet = new Boat[5];
	private int[][] grid = new int[10][10];
	private int boatIndex = 0;

	public Ocean() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				grid[i][j] = -1;
			}
		}
	}

	public void placeBoat(String boatName, String direction, Position pos) throws Exception {
		Boat boat = new Boat(boatName, pos, direction);
		if (pos.rowIndex() < 0 || pos.columnIndex() < 0 || pos.rowIndex() > 9 || pos.columnIndex() > 9) {
			throw new Exception("out of bounds");
		}
		if (direction.equalsIgnoreCase("vertical")) {
			if (boat.size() - 1 + pos.rowIndex() > 9) {
				throw new Exception("out of bounds");
			}
			for (int row = pos.rowIndex(); row < boat.size() + pos.rowIndex(); row++) {
				if (grid[row][pos.columnIndex()] != -1) {
					throw new Exception("overlap");
				}
			}
			for (int row = pos.rowIndex(); row < boat.size() + pos.rowIndex(); row++) {
				grid[row][pos.columnIndex()] = boatIndex;
			}
		} else {
			if (boat.size() - 1 + pos.columnIndex() > 9) {
				throw new Exception("out of bounds");
			}
			for (int col = pos.columnIndex(); col < boat.size() + pos.columnIndex(); col++) {
				if (grid[pos.rowIndex()][col] != -1) {
					throw new Exception("overlap");
				}
			}
			for (int col = pos.columnIndex(); col < boat.size() + pos.columnIndex(); col++) {
				grid[pos.rowIndex()][col] = boatIndex;
			}
		}
		fleet[boatIndex++] = boat;
	}

	public void placeAllBoats() {
		Random r = new Random();

		boolean placed = false;
		String[] boats = { "Aircraft Carrier", "Battleship", "Cruiser", "Destroyer", "Submarine" };
		for (int i = 0; i < boats.length; i++) {
			while (!placed) {
				try {
					placed = true;
					placeBoat(boats[i], r.nextBoolean() ? "vertical" : "horizontal",
							new Position(r.nextInt(10), r.nextInt(10)));
				} catch (Exception e) {
					placed = false;
				}
			}
			placed = false;
		}
	}

	public void shootAt(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] != -1) {
			fleet[grid[pos.rowIndex()][pos.columnIndex()]].hit(pos);
		}
	}

	public boolean hit(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] != -1) {
			return fleet[grid[pos.rowIndex()][pos.columnIndex()]].isHit(pos);
		}
		return false;
	}

	public char boatInitial(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] != -1) {
			return fleet[grid[pos.rowIndex()][pos.columnIndex()]].abbreviation();
		}
		return ' ';
	}

	public String boatName(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] != -1) {
			return fleet[grid[pos.rowIndex()][pos.columnIndex()]].name();
		}
		return "";
	}

	public boolean sunk(Position pos) {
		if (grid[pos.rowIndex()][pos.columnIndex()] != -1) {
			return fleet[grid[pos.rowIndex()][pos.columnIndex()]].sunk();
		}
		return false;
	}

	public boolean allSunk() {
		for (int i = 0; i < boatIndex; i++) {
			if (!fleet[i].sunk()) {
				return false;
			}
		}
		return true;
	}
}
