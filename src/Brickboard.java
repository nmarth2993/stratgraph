/*
 * public class Brickboard { private int[][] grid;
 * 
 * public Brickboard() { grid = new int[10][10]; for (int i = 0; i < 10; i++) {
 * for (int j = 0; j < 10; j++) { grid[i][j] = 0; } } }
 * 
 * public void placeBoat(String boatName, String direction, Position pos) throws
 * Exception { Boat boat = new Boat(boatName, pos, direction); if
 * (pos.rowIndex() < 0 || pos.columnIndex() < 0 || pos.rowIndex() > 9 ||
 * pos.columnIndex() > 9) { throw new Exception("out of bounds"); } if
 * (direction.equalsIgnoreCase("vertical")) { if (boat.size() + pos.rowIndex() >
 * 9) { throw new Exception("out of bounds"); } for (int row = pos.rowIndex();
 * row < boat.size() + pos.rowIndex(); row++) { if (grid[row][pos.columnIndex()]
 * != 0) { throw new Exception("overlap"); } } } else { if (boat.size() +
 * pos.columnIndex() > 9) { throw new Exception("out of bounds"); } for (int col
 * = pos.columnIndex(); col < boat.size() + pos.columnIndex(); col++) { if
 * (grid[pos.rowIndex()][col] != 0) { throw new Exception("overlap"); } } } }
 * 
 * public void addMark(Position pos) { // XXX: Remove the try/catch, this is
 * just for debugging
 * 
 * try { grid[pos.rowIndex()][pos.columnIndex()] = 1; } catch (Exception e) {
 * System.err.println("there has been an error"); } }
 * 
 * // public static void main(String[] args) { // Brickboard b = new
 * Brickboard(); // b.addMark(new Position(1, 0)); // try { //
 * b.placeBoat("Aircraft Carrier", "vertical", new Position(0, 0)); // } catch
 * (Exception e) { // e.printStackTrace(); // } // } }
 */