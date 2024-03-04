package p1;

import java.util.Arrays;

public class Board implements Comparable<Board> {
	/**
	 * Construct a number puzzle board by beginning with the solution board making a
	 * number of random moves such that no board position is repeated while
	 * performing the sequence of moves.
	 * 
	 * @param moves the number of moves to make when generating the board.
	 */
	
	
	//number of tiles in row
	final int N = 2;
	
	//number of tiles in column
	final int M = 5;
	
	//initialize board
	public byte[][] board = new byte[2][5];

	//position of current/target
	public int row;
	public int col;
	
	/** NEW ->
	 * Construct a number puzzle board by beginning with the solution board making a
	 * number of random moves such that no board position is repeated while
	 * performing the sequence of moves.
	 * 
	 * @param moves the number of moves to make when generating the board.
	 */
	public Board(int moves) { 
		
				//solution board
				byte[][] b = {
						{0, 1, 2, 3, 4},
						{5, 6, 7, 8, 9}
				};
				
				//setting global variable board to the board we just initialized
				board = b; 
		}
			
	
	

	 
	/**
	 * NEW ->
	 * Construct a number puzzle board using a 2 by 5 array of bytes. Each array
	 * cell holds a different number between 0 and 9 inclusive.
	 * 
	 * @param b a 2x5 byte array representing the 10 numbers in the number puzzle.
	 */
	public Board(byte[][] b) {

		//nested for loop to remain in the 2x5
		for (int i=0; i < N; i++) {
			for (int j=0; j < M; j++) {
				//allows us to reference the global variable board
				board[i][j] = b[i][j];
			}
		}
	}
	
	
	
	/**
	 * Construct a copy of a board.
	 * 
	 * @param original the board to be copied
	 */
	public Board(Board original) {
		for (int i=0; i < N; i++) {
			for (int j=0; j < M; j++) {
				//allows us to reference the global variable board
				board[i][j] = original.board[i][j];
			}
		}
		
	}
		
	
	
	/**
	 * Makes a move on the board at position (row, col) on the board.
	 * This flips the state of the cell at that position from filled to
	 * clear or from clear to filled.  It also flips the state of the cell
	 * above, below, to the left, and to the right, (if they exist).
	 * 
	 * @param row the row of the cell where the move is to be made
	 * @param col the column of the cell where the move is to be made
	 */
	
	/** NEW ->
	 * Makes a move on the board. The move must be a number between -2 and 7
	 * excluding 0. The numbers -1 and -2 shift the top or bottom row left one
	 * position. The numbers 1 and 2 shift the top or bottom row right one position.
	 * The numbers 3 through 7 exchange the two numbers in the given column. (The
	 * leftmost column is column 3 and the rightmost column is column 7.)
	 * 
	 * @param m the move to be made
	 */
	public void move(int m) {
		
		//Shift top row to the left
		if(m==-1) {
			byte temp = board[row][col];
			    for (int col = 0; col < board[row].length; col++) {
			        board[row][col] = board[row][(col + 1) % board[row].length];
			    }
			board[row][col+4] = temp;
		}
		
					
		
		//Shift bottom row to the left
		if (m==-2) {
			byte temp = board[row+1][col];
				    for (int col = 0; col < board[row].length; col++) {
				        board[1][col] = board[1][(col + 1) % board[1].length];
				    }
			board[1][col+4] = temp;
	}	    
		
		//Shift bottom row TO THE RIGHT
				if (m==2) {
					byte temp = board[row+1][4];
					for (int col = 4; col > 0; col--)
						board[1][col] = board[1][col - 1];
					board[1][0] = temp;
				}

				
		//Shift top row TO THE RIGHT
		if (m==1) {
			byte temp = board[row][4];
			for (int col = 4; col > 0; col--)
				board[row][col] = board[row][col - 1];
			board[row][0] = temp;
		}
		
		//flipping top and bottom columns 3-7
		 if(m==3) {
			 byte temp = board[row][col];

			 board[row][col] = board[row+1][col];
			 board[row+1][col] = temp;
			    }
		 
		 if(m==4) {
			 byte temp = board[row][col+1];
			 
			 board[row][col+1] = board[row+1][col+1];
			 
			board[row+1][col+1] = temp;
			    }
		 
		 if(m==5) {
			 byte temp = board[row][col+2];
			 
			 board[row][col+2] = board[row+1][col+2];
			 
			board[row+1][col+2] = temp;
			    }
		 
		 if(m==6) {
			 byte temp = board[row][col+3];
			 
			 board[row][col+3] = board[row+1][col+3];
			 
			board[row+1][col+3] = temp;
			    }
		 
		 if(m==7) {
			 byte temp = board[row][col+4];
			 
			 board[row][col+4] = board[row+1][col+4];
			 
			board[row+1][col+4] = temp;
			    }
		
	}
//		 else {
//			throw new IllegalArgumentException("Please pick a number between -2 and 7 inclusive, excluding 0. Thanks!!!!!!");
//	}
//	}
	
		

		
	
	/**
	 * Returns a 2x5 table of numbers representing the board. This method is used
	 * solely for testing purposes and my tests never modify the contents of the
	 * array.
	 * 
	 * @return the 2x5 table of numbers representing the board.
	 * 2 rows, 5 cols
	 */
	public byte[][] getArray() {
		byte[][] testBoard = new byte[2][5];
		for (int i=0; i < N; i++) {
			for (int j=0; j < M; j++) {
		//'copying' 2x5 array to printed out gameBoard
				board[i][j] = testBoard[i][j];
			}
		}
		return testBoard;
	}

	
	//For P2


	@Override
	public int compareTo(Board o) {
		  int x = 0;
	      for (int i=0; i<board.length; i++) {
	          for (int j = 0; j<board[i].length; j++) {
	              if(this.board[i][j] > o.board[i][j]) {
	                   x = 1;
	                }
	               else if (this.board[i][j] < o.board[i][j]) {
                    	x = -1;
                    }
	            }
	        }
	        return x;
	   }


	public void printBoard() {
		System.out.println(Arrays.deepToString(board).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
		//System.out.println(Arrays.deepToString(board));
	}
}
//	public boolean checkLoose() {
//		if checkL.contains(board) {
//			System.out.println("You loose!");
//			return false;
//		}
//		return true;
//	}









