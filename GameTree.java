package TTT;
import java.util.Scanner;

public class GameTree {
	private Node root;
    //Class and constructor for a tree node
    private class Node {
        GameBoard board;
        int xScore;
        int yScore;
        int drawScore;
        //all 9 possible move locations
        Node zz;
        Node zo;
        Node zt;
        Node oz;
        Node oo;
        Node ot;
        Node tz;
        Node to;
        Node tt;

        Node(GameBoard board) {
            this.board = board;
        }
    }
    
    Scanner scan = new Scanner(System.in);
    
    public Node getPos(Node current, int x) {
    	switch(x) {
    	case 1:
    		return(current.zz);
    	case 2:
    		return(current.zo);
    	case 3:
    		return(current.zt);
    	case 4:
    		return(current.oz);
    	case 5:
    		return(current.oo);
    	case 6:
    		return(current.ot);
    	case 7:
    		return(current.tz);
    	case 8:
    		return(current.to);
    	case 9:
    		return(current.tt);
       	default:
    		return(current);
    	}
    }
    
    public void addNodes(GameBoard board) {
    	root = addNodes(root, board);
    }
    
    private Node addNodes(Node current, GameBoard board) {
    	
    	if(current == null) {
    		current = new Node(board);
    	}
    	
    	current.board = new GameBoard(board);
    	
    	if(current.board.isWin() || current.board.isFull()) {
    		return(current);
    	}
    	
    	if(current.board.test(0, 0)) {
    		GameBoard newBoard = new GameBoard(current.board);
            newBoard.play(0, 0);
            current.zz = addNodes(current.zz, newBoard);
    	}
    	if(current.board.test(0, 1)) {
    		GameBoard newBoard = new GameBoard(current.board);
            newBoard.play(0, 1);
            current.zo = addNodes(current.zo, newBoard);
    	}
    	if(current.board.test(0, 2)) {
    		GameBoard newBoard = new GameBoard(current.board);
            newBoard.play(0, 2);
            current.zt = addNodes(current.zt, newBoard);
    	}
    	if(current.board.test(1, 0)) {
    		GameBoard newBoard = new GameBoard(current.board);
            newBoard.play(1, 0);
            current.oz = addNodes(current.oz, newBoard);
    	}
    	if(current.board.test(1, 1)) {
    		GameBoard newBoard = new GameBoard(current.board);
            newBoard.play(1, 1);
            current.oo = addNodes(current.oo, newBoard);
    	}
    	if(current.board.test(1, 2)) {
    		GameBoard newBoard = new GameBoard(current.board);
            newBoard.play(1, 2);
            current.ot = addNodes(current.ot, newBoard);
    	}
    	if(current.board.test(2, 0)) {
    		GameBoard newBoard = new GameBoard(current.board);
            newBoard.play(2, 0);
            current.tz = addNodes(current.tz, newBoard);
    	}
    	if(current.board.test(2, 1)) {
    		GameBoard newBoard = new GameBoard(current.board);
            newBoard.play(2, 1);
            current.to = addNodes(current.to, newBoard);
    	}
    	if(current.board.test(2, 2)) {
    		GameBoard newBoard = new GameBoard(current.board);
            newBoard.play(2, 2);
            current.tt = addNodes(current.tt, newBoard);
    	}
    	return(current);
    }
    
    public void getScores() {
    	root.xScore = getScoresX(root);
    	root.yScore = getScoresY(root);
    	root.drawScore = getScoresDraw(root);

    }
    
    private int getScoresX(Node current) {
    	if(current == null) {
    		return(0);
    	}
    	
    	if(current.board.isWin()) {
    		if(current.board.winTeam() == 1) {
    			current.xScore = 1;
    			return(1);
    		}
    	}
    	
    	if(current.board.isFull()) {
    		return(0);
    	}
    		
    	current.xScore = getScoresX(current.zz) + getScoresX(current.zo) + getScoresX(current.zt) + getScoresX(current.oz) + getScoresX(current.oo) + getScoresX(current.ot) + getScoresX(current.tz) + getScoresX(current.to) + getScoresX(current.tt); 
    	return(current.xScore);
    }    
    
    private int getScoresY(Node current) {
    	if(current == null) {
    		return(0);
    	}
    	
    	if(current.board.isWin()) {
    		if(current.board.winTeam() == 2) {
    			current.yScore = 1;
    			return(1);
    		}
    	}
    	
    	if(current.board.isFull()) {
    		return(0);
    	}
    		
    	current.yScore = getScoresY(current.zz) + getScoresY(current.zo) + getScoresY(current.zt) + getScoresY(current.oz) + getScoresY(current.oo) + getScoresY(current.ot) + getScoresY(current.tz) + getScoresY(current.to) + getScoresY(current.tt); 
    	return(current.yScore);
    }    
    
