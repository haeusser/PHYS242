package MC;

public class Lattice {
	
	private int L;
	private int[][] spins;
	private double T,
					tauEquil;
	public static double Tc = 2/(Math.log(Math.sqrt(2)+1));
					
	
	public Lattice(int L, double T){
		this.L = L;
		this.T = T;
		spins = new int[L][L];
		
		if(T>Tc) randomizeSpins(spins);
		else initializeSpins(spins);
		
		tauEquil = getTauEquil();
		
		equilibrate();
	}
	
	private double getTauEquil(){
		return L*L*Math.exp(-(T-Tc)*(T-Tc)/5);
	}
	
	private void initializeSpins(int[][] spins){
		for(int i=0; i<L; i++){
			for(int j=0; j<L; j++){
				spins[i][j] = 1;
			}
			
		}
	}

	private void randomizeSpins(int[][] spins){
		for(int i=0; i<L; i++){
			for(int j=0; j<L; j++){
				spins[i][j] = randomSpin();
			}
			
		}
	}
	
	private static int randomSpin(){
		return Math.random()<0.5?-1:1;
	}
	
	
	public double getMagnetization2(){
		double M=0;
		for(int i=0; i<L; i++){
			for(int j=0; j<L; j++){
				M += spins[i][j];
			}
			
		}
		return M*M/(L*L*L*L);
	}
	

	

	
	public void printLattice(){
		for(int i=0; i<L; i++){
			for(int j=0; j<L; j++){
				System.out.printf("%d\t",spins[i][j]);
			}
			System.out.println();
		}
	}
	
	public void printLatticeVecFormat(){
		for(int i=0; i<L; i++){
			System.out.print("[");
			for(int j=0; j<L; j++){
				System.out.printf("%d",spins[i][j]);
				if(j<L-1) System.out.print(",");
			}
			System.out.print("]");
		}
	}
	
	private void doSweep(){
		for(int i=1; i<L+1; i++){
			for(int j=1; j<L+1; j++){
				int spin = spins[i%L][j%L],
					NN1 = spins[(i+1)%L][j%L],
					NN2 = spins[(i-1)%L][j%L],
					NN3 = spins[i%L][(j+1)%L],
					NN4 = spins[i%L][(j-1)%L],
					deltaE = 2*spin*(NN1+NN2+NN3+NN4);
				
				double r = Math.random(),
						metropolisCondition = Math.exp(-deltaE/T);
				
				if(r<metropolisCondition) spins[i%L][j%L] *= -1;

			}
		}
	}
	
	private void equilibrate(){
		//System.out.print("[");
		for(int i=0; i<tauEquil; i++){
			
			//printLatticeVecFormat(); // --------
			doSweep();
			//if(i<tauEquil-1) System.out.print(",");
		}
		//System.out.print("]");
	}
	
	public double measureM2(int Mmeas){
		double m = 0;
		for(int i=0; i<Mmeas; i++){
			this.doSweep();
			m += getMagnetization2();
		}
		return m/Mmeas;
	}
	

	public double[] measureMagnetization(int Mmeas){
		double[] m = new double[2];
		
		for(int i=0; i<Mmeas; i++){
			this.doSweep();
			double measurement = getMagnetization2();
			m[0] += measurement;
			m[1] += measurement*measurement;
		}
		m[0] /= Mmeas;
		m[1] /= Mmeas;
		return m;
	}

	
}
