package aiproj.squatter;
/**
 * 
 * features that need information about how many potential loops
 * that are one step away from forming.
 *
 */
public abstract class StepToLoop extends CapCount {
	
	public StepToLoop(Board board, int role) {
		super(board, role);
		// TODO Auto-generated constructor stub
	}

	@Override

	public void setFeature(Board board) {
		
		//get current captured count of the board
		setCapturedCount(getCaptured(board));
		
		int maxCount=Integer.MIN_VALUE;
		
		int tmpCount;
		
		for (Cell cell : board.getFreeCells()) {
			Board newBoard = new Board(board);
			
			Move move=cell.cellToMove(getRole());
			newBoard.updateBoard(move);
			
			//get captured count of new board
			tmpCount=getCaptured(newBoard);
			
			if (tmpCount>maxCount) {
				maxCount=tmpCount;
			}
		}
		
		//calculate the diference of the max capturedcount of 
		//new boards(plus one move) and original captured count
		//this result is further divided by the board size to get a proportion of captured.
		setValue((double)(maxCount-getCapturedCount())/(board.getDimension()*board.getDimension()));

	}

}
