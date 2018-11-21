public class Matrix extends Thread { 

    private static int [][] a; 
    private static int [][] b; 
    public static int [][] c; 
	private static int start;
	private static int end;
	private static int numThreads;
	

    /* You might need other variables as well */

    public Matrix(int [][] m, int [][] n, int d ) { // need to change this, might need some information 
		this.a = m;
		this.b = n;
		this.numThreads = d;
	}
	
    public static int [][] multiply() {

	/* check if multipication can be done, if not 
	 * return null 
	 * allocate required memory 
	 * return a * b
	 */

	int x = a.length; 
	int y = b[0].length; 

	int z1 = a[0].length; 
	int z2 = b.length; 

	if(z1 != z2) { 
	    System.out.println("Cannnot multiply");
	    return null;
	}

	
	int i, j, k, s; 

	for(i=0; i<x; i++) 
	    for(j=0; j<y; j++) {
		for(s=0, k=0; k<z1; k++) 
		    s += a[i][k] * b[k][j];
		c[i][j] = s;
	    }

	return c; 
	}
	
	public void run(){
		int x = a.length; 
		int y = b[0].length; 

		int z1 = a[0].length; 
		int z2 = b.length; 

		/*if(z1 != z2) { 
	    	System.out.println("Cannnot multiply");
	    	return null;
		}*/

		c = new int [x][y]; 
		int i, j, k, s; 

		for(i=0; i<x; i++){ 
	    	for(j=0; j<y; j++) {
				for(s=0, k=0; k<z1; k++) 
		    		s += a[i][k] * b[k][j];
				c[i][j] = s;
			}
		}

		//return c;
	}
}