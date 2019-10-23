import java.util.ArrayList;

public class NicholasMarthinussStrategy extends ComputerBattleshipPlayer {
	final static int WEIGHT = 5;
	
	ArrayList<String> aliveBoats;
	Brickboard board;
	Heatmap heatmap;

	public String playerName() {
		return "Nicholas Marthinuss' Strategy";
	}

	public String author() {
		return "author: Nicholas Marthinuss";
	}
	
	//XXX: Note to self:
	//isloate boats when hit
	//only the boat in only that position that intersects all hits of that boat
	//will add a fair amount of complexity to this strategy (and it will be slow)
	//Your challenge, should you choose to accept it is to create this new method/alter an existing method so that you can find which ships are hit at which locations and only iterate spaces with those ships hit. This should integrate well with the already

	public void startGame() {
		initAliveBoats();
		board = new Brickboard();
		super.startGame();
	}

	private void initAliveBoats() {
		// begin with all boats as being alive
		aliveBoats = new ArrayList<String>();
		aliveBoats.add("Aircraft Carrier");
		aliveBoats.add("Battleship");
		aliveBoats.add("Cruiser");
		aliveBoats.add("Destroyer");
		aliveBoats.add("Submarine");
	}

	public Position shoot() {

//		heatmap.printMap();

//		Position target = heatmap.hottestPos();

//		System.out.println("shooting at " + target);

		genHeatmap();
		return heatmap.hottestPos();

		// XXX
		// 3 hours into coding and chill and she gives you this look:
		/*
		 * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: -1 at
		 * BattleshipGrid.hit(BattleshipGrid.java:21) at
		 * NicholasMarthinussStrategy.shoot(NicholasMarthinussStrategy.java:28) at
		 * BattleshipGame.play(BattleshipGame.java:18) at
		 * PlayerEvaluator.<init>(PlayerEvaluator.java:11) at
		 * PlayerEvaluatorTester.main(PlayerEvaluatorTester.java:8)
		 */
//		return new Position(0, 0);
	}

	public void updatePlayer(Position pos, boolean hit, char initial, String boatName, boolean sunk, boolean gameOver,
			boolean tooManyTurns, int turns) {

		///////////////////////////////////////////////////////////////////////

//		if (hit) {
//			System.out.print(" hit the " + boatName);
//			System.out.print(sunk ? " and sunk it. " : " ");
//		} else {
//			System.out.println(" was a miss. ");
//		}
//		if (gameOver) {
//			System.out.print("The game is over. ");
//		}
//		if (tooManyTurns) {
//			System.out.print("The game has gone on too long.");
//		}
//		updateGrid(pos, hit, initial);
//		System.out.println("\n   1 2 3 4 5 6 7 8 9 10");
//		for (int i = 0; i < 10; i++) {
//			System.out.print((char) (65 + i) + "  ");
//			for (int j = 0; j < 10; j++) {
//				if (getGrid().empty(new Position(i, j))) {
//					System.out.print(". ");
//				} else if (getGrid().miss(new Position(i, j))) {
//					System.out.print("* ");
//				} else {
//					System.out.print(getGrid().boatInitial(new Position(i, j)) + " ");
//				}
//				if (j == 9) {
//					System.out.print("\n");
//				}
//			}
//		}

		////////////////////////////////////////////////////////////////////////////////////
		super.updatePlayer(pos, hit, initial, boatName, sunk, gameOver, tooManyTurns, turns);

		if (!hit) {
			board.addMark(pos);
		}

		if (sunk) {
			aliveBoats.remove(boatName);
			Position[] sunkDisplacements = sunkDisplacement(pos, boatName);
			for (int i = 0; i < sunkDisplacements.length; i++) {
				board.addMark(sunkDisplacements[i]);
			}
		}

	}

