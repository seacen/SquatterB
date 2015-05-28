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

//            for (Cell c : newBoard.getFreeCells()) {
//                System.out.println("Cell: " + c.getVal() + " " + c.getRow() + " " + c.getCol());
//            }

            System.out.println("Max Function Call:");
            if (checkSymmetry(newBoard)) {
				continue;
			}
//			System.out.println("BEGIN!!");
//			newBoard.printBoard(System.out);
			double tmp=minValue(newBoard, alpha, beta,level);
//			System.out.println(tmp);
//			System.out.print("\n\n");
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

        System.out.println("input Board in Min");
        board.printBoard(System.out);
        for (Cell cell : board.getFreeCells()) {
            System.out.println("Cell: " + cell.getVal() + " " + cell.getRow() + " " + cell.getCol());
        }
        System.out.println("End of free cell printing");

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

            System.out.println("newBoard from Min: ");
            newBoard.printBoard(System.out);

            Move move=cell.cellToMove(levelRole);
            newBoard.updateBoard(move);

            System.out.println("Move updated: " + move.P + " " + move.Row + " " + move.Col);
            System.out.println("newBoard from Min after move: ");
            newBoard.printBoard(System.out);

            System.out.println("Min Function Call:");
            if (checkSymmetry(newBoard)) {
				continue;
			}
			
			double tmp=maxValue(newBoard, alpha, beta,level);
			if (beta>tmp) {
				beta=tmp;
			}
			
			//pruning
			if (beta<=alpha) {
				return alpha;
			}
			
		}
		
		System.out.print("\nEND!!\n");
		
		return beta;
	}

    /* Return true if a symmetric copy of the specified board has alread been explored.
     * Return false otherwise, and add its hash values to the explored list. */
	private boolean checkSymmetry(Board board) {
        // Test Code Below
        System.out.println("Symmetry Checking\nExplored: ");
//        for (int i : explored) {
//            System.out.print(i + " ");
//        }
        System.out.println();
        board.printBoard(System.out);
        System.out.println("Current hash are: ");
        for (int i : board.getHash()) {
            System.out.print(i + " ");
        }
        // End of Test code


        int[] hash = board.getHash();
        if (isSymmetric(board)) {
            System.out.println("Skipping this board\nEnd of Symmetry Checking\n\n\n");
            return true;
        }
        else {
            System.out.println("Board hash is added to exploredList");
            for (int h : hash) {
                explored.add(h);
            }
//            for (int i : explored) {
//                System.out.print(i + " ");
//            }
            System.out.println("End of Symmetry Checking\n\n\n");
            return false;
        }
	}

    /* Check if a symmetric copy of the specified board has alread been explored. */
    private boolean isSymmetric(Board board) {
        return explored.contains(board.getHash()[0]);
    }

}
