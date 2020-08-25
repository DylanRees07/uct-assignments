import java.util.concurrent.RecursiveAction;

public class ClassifyThread  extends RecursiveAction{
	
	public int colstart;
	public int rowstart;
	public int colend;
	public int rowend;
	float[][] terrain;
	public int SEQUENTIAL_CUTOFF = 1044484;
	
	public ClassifyThread(int prowstart, int pcolstart, int prowend, int pcolend, float[][] pterrain) {	
		
		this.colstart = pcolstart;
		this.rowstart = prowstart;
		this.colend = pcolend;
		this.rowend = prowend;
		this.terrain = pterrain;		
	}
		
	public void compute() {
		
		System.out.println((rowend - rowstart + 1)*(colend - colstart + 1));
		if ( ((rowend - rowstart + 1)*(colend - colstart + 1))<= SEQUENTIAL_CUTOFF)
		
		
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
			
		return;
	}
}
