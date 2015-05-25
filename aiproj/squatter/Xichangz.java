package aiproj.squatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;



/**
 * Created by hexin on 5/05/15.
 */
public class Xichangz implements Player, CellStatus {
	
	private int role;
	private int oppoRole;
	private Board board;
	
	public int getRole() {
		return role;
	}

	public Board getBoard() {
		return board;
	}
	
	/**
	 * initialize player in standard approach
	 * @param n	board dimension
	 * @param p	player role
	 */
	public int init(int n, int p) {
		// TODO Auto-generated method stub
		role=p;
		if (p==BLACK) {
			oppoRole=WHITE;
		}
		else {
			oppoRole=BLACK;
		}
		board= new Board(n);
		return 0;
	}
	
	/**
	 * initialize for test player, board built from stdin input
	 * @param n	board dimension
	 * @param p	player role
	 * @param input	stdin board input
	 */
	public int init(int n, int p, BufferedReader input) throws NumberFormatException, IOException {
		role=p;
		if (p==BLACK) {
			oppoRole=WHITE;
		}
		else {
			oppoRole=BLACK;
		}
		board= new Board(n,input);
		return 0;
	}

	/**
	 * make a move
	 * @return a move
	 */
	public Move makeMove() {
		// TODO Auto-generated method stub
		Move move = new Move();
		
		move.P=role;
		
		randomMoveAlgo(move);
		
		board.updateBoard(move);

		return move;
	}

	/**
	 * validate opponent's move
	 * @param m previous move by opponent
	 * @return INVALID(-1) if move is invalid, else 0
	 */
	public int opponentMove(Move m) {
		// TODO Auto-generated method stub
		
		
		if (m.P!=oppoRole) {
			return INVALID;
		}
		
		if (!board.updateBoard(m)) {
			return INVALID;
		}
		return 0;
	}

	public int getWinner() {
		// TODO Auto-generated method stub
		return board.getWinner();
	}

	/**
	 * print a player's board
	 * @param output printStream type output
	 */
	public void printBoard(PrintStream output) {
		// TODO Auto-generated method stub
		
		Cell[][] printBoard=board.getBoard();
		int dimension=board.getDimension();
		
		for (int i=0;i<dimension;i++) {
			for (int x=0;x<dimension;x++) {
				
				//print the char value of a cellStatus int key
				output.print(mapToChar.get(printBoard[i][x].getVal()));
				
				//print space after each cell unless last cell
				if (x<(dimension-1)) {
					output.print(' ');
				}
			}
			
			output.print('\n');
		}
		
	}
	
	/**
	 * an moving algorithm for determining row,column values of a move to make 
	 */
	public void randomMoveAlgo(Move move) {
		
		ArrayList<Cell> freeCells=board.getFreeCells();
		
		int max=freeCells.size();
		int min=0;
		
		Cell cellToUpdate=freeCells.get(randInt(min,max));
		
		move.Row=cellToUpdate.getRow();
		move.Col=cellToUpdate.getCol();
		
	}
	
	/**
	 * generate a random number within range min,max
	 * @param min
	 * @param max
	 * @return integer generated
	 */
	public static int randInt(int min, int max) {
		// Attribute to Greg Case from stack overflow
		
	    Random rand = new Random();

	    int randomNum = rand.nextInt(max - min) + min;

	    return randomNum;
	}
	
	public void moveAlgo(Move move) {
		
	}

}
