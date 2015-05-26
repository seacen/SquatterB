package aiproj.squatter;

public abstract class CapCount extends Feature {
	
	private int capturedCount;

	public CapCount(Board board, int role) {
		super(board, role);
		// TODO Auto-generated constructor stub
	}

	public int getCaptured(Board board) {
		
		int[] capturedCounts = new int[board.getDimension()];
		
		board.calculateCaptured(capturedCounts);
		
		if (getRole()==BLACK) {
			return capturedCounts[0];
		}
		else {
			return capturedCounts[1];
		}
	}

	public int getCapturedCount() {
		return capturedCount;
	}

	public void setCapturedCount(int capturedCount) {
		this.capturedCount = capturedCount;
	}
	
	

}
