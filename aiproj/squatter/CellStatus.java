package aiproj.squatter;
/*
 *   Piece:
 *      Define types of states that can appear on a board
 *      
 *   @author lrashidi
 *   
 */

public interface CellStatus extends Piece {
    public static final int CAPWHITE = 3,
                            CAPBLACK = 4,
                            CAPEMPTY = 5;
}