    private int getScoresDraw(Node current) {
    	if(current == null) {
    		return(0);
    	}
    	
    	if(current.board.isWin()) {
    		return(0);
    	}
    	
    	if(current.board.isFull()) {
    		current.drawScore = 1;
    		return(1);
    	}
    		
    	current.drawScore = getScoresDraw(current.zz) + getScoresDraw(current.zo) + getScoresDraw(current.zt) + getScoresDraw(current.oz) + getScoresDraw(current.oo) + getScoresDraw(current.ot) + getScoresDraw(current.tz) + getScoresDraw(current.to) + getScoresDraw(current.tt); 
    	return(current.drawScore);
    }
    
    public void investigate() {
    	System.out.println("Select which board # to travel to, or type 0 to leave");
    	investigate(root);
    }
    
    private void investigate(Node current) {
    	if(current == null) {
    		System.out.print("Null state reached");
    	}else {
	    	System.out.println("Current board: \nNumber of possible X wins = " + current.xScore + "\nNumber of possible O wins = " + current.yScore + "\nNumber of possible draws = " + current.drawScore);
	    	if (current.yScore == 0) {
	    		System.out.println("X favorability is 100%");
	    	}else {
	    		System.out.printf("X favorability is %.2f\n", ((double)current.xScore /(current.yScore + current.drawScore)));
	    		System.out.printf("O favorability is %.2f\n", (((double)current.yScore + current.drawScore) /current.xScore));
	    	}
	    	current.board.print();
	    	
	    	//Print what board you're seeing
	    	System.out.print("Board # ");
	    	for(int x = 1; x < 10; x++) {
	    		if(getPos(current, x) != null) {
	    			System.out.print(x + "    ");
	    		}
	    	}
	    	System.out.println();
	    	
	    	//Print x score
	    	System.out.print("X Ratio ");
	    	for(int x = 1; x < 10; x++) {
	    		if(getPos(current, x) != null) {
	    			if ((getPos(current, x).yScore + getPos(current, x).drawScore) == 0) {
	    	    		System.out.print("100% ");
	    	    	}else {
	    	    		System.out.printf("%.2f ", ((double)getPos(current, x).xScore /(getPos(current, x).yScore + getPos(current, x).drawScore)));
	    	    	}
	    	    }
	    	}
	    	System.out.println();
	    	
	    	//Print y score
	    	System.out.print("O Ratio ");
	    	for(int x = 1; x < 10; x++) {
	    		if(getPos(current, x) != null) {
	    			if (getPos(current, x).xScore == 0) {
	    	    		System.out.print("100% ");
	    	    	}else {
	    	    		System.out.printf("%.2f ", (((double)getPos(current, x).yScore + getPos(current, x).drawScore) /(getPos(current, x).xScore)));
	    	    	}
	    		}
	    	}
	    	System.out.println();
	    	
	    	//Print each layer
	    	System.out.print("Preview ");
	    	for(int x = 1; x < 10; x++) {
	    		if(getPos(current, x) != null) {
	    			getPos(current, x).board.printOne();
	    			System.out.print("  ");
	    		}
	    	}
	    	System.out.println();
	    	
	    	System.out.print("        ");
	    	for(int x = 1; x < 10; x++) {
	    		if(getPos(current, x) != null) {
	    			getPos(current, x).board.printTwo();
	    			System.out.print("  ");
	    		}
	    	}
	    	System.out.println();
	    	
	    	System.out.print("        ");
	    	for(int x = 1; x < 10; x++) {
	    		if(getPos(current, x) != null) {
	    			getPos(current, x).board.printThree();
	    			System.out.print("  ");
	    		}
	    	}
	    	System.out.println();
	    	
	    	
	    	
	    	int choice = scan.nextInt();
	    	
	    	switch(choice) {
	    	case 0:
	    		break;
	    	case 1:
	    		investigate(current.zz);    	
	    		break;
	    	case 2:
	    		investigate(current.zo);    	
	    		break;
	    	case 3:
	    		investigate(current.zt);    	
	    		break;
	    	case 4:
	    		investigate(current.oz);    	
	    		break;
	    	case 5:
	    		investigate(current.oo);    	
	    		break;
	    	case 6:
	    		investigate(current.ot);    	
	    		break;
	    	case 7:
	    		investigate(current.tz);    	
	    		break;
	    	case 8:
	    		investigate(current.to);    	
	    		break;
	    	case 9:
	    		investigate(current.tt);    	
	    		break;
	    	}
    	}
    }
}

