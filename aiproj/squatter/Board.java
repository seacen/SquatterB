package aiproj.squatter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board implements CellStatus{
	
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
					board[i][x]=new Cell(i,x,CAPEMPTY);
				}
				else if (c=='b') {
					board[i][x]=new Cell(i,x,CAPBLACK);
				}
				else if (c=='w') {
					board[i][x]=new Cell(i,x,CAPWHITE);
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
		
//		checkLoop(move);
		
		return true;
	}

    /* Check if there is any loop formed by the move made, and update the board accordingly.
     * Return false if no loop is found, true otherwise. */
    public boolean checkLoop(Move move) {
        List<Cell> adjCells = adjacentCells(move.Row, move.Col);
        if (numEmptyCells(adjCells) < 2) {
            return false;
        }

        for (Cell c : adjCells) {
//            floodFill(c, move.P); remember to check the condition for exploring cell.
        }
    }

    /* Explore the specified cell using flood fill method.
     * Mark a cell as checked if its status has already been determined.
     * If it is captured, mark all cells in its capture cells as captured, and return true.
     * If it is not captured, return false. */
    private boolean floodFill(Cell c, int loopColor) {
        // All cells in the below two lists are not of loopColor.
        // Every non-loopColor cell will be explored
        // They will all be marked as captured if all cells are explored and none of them is a border cell.
        List<Cell> innerCells = new ArrayList<Cell>();
        List<Cell> exploring = new ArrayList<Cell>();
        exploring.add(c);
        while (!exploring.isEmpty()) {
            Cell current = exploring.remove(0);
            current.setChecked(true);

            if (isBorderCell(current)) {
                return false;
            }

            List<Cell> adjCells = new ArrayList<Cell>();
            adjCells = adjacentCells(c.getRow(), c.getCol());

            for (Cell adjCell : adjCells) {
                if (!adjCell.matchColor(loopColor)) {
                    exploring.add(adjCell);
                }
            }
        }

    }

    /* Explore the specified cell.
     * Add its adjacent non-loop cells to exploringList.
     * Return false if it is a border cell. */
    private boolean exploreCell(cell c, int loopColor, List<Cell> innerCellsList, List<Cell> exploringList) {

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
            if (c.val == EMPTY) {
                num++;
            }
        }
        return num;
    }


    private List<Cell> adjacentCells(int row, int col) {
        int index[] = {-1, 0 ,1};
        List<Cell> result = new ArrayList<Cell>();
        for (int x : index) {
            for (int y : index) {
                int rowX = x + row;
                int colY = y + col;
                if (isValidCell(rowX, colY) && (rowX!=0 || colY!=0)) {
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
