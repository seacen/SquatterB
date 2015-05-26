package aiproj.squatter;

import java.util.ArrayList;

public class MinimaxAlgorithm extends Intelligence {
	
	private int depth;

	public MinimaxAlgorithm(Xichangz player, Board board, int depth) {
		super(player, board);
		this.depth = depth;
	}

	@Override
	public Move makeMove() {
		
		int level=0;
		
		setCellToUpdate(getBoard().getFreeCells().get(0));
		
		maxValue(getBoard(),Integer.MIN_VALUE,Integer.MAX_VALUE,level);
		
		return getCellToUpdate().cellToMove(getMaster().getRole());
		
		
	}
	
	private double maxValue(Board board, double alpha, double beta, int level) {
		
		level++;
		
		if ((level>=depth) || board.getFreeCells().size()==0) {
			
			HeZhaoSquatterAlgorithm evaluator = new HeZhaoSquatterAlgorithm(board,getMaster());
			
			return evaluator.evalFunction();
		}
		
		ArrayList<Board> successors=getSuccessors(board,level);
		
		int i=0;
		for (Board successor : successors) {
			double tmp=minValue(successor, alpha, beta,level);
			if (alpha<tmp) {
				alpha=tmp;
				if (level==1) {
					setCellToUpdate(board.getFreeCells().get(i));
				}
			}
			if (alpha>=beta) {
				return beta;
			}
			i++;
		}
		
		return alpha;
	}
	
	private double minValue(Board board, double alpha, double beta, int level) {
		
		level++;
		
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
			if (beta<=alpha) {
				return alpha;
			}
		}
		
		return beta;
	}
	
	private ArrayList<Board> getSuccessors(Board board,int level) {
		
		int levelRole;
		ArrayList<Cell> freeCells = board.getFreeCells();
		
		ArrayList<Board> successors = new ArrayList<Board>(freeCells.size());
		
		if (level%2==0) {
			levelRole=getMaster().getOppoRole();
		}
		else {
			levelRole=getMaster().getRole();
		}
		
		ArrayList<Cell> checkedCells = new ArrayList<Cell>(freeCells.size());
		
		for (Cell cell : freeCells) {
			
			if (isSymmetry(cell,checkedCells)) {
				continue;
			}
			
			checkedCells.add(cell);
			Board newBoard=new Board(board);
			
			Move move=cell.cellToMove(levelRole);
			newBoard.updateBoard(move);
			
			successors.add(newBoard);
			
		}
		
		return successors;
	}
	
	private boolean isSymmetry(Cell cell, ArrayList<Cell> CheckedCells) {
		
		return false;
	}

}
