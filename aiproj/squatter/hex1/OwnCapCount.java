package aiproj.squatter.hex1;

public class OwnCapCount extends CapCount {

	public OwnCapCount(Board board, int role) {
		super(board, role);
		// TODO Auto-generated constructor stub
	}
	
	public void setFeature(Board board) {
		
		setCapturedCount(getCaptured(board));
		
		setValue((double)getCapturedCount()/(board.getDimension()*board.getDimension()));
		
		setWeight(100);
	}

}
