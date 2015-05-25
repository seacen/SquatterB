package aiproj.squatter;

public class HeZhaoSquatterAlgorithm extends LinearEvaluator {

	public HeZhaoSquatterAlgorithm(Board board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setParameters(Board board) {
		getFeatures()[0] = new OppoCapCount(board);
		getFeatures()[1] = new OwnCapCount(board);
		getFeatures()[2] = new OppoStepsToLoop(board);
		getFeatures()[3] = new OwnStepsToLoop(board);
		getFeatures()[4] = new SidePieces(board);
	}

}
