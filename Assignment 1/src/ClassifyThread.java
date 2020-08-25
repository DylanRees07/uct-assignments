import java.util.concurrent.RecursiveAction;

public class ClassifyThread  extends RecursiveAction{
	
	public int col;
	public int row;
	double[][] terrain;

	
	public ClassifyThread(int prow, int pcol, double[][] pterrain) {	
		
		this.col = pcol;
		this.row = prow;
		this.terrain = pterrain;
		
	}
	
	
	public void compute() {
		
		double test = TerrainMapping.terrain[row][col];
		boolean basin = true;
		
			
		if ((test + 0.01) >= terrain[row + 1][col]) {
			basin = false;
		}
			
		if ((test + 0.01) >= terrain[row - 1][col]) {
			basin = false;
		}
			
		if ((test + 0.01) >= terrain[row + 1][col + 1]) {
			basin = false;
		}
			
		if ((test + 0.01) >= terrain[row + 1][col - 1]) {
			basin = false;
		}
			
		if ((test + 0.01) >= terrain[row - 1][col + 1]) {
			basin = false;
		}
			
		if ((test + 0.01) >= terrain[row - 1][col - 1]) {
			basin = false;
		}
			
		if ((test + 0.01) >= terrain[row][col + 1]) {
			basin = false;
		}
			
		if ((test + 0.01) >= terrain[row][col - 1]) {
			basin = false;
		}
			
		
		if (basin == true) {
			TerrainMapping.classified[row][col] = 1;
		}
		else {
			TerrainMapping.classified[row][col] = 0;
		}
		
		return;
	}

}
