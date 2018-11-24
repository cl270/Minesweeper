import java.util.Scanner;

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
		
		for (int i = 0; i< dimensions; i++) {
			for(int j = 0; j<dimensions; j++) {
				
				
				//board[i][j] =
			}
		}
		
		
		while(true) {
			
		}
		
		//System.out.println(myLine);
    }
}
