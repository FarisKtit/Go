package models;

import java.io.Serializable;

public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	// size of the board
	private int size = 9;
	// grid intersection
	private int[][] board;
	// player 2
	private final int player2 = 2;
	// player 1 
	private final int player1 = 1;

	// constructor of class board
	public Board() {
		this.board = new int[size][size];
	}

	// method to place stone
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
	
	//main method to find captured stones
	public boolean[][] connectedStones(int oppositePlayer) {
		boolean[][] connectedStones = null;
		boolean isCaptured = false;
		for (int row = 0; row < board.length; ++row) {
			for (int column = 0; column < board[row].length; ++column) {
				if (board[row][column] == oppositePlayer) {
					connectedStones = new boolean[size][size];
					findConnectedStones(row, column, oppositePlayer, connectedStones);
					isCaptured = isCaptured(oppositePlayer, connectedStones);
				}
			}
		}
		if (isCaptured)
			return connectedStones;
		else
			return null;
	}

	//replace captured stones with 0
	public void replaceConnectedStones(boolean[][] connectedStones) {
		for (int row = 0; row < board.length; ++row) {
			for (int column = 0; column < board[row].length; ++column) {
				if (connectedStones[row][column]) {
					board[row][column] = 0;
				}
			}
		}
	}

	// method to check if the stone is captured
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

	//method to find opponent's stones on the board
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


	// method to remove stone
	public void removeStone(int row, int column) {
		board[row][column] = 0;
	}

	// method to check if the given position is occupied
	public boolean isOccupied(int row, int column) {
		if (board[row][column] == 0) {
			return false;
		}
		return true;
	}
	  public String toString() {
	      return size+"=="+board+"=="+player2+"=="+player1;
	  }
}