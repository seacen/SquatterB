package aiproj.squatter;

import java.util.ArrayList;
import java.util.Random;

public class RandomAlgorithm extends Intelligence {

	public RandomAlgorithm(Xichangz player, Board board) {
		super(player, board);
		// TODO Auto-generated constructor stub
	}

	/**
	 * an moving algorithm for determining row,column values of a move to make 
	 */
	public Move makeMove() {
		
		ArrayList<Cell> freeCells=getBoard().getFreeCells();
		
		int max=freeCells.size();
		int min=0;
		
		setCellToUpdate(freeCells.get(randInt(min,max)));
		
		return getCellToUpdate().cellToMove(getMaster().getRole());
		
	}
	
	/**
	 * generate a random number within range min,max
	 * @param min
	 * @param max
	 * @return integer generated
	 */
	private static int randInt(int min, int max) {
		// Attribute to Greg Case from stack overflow
		
	    Random rand = new Random();

	    int randomNum = rand.nextInt(max - min) + min;

	    return randomNum;
	}

}
