package aiproj.squatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test implements CellStatus{

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		int n=6;
		int p=WHITE;
		Move m = new Move();
		Move prev;
		
		Xichangz player= new Xichangz();
		
		player.init(n, p);
		

		// TEST CONSTRUCTING BOARD FROM STDIN, TEST GETWINNER

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		player.init(n, p, br);

        System.out.println("player:" + player.getRole());
        System.out.println("free cell counts:" + player.getBoard().getFreeCellCount());
		System.out.println("winner:" + player.getWinner());
        System.out.println();

        player.printBoard(System.out);

        Move move = new Move();
        move.P = BLACK;
        move.Col = 4;
        move.Row = 1;

        player.opponentMove(move);

        System.out.println();

        player.printBoard(System.out);



        // remind to put below codes to comment


		
//		System.out.println(mapToChar.get(player.getRole()));
//
//		player.printBoard(System.out);
//		System.out.println();
//
//		player.makeMove();
//		prev=player.makeMove();
//		player.printBoard(System.out);
//		System.out.println();
		
		
		/*
		//TEST BOARD VALIDATE PERFORMANCE: ROW OVERBOARD
		
		m.P=WHITE;
		m.Row=n;
		m.Col=n-1;
		*/
		
		/*
		//TEST BOARD VALIDATE PERFORMANCE: NON FREE CELL MOVE
		
		m=prev;
		m.P=WHITE;
		*/
		
		/*
		//TEST BOARD VALIDATE PERFORMANCE: INVALID ROLE
		
		m.P=BLACK;
		m.Row=n-1;
		m.Col=n-1;
		*/
		
		
//		m.P=WHITE;
//		m.Row=n-1;
//		m.Col=n-1;
//
//		if (player.opponentMove(m)==-1) {
//			System.out.println("ERROR!!\n");
//		}
//
//
//		player.printBoard(System.out);
	}

}
