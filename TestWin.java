/*
* name: Xichang Zhao
* login_id: xichangz
*
* name: Xin He
* login_id: hex1
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Test if a player has won and count number of captured cells for each player for a given input
 * 
 * @author Xichang Zhao
 */
public class TestWin {

	/** test and print the output 
	 * @param input		a bufferedReader type input containing the board state and structure
	 * */
	public static void test(BufferedReader input) {
		
		String winPlayer="None";
		int dimension,blackCaptured=0,whiteCaptured=0;
		
		// if the game is won by players
		boolean winState=true;
		
		try {
			
			//read the dimension of the board for future iteration counts
			dimension = Integer.parseInt(input.readLine());
			
			for (int i=0;i<dimension;i++) {
				
				String line;
				
				//if all lines have been read when there should still be lines expecting
				if ((line=input.readLine())==null) {
					System.out.println("input is invalid, number of lines is less than the dimension number");
					System.exit(0);
				}
				
				/*the valid line length should be the number of cells suggested by the dimension(d)
				plus the number of white spaces in between(d-1)*/
				if (line.length()!=2*dimension-1) {
					System.out.println("input is invalid, line length does not match with the dimension number.");
					System.exit(0);
				}
				
				//an integer indicates the current number of unclaimed captured cells
				int capturedCount=0;
				
				//iterate through each of the cells in a row
				for (int x=0;x<dimension;x++) {
					char c=line.charAt(x*2);
					if (c=='-') {
						capturedCount++;
					}
					else if (c=='+') {
						winState=false;
					}
					else if (c=='B' || c=='W'){
						if (c=='B') {
							blackCaptured+=capturedCount;
						}
						else {
							whiteCaptured+=capturedCount;
						}
						capturedCount=0;
					}
					else if(c==' ') {
						System.out.println("input is invalid, white spaces are not arranged correctly");
						System.exit(0);
					}
					else {
						System.out.println("input is invalid, no characters other than 'B','W','-','+' are allowed.");
						System.exit(0);
					}
					//check if the character next to the cell is a white space
					if (x!=(dimension-1) && line.charAt(x*2+1)!=' ') {
						System.out.println("input is invalid, a character is read when it should be a white space.");
						System.exit(0);
					}
				}
			}
			
			//if there is still more lines in input when dimension number of lines have already been read
			if (input.readLine()!=null) {
				System.out.println("input is invalid, number of lines is greater than the dimension number");
				System.exit(0);
			}
			
			//if no free cell has been detected
			if (winState) {
				if (blackCaptured>whiteCaptured) {
					winPlayer="Black";
				}
				else if (blackCaptured<whiteCaptured) {
					winPlayer="White";
				}
				else {
					winPlayer="Draw";
				}
			}
			
			System.out.println(winPlayer);
			System.out.println(whiteCaptured);
			System.out.println(blackCaptured);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("first line input is incorrect, should be a number indicating the dimension.");
            System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No input is passed.");
            System.exit(0);
		} catch (Exception e) {
			System.out.println("input is invalid.");
            System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		TestWin.test(br);
	}

}
