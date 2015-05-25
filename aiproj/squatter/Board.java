package aiproj.squatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Xichang Zhao
 *
 */
public class Board implements CellStatus{
	
	private Cell[][] board;
	private int dimension,freeCellCount,winner;
	private ArrayList<Cell> freeCells;
	
	public Board(int n) {
		// standard board initialization
		dimension=n;
		freeCellCount=dimension*dimension;
		winner=checkWinner();
		
		board=new Cell[dimension][dimension];
		freeCells= new ArrayList<Cell>(freeCellCount);
		
		for (int i=0;i<dimension;i++) {
			for (int x=0;x<n;x++) {
				board[i][x]=new Cell(i,x);
				freeCells.add(board[i][x]);
			}
		}
	}

	public Board(int n, BufferedReader input) throws NumberFormatException, IOException {
		
		// test version board initialization from stdin
		
		dimension = n;
		freeCellCount=dimension*dimension;
		
		freeCells= new ArrayList<Cell>(freeCellCount);
		board=new Cell[dimension][dimension];
		
		String line;
		
		for (int i=0;i<dimension;i++) {
			line=input.readLine();
			for (int x=0;x<dimension;x++) {
				
				//escape spaces
				char c=line.charAt(x*2);
				
				if (c=='-') {
					freeCellCount--;
					board[i][x]=new Cell(i,x,CAPEMPTY);
				}
				else if (c=='b') {
					freeCellCount--;
					board[i][x]=new Cell(i,x,CAPBLACK);
				}
				else if (c=='w') {
					freeCellCount--;
					board[i][x]=new Cell(i,x,CAPWHITE);
				}
				else if (c=='+') {
					board[i][x]=new Cell(i,x,EMPTY);
				}
				else if (c=='B') {
					freeCellCount--;
					board[i][x]=new Cell(i,x,BLACK);
				}
				else {
					freeCellCount--;
					board[i][x]=new Cell(i,x,WHITE);
				}
			}
		}
		
		winner=checkWinner();
		
	}
	
	public int getFreeCellCount() {
		return freeCellCount;
	}

	public int getWinner() {
		return winner;
	}

	public Cell[][] getBoard() {
		return board;
	}

	public int getDimension() {
		return dimension;
	}

	/**
	 * update the board when a move is made
	 * @param move move just made that is not yet reflected in board
	 * @return true if move is valid, else false
	 */
	public boolean updateBoard(Move move) {
		
		int row=move.Row, column=move.Col;
		
		if (!validateMove(row,column)) {
			winner=INVALID;
			return false;
		}
		
		updateCell(row, column, move.P);
		
		checkLoop(board,move);
		
		winner=checkWinner();
		
		return true;
	}
	
	public int checkWinner() {
		
		if (freeCellCount!=0) {
			return EMPTY;
		}
		
		int blackCaptured=0, whiteCaptured=0;
		
		for (int i=0;i<dimension;i++) {
			
			int capturedCount=0;
			for (int x=0;x<dimension;x++) {
				int cell=board[i][x].getVal();
				if ((cell==CAPWHITE) || (cell==CAPBLACK) || (cell==CAPEMPTY)) {
					capturedCount++;
				}
				else if (cell==BLACK) {
					blackCaptured+=capturedCount;
					capturedCount=0;
				}
				else {
					whiteCaptured+=capturedCount;
					capturedCount=0;
				}
			}
		}
		
		if (blackCaptured>whiteCaptured) {
			return BLACK;
		}
		else if (blackCaptured<whiteCaptured) {
			return WHITE;
		}
		else {
			return DEAD;
		}
	}
	
	/**
	 * update a cell by the new cell status
	 * @param row
	 * @param column
	 * @param status cell status (e.g. BLACK, WHITE, CAPWHITE)
	 */
	public void updateCell(int row, int column, int status) {
		board[row][column].setVal(status);
		freeCells.remove(board[row][column]);
		freeCellCount--;
	}
	
	/**
	 * validate a move to board
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean validateMove(int row, int column) {
		
		// check if move is out of board range
		if ((row>=dimension) || (column>=dimension)) {
			return false;
		}
		
		Cell cell=board[row][column];
		
		// check if move is not made to a non-empty cell
		if (cell.getVal()!=EMPTY) {
			return false;
		}
		return true;
	}
	
	public void checkLoop(Cell[][] board, Move move) {
		
	}

	public ArrayList<Cell> getFreeCells() {
		return freeCells;
	}
	
	
}
