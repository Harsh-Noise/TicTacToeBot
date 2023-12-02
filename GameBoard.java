package TTT;

public class GameBoard {
	int[][] board = new int[3][3];
	boolean isX = true;
	
	public GameBoard() {
		////System.out.println("Setting");
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				board[x][y] = 0;
			}
		}
	}
	
	public GameBoard(GameBoard boardCopy) {
		////System.out.println("Setting");
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				board[x][y] = boardCopy.board[x][y];
			}
		}
	    isX = boardCopy.isX; // Copy the boolean field
	}
	
	public boolean test(int x, int y) {
		return(board[x][y] == 0);
	}
	
	public void play(int x, int y) {
		if(isX) {
			isX = false;
			board[x][y] = 1;
		}else {
			isX = true;
			board[x][y] = 2;
		}
	}
	
	public void print() {
		for(int x = 2; x >= 0; x--) {
			for(int y = 0; y < 3; y++) {
				switch(board[y][x]) {
					case 0:
						System.out.print(".|");
						break;
					case 1:
						System.out.print("X|");
						break;
					case 2:
						System.out.print("O|");
						break;
				}
			}
			System.out.println("\n------");
		}
	}
	
	public void printOne() {
		for(int x = 0; x < 3; x++) {
			switch(board[x][2]) {
				case 0:
					System.out.print(".");
					break;
				case 1:
					System.out.print("X");
					break;
				case 2:
					System.out.print("O");
					break;
			}
		}
	}
	
	public void printTwo() {
		for(int x = 0; x < 3; x++) {
			switch(board[x][1]) {
				case 0:
					System.out.print(".");
					break;
				case 1:
					System.out.print("X");
					break;
				case 2:
					System.out.print("O");
					break;
			}
		}
	}
	
	public void printThree() {
		for(int x = 0; x < 3; x++) {
			switch(board[x][0]) {
				case 0:
					System.out.print(".");
					break;
				case 1:
					System.out.print("X");
					break;
				case 2:
					System.out.print("O");
					break;
			}
		}
	}
	
	public boolean isWin() {
		boolean temp = false;
		
		for(int a = 0; a < 3; a ++) {
			for(int b = 0; b < 3; b ++) {
				
				//skip middle square because it will always be false
				if(a == 1 && b == 1) {
					continue;
				}
				
				//if reference square is unplayed, skip because it will be false
				int ab = board[a][b];
				if(ab == 0) {
					continue;
				}
				
				//Determine direction which you check for a win
				for(int c = -1; c < 2; c++) {
					for(int d = -1; d < 2; d++) {
						
						//skip 0 0 because pointer won't move
						if(c == 0 && d == 0) {
							continue;
						}
						
						//break if out of bounds
						if((a + (1*c)) < 0 || (b + (1*d)) < 0 || (a + (1*c)) > 2 || (b + (1*d)) > 2) {
							continue;
						}
						
						//check the first number in a row
						if(board[a + (1*c)][b + (1*d)] == ab) {
							
							//break if out of bounds
							if((a + (2*c)) < 0 || (b + (2*d)) < 0 || (a + (2*c)) > 2 || (b + (2*d)) > 2) {
								continue;
							}
							
							//check the second number in a row
							if(board[a + (2*c)][b + (2*d)] == ab) {
								temp = true;
							}
						}
					}
				}
			}
		}
		return(temp);
	}
	
	public int winTeam() {
		for(int a = 0; a < 3; a ++) {
			for(int b = 0; b < 3; b ++) {
				
				//skip middle square because it will always be false
				if(a == 1 && b == 1) {
					continue;
				}
				
				//if reference square is unplayed, skip because it will be false
				if(board[a][b] == 0) {
					continue;
				}
				
				//Determine direction which you check for a win
				for(int c = -1; c < 2; c++) {
					for(int d = -1; d < 2; d++) {
						
						//skip 0 0 because pointer won't move
						if(c == 0 && d == 0) {
							continue;
						}
						
						//break if out of bounds
						if((a + (1*c)) < 0 || (b + (1*d)) < 0 || (a + (1*c)) > 2 || (b + (1*d)) > 2) {
							continue;
						}
						
						//check the first number in a row
						if(board[a + (1*c)][b + (1*d)] == board[a][b]) {
							
							//break if out of bounds
							if((a + (2*c)) < 0 || (b + (2*d)) < 0 || (a + (2*c)) > 2 || (b + (2*d)) > 2) {
								continue;
							}
							
							//check the second number in a row
							if(board[a + (2*c)][b + (2*d)] == board[a][b]) {
								return(board[a][b]);
							}
						}
					}
				}
			}
		}
		return(-1);
	}
	
	public boolean isFull() {
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 3; y++) {
				if(board[x][y] == 0) {
					return(false);				
				}
			}
		}
		return(true);
	}
	
	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		
		board.play(0, 0);
		board.play(0, 1);
		board.play(1, 1);
		
		board.print();
		
		board.printOne();
		System.out.println();
		board.printTwo();
		System.out.println();
		board.printThree();
	}
}
