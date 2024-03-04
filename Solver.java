package p2;

import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;
import p1New.Board;

public class Solver {
	
	public Solver() {
		// TODO
		// You can add any initialization/preprocessing code here.
		// There are valid solutions where this constructor does nothing
		// but there are also solutions where some preprocessing done here
		// leads to the solve method running much faster.
	}

	/**
	 * Finds a solution to a given puzzle board that uses the fewest number of moves.
	 * 
	 * @param start a representation of the initial state of the puzzle
	 * @return a minimal sequence of moves that solves the given puzzle
	 */
	
	public int[] solve(byte[][] start) {
		//ALL data structures
		//MovesTracker - value is the move we made to get to that board (the key)
		SeparateChainingHashST<Board, Integer> movesTracker = new SeparateChainingHashST<>();
		//key is new Board we just created after move
		//value is Board we just came from (edgeTo)
		//edgeTo Map (Graph)
		SeparateChainingHashST<Board, Board> edgeMap = new SeparateChainingHashST<Board, Board>();
		//Marked symbol table
		SeparateChainingHashST<Board, Boolean> marked = new SeparateChainingHashST<>();
		//queue for bfs
		Queue<Board> q = new Queue<Board>();
		//---------------------------
		//for BFS pathFrom solution board to source board
		Stack<Integer> stack = new Stack<Integer>();
		byte[][] startPath = new byte[2][5];
		Board startPath1 = new Board(startPath);
		int moveToAdd;
		
		//solution array for zero moves
		int[] zeroMoves = new int[0];
		
		//possible moves
		int[] m = {-2,-1, 1, 2, 3, 4, 5, 6, 7};
		
		//solution board
		byte[][] sol = {{0,1,2,3,4},{5,6,7,8,9}};
		Board solution = new Board(sol);
		boolean checkW = false;
		
		Board s = new Board(start);
		
		//if board is already solved from beginning
		
		q.enqueue(s);
		while (!q.isEmpty()) {
			if (checkW==true) {
				break;
				}
			Board prev = q.dequeue();
			
			for (int move : m) {
				Board current = new Board(prev);
				current.move(move);
				marked.put(current, false);
			
				if(marked.get(current)==false) {
					edgeMap.put(current, prev);
					movesTracker.put(current, move);
					q.enqueue(current); 
					marked.put(current, true);
					
					//checkSolution
					if (current.compareTo(solution)==0) {
						checkW = true;
						startPath1 = current;
						
					}
					if(s.compareTo(solution)==0) {
						return zeroMoves;
					}
				}
			}
		}
		
		//FINDING path from sol board to source board
		while (startPath1.compareTo(s)!=0) {
			moveToAdd = movesTracker.get(startPath1);
			stack.push(moveToAdd);
			startPath1 = edgeMap.get(startPath1);
			if(startPath1.compareTo(s)==0) {
				break;
			}
		}
	
		int arrSize = stack.size();
		int solMoves[] = new int[arrSize];
		int j=0;
			for (Integer i: stack) {
				solMoves[j++] = i; 
				}
				return solMoves; 
			}


	// Below is an example of how your code will get called to solve a puzzle.
	// This is the example from the write-up and the solution is [4, 1].
	//SOLUTION 1, 4 in order (CHANGED FOR 6 MOVES)
	public static void main(String[] args) {
		Solver solver = new Solver();
		byte[][] array = {{6,8,4,3,9}, {7,1,2,5,0}};
		int[] sol = solver.solve(array);
		System.out.println(Arrays.toString(sol));
	}
}