	private Position[] sunkDisplacement(Position pos, String boatName) {
		Position[] sunkDisplacement = new Position[boatSize(boatName)];
		int index = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (boatName.charAt(0) == getGrid().boatInitial(new Position(i, j))) {
					sunkDisplacement[index++] = new Position(i, j);
				}
			}
		}
		return sunkDisplacement;
	}

	/*
	 * private void sunkDisplacement(Position pos, String boatName) { // TODO: Add
	 * obstructions for every square of a sunk ship
	 * 
	 * ArrayList<Position> positions = new ArrayList<Position>(); boolean vertical =
	 * false; boolean horizontal = false;
	 * 
	 * while (pos.rowIndex() > 0 && getGrid().hit(new Position(pos.rowIndex() - 1,
	 * pos.columnIndex())) && getGrid().boatInitial(pos) == boatName.charAt(0)) {
	 * pos = new Position(pos.rowIndex() - 1, pos.columnIndex());
	 * System.out.println("true"); vertical = true; } while (pos.columnIndex() > 0
	 * && getGrid().hit(new Position(pos.rowIndex(), pos.columnIndex() - 1)) &&
	 * getGrid().boatInitial(pos) == boatName.charAt(0)) { pos = new
	 * Position(pos.rowIndex(), pos.columnIndex() - 1); horizontal = true; } // } //
	 * now in the top left // from here, we can now test which direction (it's
	 * inefficient but it's fine)
	 * 
	 * // if there's a hit on the same ship one square to the right (+1 col) // if
	 * (getGrid().hit(new Position(pos.rowIndex(), pos.columnIndex() + 1)) // &&
	 * getGrid().boatInitial(new Position(pos.rowIndex(), pos.columnIndex() + 1)) ==
	 * boatName.charAt(0)) { // vertical = false; // } else if (getGrid().hit(new
	 * Position(pos.rowIndex() + 1, pos.columnIndex())) // &&
	 * getGrid().boatInitial(new Position(pos.rowIndex() + 1, pos.columnIndex())) ==
	 * boatName.charAt(0)) { // vertical = true; // }
	 * 
	 * int iterations = boatSize(boatName);
	 * 
	 * for (int i = 0; i < iterations; i++) { if (vertical) { positions.add(new
	 * Position(pos.rowIndex() + i, pos.columnIndex())); } else if (horizontal) {
	 * positions.add(new Position(pos.rowIndex(), pos.columnIndex() + i)); } } for
	 * (Position p : positions) { board.addMark(p); } }
	 */

	private int boatSize(String boatName) {
		if (boatName.equals("Aircraft Carrier")) {
			return 5;
		} else if (boatName.equals("Battleship")) {
			return 4;
		} else if (boatName.equals("Destroyer")) {
			return 2;
		} else {
			return 3;
		}
	}

	private int[][] displacement(Boat b) {
		int[][] displacement = new int[b.size()][b.size()];
		for (int i = 0; i < b.size(); i++) {
			displacement[i] = b.direction().equals("horizontal")
					? new int[] { b.position().rowIndex(), b.position().columnIndex() + i }
					: new int[] { b.position().rowIndex() + i, b.position().columnIndex() };
		}
		return displacement;
	}

	public void genHeatmap() {
		heatmap = new Heatmap();
		synchronized (aliveBoats) {
		for (String s : aliveBoats) {
//			System.out.println("testing boat: " + s);
			boolean vertical = true;
			for (int a = 0; a < 2; a++) { // iterate twice, once for each direction
				for (int i = 0; i < 10; i++) { // rows
					for (int j = 0; j < 10; j++) { // cols
						Boat b = new Boat(s, new Position(i, j), vertical ? "vertical" : "horizontal");
						try {
							board.placeBoat(b.name(), b.direction(), b.position());
//							if (hitIntersection(b)) {
//								heatmap.weightedIncrement(displacement(b));
//							} else {
//								heatmap.increment(displacement(b));
//							}
							
							heatmap.increment(displacement(b), hitIntersection(b) * WEIGHT + 1);
							
						} catch (Exception e) {
							// do nothing here, skips incrementing heatmap
						}
					}
				}
				vertical = false;
			}
//			System.out.println("\n\n\n");
//			heatmap.printMap();
		}
		}
	}

	
	private int hitIntersection(Boat b) {
//		boolean hitIntersect = false;
		boolean vertical = b.direction().equals("vertical");
		int count = 0;
		for (int i = 0; i < boatSize(b.name()); i++) {
			if (vertical) {
				if (getGrid().hit(new Position(b.position().rowIndex() + i, b.position().columnIndex()))) {
//					hitIntersect = true;
					count++;
				}
			} else {
				if (getGrid().hit(new Position(b.position().rowIndex(), b.position().columnIndex() + i))) {
//					hitIntersect = true;
					count++;
				}
			}
		}
		return count;
	}

	/*
	 * public static void main(String[] args) { NicholasMarthinussStrategy strat =
	 * new NicholasMarthinussStrategy(); strat.startGame(); strat.genHeatmap();
	 * strat.heatmap.printMap();
	 * 
	 * System.out.println("\n\n\n");
	 * 
	 * // strat.board.addMark(new Position(4, 4)); // strat.board.addMark(new
	 * Position(4, 6));
	 * 
	 * for (int i = 0; i < 8; i++) { for (int j = 0; j < 8; j++) {
	 * strat.board.addMark(new Position(i, j)); } }
	 * 
	 * strat.genHeatmap(); strat.heatmap.printMap();
	 * 
	 * // int[][] coords = new NicholasMarthinussStrategy() // .displacement((new
	 * Boat("Aircraft Carrier", new Position(1, 2), "horizontal"))); // // for (int
	 * i = 0; i < coords.length; i++) { // for (int j = 0; j < coords[i].length;
	 * j++) { // System.out.println(coords[i][j]); // } // }
	 * 
	 * }
	 */

	class Heatmap {
		int[][] heatmap;

		public Heatmap() {
			heatmap = new int[10][10];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					heatmap[i][j] = 0;
				}
			}
		}

		public void increment(int[][] coords, int amt) {
			for (int i = 0; i < coords.length; i++) {
				for (int j = 0; j < coords[i].length - 1; j++) {
					if (!getGrid().hit(new Position(coords[i][j], coords[i][j + 1]))) { // if the square isn't already
																						// hit, iterate
						heatmap[coords[i][j]][coords[i][j + 1]] += amt;
					}
				}
			}
		}

