import java.util.concurrent.ForkJoinPool;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.PrintWriter;


public class TerrainMapping {
	
	static float[][] terrain;
	static int[][] classified;
	static ForkJoinPool fjp = new ForkJoinPool();
	static File input;
	static File output;
	static int basins = 0;
	static Scanner scanner;
	static PrintWriter printer;
	static FileWriter write;
	static long starttime;
	
	
	public static void main(String[] args) {
		
		try {

		input = new File(args[0]);
		write = new FileWriter(args[1], false);
		printer = new PrintWriter(write);
		scanner = new Scanner(input);
		int rows = scanner.nextInt();
		int cols = scanner.nextInt();
		
		terrain = new float[rows][cols];
		classified = new int[rows][cols];
		
		for (int i = 0; i < terrain.length; i++) {
			
			for (int j = 0; j < terrain[i].length; j++) {
				terrain[i][j] = scanner.nextFloat();
			}
		}
		
		
		tick();
		classify();
		tock();
		
		System.out.println(basins);

		output();

		}
		catch(IOException ex){
			System.out.println("Could not locate essential file");
			
		}					
	}
	
	public static void tick() {
	
		starttime = System.currentTimeMillis();
	}
	
	public static void tock() {
		System.out.println("Time taken for run: " + (System.currentTimeMillis() - starttime)/1000f);
	}
	
	public static void classify() {
		
		fjp.invoke(new ClassifyThread(1, 1, terrain.length - 2, terrain[1].length - 2, terrain));
	}
	
	public static void output() {
		
		printer.printf("%s " + "%n", basins);
		
		for (int i = 0; i < classified.length; i++) {
			
			for (int j = 0; j < classified[i].length; j++) {
				
				if (classified[i][j] == 1) {
					
					printer.printf("%s" + "%n", i + " " + j);
				}
				
			}
		}
		
		printer.close();
	}
}
