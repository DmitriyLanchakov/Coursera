/**
 * Created by den on 24.01.15.
 */
public class Percolation {
    private boolean[] openSite;
    private int N;
    private WeightedQuickUnionUF wquUF;
    private WeightedQuickUnionUF withoutBackwash;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N)  {
        if (N <= 0) throw new IllegalArgumentException("N must be >= 0");
        else {
            this.N = N;
            wquUF = new WeightedQuickUnionUF(N*N+2); //plus bottom and top virtual row
            withoutBackwash = new WeightedQuickUnionUF(N*N+2);
            openSite = new boolean[N*N];
            for (int i = 0; i < N*N; i++)
                openSite[i] = false;
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (i <= 0 || i > N || j <= 0 || j > N) throw new IndexOutOfBoundsException("row index i or j out of bounds");
        else {
            int site = xyTo1D(i, j);
            if (openSite[site] == false) {
                openSite[site] = true;
                connectNeighbours(i, j);
            }
        }

    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (i <= 0 || i > N || j <= 0 || j > N) throw new IndexOutOfBoundsException("row index i or j out of bounds");
        else {
            if (openSite[xyTo1D(i,j)] == true)
                return true;
            else
                return false;
        }
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (i <= 0 || i > N || j <= 0 || j > N) throw new IndexOutOfBoundsException("row index i or j out of bounds");
        else {
            if (withoutBackwash.connected(xyTo1D(i, j), N*N))
                return true;
            else
                return false;

        }
    }

    // does the system percolate?
    public boolean percolates() {
        if (wquUF.connected(N*N, N*N+1))
            return true;
        else
            return false;
    }

    private int xyTo1D(int i, int j) {
        if (i <= 0 || i > N || j <= 0 || j > N) throw new IndexOutOfBoundsException("row index i or j out of bounds");
        else
            return (i-1) * N + (j-1);
    }

    private void connectNeighbours(int i, int j) {
        if ((j > 1) && (isOpen(i, j-1)))
            union(xyTo1D(i, j), xyTo1D(i, j-1));
        if ((j < N) && (isOpen(i, j+1)))
            union(xyTo1D(i, j), xyTo1D(i, j+1));
        if (i > 1) {
            if (isOpen(i-1, j))
                union(xyTo1D(i, j), xyTo1D(i-1, j));
        } else {
            union(xyTo1D(i, j), N * N); // N*N is 1D index of the virtualTop
        }
        if (i < N) {
            if (isOpen(i+1, j))
                union(xyTo1D(i, j), xyTo1D(i+1,j));
        } else {
            union(xyTo1D(i, j), N * N + 1); //N*N + 1 is 1D index of the virtualBottom
        }

    }

    private void union(int i, int j) {
        wquUF.union(i, j);
        if (j != N*N+1)
            withoutBackwash.union(i, j);
    }

    // test client (optional)
    public static void main(String[] args) {
        // Bad test case
        Percolation p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 2);
        System.out.println("Test should fail: " + p.percolates());
// Good test case
        p = new Percolation(2);
        p.open(1, 1);
        p.open(2, 1);
        System.out.println("Test should pass: " + p.percolates());
// Good test case
        p = new Percolation(4);
        p.open(1, 1);
        p.open(2, 1);
        p.open(2, 2);
        p.open(3, 2);
        p.open(4, 2);
        System.out.println("Test should pass: " + p.percolates());
    }
}