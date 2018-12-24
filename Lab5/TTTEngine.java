/*
    Lab 05 | TicTacToe Game
    E/15/131
*/
public class TTTEngine {
    public int counter = 0;
    private int[][] grid = new int[3][3];

    //Method to map buttons to a matrix and record button cliked instance
    //If Player 1 is clicked mark the cooresponding elemnt in the matrix with -1 and
    //If Player 2 is clicked mark 1
    public void setGrid( int buttonNum , String player ){
        Integer gridNum = null;
        if( player.equals("1") ){
            gridNum = -1;
        }else if( player.equals("2") ){
            gridNum = 1;
        }
        grid[ buttonNum/3 ][ buttonNum %3 ] = gridNum;
        counter++;
    }

    //Method to check winning possibilities.( 8 possibilities)
    //Returns true if a  player is won and false otherwise 
    public boolean checkResult(){   
        int mainDiag = 0;
        int minorDiag = 0;
        int column = 0;
        int row = 0;
        
        for (int i=0; i<3; i++){
            mainDiag = mainDiag + grid[i][i];
            minorDiag = minorDiag + grid[i][2-i];
            
            for( int j=0; j<3; j++ ){
                column = column + grid[j][i];
                row = row + grid[i][j];
            }

            if( Math.abs(column) == 3 || Math.abs(row) == 3){   //Checks for Columns and Rows for winning possibilities
                return true;
            }else{
                column = 0;
                row = 0;
            }
        }
        if(Math.abs( mainDiag ) == 3 || Math.abs( minorDiag ) == 3){    //Checks for diagonals for winning possibilities
            return true;
        }
        return false;
    }
    
}