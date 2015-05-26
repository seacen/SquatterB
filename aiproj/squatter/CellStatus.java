package aiproj.squatter;

import java.util.*;

/*
 *   Piece:
 *      Define types of states that can appear on a board
 *      
 *   @author lrashidi
 *   
 */

public interface CellStatus extends Piece {
    public static final int CAPWHITE = 4,
                            CAPBLACK = 5,
                            CAPEMPTY = 6;

    public static final List<Integer> VALID_STATUS = Arrays.asList(WHITE, BLACK, EMPTY, CAPWHITE, CAPBLACK, CAPEMPTY);

    public static final Map<Integer, Character> mapToChar = 
    		Collections.unmodifiableMap(
    				new HashMap<Integer, Character>(6) {
						private static final long serialVersionUID = 1L;

						{
    						put(BLACK,'B');
    						put(WHITE,'W');
    						put(EMPTY,'+');
    						put(CAPBLACK,'b');
    						put(CAPWHITE,'w');
    						put(CAPEMPTY,'-');
    					}
    				});
}
