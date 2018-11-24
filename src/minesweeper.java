import java.util.Arrays;
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
		
		int[][] boardStatus = new int[dimensions][dimensions];
		for(int[] row:boardStatus) {Arrays.fill(row, -1);}
		
		while(alive) {
			System.out.print("Please enter tile to sweep, format \"x,y\": ");
			Scanner location = new Scanner(System.in);
			
			coords = location.nextLine();
			xy = coords.trim().split(",");
			
			x = Integer.valueOf(xy[0]);
			y = Integer.valueOf(xy[1]);
			
			if(board[x][y]) { 
				System.out.println("You stepped on a mine. Game over");
				alive = false;
				break;
			}
				
			boardStatus = floodFill(boardStatus,board, x, y, dimensions);
			
			for (int i = 0; i< dimensions; i++) {
				for(int j = 0; j<dimensions; j++) {
					System.out.print(boardStatus[i][j]);
				}
				System.out.println("");
			}
		}
		
		//System.out.println(myLine);
    }
	
	private static int[][] floodFill(int[][] status, boolean[][] b, int x, int y, int dim) {
		if(b[x][y] || x >= dim || y>= dim || x<0 || y<0) { return status;}
		
		countAdjacentMines(status, b, x, y);
		
		floodFill(status, b, x+1, y, dim);
		floodFill(status, b, x-1, y, dim);
		floodFill(status, b, x, y+1, dim);
		floodFill(status, b, x, y-1, dim);
		
		return status;
	}

	private static void countAdjacentMines(int[][] status, boolean[][] b, int x, int y) {
		status[x][y]++;
		for(int i = -1; i< 2; i++) {
			for(int j = -1; j<2; j++) {
				if(b[x+i][j+i]) { status[x][y]++;}
			}
		}
	}

	private static void createBoard(int dimensions, int ratio, boolean[][] board) {
		int prob;
		String minePrint;
		System.out.println("Please Note * denote unknown tile, Integer denotes number of mines adjacent to visited tile");
		for (int i = 0; i< dimensions; i++) {
			for(int j = 0; j<dimensions; j++) {
				prob = ThreadLocalRandom.current().nextInt(0, 101);
				board[i][j] = (prob<=ratio);
				//minePrint = board[i][j] ? "x" : "o";
				System.out.print("*");
			}
			System.out.println("");
		}
	}
}
