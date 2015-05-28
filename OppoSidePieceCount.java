package aiproj.squatter.hex1;

public class OppoSidePieceCount extends SidePieceCount {

	public OppoSidePieceCount(Board board, int role) {
		super(board, role);
		setWeight(-10);
	}

}
