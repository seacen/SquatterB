package aiproj.squatter.hex1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;



/**
 * Xichangz player in game
 */
public class Xichangz implements Player, CellStatus {
	
	private int role;
	private int oppoRole;
	private Board board;
	private boolean minimax=true;	//indicates which algorithm to use
	private final int DEPTH=6;		//cut off depth of the minimax tree
    private int[][][] BOARD_HASH_ARRAY;
	
	public Xichangz(boolean value) {
		minimax=false;
	}
	
	public Xichangz() {
		minimax=true;
	}

	/**
	 * initialize player in standard approach
	 * @param n	board dimension
	 * @param p	player role
	 */
	public int init(int n, int p) {
		// TODO Auto-generated method stub
		role=p;
		if (p==BLACK) {
			oppoRole=WHITE;
		}
		else {
			oppoRole=BLACK;
		}
        BOARD_HASH_ARRAY = createBoardHashArray(n);
		board= new Board(n, BOARD_HASH_ARRAY);
		return 0;
	}
	
	/**
	 * initialize for test player, board built from stdin input
	 * @param n	board dimension
	 * @param p	player role
	 * @param input	stdin board input
	 */
	public int init(int n, int p, BufferedReader input) throws NumberFormatException, IOException {
		role=p;
		if (p==BLACK) {
			oppoRole=WHITE;
		}
		else {
			oppoRole=BLACK;
		}
        BOARD_HASH_ARRAY = createBoardHashArray(n);
        board= new Board(n,input, BOARD_HASH_ARRAY);
		return 0;
	}

	/**
	 * make a move
	 * @return a move
	 */
	public Move makeMove() {
		// TODO Auto-generated method stub
		Move move;
		
		if (minimax) {
			Intelligence intelligence = new MinimaxAlgorithm(this,board,DEPTH);
			move=intelligence.makeMove();
		}
		
		else {
			Intelligence intelligence = new RandomAlgorithm(this,board);
			move=intelligence.makeMove();
		}
		
		board.updateBoard(move);

		return move;
	}

	/**
	 * validate opponent's move
	 * @param m previous move by opponent
	 * @return INVALID(-1) if move is invalid, else 0
	 */
	public int opponentMove(Move m) {
		// TODO Auto-generated method stub
		
		
		if (m.P!=oppoRole) {
			return INVALID;
		}
		
		if (!board.updateBoard(m)) {
			return INVALID;
		}
		return 0;
	}

    public int[][][] getBoardHashArray() {
        return BOARD_HASH_ARRAY;
    }

    /**
	 * print a player's board
	 * @param output printStream type output
	 */
	public void printBoard(PrintStream output) {
		board.printBoard(output);
	}
	
	
	//getter and setters
	public int getWinner() {
		// TODO Auto-generated method stub
		return board.getWinner();
	}
	public int getRole() {
		return role;
	}

	public Board getBoard() {
		return board;
	}
	
	public int getOppoRole() {
		return oppoRole;
	}

    private int[][][] createBoardHashArray(int dimension) {
        int[][][] boardHashArray = new int[dimension][dimension][CellStatus.VALID_STATUS.size()];

        Random randomGenerator = new Random();
        for (int x = 0; x < dimension; x ++) {
            for (int y = 0; y < dimension; y ++) {
                for (int option = 0; option < CellStatus.VALID_STATUS.size(); option ++) {
                    boardHashArray[x][y][option] = randomGenerator.nextInt();
                }
            }
        }

        return boardHashArray;
    }
}
