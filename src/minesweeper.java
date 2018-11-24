import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class minesweeper {
	public static void main ( String [] arguments )
    {
		runMinesweeper();
		
    }

	private static void runMinesweeper() {
		System.out.print("Please enter side dimensions: ");
		Scanner dimScan = new Scanner(System.in);
		int dimensions = Integer.valueOf(dimScan.nextLine());
		
		System.out.print("Please enter per tile mine probability % (Integer 0-100): ");
		Scanner mineScan = new Scanner(System.in);
		int ratio = Integer.valueOf(mineScan.nextLine());
		
		boolean[][] board = new boolean[dimensions][dimensions];
		int mineCounter = createBoard(dimensions, ratio, board, 0);;
		
		boolean winner = false;
		int x,y;
		String coords;
		String[] xy;
		
		int[][] boardStatus = new int[dimensions][dimensions];
		for(int[] row:boardStatus) {Arrays.fill(row, -1);}
		
		while(true) {
			System.out.print("Please enter tile to sweep, format \"x,y\": ");
			Scanner location = new Scanner(System.in);
			
			coords = location.nextLine();
			xy = coords.trim().split(",");
			
			x = Integer.valueOf(xy[0]);
			y = Integer.valueOf(xy[1]);
			
			if(board[x][y]) { 
				System.out.println("You stepped on a mine. Game over");
				break;
			}
				
			boardStatus = floodFill(boardStatus,board, x, y, dimensions);
			
			winner = displayBoard(dimensions, boardStatus, mineCounter);
			
			if(winner) { 
				System.out.println("You opened all tiles without a mine. you win");
				break;
			}
		}
	}

	private static boolean displayBoard(int dimensions, int[][] boardStatus, int minecounter) {
		int counter = 0;
		for (int i = 0; i< dimensions; i++) {
			for(int j = 0; j<dimensions; j++) {
				if(boardStatus[i][j]>=0) {System.out.print(boardStatus[i][j]);}
				else {
					counter++;
					System.out.print("*"); 
					
				}
			}
			System.out.println("");
		}
		return counter == minecounter;
	}
	
	private static int[][] floodFill(int[][] status, boolean[][] b, int x, int y, int dim) {
		if(x >= dim || y>= dim || x<0 || y<0 || status[x][y] >=0 || b[x][y] ) { return status;}
		
		countAdjacentMines(status, b, x, y, dim);
		
		floodFill(status, b, x+1, y, dim);
		floodFill(status, b, x-1, y, dim);
		floodFill(status, b, x, y+1, dim);
		floodFill(status, b, x, y-1, dim);
		
		return status;
	}

	private static void countAdjacentMines(int[][] status, boolean[][] b, int x, int y, int dim) {
		status[x][y]++;
		for(int i = -1; i< 2; i++) {
			if(x+i >= dim || x+i<0) {continue;}
			for(int j = -1; j<2; j++) {
				if(y+j >= dim || y+j<0) {continue;}
				if(b[x+i][y+j]) { status[x][y]++;}
			}
		}
	}

	private static int createBoard(int dimensions, int ratio, boolean[][] board, int mineCounter) {
		int prob;
		System.out.println("Please Note * denote unknown tile, Integer denotes number of mines adjacent to visited tile");
		for (int i = 0; i< dimensions; i++) {
			for(int j = 0; j<dimensions; j++) {
				prob = ThreadLocalRandom.current().nextInt(0, 101);
				board[i][j] = (prob<=ratio);
				mineCounter = (board[i][j] ? mineCounter+1 : mineCounter);
				System.out.print("*");
			}
			System.out.println("");
		}
		return mineCounter;
	}
}
