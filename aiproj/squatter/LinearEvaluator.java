package aiproj.squatter;

public abstract class LinearEvaluator {
	
	private final int featureNum=5;
	
	private Feature[] features;
	
	public LinearEvaluator(Board board) {
		features = new Feature[featureNum];
		setParameters(board);
	}
	
	public abstract void setParameters(Board board);

	public int evalFunction() {
		
		int sum=0;
		
		for (int i=0;i<features.length;i++) {
			sum+=(features[i].getValue()*features[i].getWeight());
		}
		
		return sum;
		
	}
	
	//getter
	public Feature[] getFeatures() {
		return features;
	}

}
