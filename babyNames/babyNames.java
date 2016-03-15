/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * "false" below to access csv file without header row, columns are now indexed 1, 2,...
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class babyNames {
    public void printNames () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            int numBorn = Integer.parseInt(record.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + record.get(0) + " Gender " + record.get(1) + " Num Born " + record.get(2));
            }
        }
    }
    
    public void readOneFile () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int numBirths = 0;
        int numGirls = 0;
        int numBoys = 0;
        int numNames = 0;
        int numFNames = 0;
        int numMNames = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            numBirths += numBorn;
            numNames += 1;
            if (record.get(1).equals("F")) {
                numGirls += numBorn;
                numFNames += 1;
            }
            else {
                numBoys += numBorn;
                numMNames += 1;
            }
        }
        System.out.println("number of births in file" + "\t" + numBirths);
        System.out.println("number of F births in file" + "\t" + numGirls);
        System.out.println("number of M births in file" + "\t" + numBoys);
        System.out.println("number of names in file" + "\t" + numNames);
        System.out.println("number of F names in file" + "\t" + numFNames);
        System.out.println("number of M names in file" + "\t" + numMNames);
    }
    
    public int getRank (int year, String name, String gender) {
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int numBorntoName = 0;
        int numNamesMorePop = 0;
        for (CSVRecord record : parser) {
            if ((! record.get(0).equals(name)) && record.get(1).equals(gender)) {
                numNamesMorePop +=1;
            }
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                numBorntoName = Integer.parseInt(record.get(2));
                break;
            }
        }
        if (numBorntoName == 0) {
            return -1;
        }
        else {
            return numNamesMorePop + 1;
        }
    }
    
    public int getNumBirthsMorePop (int year, String name, String gender) {
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int numBorntoName = 0;
        int numBirthsMorePop = 0;
        for (CSVRecord record : parser) {
            if ((! record.get(0).equals(name)) && record.get(1).equals(gender)) {
                numBirthsMorePop += Integer.parseInt(record.get(2));
            }
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                numBorntoName = Integer.parseInt(record.get(2));
                break;
            }
        }
        if (numBorntoName == 0) {
            return -1;
        }
        else {
            return numBirthsMorePop;
        }
    }
    
    public String getName (int year, String gender, int rank) {
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        String nameatrank = "NO NAME";
        int i = 0;
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                i += 1;
            }
            if (i == rank) {
                nameatrank = record.get(0);
            }
        }
        return nameatrank;
    }
    
    public String whatIsNameInYear (String name, String gender, int year, int newyear) {
        int rank = getRank(year, name, gender);
        return getName(newyear, gender, rank);
    }
    
    public int getHighestRankYear (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 20000;
        int highestRankYear = -1;
        for (File file : dr.selectedFiles()) {
            String filename = file.getName();
            int year = Integer.parseInt(filename.substring(3,7));
            FileResource fr = new FileResource(file);
            int rank = getRank(year, name, gender);
            if (-1 < rank && rank < highestRank) {
                highestRank = rank;
                highestRankYear = year;
            }
        }
        return highestRankYear;
    }
    
    public double getAverageRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int rankTotal = 0;
        int numberOfRanks = 0;
        int averageRank = 0;
        for (File file : dr.selectedFiles()) {
            String filename = file.getName();
            int year = Integer.parseInt(filename.substring(3,7));
            FileResource fr = new FileResource(file);
            int rank = getRank(year, name, gender);
            rankTotal += rank;
            numberOfRanks += 1;
        }
        return ((float) rankTotal) / numberOfRanks;
    }
    
    public void testgetRank (int year, String name, String gender) {
        System.out.println(name + " " + gender + " ranks " + getRank(year, name, gender) + " in " + year);
    }
    
    public void testgetNumBirthsMorePop (int year, String name, String gender) {
        System.out.println("number of births to names more popular than " + name + " " + gender
        + " in " + year + " is " + getNumBirthsMorePop(year, name, gender));
    }
    
    public void testgetName (int year, String gender, int rank) {
        System.out.println("rank " + rank + " " + gender + " in " + year + " is " + getName(year, gender, rank));
    }
    
    public void testwhatIsNameInYear (String name, String gender, int year, int newyear) {
        System.out.println(name + " in " + year + " would be "
        + whatIsNameInYear(name, gender, year, newyear) + " in " + newyear);         
    }
    
    public void testgetHighestRankYear () {
        String name = "Mich";
        String gender = "M";
        int highestrankyear = getHighestRankYear(name, gender);
        System.out.println(name + " " + gender + " was ranked highest in " + highestrankyear);
    }
    
    public void testgetAverageRank (String name, String gender) {
        double averageRank = getAverageRank(name, gender);
        System.out.println(name + " " + gender + " ranked " + averageRank + " on average");
    }
    
}
