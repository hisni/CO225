import java.io.*;
import java.lang.*;

public class Matrix extends Thread { 

    private static int [][] a; 
    private static int [][] b; 
	protected static int [][] c; 
	private static int numRow;
	private static int numColumn;
	private static int numThread;

	private int start;
	private int end;
	
		
	public Matrix(int [][] m, int [][] n, int threadCount, int threadID ) {
		a = m;
		b = n;
		numRow = a.length;
		numColumn = b[0].length;
		numThread = threadCount;
		c = new int [ numRow ][ numColumn ];

		start = (int)Math.floor( (threadID)*( (double)numRow/numThread ) );
		end = (int)Math.floor( (threadID+1)*( (double)numRow/numThread ) );

	}
	
	public void run(){
		int length = b.length;
		try{
			if( a[0].length != length ) { 
				throw new IOException("Matrix could not multicable");
			}

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
			System.out.println("Matrix limit exceed");
			System.exit(-1);
		}catch(IOException e){
			System.out.println(e);
			System.exit(-1);
		}
	}
}