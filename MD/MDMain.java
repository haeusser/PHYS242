package MD;

public class MDMain {
	
	// define global constants ------------------------------------------------
	static boolean printXvalues = true;	// for plotting/debugging
	static final int 	N = 30;			// number of atoms, N>1
	static double 		h = 0.02,		// time step size
			     		tEquil = 1000,	// time we discard for <v^2> estimate
			     		tEstim = 4000,	// time we run the actual simulation
			     		delta = 1./3,	// symmetry-avoiding shift for v init.
			     		binSize = 0.1, // bin size for velocity histogram
			     		vRange = 100,	// range of velocities for histogram
										// (will get updated later)
						vMin = -3, 		// needed for histogram ...
						vMax = 3;		// ... inferred from first run
	static double[]		hist;			// velocity histogram
	
	public static void main(String[] args) {
		
		// initilize printing output to file ----------------------------------
		java.io.PrintStream original = System.out; // record stdout
		String filename = "MD5";
		philIO.tools.initializeIO(filename + ".txt");
		
		// initialization -----------------------------------------------------
		// create arrays
		double[] 	x = new double[N],		// initial positions
					v = new double[N];		// initial velocities

		for(int i=0; i<N; i++){
			x[i] = i;
			v[i] = MDFunctions.vInitial(i, delta, N);
			
			// for histogram, get v_min and v_max, if not known yet
			// if (v[i] > vMax) vMax=v[i];
			// if (v[i] < vMin) vMin=v[i];
		}
		
		// create array used for histogram
		// determine range of velocities: factor 2 accounts for pos./neg
		// extra factor 2 to catch outlier velocities
		vRange = 4*Math.max(Math.abs(vMax), Math.abs(vMin));

		hist = new double[(int)(vRange/binSize)];
		
		// equilibration ------------------------------------------------------
		{	double equilSteps = tEquil/h; // calculate how many steps are needed
			for(int i=0; i<equilSteps; i++){
				// print positions in file
				//if(printXvalues){System.out.print(i); MDFunctions.printX(x);}
				MDFunctions.posVerlet(N, h, x, v);
			}
		}	// now the system should be equilibrated
		
		// simulation ---------------------------------------------------------
		{	double estimSteps = tEstim/h; // calculate how many steps are needed
			double[] aveVsquare = new double[N]; // here we store <v^2>
			
			// do the position verlet steps
			for(int i=0; i<estimSteps; i++){
				// print positions in file
				if(printXvalues){System.out.print(i); MDFunctions.printXnV(x,v);}
				
				// do the position verlet step
				MDFunctions.posVerlet(N, h, x, v);
				
				// record v^2 for each atom
				for(int j=0; j<N; j++) aveVsquare[j] += v[j]*v[j];
				
				// add velocities to histogram
				MDFunctions.fillHist(v, hist, binSize);
			}
			
			// analysis: <v^2> ------------------------------------------------
			// divide each v^2 by number of samples to get average v^2 per atom
			for(int j=0; j<N; j++) aveVsquare[j] /= estimSteps;
			
			// now average over all atoms to get overall average of v^2
			double aveVsquareAll = 0;
			for(int j=0; j<N; j++) aveVsquareAll += aveVsquare[j];
			aveVsquareAll /= N;
			
			System.setOut(original);
			System.out.printf("# Average v^2 over all atoms: <v^2> = %.12f",
															aveVsquareAll);
		}	
		
		// analysis: histogram ------------------------------------------------
		MDFunctions.normalizeHisto(hist);
		philIO.tools.initializeIO(filename + "-histo.txt");
		MDFunctions.printHisto(hist, binSize);
	}
}
