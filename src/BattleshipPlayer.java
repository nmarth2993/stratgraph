import javax.swing.JOptionPane;

public class BattleshipPlayer {
	private BattleshipGrid grid;
	private String name;

	public BattleshipPlayer() {
		name = "";
	}

	public void startGame() {
		initializeGrid();
		if (name.equals("")) {
//			name = (String) JOptionPane.showInputDialog(null, "Enter your name:", "Name entry",
//					JOptionPane.INFORMATION_MESSAGE, null, null, 0);
//			name = JOptionPane.showInputDialog(null, "Enter your name: ", "Name entry",
//					JOptionPane.INFORMATION_MESSAGE);
			if (name == null) {
				name = "";
			}
//			name = new Scanner(System.in).nextLine();
		}
	}

	public String playerName() {
		return name;
	}

	public Position shoot() {
		// XXX: I need to re-code this entire method.
		// might not even need this method, because position
		// is recieved from a click
//		boolean error = false;
//		char row = 0;
//		int column = 0;
//		Scanner input = new Scanner(System.in);
//		do {
//			if (error) {
//				System.out.println("Unrecognized input");
//			}
//			error = false;
//			System.out.println("Enter a row to shoot at (A-J): ");
//			try {
//				row = Character.toUpperCase(input.nextLine().charAt(0));
//				if ((int) row < 65 || (int) row > 74) {
//					error = true;
//				}
//			} catch (Exception e) {
//				error = true;
//			}
//
//		} while (error);
//		do {
//			if (error) {
//				System.out.println("Unrecognized input");
//			}
//			error = false;
//			System.out.println("Enter a column to shoot at (1-10): ");
//			try {
//				column = Integer.parseInt(input.nextLine());
//				if (column < 1 || column > 10) {
//					error = true;
//				}
//			} catch (Exception e) {
//				error = true;
//			}
//
//		} while (error);
//		return new Position(row, column);
		return null;
	}

	public void updateGrid(Position pos, boolean hit, char initial) {
		grid.shotAt(pos, hit, initial);
	}

	public BattleshipGrid getGrid() {
		return grid;
	}

	public void initializeGrid() {
		grid = new BattleshipGrid();
	}

	public void updatePlayer(Position pos, boolean hit, char initial, String boatName, boolean sunk, boolean gameOver,
			boolean tooManyTurns, int turns) {
		updateGrid(pos, hit, initial);

//		System.out.print("Turn #" + turns + ": Your shot at " + pos);
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
//				if (grid.empty(new Position(i, j))) {
//					System.out.print(". ");
//				} else if (grid.miss(new Position(i, j))) {
//					System.out.print("* ");
//				} else {
//					System.out.print(grid.boatInitial(new Position(i, j)) + " ");
//				}
//				if (j == 9) {
//					System.out.print("\n");
//				}
//			}
//		}
	}
}
