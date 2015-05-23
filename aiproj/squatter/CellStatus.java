package aiproj.squatter;
/*
 *   Piece:
 *      Define types of states that can appear on a board
 *      
 *   @author lrashidi
 *   
 */

public interface CellStatus {
    public static final int WHITE = 1, 
                            BLACK = 2,
                            CAPWHITE = 3,
                            CAPBLACK = 4,
                            CAPEMPTY = 5,
                            EMPTY = 0,
    		                INVALID = -1;
}
