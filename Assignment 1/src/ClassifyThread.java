import java.util.concurrent.RecursiveAction;

public class ClassifyThread extends RecursiveAction{
	
	public int colstart;
	public int rowstart;
	public int colend;
	public int rowend;
	float[][] terrain;
	public int SEQUENTIAL_CUTOFF = 262143;
	
	public ClassifyThread(int prowstart, int pcolstart, int prowend, int pcolend, float[][] pterrain) {	
		
		this.colstart = pcolstart;
		this.rowstart = prowstart;
		this.colend = pcolend;
		this.rowend = prowend;
		this.terrain = pterrain;		
	}
		
	public void compute() {
		
		float test;
		if ( ((rowend - rowstart + 1)*(colend - colstart + 1))<= SEQUENTIAL_CUTOFF) {
			
			for (int i = (int) rowstart; i <= rowend; i++) {

				for (int j = (int) colstart; j <= colend; j++) {
					
					test = terrain[i][j];
					boolean basin = true;
											
					if ((test + 0.01) > terrain[i + 1][j]) {
						basin = false;
					}
						
					if ((test + 0.01) > terrain[i - 1][j]) {
						basin = false;
					}
						
					if ((test + 0.01) > terrain[i + 1][j + 1]) {
						basin = false;
					}
						
					if ((test + 0.01) > terrain[i + 1][j - 1]) {
						basin = false;
					}
						
					if ((test + 0.01) > terrain[i - 1][j + 1]) {
						basin = false;
					}
						
					if ((test + 0.01) > terrain[i - 1][j - 1]) {
						basin = false;
					}
						
					if ((test + 0.01) > terrain[i][j + 1]) {
						basin = false;
					}
						
					if ((test + 0.01) > terrain[i][j - 1]) {
						basin = false;
					}
								
					if (basin == true) {
						TerrainMapping.classified[i][j] = 1;
						TerrainMapping.basins+=1;
					}
					else {
						TerrainMapping.classified[i][j] = 0;
					}				
				}
			}
		}
		else {
			ClassifyThread topleft = new ClassifyThread(rowstart, colstart, rowend/2, colend/2, terrain);
			ClassifyThread topright = new ClassifyThread(rowstart, colend/2, rowend/2, colend, terrain);
			ClassifyThread botleft = new ClassifyThread(rowend/2, colstart, rowend, colend/2, terrain);
			ClassifyThread botright = new ClassifyThread(rowend/2, colend/2, rowend, colend, terrain);
			topleft.fork();
			topright.fork();
			botleft.fork();
			botright.compute();
			topleft.join();
			topright.join();
			botleft.join();
			return;
		}			
	}
}
