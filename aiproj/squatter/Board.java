package aiproj.squatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Xichang Zhao
 *
 */
public class Board implements CellStatus{
	
	private Cell[][] board;
	private int dimension,winner;
	private ArrayList<Cell> freeCells;
	
	public Board(int n) {
		// standard board initialization
		dimension=n;
		winner=checkWinner();
		
		board=new Cell[dimension][dimension];
		freeCells= new ArrayList<Cell>(dimension*dimension);
		
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
		
		freeCells= new ArrayList<Cell>(dimension*dimension);
		board=new Cell[dimension][dimension];
		
		String line;
		
		int freeCellCount=dimension*dimension;
		
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
		
		freeCells= new ArrayList<Cell>(freeCellCount);
		
		winner=checkWinner();
		
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
		
		System.out.println("\nNumber of cells captured: " + checkLoop(move.Row, move.Col, move.P, true));
		
		winner=checkWinner();
		
		return true;
	}
	
	public int checkWinner() {
		
		if (freeCells.size()!=0) {
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

    /* Check if there is any loop formed by the move made, and update the board accordingly.
     * Cells will be changed when overwrite is set to be true.
     * targetColor can be BLACK or WHITE. It is the loop color.
     * Return the number of captured cells found, 0 if none. */
    public int checkLoop(int row, int col, int targetColor, Boolean overwrite) {
        List<Cell> adjCells = crossAdjCells(row, col);
        int totalCaptured = 0;

        if (numMatchingCells(adjacentCells(row, col), targetColor) < 2) {
            return 0;
        }

        for (Cell c : adjCells) {
            totalCaptured += floodFill(c, targetColor, overwrite);
        }

        uncheckAll();
        return totalCaptured;
    }

    /* Explore the specified cell using flood fill method.
     * Mark a cell as checked if its status has already been determined.
     * If it is captured, mark all cells in its capture cells as captured, and return true.
     * Captured cells will be updated when overwrite is set to be true
     * Return the number of captured cells, 0 if none. */
    private int floodFill(Cell c, int loopColor, Boolean overwrite) {
        // All cells in the below two lists are not of loopColor.
        // Every non-loopColor cell will be explored
        // They will all be marked as captured if all cells are explored and none of them is a border cell.
        List<Cell> capturedList = new ArrayList<Cell>();
        Set<Cell> brotherList = new HashSet<Cell>();
        List<Cell> exploring = new ArrayList<Cell>();

        // Cell of its own color is not captured.
        if (c.matchColor(loopColor)) return 0;

        exploring.add(c);

        while (!exploring.isEmpty()) {
            Cell current = exploring.remove(0);
            if (!exploreCell(current, loopColor, capturedList, exploring, brotherList)) return 0;
        }

        if (overwrite) {
            for (Cell captured : capturedList) {
                if (captured.isEmpty()) freeCells.remove(captured);
                captured.setCaptured();
            }

            for (Cell brother : brotherList) {
                brother.setFreed();
            }
        }

        return capturedList.size();
    }

    /* Explore the specified cell.
     * Add its adjacent non-loop cells to exploringList.
     * Add itself to innerCellList.
     * Return false if it is a border cell. */
    private boolean exploreCell(Cell current, int loopColor, List<Cell> capturedList, List<Cell> exploringList,
                                Set<Cell> brotherList) {
        if (exploringList.contains(current)) exploringList.remove(current);
        current.setChecked(true);

        if (isBorderCell(current)) {
            return false;
        }

        List<Cell> adjCells = crossAdjCells(current.getRow(), current.getCol());

        for (Cell adjCell : adjCells) {
            if (!adjCell.matchColor(loopColor) && !adjCell.isChecked()) exploringList.add(adjCell);
            if (adjCell.matchColor(loopColor)) brotherList.add(adjCell);
        }

        capturedList.add(current);
        return true;
    }

    private boolean isBorderCell(Cell c) {
        if (c.getRow() == 0 || c.getRow() == dimension || c.getCol() == 0 || c.getCol() == dimension) {
            return true;
        }
        return false;
    }

    private void uncheckAll() {
        for (int x = 0; x < dimension; x++ ) {
            for (int y = 0; y < dimension; y++ ) {
                board[x][y].setChecked(false);
            }
        }
    }

    /* Return the number of empty cells in the array of cells */
    private int numEmptyCells(List<Cell> cells) {
        int num = 0;
        for (Cell c : cells) {
            if (c.getVal() == EMPTY) {
                num++;
            }
        }
        return num;
    }


    /* Return the number of cells which has the same color as the specified in the array of cells */
    private int numMatchingCells(List<Cell> cells, int target) {
        int num = 0;
        for (Cell c : cells) {
            if (c.matchColor(target)) {
                num++;
            }
        }
        return num;
    }

    /* Return the list of a cell's up, down, left, right adjacent cells */
    private List<Cell> crossAdjCells(int row, int col) {
        int index[] = {-1, 1};
        List<Cell> result = new ArrayList<Cell>();
        for (int i : index) {
            int rowX = row + i;
            if (isValidCell(rowX, col)) {
                result.add(board[rowX][col]);
            }

            int colY = col + i;
            if (isValidCell(row, colY)) {
                result.add(board[row][colY]);
            }
        }
        return result;
    }

    /* A list of adjacent cells */
    private List<Cell> adjacentCells(int row, int col) {
        int index[] = {-1, 0 ,1};
        List<Cell> result = new ArrayList<Cell>();
        for (int x : index) {
            for (int y : index) {
                int rowX = x + row;
                int colY = y + col;
                if (isValidCell(rowX, colY) && (rowX!=row || colY!=col)) {
                    result.add(board[rowX][colY]);
                }
            }
        }
        return result;
    }


    private boolean isValidCell(int row, int col) {
        if (row < dimension && row >= 0 && col < dimension && col >= 0) {
            return true;
        }
        return false;
    }

	public ArrayList<Cell> getFreeCells() {
		return freeCells;
	}
	
	
}
