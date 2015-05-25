package aiproj.squatter;

public abstract class Feature implements Piece{
	
	private int value,weight;

	public Feature(Board board) {
		setFeature(board);
	}

	public abstract void setFeature(Board board);
	
	
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	

}
