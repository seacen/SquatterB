package aiproj.squatter.hex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test implements CellStatus{

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		int n=4;
		int p=WHITE;
		
		Xichangz player= new Xichangz();


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		player.init(n, p, br);
		
		player.printBoard(System.out);
		
		Move move=player.makeMove();
		
		player.getBoard().updateBoard(move);
		
		player.getBoard().printBoard(System.out);

	}

}
