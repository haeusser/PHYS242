package HW04;

import java.io.*;

public class problem05 {

	public static void main(String[] args) {
		
		// file I/O
		PrintStream stdout = System.out;
		
		// parameters
		int N = (int)1e6;
		
		// PART (i), (iii) -----------------------------------------------------
		double[] a = randArray(N); // generate random array
		philIO.tools.initializeIO("HW04-5-unsorted.txt"); // prepare output file
		printArray(a); // print unsorted array
		
		// sorting
		long startTime = System.currentTimeMillis(); // start timer
		heapSort(a); // do the sorting
		long endTime   = System.currentTimeMillis(); // stop timer
		long time1 = endTime - startTime; // keep duration of sorting
		philIO.tools.initializeIO("HW04-5-sorted.txt"); // prepare output file
		printArray(a); // print sorted array
		
		System.setOut(stdout); // go back to console output
		printArray(a,0,4); // print first 5 values
		printArray(a,N-5,N-1); // print last 5 values
		
		// PART (ii) -----------------------------------------------------------
		int[] indices = bracketingIndices(a, 0.7, 0, N-1);
		System.out.printf("\nThe value %.3f lies between the indices %d and %d\n",
										0.7, indices[0], indices[1]);
		
		// PART (iii) ----------------------------------------------------------
		N = (int)1e7;
		a = randArray(N); // generate random array
		
		// sorting
		startTime = System.currentTimeMillis(); // start timer
		heapSort(a); // do the sorting
		endTime   = System.currentTimeMillis(); // stop timer
		long time2 = endTime - startTime; // keep duration of sorting
		
		// print times to console
		System.out.printf("\nSorting of 10^6 random numbers took %d ms\n" +
					      "Sorting of 10^7 random numbers took %d ms\n",
					      time1, time2);
		double factor = ((double)time2)/time1;
		System.out.printf("This corresponds to a factor of %.3e", factor);
	}
	
	// PART (i) ----------------------------------------------------------------
	/**
	 * Generate an array of given length containing uniformly distributed random
	 * numbers in the range of (0,1).
	 */
	private static double[] randArray(int length){
		
		double[] a = new double[length];
		
		for(int i=0; i<length; i++){
			a[i] = Math.random();
		}
		
		return a;
	}
	
	/**
	 * Reorganizes an array representing a heap tree
	 * @param a		array to be sorted
	 * @param from	starting index for reorganizing
	 * @param to	last index for reorganizing
	 */
	private static void reorganizeHeap(double[] a, int from, int to){
		
		int 	i = from,  // left node
				j = 2*i+1; // right node
		double 	x = a[i];  // element I'm looking at
		
		if((j<to) && (a[j+1]>a[j])) j++;	 // now j is the bigger node
		
		while((j <= to) && (a[j]>x)){ 		 // if there's a bigger node
			a[i] = a[j];
			i 	 = j;
			j    = 2*i+1;
			if((j<to) && (a[j+1]>a[j])) j++; // now j is the bigger node
		}
		a[i] = x;
	}
	
	/**
	 * Implementation of the heap sort algorithm
	 * @param a	array to be sorted
	 */
	private static void heapSort(double[] a){
		int N = a.length;
		
		// start from last node with sub-nodes and work through heap
		for(int from = N/2-1; from >= 0; from--){
			reorganizeHeap(a, from, N-1);
		}
		
		for(int to=N-1; to>0; to--){
			// swap respective elements
			double x = a[0];
			a[0] = a[to];
			a[to] = x;
			reorganizeHeap(a, 0, to-1);
		}
	}
	
	// PART (ii) ---------------------------------------------------------------
	/**
	 * Returns the weo indices "bracketing" a given value in an array
	 * @param a		array
	 * @param value	desired value (e.g. 0.7)
	 * @param lower	lower estimate (e.g. 0)
	 * @param upper	upper estimate (e.g. N)
	 * @return
	 */
	private static int[] bracketingIndices(double[] a, double value, int lower, int upper){
		
		// get midpoint
		int midp  = (lower+upper)/2;
		
		// stop if we have "bracketed" the value
		if(lower+1 == upper){
			int[] result = {lower, upper};
			return result;
		}
		
		// check if value lies between lower&midp or between midp&upper
		if(Math.signum(a[lower]-value) != Math.signum(a[midp]-value)){
			return bracketingIndices(a, value, lower, midp);
		} else{
			return bracketingIndices(a, value, midp, upper);
		}
	}
	
	/**
	 * Print an array (overloaded fn)
	 */
	private static void printArray(double[] a){
		for(int i=0; i<a.length; i++){
			System.out.println(a[i]);
		}
	}
	
	/**
	 * Print an array (overloaded fn)
	 * @param from	starting index
	 * @param to	stopping index
	 */
	private static void printArray(double[] a, int from, int to){
		from = Math.max(from, 0);
		to   = Math.min(to, a.length);
		
		for(int i=from; i<=to; i++){
			System.out.println(a[i]);
		}
	}
	
}
