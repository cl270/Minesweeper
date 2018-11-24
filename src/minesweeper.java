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
		
		for (int i = 0; i< dimensions; i++) {
			for(int j = 0; j<dimensions; j++) {
				prob = ThreadLocalRandom.current().nextInt(0, 101);
				board[i][j] = (prob<=ratio);
				minePrint = board[i][j] ? "x" : "o";
				System.out.print(minePrint);
			}
			System.out.println("");
		}
		
		
		while(true) {
			
		}
		
		//System.out.println(myLine);
    }
}
