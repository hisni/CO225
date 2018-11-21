class Main { 
	public static int [][] a = { {1, 1, 1}, {1, 1, 1}, {1, 1, 1} };
    public static int [][] b = { {1,2,1 }, {1,3,2 }, {1,4,3 } };
	public static int numThreads;

    public static void print_matrix(int [][] a) {
	for(int i=0; i < a.length; i++) {
	    for(int j=0; j< a[i].length; j++) 
		System.out.print(a[i][j] + " "); 
	    System.out.println();
	}
    }
    

    public static void main(String [] args) {
		Matrix mat = new Matrix(a, b, numThreads);
		mat.start();
		//int [][] x = mat.multiply();
		print_matrix(Matrix.c); // see if the multipication is correct 	
    }
}