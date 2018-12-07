/*
    Author: Hisni Mohammed M.H.  (E/15/131)
    Date: 07/12/2018
    Lab 03 | Matrix multiplication using Java threads
*/
import java.io.*;

class Main { 
	public static int [][] a = { {0, 2, 2}, {1, 1, 1}, {1, 1, 1}, {1, 1, 0}, {0, 1, 1}, {1, 0, 1} };
	public static int [][] b = { {1,2,1,3 ,1 }, {1,0,1,1 ,2 }, {0,1,3,2 ,1 } };
	public static int numThreads=4;				//Number of Threads

    public static void print_matrix(int [][] matrix) {		//Method to print Matrices
		for(int i=0; i < matrix.length; i++) {
			for(int j=0; j< matrix[i].length; j++) 
			System.out.print(matrix[i][j] + " "); 
			System.out.println();
		}
	}
    public static void main(String [] args) {

		if( a[0].length != b.length ) {		//Checking condition for valid matrix multiplication 
			System.out.println("Matrix could not multicable");
			return;
		}
		
		if( numThreads > a.length ){		//If number of the threads are greater than number of rows set thread count to number of rows
			numThreads = a.length;
		}
		
		Matrix.setThreadCount( numThreads );		//Set number of threads to be used
		Matrix.initializeMatrices( a, b );			//Set matrix A,B and initialize resultant matrix.
		Matrix [] threads = new Matrix[numThreads];	//Create array of threads to process

		/*
			One thread evaluats some part of the matrix multiplication. 
			Each thread evaluats selected number of distinct rows. 
			Each thread process (TotalRows/TotalThreads) and final thread evaluats remaining rows.
			Thread ID is used to determine which rows to be processed in particular thread
			Since Results for Each elements are independent of other elements each,
			Results of each threads are independent of other results.
			But need to print the Result after all Threads are completed their work
		*/
		
		for( int i = 0; i < numThreads; i++){
			threads[i] = new Matrix( i );			//Creating thread and starting
			threads[i].start();
		}

		for( int i = 0; i < numThreads; i++){		//Making sure all threads are completed.
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				System.out.println("Thread error");
			}
		}
		print_matrix( Matrix.c );		//Printing Resultant Matrix
	}
}