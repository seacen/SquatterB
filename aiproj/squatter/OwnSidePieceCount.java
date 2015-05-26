package aiproj.squatter;

public class OwnSidePieceCount extends SidePieceCount {

	public OwnSidePieceCount(Board board, int role) {
		super(board, role);
		setWeight(10);
	}

}
