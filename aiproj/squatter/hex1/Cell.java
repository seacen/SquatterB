package aiproj.squatter.hex1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hexin on 23/05/15.
 */
public class Cell implements CellStatus {
    private int val;
    private int row, col;
    private boolean checked;
    
	public Move cellToMove(int role) {
		
		Move move = new Move();
		
		move.Row=row;
		move.Col=col;
		move.P=role;
		
		return move;
	}

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.val = EMPTY;
        this.checked = false;
    }
    
    public Cell(Cell cell) {
        this.row = cell.getRow();
        this.col = cell.getCol();
        this.val = cell.getVal();
        this.checked = cell.isChecked();
    }
    
    public Cell(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
        this.checked = false;
    }

    /* Return true if the cell has a piece with the provided color. */
    public boolean matchColor(int color) {
        if (val == color) {
            return true;
        }

        Set<Integer> set = new HashSet<Integer>();
        set.add(val);
        set.add(color);

        if ((set.contains(WHITE) && set.contains(CAPWHITE)) || (set.contains(BLACK) && set.contains(CAPBLACK)) ||
                (set.contains(EMPTY) && set.contains(CAPEMPTY))) {
            return true;
        }
        return false;
    }

    public void setCaptured(int loopColor) {
        if (val == EMPTY) val = CAPEMPTY;

        if (loopColor == WHITE) {
            if (val == CAPWHITE) val = WHITE;
            else if (val == BLACK) val = CAPBLACK;
        }

        else if (loopColor == BLACK) {
            if (val == CAPBLACK) val = BLACK;
            else if (val == WHITE) val = CAPWHITE;
        }
    }

    public void setFreed() {
        if (val == CAPWHITE) val = WHITE;
        else if (val == CAPBLACK) val = BLACK;
    }

    public boolean isEmpty() {
        return val == EMPTY;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "val=" + val +
                ", row=" + row +
                ", col=" + col +
                ", checked=" + checked +
                '}';
    }
    
}
