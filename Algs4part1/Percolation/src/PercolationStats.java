import java.util.InputMismatchException;

/**
 * Created by vladimir on 19.02.15.
 */
public class PercolationStats {
	public PercolationStats(int N, int T)
	{
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();
		this.T = T;
		this.N = N;
		pvalues = new double[T];
		for(int i = 0; i < T; i++)
		{
			Percolation p = new Percolation(N);
			do {
				int x = StdRandom.uniform(N) + 1;
				int y = StdRandom.uniform(N) + 1;
				if(!p.isOpen(x, y)) {
					p.open(x, y);
					pvalues[i]++;
				}
			} while(!p.percolates());
			pvalues[i] = (double)pvalues[i] / (N * N);
		}
	}

	public double mean()
	{
		double sum = 0;
		for(int i = 0; i < T; i++)
			sum += pvalues[i];
		return sum / T;
	}

	public double stddev()
	{
		if(T == 1)
			return Double.NaN;
		double mn = mean();
		double sum = 0;
		for(int i = 0; i < T; i++)
		{
			sum += Math.pow(pvalues[i] - mn, 2);
		}
		return Math.sqrt(sum / (T - 1));
	}

	public double confidenceLo()
	{
		return mean() - 1.96 * stddev() / Math.sqrt(T);
	}

	public double confidenceHi()
	{
		return mean() + 1.96 * stddev() / Math.sqrt(T);
	}

	public static void main(String[] args)
	{
		int T, N = 0;

		try {
			T = Integer.parseInt(args[1]);
			N = Integer.parseInt(args[0]);
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Too little arguments");
		}
		PercolationStats ps = new PercolationStats(N, T);
		StdOut.printf("%-25s = %f\n", "mean", ps.mean());
		StdOut.printf("%-25s = %f\n", "stddev", ps.stddev());
		StdOut.printf("%-25s = %f, %f", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());
	}

	private double[] pvalues;
	private int T;
	private int N;
}
