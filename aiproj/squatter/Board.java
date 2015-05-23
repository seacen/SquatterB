package aiproj.squatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


public class Board implements Piece{
	
	private Cell[][] board;
	private int dimension;
	private ArrayList<Cell> freeCells;
	
	public Board(int n) {
		// standard board initialization
		dimension=n;
		board=new Cell[dimension][dimension];
		freeCells= new ArrayList<Cell>(dimension*dimension);
		
		for (int i=0;i<dimension;i++) {
			for (int x=0;x<n;x++) {
				board[i][x]=new Cell(i,x);
				freeCells.add(board[i][x]);
			}
		}
	}

	public Board(BufferedReader input) throws NumberFormatException, IOException {
		
		// test version board initialization from stdin
		
		dimension = Integer.parseInt(input.readLine());
		board=new Cell[dimension][dimension];
		
		String line;
		
		for (int i=0;i<dimension;i++) {
			line=input.readLine();
			for (int x=0;x<dimension;x++) {
				char c=line.charAt(x*2);
				if (c=='-') {
					board[i][x]=new Cell(i,x,DEAD);
				}
				else if (c=='+') {
					board[i][x]=new Cell(i,x,EMPTY);
				}
				else if (c=='B') {
					board[i][x]=new Cell(i,x,BLACK);
				}
				else {
					board[i][x]=new Cell(i,x,WHITE);
				}
			}
		}
		
	}
	
	public boolean updateBoard(Move move) {
		
		int row=move.Row, column=move.Col;
		
		if ((row>=dimension) || (column>=dimension)) {
			return false;
		}
		
		Cell cell=board[row][column];
		
		if (cell.getVal()!=EMPTY) {
			return false;
		}
		board[row][column].setVal(move.P);
		freeCells.remove(cell);
		
		checkLoop(board,move);
		
		return true;
	}
	
	public void checkLoop(Cell[][] board, Move move) {
		
	}

	public ArrayList<Cell> getFreeCells() {
		return freeCells;
	}
	
	
}
