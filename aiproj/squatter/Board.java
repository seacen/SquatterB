package aiproj.squatter;
import java.io.BufferedReader;
import java.io.IOException;


public class Board implements Piece{
	
	private int[][] board;
	private int dimension;
	
	public Board(int n) {
		// standard board initialization
		dimension=n;
		board=new int[dimension][dimension];
		
		for (int i=0;i<dimension;i++) {
			for (int x=0;x<n;x++) {
				board[i][x]=EMPTY;
			}
		}
	}

	public Board(BufferedReader input) throws NumberFormatException, IOException {
		
		// test version board initialization from stdin
		
		dimension = Integer.parseInt(input.readLine());
		board=new int[dimension][dimension];
		
		String line;
		
		for (int i=0;i<dimension;i++) {
			line=input.readLine();
			for (int x=0;x<dimension;x++) {
				char c=line.charAt(x*2);
				if (c=='-') {
					board[i][x]=DEAD;
				}
				else if (c=='+') {
					board[i][x]=EMPTY;
				}
				else if (c=='B') {
					board[i][x]=BLACK;
				}
				else {
					board[i][x]=WHITE;
				}
			}
		}
		
	}
	
	public boolean updateBoard(Move move) {
		
		int row=move.Row, column=move.Col;
		
		if ((row>=dimension) || (column>=dimension)) {
			return false;
		}
		
		int cell=board[row][column];
		
		if (cell!=EMPTY) {
			return false;
		}
		board[row][column]=move.P;
		
		checkLoop(board,move);
		
		return true;
	}
	
	public void checkLoop(int[][] board, Move move) {
		
	}
	

}
