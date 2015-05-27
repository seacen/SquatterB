package aiproj.squatter.hex1;

public class AngleCount extends Feature {
	
	private int count;

	public AngleCount(Board board, int role) {
		super(board, role);
		count=0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setFeature(Board board) {
		
		if (board.getBoard()[0][0].getVal()==getRole()) {
			count++;
		}
		if (board.getBoard()[0][board.getDimension()-1].getVal()==getRole()) {
			count++;
		}
		if (board.getBoard()[board.getDimension()-1][board.getDimension()-1].getVal()==getRole()) {
			count++;
		}
		if (board.getBoard()[board.getDimension()-1][0].getVal()==getRole()) {
			count++;
		}
		
		setValue(count);
		setWeight(-2000);
	}

}
