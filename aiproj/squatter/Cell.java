package aiproj.squatter;

/**
 * Created by hexin on 23/05/15.
 */
public class Cell implements Piece {
    private int val;
    private int row, col;
    private boolean checked;

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
    
    public Cell(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
        this.checked = false;
    }

    @Override
    public String toString() {
        return "BoardCell{" +
                "val=" + val +
                ", row=" + row +
                ", col=" + col +
                ", checked=" + checked +
                '}';
    }
}
