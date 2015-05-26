package aiproj.squatter;

public class OppoCapCount extends CapCount {

	public OppoCapCount(Board board, int role) {
		super(board, role);
		// TODO Auto-generated constructor stub
	}
	
	public void setFeature(Board board) {
		
		setCapturedCount(getCaptured(board));
		
		setValue((double)getCapturedCount()/(board.getDimension()*board.getDimension()));
		
		setWeight(-100);
	}

}
