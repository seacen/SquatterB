package aiproj.squatter;

public abstract class SidePieceCount extends Feature {
	
	private int count=0;

	public SidePieceCount(Board board, int role) {
		super(board, role);
		// TODO Auto-generated constructor stub
	}
	
	public void setFeature(Board board) {
		for (int i=1;i<(board.getDimension()-1);i++) {
			if (board.getBoard()[i][0].getVal()==getRole()) {
				count++;
			}
			if (board.getBoard()[i][board.getDimension()-1].getVal()==getRole()) {
				count++;
			}
		}
		for (int i=0;i<board.getDimension();i++) {
			
			if (board.getBoard()[0][i].getVal()==getRole()) {
				count++;
			}
			if (board.getBoard()[board.getDimension()-1][i].getVal()==getRole()) {
				count++;
			}
		}
		
		setValue((double)count/(board.getDimension()*4-4));
	}

}
