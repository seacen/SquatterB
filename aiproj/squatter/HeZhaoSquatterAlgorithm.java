package aiproj.squatter;

public class HeZhaoSquatterAlgorithm extends LinearEvaluator {
	
	private Xichangz master;

	public HeZhaoSquatterAlgorithm(Board board, Xichangz master) {
		super(board);
		this.master=master;
		setFeatures(board);
	}

	@Override
	public void setFeatures(Board board) {
		getFeatures()[0] = new OppoCapCount(board,master.getOppoRole());
		getFeatures()[1] = new OwnCapCount(board,master.getRole());
		getFeatures()[2] = new OppoSidePieceCount(board,master.getOppoRole());
		getFeatures()[3] = new OwnSidePieceCount(board,master.getRole());
//		getFeatures()[4] = new OppoStepToLoop(board,master.getOppoRole());
//		getFeatures()[5] = new OwnStepToLoop(board,master.getRole());
	}

}
