package aiproj.squatter.hex1;

/**
 * 
 * feature object used in evaluators
 *
 */
public abstract class Feature implements Piece{
	
	private int role;		//perspective of side for the feature
	private double value,weight;

	public Feature(Board board,int role) {
		this.role=role;
		setFeature(board);
	}

	public abstract void setFeature(Board board);
	
	
	//getter and setters
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	

}
