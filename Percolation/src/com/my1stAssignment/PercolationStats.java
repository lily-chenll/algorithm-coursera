package com.my1stAssignment;

import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;
import com.my1stAssignment.Percolation;

public class PercolationStats {
	private Percolation p;
	private double[] threholds;
	
	public PercolationStats(int n, int trials) {
		int i = 0;
		threholds = new double[trials];
		while (i < trials) {
			p = new Percolation(n);
			
			while (!p.percolates()) {
				int row = StdRandom.uniform(n) + 1;
				int col = StdRandom.uniform(n) + 1;
				
				p.open(row, col);
			}
			
			double threhold = ((double)p.numberOfOpenSites()) / (n * n);
			threholds[i] = threhold;
			i++;
		}
		
	}
	
	public double mean() {
		return StdStats.mean(threholds);
	}
	
	public double stddev() {
		return StdStats.stddev(threholds);
	}
	
	public double confidenceLo() {
		int trails = threholds.length;
		return (mean() - 1.96 * stddev() / Math.sqrt(trails));
	}
	
	public double confidenceHi() {
		int trails = threholds.length;
		return (mean() + 1.96 * stddev() / Math.sqrt(trails));
	}
	
	public static void main(String[] args) {
		StdOut.println("Input grid length: ");
		int n = StdIn.readInt();
		StdOut.println("Input trial times: ");
		int m = StdIn.readInt();
		
		Stopwatch watch = new Stopwatch();
		
		PercolationStats ps = new PercolationStats(n, m);
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = [" + ps.confidenceLo() + " , " + ps.confidenceHi() + "]");
		StdOut.println("time: " + watch.elapsedTime() + "s");
	}
}
