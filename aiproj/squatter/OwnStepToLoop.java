package aiproj.squatter;

public class OwnStepToLoop extends StepToLoop {

	public OwnStepToLoop(Board board, int role) {
		super(board, role);
		setWeight(100);
	}

}
