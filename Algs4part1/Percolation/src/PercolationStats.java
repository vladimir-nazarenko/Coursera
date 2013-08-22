
public class PercolationStats {
	public PercolationStats(int N, int T) {
		if (T <= 0 || N <= 0)
			throw new IllegalArgumentException("check args");
		numberOfObservations = T;
		gridSize = N;
		percents = new double[T];
		trulyMain(T, N);		
	}
	public static void main(String[] args) {
		int T = Integer.parseInt(args[1]);
		int N = Integer.parseInt(args[0]);
		PercolationStats test = new PercolationStats(N, T);
		StdOut.print("mean \t\t\t = ");
		StdOut.println(test.mean());
		StdOut.print("stddev\t\t\t = ");
		StdOut.println(test.stddev());
		StdOut.print("95% confidence interval  = ");
		StdOut.print(test.confidenceLo());
		StdOut.print(',');
		StdOut.println(test.confidenceHi());
		/*for (int i = 0; i < test.numberOfObservations; ++i)
		{
			test.testP = new Percolation(test.gridSize);
			int count = 0;
			while (!test.testP.percolates())
			{
				int row = (int) (test.gridSize * StdRandom.random()) + 1;
				int column = (int) (test.gridSize * StdRandom.random()) + 1;
				if (!test.testP.isOpen(row, column))
				{
					test.testP.open(row, column);
					++count;
				}
			}		
			double percent = (double) count / (double) (test.gridSize * test.gridSize);
			test.percents[i] = percent;
		}*/
	}
	public double mean() {
		return StdStats.mean(percents);	
	}
	public double stddev() {
		return StdStats.stddev(percents);
	}
	public double confidenceLo() {
		// returns lower bound of the 95% confidence interval
		return mean() - 1.96 * stddev() / Math.sqrt(numberOfObservations);
	}
	public double confidenceHi() {
		// returns upper bound of the 95% confidence interval
		return mean() + 1.96 * stddev() / Math.sqrt(numberOfObservations);
	}
	/*private void printStat(String moduleCalled) {
		/*StdOut.printf("mean \t\t\t = %f9" +
				"\nstddev\t\t\t = %f9\n95%% confidence interval  = " +
				"%f9, %f9\n%s\n", mean(), stddev(), 
				confidenceLo(), confidenceHi(), moduleCalled);
		StdOut.print(moduleCalled);
		StdOut.print(percents[0]);
		StdOut.print(numberOfObservations);
	}*/
	private void trulyMain(int T, int N)
	{
		gridSize = N;
		numberOfObservations = T;
		Percolation testP;
		for (int i = 0; i < numberOfObservations; ++i)
		{
			testP = new Percolation(gridSize);
			int count = 0;
			while (!testP.percolates())
			{
				int row = (int) (gridSize * StdRandom.random()) + 1;
				int column = (int) (gridSize * StdRandom.random()) + 1;
				if (!testP.isOpen(row, column))
				{
					testP.open(row, column);
					++count;
				}
			}		
			double percent = (double) count / (double) (gridSize * gridSize);
			percents[i] = percent;
		}
	}
	private int numberOfObservations;
	private int gridSize;
	/*private double sd;
	private double mn;
	private double ch;
	private double cl;*/
	private double[] percents;
}
