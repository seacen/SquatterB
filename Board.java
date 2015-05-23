import aiproj.squatter.Piece;


public class Board implements Piece{
	
	private int[][] board;
	
	public Board(int n) {
		// TODO Auto-generated constructor stub
		board=new int[n][n];
		for (int i=0;i<n;i++) {
			for (int x=0;x<n;x++) {
				board[i][x]=EMPTY;
			}
		}
	}
	

}
