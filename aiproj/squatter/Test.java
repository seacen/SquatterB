package aiproj.squatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test implements CellStatus{

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		int n=2;
		int p=WHITE;
		Move m = new Move();
		Move prev;
		
		Xichangz player= new Xichangz();
//		
//		player.init(n, p);
		

		// TEST CONSTRUCTING BOARD FROM STDIN, TEST GETWINNER


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		player.init(n, p, br);

        Board board1 = player.getBoard();
        board1.printBoard(System.out);
        int[] hash1 = board1.getHash();
        for (int i : hash1) {
            System.out.print(i + " ");
        }

//        System.out.println(board1.getHash()[0] + " " + board1.getHash()[1]);

//        br.close();

//        BufferedReader newBR = new BufferedReader(new InputStreamReader(System.in));
        Board board2 = new Board(board1.getDimension() ,br, player.getBoardHashArray());
        board2.printBoard(System.out);
//        System.out.println(board2.getHash()[0] + " " + board2.getHash()[1]);

        int[] hash2 = board2.getHash();
        for (int i : hash2) {
            System.out.print(i + " ");
        }


//        System.out.println("player:" + player.getRole());
//		System.out.println("winner:" + player.getWinner());
//        System.out.println();
//
//        player.printBoard(System.out);
//
//        Move move = new Move();
//        move.P = BLACK;
//        move.Col = 4;
//        move.Row = 1;
//
//
//        System.out.println();
//
//        player.opponentMove(move);
//        player.printBoard(System.out);




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
		
//		System.out.println(2^2);
	}

}
