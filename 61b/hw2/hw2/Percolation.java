package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;


public class Percolation {

    private int n; // n * n grid
    private WeightedQuickUnionUF WQUUF;
    private boolean[][] state; // represent whether the point has been dig
    private int topSite; // the virtual top site
    private int bottomSite; // the virtual bottom site
    private int count = 0; // the count number of open sites

    /** create N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("the grid of n must be positive integers.");
        }
        this.n = N;
        this.topSite = N * N;
        this.bottomSite = topSite + 1;
        WQUUF = new WeightedQuickUnionUF(N * N + 2);
        state = new boolean[n][n];
        // 初始时,没有dig任何点,所以state均为false
        for (int i = 0; i < n; i += 1) {
            for (int j = 0; j < n; j += 1) {
                state[i][j] = false;
            }
        }
        // 连接topSite与row = 0 以及 bottomSite与row = n-1的所有点
        for (int i = 0; i < n; i += 1) {
            int indexTop = xyTo1d(0, i);
            int indexBottom = xyTo1d(n - 1, i);
            WQUUF.union(topSite, indexTop);
            WQUUF.union(bottomSite, indexBottom);
        }
    }

    private int xyTo1d(int row, int col) {
        return row * n + col;
    }

    /** open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
            throw new IndexOutOfBoundsException("the row and column indices must be integers between 0 and n - 1.");
        }
        if (isOpen(row, col)) {
            return;
        }
        state[row][col] = true;
        count += 1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 表示上、下、左、右四个方向
        int index = xyTo1d(row, col);
        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (nextRow < 0 ||  nextRow > n - 1 || nextCol < 0 || nextCol > n - 1) {
                continue;
            }
            if (isOpen(nextRow, nextCol)) {
                int nextIndex = xyTo1d(nextRow, nextCol);
                WQUUF.union(index, nextIndex);
            }
        }
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > n - 1|| col < 0 || col > n - 1) {
            throw new IndexOutOfBoundsException("the row and column indices must be integers between 0 and n - 1.");
        }
        return state[row][col];
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        if (row < 0 || row > n - 1 || col < 0 || col > n - 1) {
            throw new IndexOutOfBoundsException("the row and column indices must be integers between 0 and n - 1.");
        }
        int index = xyTo1d(row, col);
        return WQUUF.connected(topSite, index);
    }

    /** number of open sites. */
    public int numberOfOpenSites() {
        return count;
    }

    /** does the system percolate? */
    public boolean percolates() {
        return WQUUF.connected(topSite, bottomSite);
    }

    //  use for unit testing (not required)
    public void main(String[] args) {
        QuickFindUF QUUF = new QuickFindUF(20);
    }

}
