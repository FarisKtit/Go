package models;

public class Board{
	//size of the board
	public static int size = 9;
	//grid intersection
	private int[][] board;
	//constructor of class board
	public Board(int[][] board){
		this.board = new int [size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				this.board[i][j] = 0;
			}
		}
	}
}