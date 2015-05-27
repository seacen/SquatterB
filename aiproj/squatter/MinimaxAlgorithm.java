package aiproj.squatter;

import java.util.ArrayList;
/**
 * 
 * minimax tree algorithm for determining the move
 *
 */
public class MinimaxAlgorithm extends Intelligence {
	
	private int depth;		//cut off depth of the tree

	public MinimaxAlgorithm(Xichangz player, Board board, int depth) {
		super(player, board);
		this.depth = depth;
	}

	@Override
	/**
	 * make a move by selecting the max value of the current board successors
	 */
	public Move makeMove() {
		
		int level=0;
		
		setCellToUpdate(getBoard().getFreeCells().get(0));
		
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
		if ((level>=depth) || board.getFreeCells().size()==0) {
			
			HeZhaoSquatterAlgorithm evaluator = new HeZhaoSquatterAlgorithm(board,getMaster());
			
			return evaluator.evalFunction();
		}
		
		ArrayList<Board> successors=getSuccessors(board,level);
		
		int i=0,maxi=0;
		for (Board successor : successors) {
			double tmp=minValue(successor, alpha, beta,level);
			if (alpha<tmp) {
				alpha=tmp;
				maxi=i;
			}
			
			//pruning
			if (alpha>=beta) {
				return beta;
			}
			i++;
		}
		
		//set cellToUpdate to max value cell if at the top level
		if (level==1) {
			setCellToUpdate(board.getFreeCells().get(maxi));
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
			
			return evaluator.evalFunction();
		}
		
		ArrayList<Board> successors=getSuccessors(board,level);
		
		for (Board successor : successors) {
			double tmp=maxValue(successor, alpha, beta,level);
			if (beta>tmp) {
				beta=tmp;
			}
			
			//pruning
			if (beta<=alpha) {
				return alpha;
			}
		}
		
		return beta;
	}
	
	/**
	 * get all successors of the given board
	 * @param board current state
	 * @param level current tree level for determining which 
	 * piece color to put on board
	 * @return a list of boards that are successors of parameter board
	 */
	private ArrayList<Board> getSuccessors(Board board,int level) {
		
		int levelRole;
		ArrayList<Cell> freeCells = board.getFreeCells();
		
		ArrayList<Board> successors = new ArrayList<Board>(freeCells.size());
		
		//opponent makes the next move if current level is even, 
		//else own turn to play
		if (level%2==0) {
			levelRole=getMaster().getOppoRole();
		}
		else {
			levelRole=getMaster().getRole();
		}
		
		for (Cell cell : freeCells) {
			
			if (isSymmetry()) {
				continue;
			}

			Board newBoard=new Board(board);
			
			Move move=cell.cellToMove(levelRole);
			newBoard.updateBoard(move);
			
			successors.add(newBoard);
			
		}
		
		return successors;
	}
	
	private boolean isSymmetry() {
		
		return false;
	}

}
