package HW04;

import java.io.*;

public class problem04 {

	public static void main(String[] args) {
		
		// file I/O
		PrintStream stdout = System.out;
		philIO.tools.initializeIO("HW04-4-out.txt");
		
		// define params
		double 	x0 = 0,  	// start value of x
				xn = 1, 	// target value of x
				psi0 = 0, 	// start value of psi(x)
				psii0 = 1,  // target value of psi'(x)
				dx = 0.2,	// starting step size
				prec = 1e-5,// 5 digits precision
				diff = Double.MAX_VALUE;
		
		// do first round
		double psiAt1 = RK4(x0, xn, psi0, psii0, dx);
		
		do{
			dx /= 2;
			double 	newPsiAt1 = RK4(x0, xn, psi0, psii0, dx);
			diff = Math.abs(newPsiAt1-psiAt1);
			psiAt1 = newPsiAt1;
		}while(diff>prec);
		
		System.setOut(stdout);
		System.out.printf("\nPsi(x=1) = %.5f (with dx = %.3e )",psiAt1, dx);
	}
	
	// differential equation "function"
	private static double dpsiidt(double x){
		return -Math.PI*Math.PI*x;
	}

	/**
	 * Implementation of RK4
	 * @param x0	start value for x
	 * @param xn	target value for x
	 * @param psi0	start value for psi
	 * @param psii0	start value for psi'
	 * @param dx	step size
	 * @return		psi(xn)
	 */
	private static double RK4(double x0, double xn, double psi0, double psii0, double dx){
		
		double x   = x0,
			   psi = psi0,
			   psii = psii0;
		
		// calculate number of steps
		int n = (int)Math.round((xn-x0)/dx);
		
		// print header
		System.out.printf("#x\tpsi(x)\tpsi'(x)\n");
		
		// evaluate coupled o.d.e.
		for(int i=0; i<n; i++){
			
			// print current configuration
			System.out.printf("%.5f\t%.5f\t%.5f\n", x, psi, psii);
			
			// do steps
			double k1psi = psii,
				   k1psii = dpsiidt(psi),
				   k2psi = psii + 0.5*dx*k1psii,
				   k2psii = dpsiidt(psi+0.5*dx*k1psi),
				   k3psi = psii + 0.5*dx*k2psii,
				   k3psii = dpsiidt(psi+0.5*dx*k2psi),
				   k4psi = psii + dx*k3psii,
				   k4psii = dpsiidt(psi+dx*k3psi);
			
			psi += dx/6*(k1psi+2*k2psi+2*k3psi+k4psi);
			psii += dx/6*(k1psii+2*k2psii+2*k3psii+k4psii);
			x += dx;
		}
		return psi;
	}
}
