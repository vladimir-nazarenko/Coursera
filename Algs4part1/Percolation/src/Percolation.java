/**
 * Created by vladimir on 19.02.15.
 */



public class Percolation {
	public Percolation(int N)
	{
		if(N <= 0)
			throw new IllegalArgumentException();
		virtualSize = N + 2;
		size = N;
		opened = new boolean[virtualSize * virtualSize + 2];
		indicator = new WeightedQuickUnionUF(virtualSize * virtualSize + 2);
		antiBackslash = new WeightedQuickUnionUF(virtualSize * virtualSize + 1);
		int last = virtualSize * virtualSize + 1;
		for(int i = 0; i < size; i++)
		{
			indicator.union(0, virtualSize + 2 + i);
			antiBackslash.union(0, virtualSize + 2 + i);
		}
		for(int i = last - virtualSize - 2; i > last - 2 * virtualSize; i--)
		{
			indicator.union(last, i);
		}
	}

	public void open(int i, int j)
	{
		checkIndex(i, j);
		if(isOpenWE(i - 1, j)) {
			indicator.union(getIndex(i - 1, j), getIndex(i, j));
			antiBackslash.union(getIndex(i - 1, j), getIndex(i, j));
		}
		if(isOpenWE(i + 1, j))
		{
			indicator.union(getIndex(i + 1, j), getIndex(i, j));
			antiBackslash.union(getIndex(i + 1, j), getIndex(i, j));
		}
		if(isOpenWE(i, j - 1))
		{
			indicator.union(getIndex(i, j - 1), getIndex(i, j));
			antiBackslash.union(getIndex(i, j - 1), getIndex(i, j));
		}
		if(isOpenWE(i, j + 1))
		{
			indicator.union(getIndex(i, j + 1), getIndex(i, j));
			antiBackslash.union(getIndex(i, j + 1), getIndex(i, j));
		}
		if (i == 1)
			opened[0] = true;
		if (i == size)
			opened[virtualSize * virtualSize + 1] = true;
		opened[getIndex(i, j)] = true;
	}

	public boolean isOpen(int i, int j)
	{
		checkIndex(i, j);
		return opened[getIndex(i, j)];
	}

	public boolean isFull(int i, int j)
	{
		checkIndex(i, j);
		return antiBackslash.connected(getIndex(i, j), 0) && isOpen(i, j);
	}

	public boolean percolates()
	{
		return indicator.connected(0, virtualSize * virtualSize + 1) && opened[0] &&
				opened[virtualSize * virtualSize + 1];
	}

	public static void main(String[] args)
	{

	}

	// handles corner cases without an exception
	private boolean isOpenWE(int i, int j)
	{
		return opened[getIndex(i, j)];
	}

	private void checkIndex(int i, int j)
	{
		if (!(0 < i && i <= size) || !(0 < j && j <= size))
			throw new IndexOutOfBoundsException();
	}

	// indexes from 1 to size
	private int getIndex(int i, int j)
	{
		if (!(0 <= i && i <= size + 1) || !(0 <= j && j <= size + 1))
			throw new IndexOutOfBoundsException("getIndex");
		return 1 + virtualSize * i + j;
	}

	// indicates whether system percolates. Connected to both top and bottom
	private WeightedQuickUnionUF indicator;
	// indicates whether site is connected to the top
	private WeightedQuickUnionUF antiBackslash;
	private boolean[] opened;
	// size of a matrix in memory. Has a 1-element frame around
	private int virtualSize;
	// real problem size
	private int size;
}
