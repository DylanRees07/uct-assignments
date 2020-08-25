import java.util.concurrent.RecursiveAction;

public class ClassifyThread  extends RecursiveAction{
	
	public int colstart;
	public int rowstart;
	public int colend;
	public int rowend;
	float[][] terrain;
	public int SEQUENTIAL_CUTOFF = 16384;
	
	public ClassifyThread(int prowstart, int pcolstart, int prowend, int pcolend, float[][] pterrain) {	
		
		this.colstart = pcolstart;
		this.rowstart = prowstart;
		this.colend = pcolend;
		this.rowend = prowend;
		this.terrain = pterrain;		
	}
		
	public void compute() {
		
		System.out.println("Row: " + rowstart + " " + rowend);
		System.out.println("Col: " + colstart + " " + colend);
		if ( ((rowend - rowstart + 1)*(colend - colstart + 1))<= SEQUENTIAL_CUTOFF) {
			
			for (int i = rowstart; i <= rowend; i++) {

				for (int j = colstart; j <= colend; j++) {
					
					double test = terrain[i][j];
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
			ClassifyThread left = new ClassifyThread(rowstart, colstart, rowend/2, colend/2, terrain);
			ClassifyThread right = new ClassifyThread(rowend/2, colend/2, rowend, colend, terrain);			
			left.fork();
			right.fork();
			left.join();
			right.join();
		}
			
		return;
	}
}
