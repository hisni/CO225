import java.io.*;
import java.util.*;

public class StocksDB{
    public Map<String,StockInfo> stocksDetails = new HashMap<String, StockInfo>();
    private String [] header;

    public StocksDB( String csvFile )  {

        FileReader fileRead=null; 
        BufferedReader csvRead=null;
        
        String stockRead="";
        String [] splitStockInfo;

        try { 
            csvRead = new BufferedReader( new FileReader( csvFile ) );

            header = csvRead.readLine().split(",");
            int IndexSymbol = findIndex("Symbol");
	        int IndexName = findIndex("Security Name");
            int IndexPrice = findIndex("Price");
            
            while( (stockRead = csvRead.readLine()) != null ){
                splitStockInfo = stockRead.split(","); 
                stocksDetails.put( splitStockInfo[IndexSymbol], new StockInfo(splitStockInfo[IndexName], Float.parseFloat(splitStockInfo[IndexPrice]) ) );
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        } finally {
            if ( csvRead != null ) {
                try {
                    csvRead.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private int findIndex( String key ){
        for(int i=0; i < header.length; i++){
            if( header[i].equalsIgnoreCase(key) ){
                return i;
            }
        }
        return -1;
    }

    public void printStock( ) {
        for( Map.Entry< String, StockInfo > entry : stocksDetails.entrySet() ){
            String key = entry.getKey();
            System.out.println( key + " " + getSecurityName(entry.getValue()) + " " + stockPrice(entry.getValue()) );
        }
    }

    public String getSecurityName( StockInfo key ){
        return key.getName();
    }

    public float stockPrice( StockInfo key ){
        return key.getPrice();
    }

}

class StockInfo{
    private String SecurityName;
    private float stockPrice;

    public StockInfo(String SName, float price ) {
        SecurityName = SName;
        stockPrice = price;
    }

    public String getName() {
        return SecurityName;
    }

    public float getPrice() {
        return stockPrice;
    }

}  