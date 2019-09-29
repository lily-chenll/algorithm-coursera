//package com.my1stAssignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private boolean[][] grid;
    private int count;
    private final int len;
    private final WeightedQuickUnionUF uf;
	
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("n should be bigger than 0");  
		}
		grid = new boolean[n][n];
		uf = new WeightedQuickUnionUF(n * n);
		len = n;
		count = 0;
		// 1 -> blocked, 0 -> open
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = false;
			}
		}
	}
	
	private boolean validate(int num) {
		return num >= 1 && num <= len;
	}
	
	private int getIndex(int row, int col) {
		return row * len + col;
	}
	
	public void open(int row, int col) {
		if (!validate(row) || !validate(col)) {
			throw new IllegalArgumentException("index is not between 1 and " + len);  
		}
		
		int realRow = row - 1, realCol = col - 1;
		
		if (!grid[realRow][realCol]) {
			grid[realRow][realCol] = true;
			count++;
			
			int id0 = getIndex(realRow, realCol);
			
			if (realRow > 0) {
				if (grid[realRow-1][realCol] && !uf.connected(id0, getIndex(realRow-1, realCol))) {
					uf.union(id0, getIndex(realRow-1, realCol));
				}
			}
			
			if (realRow < len - 1) {
				if (grid[realRow+1][realCol] && !uf.connected(id0, getIndex(realRow+1, realCol))) {
					uf.union(id0, getIndex(realRow+1, realCol));
				}
			}
			
			
			if (realCol > 0) {
				if (grid[realRow][realCol-1] && !uf.connected(id0, getIndex(realRow, realCol-1))) {
					uf.union(id0, getIndex(realRow, realCol-1));
				}
			}
			
			if (realCol < len - 1) {
				if (grid[realRow][realCol+1] && !uf.connected(id0, getIndex(realRow, realCol+1))) {
					uf.union(id0, getIndex(realRow, realCol+1));
				}
			}
		}
	}
	
	public boolean isOpen(int row, int col) {
		if (!validate(row) || !validate(col)) {
			throw new IllegalArgumentException("index is not between 1 and " + len);  
		}
		return grid[row-1][col-1];
	}
	
	// need discuss
	public boolean isFull(int row, int col) {
		if (!validate(row) || !validate(col)) {
			throw new IllegalArgumentException("index is not between 1 and " + len);  
		}
		
		int realRow = row - 1, realCol = col - 1;
		
		if (grid[realRow][realCol]) {
			for (int i = 0; i < len; i++) {
				if (grid[0][i] && uf.connected(getIndex(realRow, realCol), i)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int numberOfOpenSites() {
		return count;
	}
	
	public boolean percolates() {
		int row = len;
		for (int i = 1; i <= len; i++) {
			if (isOpen(row, i) && isFull(row, i)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int n = 5;
		Percolation p = new Percolation(n);
		
		int i = 0;
		while (i < n*n) {
			StdOut.println(i + "------------------");
			int row = StdRandom.uniform(n) + 1;
			int col = StdRandom.uniform(n) + 1;
			StdOut.println("row: " + row);
			StdOut.println("col: " + col);
			p.open(row, col);

			i++;
		}
		StdOut.println("p.percolates: " + p.percolates());
		StdOut.println("p.numberOfOpenSites: " + p.numberOfOpenSites());
		StdOut.println("(1,4).isOpen: " + p.isOpen(1, 4));
		StdOut.println("(1,3).isOpen: " + p.isOpen(1, 3));
		StdOut.println("(4,5).isOpen: " + p.isFull(4, 5));
		StdOut.println("(4,4).isOpen: " + p.isFull(4, 4));
	}
}
