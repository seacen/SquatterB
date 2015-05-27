package aiproj.squatter;
/**
 * 
 * evaluator for generating minimax value
 *
 */
public abstract class LinearEvaluator {
	
	private final int featureNum=4;
	
	private Feature[] features;
	
	public LinearEvaluator(Board board) {
		features = new Feature[featureNum];
	}
	
	public abstract void setFeatures(Board board);

	/**
	 * Linear evaluation function which adds up all products 
	 * of features times their associated weights.
	 * @return sum of the products
	 */
	public final double evalFunction() {
		
		double sum=0;
		
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
