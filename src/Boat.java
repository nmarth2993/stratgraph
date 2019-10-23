
public class Boat {
	private Position pos;
	private String name;
	private String direction;
	private boolean[] hits;
	private Position[] displacement;
	
	public Boat(String name, Position pos, String direction) {
		this.pos = pos;
		this.name = name;
		this.direction = direction;
		hits = new boolean[size()];
		for (int i = 0; i < size(); i++) {
			hits[i] = false;
		}
		displacement = new Position[size()];
		initDisplacement();
	}
	private void initDisplacement() {
		for (int i = 0; i < size(); i++) {
			displacement[i] = direction().equals("horizontal") ? new Position(pos.rowIndex(), pos.columnIndex() + i)
					: new Position(pos.rowIndex() + i, pos.columnIndex());
		}
	}
	public String name() {
		return name;
	}
	public char abbreviation() {
		return name.charAt(0);
	}
	public int size() {
		if (abbreviation() == 'A') {
			return 5;
		}
		else if (abbreviation() == 'B') {
			return 4;
		}
		else if (abbreviation() == 'C' || abbreviation() == 'S') {
			return 3;
		}
		else {
			return 2;
		}
	}
	
	private boolean hasPos(Position pos) {
		for (Position p : displacement) {
			if ((p.columnIndex() == pos.columnIndex() && p.rowIndex() == pos.rowIndex())) {
				return true;
			}
		}
		return false;
	}
	private int hasPosIndex(Position pos) {
		for (int i = 0; i < size(); i++) {
			if (displacement[i].columnIndex() == pos.columnIndex() && displacement[i].rowIndex() == pos.rowIndex()) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean onBoat(Position pos) {
		return hasPos(pos);
	}
	public boolean isHit(Position pos) {
		if (!hasPos(pos)) {
			return false;
		}
		else {
			return hits[hasPosIndex(pos)];
		}
	}
	public void hit(Position pos) {
		if (hasPos(pos)) {
			hits[hasPosIndex(pos)] = true;
		}
	}
	public boolean sunk() {
		for (int i = 0; i < size(); i++) {
			if (!hits[i]) {
				return false;
			}
		}
		return true;
	}
	public Position position() {
		return pos;
	}
	public String direction() {
		return direction;
	}
}