import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Main {
	private static char[][] maze;
	private static int startRow, startCol;
	private static boolean found = false;

	public static boolean generateMatrix(String filePath){
		try{
			File file = new File(filePath);
			Scanner reader = new Scanner(file);
			int rows = reader.nextInt();
			int column = reader.nextInt();
			reader.nextLine();
			maze = new char[rows][column];
			
			for(int row = 0; row < rows; row++){
				String line = reader.nextLine();
				for(int col = 0; col < column; col++){
					maze[row][col] = line.charAt(col);
					if(maze[row][col] == '+'){
						startRow = row;
						startCol = col;
					}
					
				}
			}
			reader.close();
			return true;
		} catch(FileNotFoundException e){
			e.printStackTrace();
			return false;
		}
	}

	public static void printMaze(){
		for(int row = 0; row < maze.length; row++){
				for(int col = 0; col < maze[row].length; col++){
					System.out.print(maze[row][col]);
				}
				System.out.println();
			}
	}
	public static char north(int row, int col){
		if(row - 1 >= 0)
			return maze[row - 1][col];
		return 'X';
		} 

	public static char south(int row, int col){
		if(row + 1 < maze.length)
			return maze[row + 1][col];
		return 'X';
	}
	
	public static char west(int row, int col){
		if(col -1 >= 0)
			return maze[row][col - 1];
		return 'X';
    
	}

  public static char east(int row, int col){
		if(col + 1 < maze[row].length)
    	return maze[row][col + 1];
		return 'X';
  }
	
	// recursive method
	public static void solve(int row, int col){
	 if(east(row,col) == '-' || south(row,col) == '-' || west(row,col) == '-' || north(row,col) == '-'){
		 // base case
		 maze[row][col] = '+';
		 found = true;
		return;
	 }
	 else{
		if (east(row,col) == ' ') {
			maze[row][col] = '+';
    	solve(row,col+1);
			if(found)
				return;
  	}
    if (south(row,col) == ' ') {
			maze[row][col] = '+';
    	solve(row+1,col);
			if(found)
				return;
  	}
  	if (west(row,col) == ' ') {
			maze[row][col] = '+';
    	solve(row,col-1);
			if(found)
				return;
  	}
		if (north(row,col) == ' ') {
			maze[row][col] = '+';
			solve(row-1,col);
			if(found)
				return;
		}
		maze[row][col] = '.'; 
		
	 }
	}




  public static void main(String[] args) {
    if(generateMatrix("maze.dat.txt")){
			solve(startRow,startCol);
					System.out.println("Solved!");
				//System.out.println("Maze unsolvable!");
				printMaze();
		}
		else{
			System.out.println("Maze not found!");
		}
  }
}