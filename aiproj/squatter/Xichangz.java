package aiproj.squatter;
import java.io.PrintStream;



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
		return null;
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

}
