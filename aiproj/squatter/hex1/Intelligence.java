package aiproj.squatter.hex1;

/**
 * Intelligence for playing the game.
 *
 */
public abstract class Intelligence implements Piece{
	
	private Xichangz master;
	private Board board;
	private Cell cellToUpdate;

	public Intelligence(Xichangz player, Board board) {
		master = player;
		this.board=board;
	}

	public abstract Move makeMove();
	
	
	//getter and setters
	public Xichangz getMaster() {
		return master;
	}
	public void setMaster(Xichangz master) {
		this.master = master;
	}
	public Cell getCellToUpdate() {
		return cellToUpdate;
	}
	public void setCellToUpdate(Cell cellToUpdate) {
		this.cellToUpdate = cellToUpdate;
	}
	public Board getBoard() {
		return board;
	}

}



