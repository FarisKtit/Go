package models;
/**
 * Implements board class.
 * @author Antonius Ricky Sanjaya
 *         Faris Ktit    
 * @version 0.1
 */
import java.io.Serializable;

/**
 * Board class creates an empty board.
 */
public class Board implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** Size of the board. */
	private int size = 9;
	
	/** Grid intersection. */
	private int[][] board;
	
	/** Player 2. */
	private final int player2 = 2;
	
	/** Player 1. */ 
	private final int player1 = 1;

	/** 
	 * Main constructor to create an empty board.
	 */
	public Board() {
		this.board = new int[size][size];
	}
	
	/**
	 * Get the board component.
	 * @return The board.
	 */
	public int[][] getBoard() {
		return this.board;
	}

	/**
	 * Method to place stone on the board.
	 * @param row The row position of the board.
	 * @param column The column position of the board.
	 * @param player The player.
	 * @return String "SUCCESS" if given position is valid.
	 * 		   String "FAIL" if given position is invalid.
	 */
	public String placeStones(int row, int column, String player) {
		if (board[row][column] == 0) {
			if (player.equals("Player 1")) {
				board[row][column] = player1;
			} 
			else {
				board[row][column] = player2;
			}
			return "SUCCESS";
		} 
		else {
			return "FAIL";
		}
	}
	
	/** 
	 * Main method to find captured stones.
	 * @param oppositePlayer The enemy's stone.
	 * @return The captured stones.
	 */
	public boolean[][] connectedStones(int oppositePlayer) {
		boolean[][] connectedStones = null;
		boolean isCaptured = false;
		for (int row = 0; row < board.length; ++row) {
			for (int column = 0; column < board[row].length; ++column) {
				if (board[row][column] == oppositePlayer) {
					connectedStones = new boolean[size][size];
					findConnectedStones(row, column, oppositePlayer, connectedStones);
					isCaptured = isCaptured(oppositePlayer, connectedStones);
					if (isCaptured)
						return connectedStones;
				}
			}
		}
		
     	return null;
	}

	/** 
	 * Replace captured stones with 0.
	 * @param connectedStones A boolean 2d array of captured stones.
	 */
	public void replaceConnectedStones(boolean[][] connectedStones) {
		for (int row = 0; row < board.length; ++row) {
			for (int column = 0; column < board[row].length; ++column) {
				if (connectedStones[row][column]) {
					board[row][column] = 0;
				}
			}
		}
	}

	/**
	 * Method to check if the stone is captured.
	 * @param oppositePlayer The enemy's stone.
	 * @param connectedStones A boolean 2d array of captured stones.
	 * @return Captured if it is on the board and is captured.
	 *         False if it is not captured and not on the board.
	 */
	public boolean isCaptured(int oppositePlayer, boolean[][] connectedStones) {
		int currentPlayer;
		if (oppositePlayer == 1)
			currentPlayer = 2;
		else
			currentPlayer = 1;
		boolean captured = true;
		for (int row = 0; row < connectedStones.length; ++row) {
			for (int column = 0; column < connectedStones[row].length; ++column) {
				if (connectedStones[row][column]) {
					if ((row - 1) >= 0) {
						if (board[row - 1][column] == 0)
							return false;
					}
					if ((row + 1) <= 8) {
						if (board[row + 1][column] == 0)
							return false;
					}
					if ((column - 1) >= 0) {
						if (board[row][column - 1] == 0)
							return false;
					}
					if ((column + 1) <= 8) {
						if (board[row][column + 1] == 0)
							return false;
					}
				}
			}
		}
		return captured;
	}

	/** 
	 * Method to find opponent's stones on the board.
	 * @param row The row position on the board.
	 * @param column The column position on the board.
	 * @param oppositePlayer The enemy's stone.
	 * @param connectedStones A boolean 2d array of captured stones.
	 */
	public void findConnectedStones(int row, int column, int oppositePlayer, boolean[][] connectedStones) {
		if (row < 0 || row > 8 || column < 0 || column > 8) {
			return;
		} else if (connectedStones[row][column]) {
			return;
		} else if (board[row][column] != oppositePlayer) {
			return;
		} else {
			connectedStones[row][column] = true;
			findConnectedStones(row - 1, column, oppositePlayer, connectedStones);
			findConnectedStones(row + 1, column, oppositePlayer, connectedStones);
			findConnectedStones(row, column - 1, oppositePlayer, connectedStones);
			findConnectedStones(row, column + 1, oppositePlayer, connectedStones);
		}
	}

	// method to find the opponent's stones on the board
	public int countDeadStones(int currentPlayer) {
		int count = 0;
		count = deadStones(currentPlayer);
		return count;
	}

	// method to count the dead stones
	private int deadStones (int currentPlayer) {
		boolean[][] connectedStones = null;
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j =0; j <board[i].length; j++) {
				if(board[i][j] == currentPlayer) {
					connectedStones = new boolean[9][9];
					findConnectedStones(i, j, currentPlayer, connectedStones);
					if(isDead(currentPlayer, connectedStones)) {
						count++;
					}
				}
			}
		}
		return count;
	}

	// method to check if the stone is a dead stone
	private boolean isDead(int currentPlayer, boolean [][] connectedStones) {
		int count = 0;
		for (int i = 0; i <connectedStones.length; i++){
			for (int j = 0; j < connectedStones[i].length; j++){
				if (connectedStones[i][j]) {
					if (i - 1 >= 0) {
						if (board[i - 1][j] == 0) {
							count++;
						}
					}
					if (i + 1 <= 8) {
						if (board[i + 1][j] == 0) {
							count++;
						}
					}
					if (j - 1 >= 0) {
						if (board[i][j - 1] == 0) {
							count++;
						}
					}
					if (j + 1 <= 8) {
						if (board[i][j + 1] == 0) {
							count++;
						}
					}
				}
			}
		}
		if (count != 1) {
			return false;
		} else {
			return true;
		}
	}


	/**
	 * Method to remove stone.
	 * @param row The row position on the board.
	 * @param column The column position on the board.
	 */
	public void removeStone(int row, int column) {
		board[row][column] = 0;
	}

	/** 
	 * Method to check if the given position is occupied.
	 * @param row The row position on the board.
	 * @param column The column position on the board.
	 * @return True if the given position is occupied.
	 *         False if the given position is not occupied.
	 */
	public boolean isOccupied(int row, int column) {
		if (board[row][column] == 0) {
			return false;
		}
		return true;
	}
	/** 
	 * Method to overwrite to string.
	 */
	  public String toString() {
	      return size+"=="+board+"=="+player2+"=="+player1;
	  }
}