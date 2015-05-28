package aiproj.squatter.hex1;

import java.util.HashSet;

/**
 * 
 * minimax tree algorithm for determining the move
 *
 */
public class MinimaxAlgorithm extends Intelligence {
	
	private int depth;		//cut off depth of the tree
    private HashSet<Integer> explored;

	public MinimaxAlgorithm(Xichangz player, Board board, int depth) {
		super(player, board);
		this.depth = depth;
        explored = new HashSet<Integer>();
	}

	@Override
	/**
	 * make a move by selecting the max value of the current board successors
	 */
	public Move makeMove() {
		
		int level=0;
		
//		int freeSize=getBoard().getFreeCells().size();
//		
//		int allCells=getBoard().getDimension()*getBoard().getDimension();
//		
//		if (freeSize==allCells || freeSize==allCells-1) {
//			if (getBoard().getBoard()[0][1].getVal()==getMaster().getOppoRole()) {
//				setCellToUpdate(getBoard().getBoard()[0][2]);
//				return getCellToUpdate().cellToMove(getMaster().getRole());
//			}
//			else {
//				setCellToUpdate(getBoard().getBoard()[0][1]);
//				return getCellToUpdate().cellToMove(getMaster().getRole());
//			}
//		}
		
		maxValue(getBoard(),Integer.MIN_VALUE,Integer.MAX_VALUE,level);
		return getCellToUpdate().cellToMove(getMaster().getRole());
		
	}
	
	/**
	 * find the max value of the current board successors.
	 * @param board the current state in the tree
	 * @param alpha the best score for max along the path to state
	 * @param beta the best score for min along the path to state
	 * @param level current tree level
	 * @return the max value of current state
	 */
	private double maxValue(Board board, double alpha, double beta, int level) {
		
		level++;
		
		//if current state is in the cut off depth level or has no more successors
		if ((level==depth) || board.getFreeCells().size()==0) {
			
			HeZhaoSquatterAlgorithm evaluator = new HeZhaoSquatterAlgorithm(board,getMaster());
			
			double score=evaluator.evalFunction();
			
			return score;
		}
		
		int levelRole=getMaster().getRole();	//role to play in current tree level
		
		Cell maxCell= new Cell(board.getFreeCells().get(0));
		for (Cell cell : board.getFreeCells()) {
			
			Board newBoard=new Board(board);
			
			Move move=cell.cellToMove(levelRole);
			
			newBoard.updateBoard(move);
			
//			if (checkSymmetry(newBoard)) {
//				continue;
//			}
			System.out.println("BEGIN!!");
			newBoard.printBoard(System.out);
			double tmp=minValue(newBoard, alpha, beta,level);
			System.out.println(tmp);
			System.out.print("\n\n");
			if (alpha<tmp) {
				alpha=tmp;
				maxCell=cell;
				System.out.println("CHANGED!!\n");
			}
			
			//pruning
			if (alpha>=beta) {
				return beta;
			}
		}
		
		//set cellToUpdate to max value cell if at the top level
		if (level==1) {
			System.out.println("UPDATING!!");
			setCellToUpdate(maxCell);
		}
		return alpha;
	}
	
	/**
	 * find the min value of the current board successors.
	 * @param board the current state in the tree
	 * @param alpha the best score for max along the path to state
	 * @param beta the best score for min along the path to state
	 * @param level current tree level
	 * @return the min value of current state
	 */
	private double minValue(Board board, double alpha, double beta, int level) {
		
		level++;
		//if current state is in the cut off depth level or has no more successors
		if (level==depth || board.getFreeCells().size()==0) {
			
			HeZhaoSquatterAlgorithm evaluator = new HeZhaoSquatterAlgorithm(board,getMaster());
			
			double score=evaluator.evalFunction();
			
			return score;
		}
		
		int levelRole=getMaster().getOppoRole();	//role to play in current tree level
		
		for (Cell cell : board.getFreeCells()) {
			
			Board newBoard=new Board(board);
			Move move=cell.cellToMove(levelRole);
			
			newBoard.updateBoard(move);
			
//			if (checkSymmetry(newBoard)) {
//				continue;
//			}
			
			double tmp=maxValue(newBoard, alpha, beta,level);
			if (beta>tmp) {
				beta=tmp;
			}
			
			//pruning
			if (beta<=alpha) {
				return alpha;
			}
			
		}
		
//		System.out.print("\nEND!!\n");
		
		return beta;
	}

    /* Return true if a symmetric copy of the specified board has alread been explored.
     * Return false otherwise, and add its hash values to the explored list. */
	private boolean checkSymmetry(Board board) {
        int[] hash = board.getHash();
        if (isSymmetric(board))
            return true;
        else {
            for (int h : hash) {
                explored.add(h);
            }
            return false;
        }
	}

    /* Check if a symmetric copy of the specified board has alread been explored. */
    private boolean isSymmetric(Board board) {
        return explored.contains(board.getHash()[0]);
    }

}
