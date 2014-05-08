package MD;

public class MDMainFPU {
	
	// define global constants ------------------------------------------------
	static boolean printEvalues = true;	// for plotting/debugging
	static final int 	N = 30;			// number of atoms, N>1
	static double 		h = 0.02,		// time step size
			     		tEstim = 4000,	// time we run the actual simulation
			     		delta = 1./3;	// symmetry-avoiding shift for v init.
	
	public static void main(String[] args) {
		
		// initilize printing output to file ----------------------------------
		String filename = "MD-FPU-harm-2";
		philIO.tools.initializeIO(filename + ".txt");
		
		// initialization -----------------------------------------------------
		// create arrays
		double[] 	y = new double[N],		// initial displacements
					v = new double[N];		// initial velocities

		for(int i=0; i<N; i++){
			v[i] = MDFunctionsFPU.vInitial(i, delta, N);
		}
		
		// print header
		if(printEvalues){
			System.out.print("#m=\n#");
			for(int m = -N/2+1; m<=N/2; m++){System.out.printf("%d\t", m);}
			System.out.println();
		}
		
		// simulation ---------------------------------------------------------
		double estimSteps = tEstim/h; // calculate how many steps are needed
		
		// do the position verlet steps
		for(int i=0; i<estimSteps; i++){
			
			// print energy values (every 1 time steps)
			if(printEvalues && (i*h)%1==0){ 
				for(int m = -N/2+1; m<=N/2; m++){
					double 	aDotSquared = MDFunctionsFPU.squaredFourierComp(m, N, v),
							aSquared = MDFunctionsFPU.squaredFourierComp(m, N, y),
							omega = MDFunctionsFPU.omegaM(m, N),
							Em = MDFunctionsFPU.energyM(m, aDotSquared,
																omega, aSquared);
					System.out.printf("%.6f\t",Em);
				}
				System.out.println();
			}
			
			// do the position verlet step
			MDFunctionsFPU.posVerlet(N, h, y, v);
		}	
	}
}