//		public void weightedIncrement(int[][] coords) { // XXX: check this method too because I copied it from above
//			for (int i = 0; i < coords.length; i++) {
//				for (int j = 0; j < coords[i].length - 1; j++) {
//
//					if (!getGrid().hit(new Position(coords[i][j], coords[i][j + 1]))) { // if the square isn't already
//																						// hit, iterate
//						heatmap[coords[i][j]][coords[i][j + 1]] += 5;
//					}
//				}
//			}
//		}

		public void printMap() {
			for (int i = 0; i < heatmap.length; i++) {
				for (int j = 0; j < heatmap[i].length; j++) {
					System.out.print(heatmap[i][j] + " ");
				}
				System.out.print("\n");
			}
		}

		public int[][] getHeatmap() {
			return heatmap;
		}

		public Position hottestPos() {
			int max = -1;
			for (int i = 0; i < heatmap.length; i++) {
				for (int j = 0; j < heatmap[i].length; j++) {
					max = Math.max(max, heatmap[i][j]);
				}
			}
			for (int i = 0; i < heatmap.length; i++) {
				for (int j = 0; j < heatmap[i].length; j++) {
					if (heatmap[i][j] == max) {
						return new Position(i, j);
					}
				}
			}
			return null;
		}
	}

	class Brickboard {
		private int[][] grid;

		public Brickboard() {
			grid = new int[10][10];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					grid[i][j] = 0;
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
					if (grid[row][pos.columnIndex()] != 0) {
						throw new Exception("overlap");
					}
				}
			} else {
				if (boat.size() - 1 + pos.columnIndex() > 9) {
					throw new Exception("out of bounds");
				}
				for (int col = pos.columnIndex(); col < boat.size() + pos.columnIndex(); col++) {
					if (grid[pos.rowIndex()][col] != 0) {
						throw new Exception("overlap");
					}
				}
			}
		}

		public void addMark(Position pos) {
			// XXX: Remove the try/catch, this is just for debugging
			
			try {
				grid[pos.rowIndex()][pos.columnIndex()] = 1;
			} catch (Exception e) {
				System.err.println("there has been an error");
			}
		}
	}

	public void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

}
