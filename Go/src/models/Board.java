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