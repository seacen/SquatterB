package aiproj.squatter;

import java.util.ArrayList;

public class MinimaxAlgorithm extends Intelligence {
	
	private int depth,level;

	public MinimaxAlgorithm(Xichangz player, Board board, int depth) {
		super(player, board);
		this.depth = depth;
		this.level = 0;
	}

	@Override
	public Move makeMove() {
		
		setCellToUpdate(getBoard().getFreeCells().get(0));
		
		maxValue(getBoard(),Integer.MIN_VALUE,Integer.MAX_VALUE);
		
		return cellToMove(getCellToUpdate());
		
		
	}
	
	private int maxValue(Board board, int alpha, int beta) {
		
		level++;
		
		if ((level>=depth) || board.getFreeCells().size()==1) {
			
			HeZhaoSquatterAlgorithm evaluator = new HeZhaoSquatterAlgorithm(board);
			
			return evaluator.evalFunction();
		}
		
		ArrayList<Board> successors=getSuccessors(board);
		
		int i=0;
		for (Board successor : successors) {
			int tmp=minValue(successor, alpha, beta);
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
	
	private int minValue(Board board, int alpha, int beta) {
		
		level++;
		
		if (level==depth) {
			return evaluate(board);
		}
		
		ArrayList<Board> successors=getSuccessors(board);
		
		for (Board successor : successors) {
			int tmp=maxValue(successor, alpha, beta);
			if (beta>tmp) {
				beta=tmp;
			}
			if (beta<=alpha) {
				return alpha;
			}
		}
		
		return beta;
	}
	
	private int evaluate(Board board) {
		
		
		
		return 0;
	}
	
	private ArrayList<Board> getSuccessors(Board board) {
		
		int levelRole;
		ArrayList<Cell> freeCells = board.getFreeCells();
		
		ArrayList<Board> successors = new ArrayList<Board>(freeCells.size());
		
		if (level%2==0) {
			levelRole=getMaster().getRole();
		}
		else {
			levelRole=getMaster().getOppoRole();
		}
		
		ArrayList<Cell> checkedCells = new ArrayList<Cell>(freeCells.size());
		
		for (Cell cell : freeCells) {
			
			if (!isSymmetry(cell,checkedCells)) {
				continue;
			}
			
			checkedCells.add(cell);
			Board newBoard=board;
			
			Move move=cellToMove(cell);
			move.P=levelRole;
			newBoard.updateBoard(move);
			
			successors.add(newBoard);
			
		}
		
		return successors;
	}
	
	private boolean isSymmetry(Cell cell, ArrayList<Cell> CheckedCells) {
		
		return true;
	}

}
