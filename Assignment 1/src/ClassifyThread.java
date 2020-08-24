import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ClassifyThread extends RecursiveAction  {
	
	public int col;
	public int row;
	
	public ClassifyThread(int prow, int pcol) {	
		
		this.col = pcol;
		this.row = prow;
		
	}
	
	
	public void compute() {
		
		
		
		
	}

}
