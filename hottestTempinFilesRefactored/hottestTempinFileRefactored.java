/**
 * use CSVParser to parse a csv file into a parser of records of weather conditions
 * find different bits of information about temperature, humidity, average...
 * @DinhHuuNguyen
 * @01/12/2016
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class hottestTempinFileRefactored {
    
    public CSVRecord getHottestOfTwo (CSVRecord currentRow, CSVRecord hottestTempRow) {
		if (hottestTempRow == null) {
			hottestTempRow = currentRow;
		}
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double largestTemp = Double.parseDouble(hottestTempRow.get("TemperatureF"));
			if (largestTemp < currentTemp) {
				hottestTempRow = currentRow;
			}
		}
		return hottestTempRow;
	}
	
	public CSVRecord getColdestTempRow (CSVRecord currentRow, CSVRecord coldestTempRow) {
        if (coldestTempRow == null) {
            coldestTempRow = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestTempRow.get("TemperatureF"));
            if (-999.0 < currentTemp && currentTemp < coldestTemp) {
                coldestTempRow = currentRow;
            }
        }
        return coldestTempRow;
    }
	
    public CSVRecord getLowestHumRow (CSVRecord currentRow, CSVRecord lowestHumRow) {
        if (lowestHumRow == null) {
            lowestHumRow = currentRow;
        }
        else {
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            double lowestHum = Double.parseDouble(lowestHumRow.get("Humidity"));
            if (currentHum < lowestHum) {
                lowestHumRow = currentRow;
            }
        }
        return lowestHumRow;
    }
    
    public CSVRecord hottestTempinFile(CSVParser parser) {
		CSVRecord hottestTempRow = null;
		for (CSVRecord currentRow : parser) {
			hottestTempRow = getHottestOfTwo(currentRow, hottestTempRow);
		}
		return hottestTempRow;
	}
	
	public CSVRecord hottestTempinFiles() {
		CSVRecord hottestTempRow = null;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = hottestTempinFile(fr.getCSVParser());
			hottestTempRow = getHottestOfTwo(currentRow, hottestTempRow);
		}
		return hottestTempRow;
	}
    
    public CSVRecord coldestTempinFile(CSVParser parser) {
        CSVRecord coldestTempRow = null;
        for (CSVRecord currentRow : parser) {
            coldestTempRow = getColdestTempRow(currentRow, coldestTempRow);
        }   
        return coldestTempRow;
    }
    
     public CSVRecord coldestTempinFiles() {
        CSVRecord coldestTempRow = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = coldestTempinFile(fr.getCSVParser());
			coldestTempRow = getColdestTempRow(currentRow, coldestTempRow);
		}
		return coldestTempRow;
    }
    
    public String fileWithColdestTemp() {
        CSVRecord coldestTempRow = null;
        String coldestTempFile = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestTempinFile(fr.getCSVParser());
            coldestTempRow = getColdestTempRow(currentRow, coldestTempRow);
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestTempRow.get("TemperatureF"));
            if (coldestTempFile.equals("") || (-999 < currentTemp &&
            currentTemp == coldestTemp)) {
                    coldestTempFile = f.getName();             
            }
        }
        return coldestTempFile;
    }
    
    public CSVRecord lowestHuminFile (CSVParser parser) {
        CSVRecord lowestHumRow = null;
        for (CSVRecord currentRow : parser) {
            if (! currentRow.get("Humidity").equals("N/A"))
            lowestHumRow = getLowestHumRow(currentRow, lowestHumRow);
        }   
        return lowestHumRow;
    }
    
        public CSVRecord lowestHuminFiles() {
        CSVRecord lowestHumRow = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = lowestHuminFile(fr.getCSVParser());
			lowestHumRow = getLowestHumRow(currentRow, lowestHumRow);
		}
		return lowestHumRow;
    }
    
    public double averageTempinFile (CSVParser parser) {
        double totalTemp = 0;
        int numberofrecords = 0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            totalTemp = totalTemp + currentTemp;
            numberofrecords += 1;
        }
        double averageTemp = totalTemp / numberofrecords;
        return averageTemp;
    }
    
    public double averageTempWithHighHum (CSVParser parser, int value) {
        double totalTemp = 0;
        int numberofrecords = 0;
        for (CSVRecord currentRow : parser) {
            int currentHum = Integer.parseInt(currentRow.get("Humidity"));            
            if (currentHum >= value) {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                totalTemp = totalTemp + currentTemp;
                numberofrecords += 1;
            }
        }
        double averageTemp = totalTemp / numberofrecords;
        return averageTemp;        
    }
    
    public void testhottestTempinFile () {
        String filename = "data/2015/weather-2015-01-01.csv";
		FileResource fr = new FileResource(filename);
		CSVRecord hottestTempRow = hottestTempinFile(fr.getCSVParser());
		System.out.println("hottest temperature in " + filename + " was " + hottestTempRow.get("TemperatureF") +
				   " at " + hottestTempRow.get("TimeEST"));
	}
	
	public void testhottestTempinFiles () {
		CSVRecord hottestTempRow = hottestTempinFiles();
		System.out.println("hottest temp in selected files was " + hottestTempRow.get("TemperatureF")
		+ " at " + hottestTempRow.get("DateUTC"));
	}
	
	public void testcoldestTempinFile () {
	    String filename = "data/2014/weather-2014-05-01.csv";
        FileResource fr = new FileResource(filename);
        CSVRecord coldestTempRow = coldestTempinFile(fr.getCSVParser());
        System.out.println("information of coldest day in " + filename);
        System.out.println(coldestTempRow.get("TimeEDT") + "\t" + coldestTempRow.get("TemperatureF") + "\t" 
        + coldestTempRow.get("Humidity") + "\t" + "\t" + coldestTempRow.get("DateUTC"));
    }
    
    public void testcoldestTempinFiles() {
        CSVRecord coldest = coldestTempinFiles();
        System.out.println("coldest temp in selected fiels was " + coldest.get("TemperatureF")
        + " at " + coldest.get("DateUTC"));
    }
    
    public void testfileWithColdestTemp() {
        System.out.println("coldest temp in selected files was in " + fileWithColdestTemp());
    }
    
    public void testlowestHuminFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumrecord = lowestHuminFile(parser);
        System.out.println("information of most humid day");
        System.out.println(lowestHumrecord.get("Humidity") + "\t" + lowestHumrecord.get("DateUTC"));
    }
    
    public void testlowestHuminFiles() {
        CSVRecord lowest = lowestHuminFiles();
        System.out.println("lowest hum in 2014 was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testaverageTempinFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("average temp in file is " + averageTempinFile(parser));
    }
    
     public void testaverageTempWithHighHum () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTempWithHighHum(parser, 80);
        if (Double.isNaN(averageTemp)) {
            System.out.println("no temperature with that humidity");
        }
        else {
            System.out.println("average temp with high hum is " + averageTemp);
        }
    }
    
}