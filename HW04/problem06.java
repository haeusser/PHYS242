package HW04;

public class problem06 {

	public static void main(String[] args) {
		
		// file I/O
		philIO.tools.initializeIO("HW04-6-out");
		String datafile = "HW04-6-data.txt";

		// read text file with data
		double[][] data = philIO.tools.readYoungsTxt(datafile);
		
		// do the fit and save params in this variable
		double[] params = linFit(data);
		
		// print out the result
		System.out.printf("y = (%.3f +/- %.3f) + (%.3f +/- %.3f)*x\n" +
				"chi^2/Ndof = %.3f\n" +
				"Q = %.3f",
				params[0],params[1],params[2], params[3],params[4],params[5]);
	}
	
	/**
	 * Performs a linear fit y = a*x + b
	 * @param data	input data where data[i][0] = x_i
	 * 								 data[i][1] = y_i
	 * 								 data[i][2] = sigma_i
	 * @return parameters of the fit result[0]  = b
	 * 								 result[1]  = error(b)
	 * 								 result[2]  = a
	 * 								 result[3]  = error(b)
	 * 								 result[4]  = chi^2 / (degrees of freedom)
	 * 								 result[5]  = Q
	 */
	private static double[] linFit(double[][] data){
		int N    = data.length, // number of data points
			Ndof = N-2;	// number of degrees of freedom
		
		// linear fit: y = a*x + b
		// following the handout, do the "matrix method"
		// for 2D we're interested in 3 matrix and 2 vector components:
		double U00=0, U01=0, U11=0,	// U matrix elements
			   v0=0, v1=0,			// v vector elements
			   chi2 = 0;			// chi^2
		
		double[] result = new double[6]; // allocate result array
		
		// fill matrix U and vector v
		for(int i=0; i<N; i++){
			double sigma2 = data[i][2]*data[i][2];
			U00 += 1 / sigma2;
			U01 += data[i][0] / sigma2;
			U11 += data[i][0]*data[i][0] / sigma2;
			v0  += data[i][1] / sigma2;
			v1  += data[i][0]*data[i][1] / sigma2;
		}
		
		// calculate determinant and find fitting params from it
		double det = U00*U11-U01*U01,
				a0 = (U11*v0-U01*v1)/det,
				a1 = (-U01*v0 + U00*v1)/det;
		
		// store fitting params and errors in the result array
		result[0] = a0; // a0
		result[1] = Math.sqrt(U00/det); // error(a0)
		result[2] = a1;	// a1
		result[3] = Math.sqrt(U11/det); // error(a1)
		
		// calculate sigma^2
		for(int i=0; i<N; i++){
			double sigma = data[i][2];
			chi2 += Math.pow((data[i][1]-linF(a1,a0,data[i][0]))/sigma, 2);
		}
		
		// calculate sigma^2 per degree of freedom
		result[4] = chi2/Ndof;
		
		// calculate quality factor
		result[5] = Math.exp(-result[4]/2);
		
		return result;
	}
	
	/**
	 * Linear function
	 * @param a	slope
	 * @param b	intercept
	 * @param x evaluation point
	 * @return
	 */
	private static double linF(double a, double b, double x){
		return a*x+b;
	}
}
