
public class Percolation {
	public Percolation(int N) {
		UF = new WeightedQuickUnionUF(N * N + 2);
		safeUF = new WeightedQuickUnionUF(N * N + 1);
		opened = new boolean[N * N + 2];
		size = N;
		for (int i = 1; i < size * size + 1; ++i)
			opened[i] = false;
		opened[0] = true;
		opened[size * size + 1] = true;
	}

	public void open(int i, int j) {
		if (i > size || i < 1 || j > size || j < 1)
			throw new IndexOutOfBoundsException("wrong index");
		int number = numberOf(i, j);
		int side = -1;
		opened[number] = true;
		if ((side = left(number)) > 0)
			if (isOpen(i, j - 1))
			{
				UF.union(side, number);
				safeUF.union(side, number);
			}
		if ((side = right(number)) > 0)
			if (isOpen(i, j + 1))
			{
				UF.union(side, number);
				safeUF.union(side, number);
			}
		if ((side = bottom(number)) > 0)
		{
			if (isOpen(i + 1, j))
			{
				UF.union(side, number);
				safeUF.union(side, number);
			}
		}
		else
			UF.union(size * size + 1, number);
		if ((side = top(number)) > 0)
		{			
			if (isOpen(i - 1, j))
			{
				UF.union(side, number);
				safeUF.union(side, number);
			}
		}
		else 
		{
			UF.union(0, number);
			safeUF.union(0, number);
		}
	}

	public boolean isOpen(int i, int j) {
		if (i > size || i < 1 || j > size || j < 1)
			throw new IndexOutOfBoundsException("wrong index");
		return opened[size * (i - 1) + j];
	}

	public boolean isFull(int i, int j) {
		if (i > size || i < 1 || j > size || j < 1)
			throw new IndexOutOfBoundsException("wrong index");
		return safeUF.connected(numberOf(i, j), 0) && isOpen(i, j);
				
	}

	public boolean percolates() {
		return UF.connected(0, size * size + 1);
	}

	private int numberOf(int i, int j) {
		return size * (i - 1) + j;
	}

	private int left(int element) {
		if (element % size == 1)
			return -1;
		else
			return element - 1;
	}

	private int right(int element) {
		if (element % size == 0)
			return -1;
		else
			return element + 1;
	}

	private int top(int element) {
		if (element < size + 1)
			return -1;
		else
			return element - size;
	}

	private int bottom(int element) {
		if (element - size * (size - 1) > 0)
			return -1;
		else
			return element + size;
	}

	private WeightedQuickUnionUF UF;
	private WeightedQuickUnionUF safeUF;
	private boolean[] opened;
	private int size;
}
