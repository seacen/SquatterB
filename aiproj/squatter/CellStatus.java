package aiproj.squatter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
