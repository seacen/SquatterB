package aiproj.squatter.hex1;
/**
 * 
 * features that need information about captured cell counts
 *
 */
public abstract class CapCount extends Feature {
	
	private int capturedCount;

	public CapCount(Board board, int role) {
		super(board, role);
		// TODO Auto-generated constructor stub
	}

	/**
	 * get role specific captured count
	 * @param board game board
	 * @return role specific captured count
	 */
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

	
	//getter and setters
	public int getCapturedCount() {
		return capturedCount;
	}

	public void setCapturedCount(int capturedCount) {
		this.capturedCount = capturedCount;
	}
	
	

}
