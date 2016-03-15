/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class hottestTempinFiles {
    
	public CSVRecord hottestTempinFile(CSVParser parser) {
		//start with largestTempRow as nothing
		CSVRecord largestTempRow = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			//If largestTempRow is nothing
			if (largestTempRow == null) {
				largestTempRow = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestTempRow.get("TemperatureF"));
				//Check if currentRow’s temperature > largestTempRow’s
				if (largestTemp < currentTemp) {
					//If so update largestTempRow to currentRow
					largestTempRow = currentRow;
				}
			}
		}
		//The largestTempRow is the answer
		return largestTempRow;
	}

	public CSVRecord hottestTempinFiles() {
		CSVRecord largestTempRow = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = hottestTempinFile(fr.getCSVParser());
			if (largestTempRow == null) {
				largestTempRow = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestTempRow.get("TemperatureF"));
				//Check if currentRow’s temperature > largestTempRow’s
				if (largestTemp < currentTemp) {
					//If so update largestTempRow to currentRow
					largestTempRow = currentRow;
				}
			}
		}
		//The largestTempRow is the answer
		return largestTempRow;
	}
	
	public void testhottestTempinFile () {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord largestTempRow = hottestTempinFile(parser);
		System.out.println("hottest temperature in file was " + largestTempRow.get("TemperatureF") +
				   " at " + largestTempRow.get("TimeEST"));
	}
	
	public void testhottestTempinFiles () {
		CSVRecord largestTempRow = hottestTempinFiles();
		System.out.println("hottest temperature was " + largestTempRow.get("TemperatureF") +
				   " at " + largestTempRow.get("DateUTC"));
	}
}
