/*
    Author: Hisni Mohammed M.H.  (E/15/131)
    Date: 07/12/2018
    Lab 03 | Matrix multiplication using Java threads
*/
import java.io.*;
import java.lang.*;

public class Matrix extends Thread { 

    private static int [][] a;
    private static int [][] b; 
	public static int [][] c;		//Resultant Matrix of A*B
	private static int numRow;		//Number of rows in resultant matrix
	private static int numColumn;	//Number of columns in resultant matrix
	private static int length;		//Length for multiplication
	private static int numThread;	//Threads Count
	private int start;		//Starting and Ending row number for a particular thread to multiply
	private int end;
		
	public Matrix( int threadID ) {
		start = (int)Math.floor( (threadID)*( (double)numRow/numThread ) );	//Determining rows to multiply in one thread.
		end = (int)Math.floor( (threadID+1)*( (double)numRow/numThread ) );

		if( threadID == numThread-1 ){		//In last thread making sure all rows are considered for multiply( Remaining rows are cosidered )
			end = numRow;
		}
	}

	public static void setThreadCount( int threadCount ) {	//Method to set Thread count
		numThread = threadCount;
	}

	public static void initializeMatrices( int [][] m, int [][] n ){	//Method to set Matrices A,B and initialize Resultant Matrix
		a = m;
		b = n;
		numRow = a.length;
		numColumn = b[0].length;
		length = b.length;
		c = new int [ numRow ][ numColumn ];
	}

	public void run(){		//Matrix Multiplication
		try{
			int i, j, k; 
			for( i=start; i < end; i++ ){ 
				for( j=0; j < numColumn; j++ ) {
					for( c[i][j] = 0, k=0; k<length; k++ ){ 
						c[i][j] += a[i][k] * b[k][j];
					}
				}
			}
		}
		catch(NullPointerException e){
			System.out.println("Matrix Limit Exceed");
			System.exit(-1);
		}
	}
}