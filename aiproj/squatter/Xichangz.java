package aiproj.squatter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;



/**
 * Created by hexin on 5/05/15.
 */
public class Xichangz implements Player, Piece {
	
	private int role=INVALID;
	private Board board;
	
	@Override
	public int init(int n, int p) {
		// TODO Auto-generated method stub
		role=p;
		board= new Board(n);
		return 0;
	}

	@Override
	public Move makeMove() {
		// TODO Auto-generated method stub
		Move move = new Move();
		
		move.P=role;
		
		moveAlgo(move);
		
		board.updateBoard(move);

		return move;
	}

	@Override
	public int opponentMove(Move m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWinner() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printBoard(PrintStream output) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveAlgo(Move move) {
		
		ArrayList<Cell> freeCells=board.getFreeCells();
		
		int max=freeCells.size();
		int min=0;
		
		Cell cellToUpdate=freeCells.get(randInt(min,max));
		
		move.Row=cellToUpdate.getRow();
		move.Col=cellToUpdate.getCol();
		
	}
	
	public static int randInt(int min, int max) {
		// Attribute to Greg Case from stack overflow
		
	    Random rand = new Random();

	    int randomNum = rand.nextInt(max - min) + min;

	    return randomNum;
	}

}
