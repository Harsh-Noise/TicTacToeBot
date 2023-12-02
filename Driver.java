package TTT;

public class Driver {
	public static void main(String[] args) {
		GameTree TTT = new GameTree();
		
		GameBoard board = new GameBoard();
		
		TTT.addNodes(board);
		
		TTT.getScores();
		
		TTT.investigate();
	}
}
