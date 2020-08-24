import java.util.concurrent.ForkJoinPool;


public class TerrainMapping {
	
	static double[][] terrain;
	static int[][] classified;
	
	
	public static void main(String[] args) {
		
		terrain = new double[3][3];
		classified = new int[3][3];
		
		for (int i = 0; i < terrain.length; i++) {
			
			for (int j = 0; j < terrain[i].length; j++) {
				terrain[i][j] = 3.4;
			}
		}
		
		terrain[1][1] = 3.2;
			
	}

}
