import java.util.concurrent.ForkJoinPool;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileWriter;


public class TerrainMapping {
	
	static double[][] terrain;
	static int[][] classified;
	static ForkJoinPool fjp = new ForkJoinPool();
	static File input;
	static File output;
	
	
	
	public static void main(String[] args) {
		
		try {
		
		input = new File("trivialbasin_in.txt");
		FileWriter write = new FileWriter("testout.txt", false);
		Scanner scanner = new Scanner(input);
		int rows = scanner.nextInt();
		int cols = scanner.nextInt();
		
		terrain = new double[rows][cols];
		classified = new int[rows][cols];
		
		for (int i = 0; i < terrain.length; i++) {
			
			for (int j = 0; j < terrain[i].length; j++) {
				terrain[i][j] = scanner.nextDouble();
			}
		}
		
		for (int k = 0; k < terrain.length; k++) {
			System.out.println(Arrays.toString(terrain[k]));
		}
		
		//classify();
		System.out.println(classified[1][1]);

		}
		catch(IOException ex){
			System.out.println("Could not locate essential file");
			
		}
		
			
	}
	
	public static void classify() {
		
		fjp.invoke(new ClassifyThread(1, 1, 1, 1, terrain));
	}

}
