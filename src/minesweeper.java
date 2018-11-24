import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class minesweeper {
	public static void main ( String [] arguments )
    {
		System.out.print("Please enter side dimensions: ");
		Scanner dimScan = new Scanner(System.in);
		int dimensions = Integer.valueOf(dimScan.nextLine());
		
		System.out.print("Please enter per tile mine probability % (Integer 0-100): ");
		Scanner mineScan = new Scanner(System.in);
		int ratio = Integer.valueOf(mineScan.nextLine());
		
		boolean[][] board = new boolean[dimensions][dimensions];
		int prob;
		String minePrint;
		
		createBoard(dimensions, ratio, board);
		
		boolean alive = true;
		int x,y;
		String coords;
		String[] xy;
		
		
		while(alive) {
			System.out.print("Please enter tile to sweep, format \"x,y\": ");
			Scanner location = new Scanner(System.in);
			
			coords = location.nextLine();
			xy = coords.trim().split(",");
			
			x = Integer.valueOf(xy[0]);
			y = Integer.valueOf(xy[1]);
			
			if(board[x][y]) { 
				System.out.println("You lost");
				alive = false;
			}
			
			
			
		}
		
		//System.out.println(myLine);
    }

	private static void createBoard(int dimensions, int ratio, boolean[][] board) {
		int prob;
		String minePrint;
		for (int i = 0; i< dimensions; i++) {
			for(int j = 0; j<dimensions; j++) {
				prob = ThreadLocalRandom.current().nextInt(0, 101);
				board[i][j] = (prob<=ratio);
				minePrint = board[i][j] ? "x" : "o";
				System.out.print(minePrint);
			}
			System.out.println("");
		}
	}
}
