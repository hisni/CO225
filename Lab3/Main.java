import java.io.*;

class Main { 
	public static int [][] a = { {1, 2, 3}, {1, 1, 1}, {1, 1, 1} };
	public static int [][] b = { {1,2,1 }, {1,3,1 }, {1,4,1 } };
	public static int numThreads=3;

    public static void print_matrix(int [][] matrix) {
		for(int i=0; i < matrix.length; i++) {
			for(int j=0; j< matrix[i].length; j++) 
			System.out.print(matrix[i][j] + " "); 
			System.out.println();
		}
	}
    public static void main(String [] args) {

		if( numThreads > a.length ){
			numThreads = a.length;
		}
		
		Matrix [] threads = new Matrix[numThreads]; 

		for( int i = 0; i < numThreads; i++){
			threads[i] = new Matrix( a, b, numThreads, i );
			threads[i].start();
		}

		for( int i = 0; i < numThreads; i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				System.out.println("Thread error");
			}
		}
		
		print_matrix( Matrix.c );
	}
}