package aiproj.squatter;

public abstract class StepToLoop extends CapCount {
	
	public StepToLoop(Board board, int role) {
		super(board, role);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setFeature(Board board) {
		
		setCapturedCount(getCaptured(board));
		
		int maxCount=Integer.MIN_VALUE;
		
		int tmpCount;
		
		for (Cell cell : board.getFreeCells()) {
			Board newBoard = new Board(board);
			
			Move move=cell.cellToMove(getRole());
			newBoard.updateBoard(move);
			
			tmpCount=getCaptured(newBoard);
			
			if (tmpCount>maxCount) {
				maxCount=tmpCount;
			}
		}
		
		setValue((double)(maxCount-getCapturedCount())/(board.getDimension()*board.getDimension()));

	}

}
