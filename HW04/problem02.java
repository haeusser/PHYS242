package HW04;

import java.io.PrintStream;

public class problem02 {

	public static void main(String[] args) {
		
		// remember std output
		PrintStream stdout = System.out;
		
		// constants
		double t0 = 0,
			   tn = 7.416,
			   dt  = 0.02;
			   //E  = 0.25;
		
		// RK4 with 1 period
	    philIO.tools.initializeIO("HW04-2-RK4-1.txt");
		double rk41 = RK4(t0, tn, 1, 0, dt);
		
		// RK4 with 1000 periods
		philIO.tools.initializeIO("HW04-2-RK4-1000.txt");
		double rk41000 = RK4(t0, 1000*tn, 1, 0, dt);
		
		// FR with 1 period
		philIO.tools.initializeIO("HW04-2-FR-1.txt");
		double fr1 = FR(t0, tn, 1, 0, dt);
		
		// FR with 1000 periods
		philIO.tools.initializeIO("HW04-2-FR-1000.txt");
		double fr1000 = FR(t0, 1000*tn, 1, 0, dt);
		
		// print results (console)
		System.setOut(stdout);
		System.out.println("Method\tperiods\tmax. deviation");
		System.out.printf("RK4\t1\t%.5e\n", rk41);
		System.out.printf("RK4\t1000\t%.5e\n", rk41000);
		System.out.printf("FR\t1\t%.5e\n", fr1);
		System.out.printf("FR\t1000\t%.5e\n", fr1000);
	}
	
	// differential equation "function"
	private static double dpdt(double x){
		return -Math.pow(x, 3);
	}

	/**
	 * Implementation of RK4
	 * @param t0	start value for the time
	 * @param x0	start value for x, i.e. x0 = x(t0)
	 * @param p0	start value for momentum
	 * @param tn	target value for the time
	 * @param dt	time interval
	 * @return
	 */
	private static double RK4(double t0, double tn, double x0, double p0, double dt){
		
		double x = x0, // start at x = x0
			   p = p0, // start at p = p0
			   t = t0, // start at t = t0
			   dEmax = Double.MIN_VALUE; // bookkeeping: max deviation from E
		
		// calculate number of steps
		int n = (int)Math.round((tn-t0)/dt);
		
		// print header
		System.out.printf("#t\tx\tp\tE(t)\t|E-E(t)|/E\n");
		
		// evaluate coupled o.d.e.
		for(int i=0; i<=n; i++){
			
			// calculate energy for current x, p
			double Et = 0.5*p*p+0.25*x*x*x*x,
				   dE = Math.abs(Et-0.25)/0.25; // and the deviation
			
			// account for the maximum 
			dEmax = Math.max(dEmax, dE);
			
			// print current configuration
			System.out.printf("%.5f\t%.5f\t%.5f\t%.5f\t%.5e\n", t,x,p, Et, dE);
			
			// do steps
			double k1x = p,
				   k1p = dpdt(x),
				   k2x = p + 0.5*dt*k1p,
				   k2p = dpdt(x+0.5*dt*k1x),
				   k3x = p + 0.5*dt*k2p,
				   k3p = dpdt(x+0.5*dt*k2x),
				   k4x = p + dt*k3p,
				   k4p = dpdt(x+dt*k3x);
			
			x += dt/6*(k1x+2*k2x+2*k3x+k4x);
			p += dt/6*(k1p+2*k2p+2*k3p+k4p);
			t += dt;
		}
		return dEmax;
	}
	
	/**
	 * Implementation of the Forest-Ruth algorithm
	 * @param t0	start value for the time
	 * @param x0	start value for x, i.e. x0 = x(t0)
	 * @param p0	start value for momentum
	 * @param tn	target value for the time
	 * @param dt	time interval
	 * @return
	 */
	private static double FR(double t0, double tn, double x0, double p0, double dt){
		
		double c = 1.35120719195966, // constant theta
			   x = x0, // start at x = x0
			   p = p0, // start at p = p0
			   t = t0, // start at t = t0
			   dEmax = Double.MIN_VALUE; // bookkeeping: max deviation from E
			
		// calculate number of steps
		int n = (int)Math.round((tn-t0)/dt);
		
		// print header
		System.out.printf("#t\tx\tp\tE(t)\t|E-E(t)|/E\n");
		
		// evaluate coupled o.d.e.
		for(int i=0; i<=n; i++){
				// calculate energy for current x, p
				double Et = 0.5*p*p+0.25*x*x*x*x,
					   dE = Math.abs(Et-0.25)/0.25; // and the deviation
				
				// account for the maximum 
				dEmax = Math.max(dEmax, dE);
				
				// print current configuration
				System.out.printf("%.5f\t%.5f\t%.5f\t%.5f\t%.5e\n", t,x,p, Et, dE);

				// do steps
				x += 0.5*dt*c*p;
				p += c*dt*dpdt(x);
				x += (1-c)*0.5*dt*p;
				p += (1-2*c)*dt*dpdt(x);
				x += (1-c)*0.5*dt*p;
				p += c*dt*dpdt(x);
				x += c*0.5*dt*p;
				t += dt;
			}
			return dEmax;
	}
}
